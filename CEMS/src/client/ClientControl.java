package client;

import common.ChatIF;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientControl implements ChatIF{

	public static int DEFAULT_PORT ;

	//Instance variables **********************************************

	/**
	 * The instance of the client that created this ConsoleChat.
	 */
	Client client;

//Constructors ****************************************************

/**
 * Constructs an instance of the ClientConsole UI.
 *
 * @param host The host to connect to.
 * @param port The port to connect on.
 */
	public ClientControl(String host, int port) throws IOException 
	{
		client= new Client(host, port, this);
	}

	private List<Object> response;
	public   List<Object> getQuestions(){
		response = client.questions;
		return  response;
	}
	public   List<Object> getSubjects(){
		response = client.subjectList;
		return  response;
	}
	//Instance methods ************************************************

	/**
	 * This method waits for input from the console.  Once it is 
	 * received, it sends it to the client's message handler.
	 */
	public void accept(Object str) 
	{
	  client.handleMessageFromClientUI(str);
	}
	/**
	 * This method overrides the method in the common.ChatIF interface.  It
	 * displays a message onto the screen.
	 *
	 * @param message The string to be displayed.
	 */
	@Override
	public void display(String message) 
	{
		System.out.println("> " + message);
	}



}
