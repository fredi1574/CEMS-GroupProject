package client;

import common.ChatIF;
import common.MsgHandler;
import entity.Subject;
import entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//test
public class Client extends AbstractClient {
	private ChatIF clientUI; 
	private boolean waitResponse = false;
	public static MsgHandler<Object> messageFromServer;
	public List<Object> questions;
	public List<Object> subjectList;
	public List<Object> coursesList;
	public List<Object> allQuestions;

	public static User user;
	//constructor
	public Client(String host, int port,ChatIF clientUI) {
		super(host, port);
		this.clientUI = clientUI;
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
			 case allQuestionImported:
				 this.allQuestions = (List<Object>) messageFromServer.getMsg();
				 break;

			 case QuestionDeleted:
				 break;
			 case LoginSuccess:
				 user = (User)messageFromServer.getMsg();
				 break;
			 case QuestionsResponse:
				 this.questions = (List<Object>) messageFromServer.getMsg();

				 break;
			 case SubjectsimportSuccess:
				 this.subjectList = (List<Object>) messageFromServer.getMsg();
				 break;
			 case CoursesimportSuccess:
				 this.coursesList = (List<Object>) messageFromServer.getMsg();
				 break;
			 case QuestionUpdated:
				 break;
			 case QuestionAddedSuccessfuly:
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
