package client;

import common.ChatIF;
import common.MsgHandler;
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
    public List<Object> subjects;
    public List<Object> courses;
    public List<Object> allQuestions;
    public List<Object> tests;

    public static User user;
    public List<Object> requests;

    //constructor
    public Client(String host, int port, ChatIF clientUI) {
        super(host, port);
        this.clientUI = clientUI;
    }

    @Override
    protected void handleMessageFromServer(Object msg) {
        waitResponse = false;
        messageFromServer = (MsgHandler<Object>) msg;
        System.out.println("handleMessageFromServer ");
        System.out.println(messageFromServer.getType().toString());
        switch (messageFromServer.getType()) {
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

            case LoginResponse:
                user = (User) messageFromServer.getMsg();
                break;

            case QuestionsBySubjectImported:
                this.questions = (List<Object>) messageFromServer.getMsg();
                break;

            case CoursesimportSuccess:
                this.courses = (List<Object>) messageFromServer.getMsg();
                break;

            case QuestionUpdated:
                break;

            case QuestionAddedSuccessfuly:
                break;

            case SubjectsimportSuccess:
                this.subjects = (List<Object>) messageFromServer.getMsg();
                break;

            case CourseTableResponse:
                courses = (ArrayList<Object>) ((MsgHandler<Object>) messageFromServer).getMsg();
                this.courses = (List<Object>) messageFromServer.getMsg();
                break;
            case GetAllTestsTableResponse:
                this.tests = (List<Object>) messageFromServer.getMsg();
                break;
            case GetTestsBySubjectResponse:
                this.tests = (List<Object>) messageFromServer.getMsg();
                break;
            case AddNewTestResponse:
                break;

            case AddNewTestQuestionsResponse:
                break;

            case RequestImportedSuccessfully:
                requests = (ArrayList<Object>) ((MsgHandler<Object>) messageFromServer).getMsg();
                this.requests = (List<Object>) messageFromServer.getMsg();
                break;
            case RequestIsApproved:
                break;
            case RequestIsDeclined:
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

    public void quit() {
        try {
            closeConnection();
        } catch (IOException e) {
        }
        System.exit(0);
    }


}
