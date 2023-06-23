package Client;

import util.ChatIF;
import util.MsgHandler;

import java.io.IOException;
import java.util.List;


public class ClientControl implements ChatIF {

    public static int DEFAULT_PORT;
    /**
     * The instance of the client that created this ConsoleChat.
     */
    public static Object serverMsg;
    static Object currentUser;
    //Instance variables **********************************************
    static Client client;
    private ChatIF clientC;

//Constructors ****************************************************
    private List<Object> response;
    private Object singleResponse;


	/**
 * Constructs an instance of the ClientConsole UI.
 *
 * @param host The host to connect to.
 * @param port The port to connect on.
 */
	public ClientControl(String host, int port) {
		client= new Client(host, port, this);
	}


    /**
     * This method waits for input from the console.  Once it is
     * received, it sends it to the client's message handler.
     */
    public void accept(Object str) {
        client.handleMessageFromClientUI(str);
    }

    public static void accepted(Object str) {
        client.handleMessageFromClientUI(str);
    }

    /**
     * This method overrides the method in the util.ChatIF interface.  It
     * displays a message onto the screen.
     *
     * @param message The string to be displayed.
     */
    @Override
    public void display(String message) {
        System.out.println("> " + message);
    }
}