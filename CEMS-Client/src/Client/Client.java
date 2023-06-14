package Client;

import application.enterTest.ManualTestController;
import application.enterTest.QuestionsComputerizedTestAnswerController;
import application.mainMenuScreen.mainMenuStudentController;
import application.manageTestsScreen.viewActiveTestController;
import application.viewReportsScreen.ViewReportsController;
import application.viewReportsScreen.ViewSpecificReportHeadOfDepart.openRepoGraphs.openReportByCourseController;
import application.viewReportsScreen.ViewSpecificReportHeadOfDepart.openRepoGraphs.openReportByLecturerController;
import application.viewReportsScreen.ViewSpecificReportHeadOfDepart.openRepoGraphs.openReportByStudentController;
import application.viewReportsScreen.ViewSpecificReportHeadOfDepart.showReportByLecturerController;
import client.AbstractClient;
import entity.*;
import javafx.application.Platform;
import util.ChatIF;
import util.MsgHandler;
import util.showError;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//test
public class Client extends AbstractClient {
    public List<Object> studentTests;
    private ChatIF clientUI;
    private boolean waitResponse = false;
    public static MsgHandler<Object> messageFromServer;
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

    public static User user;

    public List<Object> requests;
    public static openReportByStudentController HODPstudentReportcontroller = new openReportByStudentController();
    public static showReportByLecturerController HODPshowReportByLecturerController = new showReportByLecturerController();
    public static openReportByLecturerController HODPopenReporLecturerController = new openReportByLecturerController();
    public static openReportByCourseController HODPopenReportCourseController = new openReportByCourseController();
    public static ViewReportsController LectureReportsController = new ViewReportsController();
    public static QuestionsComputerizedTestAnswerController StudentInTest = new QuestionsComputerizedTestAnswerController();
    public static viewActiveTestController activeTestController = new viewActiveTestController();
    public static mainMenuStudentController menuStudentController = new mainMenuStudentController();
    public static ManualTestController manualTest = new ManualTestController();


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
            case LecturerCllickedLockTestResponse:
            case ImportedTestsAverage:
            case StudentsTestIsApprvoedResponse:
            case IsLoggedValueChanged:
            case DetectedCheatingResponse:
            case DeleteRequestCompleted:
            case CompleteUnactivatingTest:
            case AfterTestRowCompleted:
            case AddNewAfterTestInfoResponse:
            case AddNewActiveTestResponse:
            case UpdateTheApproveofLecturerResponse:
            case ExtraTimeRequested:
            case changeTestDurationAnswer:
            case StudentsFinishedTestIncreased:
            case TotalStudentsInTestIncreased:
            case TestOfStudentSaved:
            case StudentAnswerAdded:
            case UpdateRemainingTimeResponse:
            case AddNewTestQuestionsResponse:
            case AddNewTestResponse:
            case QuestionAddedSuccessfuly:
            case QuestionUpdated:
            case QuestionDeleted:
                break;

            case Disconnected:
                System.exit(0);
                break;
            case ServerStopped:
                Platform.runLater(() -> {
                    showError.showErrorPopup("Server disconnected\nByebye");
                    System.exit(-1);
                });

                break;
            case allQuestionImported:
                this.allQuestions = (List<Object>) messageFromServer.getMsg();
                break;

            case LoginResponse:
                user = (User) messageFromServer.getMsg();
                break;

            case QuestionsBySubjectImported:
            case GetQuestionsByLecturerResponse:
                this.questions = (List<Object>) messageFromServer.getMsg();
                break;

            case CoursesimportSuccess:

            case ImportedStudentCourses:
                this.courses = (List<Object>) messageFromServer.getMsg();
                break;

            case SubjectsimportSuccess:
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

            case RequestImportedSuccessfully:
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
            case ImportedSubjectIDfromName:
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

            case ImportedTestAverage:
                this.singleTest = messageFromServer.getMsg();
                break;
            case TestIsForcedLockedManual:
                manualTest.lockTest();
                break;
            case TestIsForcedLockedComputrized:
                StudentInTest.lockTest();
                break;
            case ImportedAfterTestInfo:
                this.infoAboutTest = (List<Object>) messageFromServer.getMsg();
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
        } catch (IOException ignored) {
        }
        System.exit(0);
    }


}
