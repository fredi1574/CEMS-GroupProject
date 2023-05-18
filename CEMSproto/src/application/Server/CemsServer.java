package application.Server;

import application.Question;
import application.common.ConnectToClients;
import application.common.MsgHandler;
import application.common.TypeMsg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

import java.util.ArrayList;

public class CemsServer extends AbstractServer{
	// This holds the list of the connected clients to the server and their status
	static ObservableList<ConnectToClients> clientList = FXCollections.observableArrayList();
	//Constructors ****************************************************
	public String passwordSQL;

	/**
	 * Constructs an instance of the echo server.
	 *
	 * @param port The port number to connect on.
	 */
	public CemsServer(int port,String passwordSQL) {
		super(port);
		this.passwordSQL = passwordSQL;
		// TODO Auto-generated constructor stub
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
    	System.out.println("Server has stopped listening for connections.");
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
//			 client.sendToClient(new MsgHandler(TypeMsg.Connected, null));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
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
		String messgeFromClient = String.valueOf(msg); //TODO: remove this (unless needed)
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
				case  EditQuestion:
					MsgHandler<Question> m = (MsgHandler<Question>) msg;
					Question q=(Question)m.getMsg().get(0);
					String updateQuery = "UPDATE cems.questions SET question_number='"+q.getQuestion_number()+"' ,subject='" + q.getSubject() + "', course_name='" + q.getCourse_name() + "', question_text='" + q.getQuestion_text() + "', lecturer='" + q.getLecturer() + "' WHERE id='" + q.getId() + "'";
					MysqlConnection.update(updateQuery);
					client.sendToClient(new MsgHandler<>(TypeMsg.QuestionUpdated,null));
					break;
				default:
					break;
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
		

