package client;

import application.enterTest.ManualTestController;
import application.enterTest.QuestionsComputerizedTestAnswerController;
import application.mainMenuScreen.mainMenuStudentController;
import application.manageTestsScreen.viewActiveTestController;
import application.viewReportsScreen.ViewReportsController;
import application.viewReportsScreen.ViewSpecificReportHeadOfDepart.openRepoGraphs.openReportByCourseController;
import application.viewReportsScreen.ViewSpecificReportHeadOfDepart.openRepoGraphs.openReportByLecturerController;
import application.viewReportsScreen.ViewSpecificReportHeadOfDepart.openRepoGraphs.openReportByStudentController;
import application.viewReportsScreen.ViewSpecificReportHeadOfDepart.showReportByLecturerController;
import common.ChatIF;
import common.MsgHandler;
import common.TypeMsg;
import entity.*;
import javafx.fxml.FXMLLoader;

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
                System.out.println(tests);
                break;

            case AddNewTestResponse:
                break;

            case AddNewTestQuestionsResponse:
                break;

            case RequestImportedSuccessfully:
                requests = (ArrayList<Object>) ((MsgHandler<Object>) messageFromServer).getMsg();
                this.requests = (List<Object>) messageFromServer.getMsg();
                break;
            case RequestIsApproved://check delete
                break;
            case RequestIsDeclined://check delete
                break;
            case RequestIsDeclinedToLecturer://check delete
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
                this.activeTests = (List<Object>) messageFromServer.getMsg();
                break;
            case UpdateRemainingTimeResponse:
                break;
            case importedQuestionAndAnswerFromTest:
                this.singleQuestion = (Question) messageFromServer.getMsg();
                break;
            case StudentAnswerAdded:
                break;
            case ImportedTestByID:
                this.singleTest = (Test) messageFromServer.getMsg();
                break;
            case TestOfStudentSaved:
                break;
            case StudentVerified:
                this.UserAndCourse = (StudentCourse) messageFromServer.getMsg();
                break;
            case TotalStudentsInTestIncreased:
                break;
            case StudentsFinishedTestIncreased:
                break;
            case TestDurationChangedComputerized:
                StudentInTest.showNotificationAndChangeDuration((Integer) messageFromServer.getMsg());
                break;
            case TestDurationChangedManual:
                manualTest.showNotificationAndChangeDuration((Integer) messageFromServer.getMsg());
                break;
            case changeTestDurationAnswer:
                break;
            case TestDurationApprovedPopLecturer:
                activeTestController.showRequestApprovedPopUp();
                break;
            case TestDurationDeclinedPopLecturer:
                activeTestController.showRequestDeclinedPopUp();
                break;
            case ImportedSubjectIDfromName:
                this.singleSubject = (Subject) messageFromServer.getMsg();
                break;
            case ExtraTimeRequested:
                break;
            case GetTestForApprovalResponse:
                testsForApproval = (ArrayList<Object>) ((MsgHandler<Object>) messageFromServer).getMsg();
                this.testsForApproval = (List<Object>) messageFromServer.getMsg();
                break;
            case UpdateTheApproveofLecturerResponse:
                break;
            case AddNewActiveTestResponse:
                break;
            case AddNewAfterTestInfoResponse:
                break;
            case ImportedNumberOfAttendedCounter:
                this.NumOfAttended = (Integer) messageFromServer.getMsg();
                break;
            case ImportedRegisteredStudents:
                this.NumOfRegistered = (Integer) messageFromServer.getMsg();
                break;
            case AfterTestRowCompleted:
                break;
            case ImportedNumberOfFinished:
                this.NumOfFinished = (Integer) messageFromServer.getMsg();
                break;
            case CompleteUnactivatingTest:
                break;
            case DeleteRequestCompleted:
                break;
            case DetectedCheatingResponse:
                break;
            case IsLoggedValueChanged:
                break;
            case PopupTestApprove:
                menuStudentController.showTestApprovedPopUp();
                break;
            case StudentsTestIsApprvoedResponse:
                break;

            case ImportedStudentTests:
                this.studentTests = (List<Object>) messageFromServer.getMsg();
                break;

            case ImportedStudentCourses:
                this.courses = (List<Object>) messageFromServer.getMsg();
                break;

            case ImportedTestsAverage:
                break;

            case ImportedTestAverage:
                this.singleTest = (String) messageFromServer.getMsg();
                break;

            case GetActiveTestsByLecturerResponse:
                this.activeTests = (List<Object>) messageFromServer.getMsg();
                break;
            case LecturerCllickedLockTestResponse:
                break;
            case TestIsForcedLockedManual:
                manualTest.lockTest();
                break;
            case TestIsForcedLockedComputrized:
                StudentInTest.lockTest();
                break;
            case GetQuestionsByLecturerResponse:
                this.questions = (List<Object>) messageFromServer.getMsg();
                break;
            case GetTestsByLecturerResponse:
                this.tests = (List<Object>) messageFromServer.getMsg();



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
