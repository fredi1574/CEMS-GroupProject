package application.Client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import application.common.ChatIF;
import application.common.MsgHandler;
import ocsf.client.AbstractClient;

public class Client extends AbstractClient {
	private ChatIF clientUI; 
	private boolean waitResponse = false;
	public static MsgHandler<Object> messageFromServer;
	public  ArrayList<Object> list;
	//constructor
	
	public Client(String host, int port,ChatIF clientUI) {
		super(host, port);
		this.clientUI = clientUI;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void handleMessageFromServer(Object msg) {
		 waitResponse = false;
		 messageFromServer = (MsgHandler<Object>) msg;
		 System.out.println("handleMessageFromServer ");
		 System.out.println(messageFromServer.getType().toString());
		 switch(messageFromServer.getType())
		 {
		 	case Connected:
				break;
			case Disconnected:
				System.exit(0);
				break;
//			case ImportedSuccessfully:
//				break;
			 case QuestionsResponse:
				 list = ((MsgHandler<Object>)messageFromServer).getMsg();
				 break;
			 case QuestionUpdated:
				 break;

		 }		
	}


	public void handleMessageFromClientUI(Object message) {
		try {
			openConnection();
			waitResponse = true;
			sendToServer(message);
			// wait for response
			while (waitResponse) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			clientUI.display("Could not send message to server.  Terminating client.");
			quit();
		}
	}
	 public void quit()
	  {
	    try
	    {
	      closeConnection();
	    }
	    catch(IOException e) {}
	    System.exit(0);
	  }


	}
