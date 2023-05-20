package server;

import common.ConnectToClients;
import common.MsgHandler;
import common.TypeMsg;
import entity.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;

public class CemsServer extends AbstractServer{
	// This holds the list of the connected clients to the server and their status
	static ObservableList<ConnectToClients> clientList = FXCollections.observableArrayList();
	//Constructors ****************************************************
	public String passwordSQL;
	Question q;
	MsgHandler<Question> m;
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
					ArrayList<Question> list=MysqlConnection.getQuestionsTable("select * from cems.questions");
					//List<String>
					client.sendToClient(new MsgHandler<>(TypeMsg.QuestionsResponse,list));
					break;
				case DeleteQuestion:
					this.m = (MsgHandler<Question>) msg;
					this.q = (Question)m.getMsg().get(0);
					String DeleteQuery = "DELETE FROM cems.questions WHERE id='" + q.getId() + "'";;
					MysqlConnection.update(DeleteQuery);
					client.sendToClient(new MsgHandler<>(TypeMsg.QuestionDeleted,null));

				case  EditQuestion:
					this.m = (MsgHandler<Question>) msg;
					this.q = (Question)m.getMsg().get(0);
					String updateQuery = "UPDATE cems.questions SET question_number='" + q.getQuestion_number() + "', subject='" + q.getSubject() + "', course_name='" + q.getCourse_name() + "', question_text='" + q.getQuestion_text() + "', lecturer='" + q.getLecturer() + "', answer1='" + q.getAnswer1() + "', answer2='" + q.getAnswer2() + "', " +
							"answer3='" + q.getAnswer3() + "', correctAnswer='" + q.getCorrectAnswer() + "', answer4='" + q.getAnswer4() + "' WHERE id='" + q.getId() + "'";

					MysqlConnection.update(updateQuery);
					client.sendToClient(new MsgHandler<>(TypeMsg.QuestionUpdated,null));
					break;
				case  AddNewQuestion:
					this.m = (MsgHandler<Question>) msg;
					this.q = (Question)m.getMsg().get(0);
					String newQuery = "INSERT INTO cems.questions (id, subject, course_name, question_text, question_number, lecturer, answer1, answer2, answer3, correctAnswer, answer4) " +
							"VALUES ('" + q.getId() + "', '" + q.getSubject() + "', '" + q.getCourse_name() + "', '" + q.getQuestion_text() + "', " +
							"'" + q.getQuestion_number() + "', '" + q.getLecturer() + "', '" + q.getAnswer1() + "', '" + q.getAnswer2() + "', " +
							"'" + q.getAnswer3() + "', '" + q.getCorrectAnswer() + "', '" + q.getAnswer4() + "')";
					MysqlConnection.update(newQuery);
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
		

