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
    public ClientControl(String host, int port) throws IOException {
        client = new Client(host, port, this);
    }
    public ClientControl(ChatIF clientC) {
        this.clientC = clientC;
        client = new Client("", 5555, this);
    }

    public static MsgHandler getServerMsg() {
        serverMsg = client.messageFromServer;
        return (MsgHandler) serverMsg;
    }

    public static Object getUser() {

        return currentUser;
    }

    public static void setUser(Object user) {
        currentUser = user;
    }

    public List<Object> GetQuestionsBySubject() {
        response = client.questions;
        return response;
    }

    public List<Object> getCourses() {
        response = client.courses;
        return response;
    }

    public List<Object> getSubjects() {
        response = client.subjects;
        return response;
    }

    public List<Object> getTests() {
        response = client.tests;
        return response;
    }

    public List<Object> getAllQuestions() {
        response = client.allQuestions;
        return response;
    }

    public List<Object> getAllRequests() {
        response = client.requests;
        return response;
    }

    public List<Object> getTestQuestions() {
        response = client.testQuestions;
        return response;
    }

    public List<Object> getActiveTests() {
        response = client.activeTests;
        return response;
    }

    public List<Object> GetActiveTestsByLecturer() {
        response = client.activeTests;
        return response;
    }

    public Object getSingleQuestion() {
        singleResponse = client.singleQuestion;
        return singleResponse;
    }

    public Object getSingleTest() {
        singleResponse = client.singleTest;
        return singleResponse;
    }

    public Object getUserAndCourse() {
        singleResponse = client.UserAndCourse;
        return singleResponse;
    }

    public Object getSubjectID() {
        singleResponse = client.singleSubject;
        return singleResponse;
    }

    public List<Object> getTestForApproval() {
        response = client.testsForApproval;
        return response;
    }

    public Object getNumberOfRegistered() {
        singleResponse = client.NumOfRegistered;
        return singleResponse;
    }

    public Object getNumberOfAttended() {
        singleResponse = client.NumOfAttended;
        return singleResponse;
    }


    //Instance methods ************************************************

    public Object getNumberOfFinished() {
        singleResponse = client.NumOfFinished;
        return singleResponse;
    }

    public Object getStudentTests() {
        response = client.studentTests;
        return response;
    }

    public Object getInfoAboutTest() {
        response = client.infoAboutTest;
        return response;
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