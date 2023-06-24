package Client;

import application.createNewTestScreen.notesScreen.NotesController;
import application.enterTest.ManualTestController;
import application.enterTest.QuestionsComputerizedTestAnswerController;
import application.mainMenuScreen.MainMenuStudentController;
import application.manageTestsScreen.ViewActiveTestController;
import application.viewReportsScreen.ViewReportsController;
import application.viewReportsScreen.ViewSpecificReportHeadOfDepart.ShowReportByLecturerController;
import application.viewReportsScreen.ViewSpecificReportHeadOfDepart.openRepoGraphs.OpenReportByCourseController;
import application.viewReportsScreen.ViewSpecificReportHeadOfDepart.openRepoGraphs.OpenReportByLecturerController;
import application.viewReportsScreen.ViewSpecificReportHeadOfDepart.openRepoGraphs.OpenReportByStudentController;
import client.AbstractClient;
import entity.User;
import javafx.application.Platform;
import util.ChatIF;
import util.MsgHandler;
import util.ShowMessage;
import util.TypeMsg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Client class represents a client that connects to a server and communicates with it.
 * It extends the AbstractClient class and overrides the handleMessageFromServer method.
 */
public class Client extends AbstractClient {
    public static final OpenReportByStudentController HODPstudentReportcontroller = new OpenReportByStudentController();
    public static final ShowReportByLecturerController HODPshowReportByLecturerController = new ShowReportByLecturerController();
    public static final OpenReportByLecturerController HODPopenReporLecturerController = new OpenReportByLecturerController();
    public static final OpenReportByCourseController HODPopenReportCourseController = new OpenReportByCourseController();
    public static final ViewReportsController LectureReportsController = new ViewReportsController();
    public static final QuestionsComputerizedTestAnswerController StudentInTest = new QuestionsComputerizedTestAnswerController();
    public static final ViewActiveTestController activeTestController = new ViewActiveTestController();
    public static final MainMenuStudentController menuStudentController = new MainMenuStudentController();
    public static final ManualTestController manualTest = new ManualTestController();
    public static final NotesController notesController = new NotesController();
    public static MsgHandler<Object> messageFromServer;
    public static User user;
    private final ChatIF clientUI;
    public List<Object> studentTests;
    public List<Object> questions;
    public List<Object> subjects;
    public List<Object> courses;
    public List<Object> allQuestions;
    public List<Object> tests;
    public List<Object> testQuestions;
    public List<Object> activeTests;
    public List<Object> infoAboutTest;
    public Object singleQuestion;
    public Object singleTest;
    public Object singleSubject;
    public Object UserAndCourse;
    public List<Object> testsForApproval;
    public Object NumOfRegistered;
    public Object NumOfFinished;
    public Object NumOfAttended;
    public List<Object> requests;
    private boolean waitResponse = false;

    /**
     * Constructs a Client object.
     *
     * @param host     The host name of the server
     * @param port     The port number of the server
     * @param clientUI The user interface for the client
     */
    public Client(String host, int port, ChatIF clientUI) {
        super(host, port);
        this.clientUI = clientUI;
    }

    /**
     * Handles the message received from the server.
     *
     * @param msg The message received from the server
     */
    @Override
    @SuppressWarnings("unchecked")
    protected void handleMessageFromServer(Object msg) {
        waitResponse = false;
        messageFromServer = (MsgHandler<Object>) msg;
        System.out.println("handleMessageFromServer ");
        System.out.println(messageFromServer.getType().toString());
        switch (messageFromServer.getType()) {
            case Connected:
            case LecturerClickedLockTestResponse:
            case ImportedTestsAverage:
            case StudentsTestIsApprovedResponse:
            case IsLoggedValueChanged:
            case DetectedCheatingResponse:
            case DeleteRequestResponse:
            case DeactivatingTestResponse:
            case AfterTestRowCompleted:
            case AddNewAfterTestInfoResponse:
            case AddNewActiveTestResponse:
            case UpdateTheApprovalOfLecturerResponse:
            case ExtraTimeRequested:
            case changeTestDurationAnswer:
            case StudentsFinishedTestIncreased:
            case TotalStudentsInTestIncreased:
            case TestOfStudentSaved:
            case StudentAnswerAdded:
            case UpdateRemainingTimeResponse:
            case AddNewTestQuestionsResponse:
            case AddNewQuestionResponse:
            case EditQuestionResponse:
            case DeleteQuestionResponse:
                break;
            case AddNewTestResponse:
                break;
            case Disconnected:
                System.exit(0);
                break;
            case ServerStopped:
                Platform.runLater(() -> {
                    ShowMessage.showErrorPopup("Server disconnected\nBye-bye");
                    System.exit(-1);
                });

                break;
            case GetAllQuestionsResponse:
                this.allQuestions = (List<Object>) messageFromServer.getMsg();
                break;

            case LoginResponse:
                user = (User) messageFromServer.getMsg();
                break;

            case GetQuestionsBySubjectResponse:
            case GetQuestionsByLecturerResponse:
                this.questions = (List<Object>) messageFromServer.getMsg();
                break;

            case ImportCoursesResponse:

            case ImportedStudentCourses:
                this.courses = (List<Object>) messageFromServer.getMsg();
                break;

            case ImportSubjectsResponse:
                this.subjects = (List<Object>) messageFromServer.getMsg();
                break;

            case CourseTableResponse:
                courses = (ArrayList<Object>) messageFromServer.getMsg();
                this.courses = (List<Object>) messageFromServer.getMsg();
                break;
            case GetAllTestsTableResponse:
            case GetTestsByLecturerResponse:
                this.tests = (List<Object>) messageFromServer.getMsg();
                break;
            case GetTestsBySubjectResponse:
                this.tests = (List<Object>) messageFromServer.getMsg();
                System.out.println(tests);
                break;

            case GetRequestsBySubjectResponse:
                requests = (ArrayList<Object>) messageFromServer.getMsg();
                this.requests = (List<Object>) messageFromServer.getMsg();
                break;
            case RequestIsApproved://check delete
            case RequestIsDeclined:
                break;
            case RequestIsDeclinedToLecturer://check delete
            case TestDurationDeclinedPopLecturer:
                activeTestController.showRequestDeclinedPopUp();
                break;
            case StudentReportImported:
                HODPstudentReportcontroller.reportCalc((ArrayList<Object>) messageFromServer.getMsg());
                break;
            case UserImported:
                HODPshowReportByLecturerController.setLecturerCombo((ArrayList<Object>) messageFromServer.getMsg());
                break;

            case ImportedTestsByLecturer:
                HODPopenReporLecturerController.reportCalc((ArrayList<Object>) messageFromServer.getMsg());
                break;

            case ImportedTestsByCourse:
                HODPopenReportCourseController.reportCalc((ArrayList<Object>) messageFromServer.getMsg());
                break;

            case ImportedTestsByLecturerForLecturerReport:
                LectureReportsController.setTestsTable((ArrayList<Object>) messageFromServer.getMsg());
                break;

            case GetTestQuestionsResponse:
                testQuestions = (List<Object>) messageFromServer.getMsg();
                break;

            case GetActiveTestsResponse:

            case GetActiveTestsByLecturerResponse:
                this.activeTests = (List<Object>) messageFromServer.getMsg();
                break;
            case importedQuestionAndAnswerFromTest:
                this.singleQuestion = messageFromServer.getMsg();
                break;
            case ImportedTestByID:
            case ImportedTestAverage:
                this.singleTest = messageFromServer.getMsg();
                break;
            case StudentVerified:
                this.UserAndCourse = messageFromServer.getMsg();
                break;
            case TestDurationChangedComputerized:
                StudentInTest.showNotificationAndChangeDuration((Integer) messageFromServer.getMsg());
                break;
            case TestDurationChangedManual:
                manualTest.showNotificationAndChangeDuration((Integer) messageFromServer.getMsg());

                break;
            case TestDurationApprovedPopLecturer:
                activeTestController.showRequestApprovedPopUp();
                break;
            case ImportedSubjectIDFromName:
                this.singleSubject = messageFromServer.getMsg();
                break;
            case GetTestForApprovalResponse:
                testsForApproval = (ArrayList<Object>) messageFromServer.getMsg();
                this.testsForApproval = (List<Object>) messageFromServer.getMsg();
                break;
            case ImportedNumberOfAttendedCounter:
                this.NumOfAttended = messageFromServer.getMsg();
                break;
            case ImportedRegisteredStudents:
                this.NumOfRegistered = messageFromServer.getMsg();
                break;
            case ImportedNumberOfFinished:
                this.NumOfFinished = messageFromServer.getMsg();
                break;
            case PopupTestApprove:
                menuStudentController.showTestApprovedPopUp();
                break;

            case ImportedStudentTests:
                this.studentTests = (List<Object>) messageFromServer.getMsg();
                break;

            case TestIsForcedLockedManual:
                manualTest.lockTest();
                break;
            case TestIsForcedLockedComputerized:
                StudentInTest.lockTest();
                break;
            case ImportedAfterTestInfo:
                this.infoAboutTest = (List<Object>) messageFromServer.getMsg();
                break;
        }
    }

    /**
     * Handles a message received from the client's user interface.
     * This method sends the message to the server and waits for a response.
     *
     * @param message The message to be sent to the server.
     */

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

    /**
     * Closes the connection with the server and terminates the client.
     * This method should be called when the client is quitting or no longer needs to communicate with the server.
     */
    public void quit() {
        try {
            closeConnection();
        } catch (IOException ignored) {
        }
        System.exit(0);
    }
}