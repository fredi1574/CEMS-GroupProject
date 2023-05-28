package server;

import common.ConnectToClients;
import common.MsgHandler;
import common.TypeMsg;
import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CemsServer extends AbstractServer{
	// This holds the list of the connected clients to the server and their status
	static ObservableList<ConnectToClients> clientList = FXCollections.observableArrayList();
	//Constructors ****************************************************
	public String passwordSQL;
	Object q;
	MsgHandler<Object> m;
	Question question;
	/**
	 * Constructs an instance of the echo server.
	 *
	 * @param port The port number to connect on.
	 */
	public CemsServer(int port,String passwordSQL) {
		super(port);
		this.passwordSQL = passwordSQL;
	}
	/**
	 * This method updates the Connected\Disconnect clients to our Server Each time
	 * a user Connect\disconnects we update the Connect Users in our Server GUI if a
	 * user connects his status is "Connected" and if he disconnects status changes
	 * to "Disconnected"
	 * @param client
	 * @param connectionStatus
	 */
	static void updateClientList(ConnectionToClient client, String connectionStatus) {
		for (int i = 0; i < clientList.size(); i++) {
			/* Comparing clients by IP addresses */
			try {
				if (clientList.get(i).getIp().equals(client.getInetAddress().getHostAddress())) {
					clientList.get(i).setStatus(connectionStatus);
					clientList.remove(i);
				}
			} catch (NullPointerException ex) {
				System.out.println("Error! Client not found");
			}
		}

		 //In both cases of Connect and Disconnected, we will need to add Client into the
		 //list so this function covers both of them simultaneously
		try {
			clientList.add(new ConnectToClients(client.getInetAddress().getHostAddress(),
				client.getInetAddress().getHostName(), connectionStatus));
		} catch (NullPointerException ex) {
			System.out.println("Error! Client not found");
		}
	}
		
		

  /**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */
	protected void serverStarted()
	{
		System.out.println("Server Started\n" + "Server listening for connections on port " + getPort());
   
	try {
		MysqlConnection.connectToDb(passwordSQL);
	} catch (Exception ex) {
			System.out.println("Error! Connection Failed");
		}
  }
  
  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
	protected void serverStopped()
	{
		try {
			super.close();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		MysqlConnection.closeConnection();

	}

	/**
	 * gets a list of clients connected to the server
	 * @return an observable list of the connected clients
	 */
	public static ObservableList<ConnectToClients> getClientList() {
	    return clientList;
	  }

	public static void setClientList(ObservableList<ConnectToClients> clientList) {
		CemsServer.clientList = clientList;
	}
//	@Override
//	protected void clientConnected(ConnectionToClient client) {
//		updateClientList(client,"Connected");
//		try {
//			 client.sendToClient(new common.MsgHandler(common.TypeMsg.Connected, null));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	@Override
//	synchronized protected void clientDisconnected(ConnectionToClient client) {
//		updateClientList(client,"Disconnected");
//	}
	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		MsgHandler<Object> messageFromClient = (MsgHandler<Object>) msg;
		System.out.println("Message received from " + client);
		try {
			switch (messageFromClient.getType()) {
				case Connected:
					updateClientList(client, "Connected");
					client.sendToClient(new MsgHandler(TypeMsg.Connected, null));
					break;
				case Disconnected:
					updateClientList(client, "Disconnected");
					client.sendToClient(new MsgHandler<>(TypeMsg.Disconnected, null));
					break;
				case  GetQuestions:
					this.m = (MsgHandler<Object>) msg;
					this.q = (String)m.getMsg();

					ArrayList<Question> list = MysqlConnection.getQuestionsTable( "SELECT q.* " +
							"FROM questions q " +
							"JOIN lecturersubjects ls ON q.subject = ls.subjectID " +
							"JOIN users u ON u.id = ls.id " +
							"WHERE u.username = + '"+ q +"'");
					//List<String>
					client.sendToClient(new MsgHandler<>(TypeMsg.QuestionsResponse,list));
					break;
				case DeleteQuestion:
					this.m = (MsgHandler<Object>) msg;
					this.q = (Question)m.getMsg();
					this.question = (Question) q;
					String DeleteQuery = "DELETE FROM cems.questions WHERE id='" + question.getId() + "'";;
					MysqlConnection.update(DeleteQuery);
					client.sendToClient(new MsgHandler<>(TypeMsg.QuestionDeleted,null));
				case TryLogin:
					 m = (MsgHandler<Object>) msg;
					List<Object> details = (List<Object>) m.getMsg();
					String Username = (String) details.get(0);
					String Password = (String) details.get(1);
					Object user = MysqlConnection.authenticateUser(Username, Password);
					if (user instanceof User) {
						client.setName(Username);
						client.setInfo(client.getName(), ((User) user).getRole());
					}
					client.sendToClient(new MsgHandler<>(TypeMsg.LoginResponse,user));
				case  importSubjects:
					this.m = (MsgHandler<Object>) msg;
					this.q = m.getMsg();

					ArrayList<Subject> importSubjects = MysqlConnection.getSubjectList( "SELECT * " +
							"FROM subject s " +
							"JOIN lecturersubjects ls ON s.subjectID = ls.subjectID " +
							"JOIN users u ON u.id = ls.id " +
							"WHERE u.username = '" + q.toString() + "'");

							client.sendToClient(new MsgHandler<>(TypeMsg.SubjectsimportSuccess,importSubjects));
					break;

				case  importCourses:
					this.m = (MsgHandler<Object>) msg;
					this.q = m.getMsg();
					ArrayList<Course> importCourses = MysqlConnection.getCourseList( "SELECT * " +
						"FROM course AS c " +
						"JOIN lecturersubjects AS ls ON c.subjectID = ls.subjectID " +
						"JOIN users AS u ON ls.id = u.id " +
						"WHERE u.username = '"+ q.toString() + "'");

					client.sendToClient(new MsgHandler<>(TypeMsg.CoursesimportSuccess,importCourses));
				case  EditQuestion:
					this.m = (MsgHandler<Object>) msg;
					this.q = m.getMsg();
					if (q instanceof Question) {
						this.question = (Question) q;
						String updateQuery = "UPDATE cems.questions SET question_number='" + question.getQuestion_number() + "', subject='" + question.getSubject() + "'," +
								" courseName='" + question.getCourse_name() + "', question_text='" + question.getQuestion_text() + "'," +
								" lecturer='" + question.getLecturer() + "', answer1='" + question.getAnswer1() + "', answer2='" + question.getAnswer2() + "', " +
								"answer3='" + question.getAnswer3() + "', correctAnswer='" + question.getCorrectAnswer() + "', answer4='" +
								question.getAnswer4() + "' WHERE id='" + question.getId() + "'";

						MysqlConnection.update(updateQuery);
						client.sendToClient(new MsgHandler<>(TypeMsg.QuestionUpdated, null));
					}
					break;
				case GetAllQuestions:
					this.m = (MsgHandler<Object>) msg;
					this.q = (String)m.getMsg();
					ArrayList<Question> allQuestions = MysqlConnection.getQuestionsTable("SELECT * FROM questions");
					client.sendToClient(new MsgHandler<>(TypeMsg.allQuestionImported,allQuestions));

				case  AddNewQuestion:
					this.m = (MsgHandler<Object>) msg;
					this.q = (Question)m.getMsg();
					Question question = (Question) q;
					try {
						String newQuery = "INSERT INTO cems.questions (id, subject, courseName, question_text, question_number, lecturer, answer1, answer2, answer3, correctAnswer, answer4) " +
								"VALUES ('" + question.getId() + "', '" + question.getSubject() + "', '" + question.getCourse_name() + "', '" + question.getQuestion_text() + "', " +
								"'" + question.getQuestion_number() + "', '" + question.getLecturer() + "', '" + question.getAnswer1() + "', '" + question.getAnswer2() + "', " +
								"'" + question.getAnswer3() + "', '" + question.getCorrectAnswer() + "', '" + question.getAnswer4() + "')";
						MysqlConnection.update(newQuery);
					}catch (Exception e)
					{}
					client.sendToClient(new MsgHandler<>(TypeMsg.QuestionAddedSuccessfuly,null));
					break;

				default:
					break;
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
		

