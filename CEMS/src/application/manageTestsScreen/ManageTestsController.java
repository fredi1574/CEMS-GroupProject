package application.manageTestsScreen;

import client.Client;
import client.ClientUI;
import common.MsgHandler;
import common.TypeMsg;
import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;


/**
 * handles the functionality of the manage tests screen
 */
public class ManageTestsController {
    public TextField searchField;

    @FXML
    private AnchorPane header;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button viewTestResultsBtn;
    @FXML
    private Button viewTestInProgressBtn;
    @FXML
    private Button editBtn;
    @FXML
    private TableView<Test> testsFromDBTableView;
    @FXML
    private TableView<TestForApproval> testApprovalTableView;
    @FXML
    private TableView<ActiveTest> activeTestsTableView;
    @FXML
    private Text usernameText;
    public StateManagement stateManagement;
    Test testRowData;
    @FXML
    private TableView<Test> approvedTestsTableView; //contains every test that has been verified by the lecturer
    ActiveTest activeTestRowData;
    TestForApproval testForApprovalRowData;

    String fullName = Client.user.getFullName();


    public void initialize() {
        ScreenManager.dragAndDrop(header);
        usernameText.setText(fullName);

        stateManagement = StateManagement.getInstance();

        displayDbTestsTable();
        displayApprovalTestsTable();
        displayActiveTestsTable();

    }


    /**
     * fetches the tests table from the database
     * only tests belonging to the lecturer's subjects are shown
     */
    private void displayDbTestsTable() {

        MsgHandler getDbTestTable = new MsgHandler(TypeMsg.GetTestsByLecturer, Client.user.getFullName());
        ClientUI.chat.accept(getDbTestTable);

        ObservableList<Test> dbTests = FXCollections.observableArrayList((List) ClientUI.chat.getTests());
        ObservableList<String> columns = FXCollections.observableArrayList();

        columns.addAll("Test Number", "ID", "Subject", "Course Name", "Year", "Semester", "Session", "Author");
        TableManager.createTable(testsFromDBTableView, columns);
        TableManager.importData(testsFromDBTableView, dbTests);
        double[] dbTestsMultipliers = {0.1, 0.08, 0.1, 0.225, 0.1, 0.1, 0.1, 0.19};
        TableManager.resizeColumns(testsFromDBTableView, dbTestsMultipliers);

        //makes the elements in the database questions table clickable
        testsFromDBTableView.setOnMouseClicked((e) -> {
            testRowData = testsFromDBTableView.getSelectionModel().getSelectedItem();
        });

        //filter tests by course name
        FilteredList<Test> filteredData = new FilteredList<>(dbTests, b -> true);
        TableManager.MakeFilterListForSearch(filteredData, searchField, Test::getCourseName);

        SortedList<Test> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(testsFromDBTableView.comparatorProperty());

        testsFromDBTableView.setItems(sortedData);
    }

    /**
     * Displays the tests that were automatically checked and are
     * ready for approval by the lecturer
     */
    private void displayApprovalTestsTable() {
        MsgHandler getTestForApproval = new MsgHandler(TypeMsg.GetTestForApproval, fullName);
        ClientUI.chat.accept(getTestForApproval);

        ObservableList<TestForApproval> studentTests = FXCollections.observableArrayList((List) ClientUI.chat.getTestForApproval());
        ObservableList<TestForApproval> filteredTests = FXCollections.observableArrayList();

        //filter out tests that were already approved
        for (TestForApproval test : studentTests) {
            if (test.getApproved() == ApprovalStatus.NO) {
                filteredTests.add(test);
            }
        }

        ObservableList<String> columnsForAppropval = FXCollections.observableArrayList();
        columnsForAppropval.addAll("Student ID", "Test ID", "Course", "Semester", "Session", "Grade", "Approved");
        TableManager.createTable(testApprovalTableView, columnsForAppropval);
        TableManager.importData(testApprovalTableView, filteredTests);
        double[] approvalTestsMultipliers = {0.15, 0.14, 0.15, 0.15, 0.175, 0.12, 0.11};
        TableManager.resizeColumns(testApprovalTableView, approvalTestsMultipliers);

        testApprovalTableView.setOnMouseClicked((e) -> {
            testForApprovalRowData = testApprovalTableView.getSelectionModel().getSelectedItem();
        });
        stateManagement.setTestForApproval(filteredTests);
    }

    /**
     * fetches the active tests table from the database
     */
    private void displayActiveTestsTable() {

        MsgHandler getActiveTestsTable = new MsgHandler(TypeMsg.GetActiveTestsByLecturer, fullName);
        ClientUI.chat.accept(getActiveTestsTable);

        ObservableList<ActiveTest> activeTests = FXCollections.observableArrayList((List) ClientUI.chat.GetActiveTestsByLecturer());
        //ObservableList<ActiveTest> userActiveTests = TableManager.filterByAuthor(activeTests,fullName);

        ObservableList<String> columns = FXCollections.observableArrayList();
        columns.addAll("ID", "Starting Time", "Test Code");
        TableManager.createTable(activeTestsTableView, columns);
        TableManager.importData(activeTestsTableView, activeTests);

        double[] activeTestsMultipliers = {0.22, 0.39, 0.39};
        TableManager.resizeColumns(activeTestsTableView, activeTestsMultipliers);

        //makes the elements in the database questions table clickable
        activeTestsTableView.setOnMouseClicked((e) -> {
            activeTestRowData = activeTestsTableView.getSelectionModel().getSelectedItem();
        });
    }

    /**
     * goes to the test creation screen
     * @param event the event that triggered the method (clicking the "create new test" button)
     */
    public void createNewTest(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.createNewTestPath);
    }

    /**
     * switches to the edit test screen (identical to stage 1 of test creation)
     * the relevant fields in stateManagement are updated with the values
     * of the test that was selected
     *
     * @param event the event that triggered the method
     */
    public void editTest(ActionEvent event) {
        stateManagement = StateManagement.getInstance();

        if (testRowData == null) {
            showError.showErrorPopup("Select a test to edit");
            return;
        }

        //prevents an active test from being edited
        for (int i = 0; i < activeTestsTableView.getItems().size(); i++) {
            if (activeTestsTableView.getItems().get(i).getId().equals(testRowData.getId())) {
                showError.showErrorPopup("Test is currently in process and cannot be edited");
                return;
            }
        }
        loadTestState(testRowData);
        stateManagement.setPreviousScreenPath(PathConstants.manageTestsPath);
        ScreenManager.goToNewScreen(event, PathConstants.createNewTestPath);
    }

    /**
     * Deletes the selected test from the manageTests table.
     *
     * @param event The event triggered by clicking the "Delete" button.
     */
    public void deleteTest(ActionEvent event) {
        if (testRowData == null) {
            showError.showErrorPopup("Select a test to delete");
            return;
        }
        int selectedTestIndex = testsFromDBTableView.getSelectionModel().getFocusedIndex();
        if (selectedTestIndex != -1) {
            Test testToDelete = testsFromDBTableView.getItems().get(selectedTestIndex);
            if (showError.showConfirmationPopup("Are you sure you want to delete this test?")) {
                MsgHandler deleteTestMsg = new MsgHandler(TypeMsg.DeleteTest, testToDelete);
                ClientUI.chat.accept(deleteTestMsg);

                reloadPage();
            }
        }
    }

    /**
     * Reloads the manage tests page.
     */
    private void reloadPage() {
        Stage currentStage = (Stage) deleteBtn.getScene().getWindow();
        currentStage.close();
        ScreenManager.showStage(PathConstants.manageTestsPath, PathConstants.iconPath);
    }

    /**
     * sets the chosen test as "active" and generates a random test code for it
     * @param actionEvent the event that triggered the method (clicking the "activate test" button)
     */
    public void activateTest(ActionEvent actionEvent) {

        if (testRowData == null) {
            showError.showErrorPopup("Select a test to activate");
            return;
        }

        if (showError.showConfirmationPopup("Are you sure you want to activate the test?")) {

            loadTestState(testRowData); //loads the details of the relevant test

            //setting the activeTest data
            LocalDate currentDate = LocalDate.now();
            LocalTime currentTime = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
            ;
            ActiveTest testToActivate = new ActiveTest(
                    stateManagement.getTestID(),
                    stateManagement.getTestQuestions().size(),
                    currentDate.toString(),
                    currentTime.toString(),
                    generateTestCode()
            );

            //adds a row to the activetest table
            MsgHandler addNewActiveTest = new MsgHandler(TypeMsg.AddNewActiveTest, testToActivate);
            ClientUI.chat.accept(addNewActiveTest);

            //adds a row to the aftertestinfo table
            String[] infoArray = {testRowData.getId(), testToActivate.getTestDate(), testRowData.getTestDuration()};
            MsgHandler addNewAfterTestInfo = new MsgHandler(TypeMsg.AddNewAfterTestInfo, infoArray);
            ClientUI.chat.accept(addNewAfterTestInfo);
            StateManagement.resetInstance();
            reloadPage();

        }
    }

    /**
     * Generates a random 4-character string used as the test code
     * The test code is used by the student to access a test
     * @return the tet code string
     */
    public String generateTestCode(){
        UUID uuid = UUID.randomUUID();
        String randomString = uuid.toString().substring(0, 4);
        return randomString;
    }

    /**
     * loads the selected test's details
     * @param test the selected test
     */
    public void loadTestState(Test test) {
        //load test's course
        String subjectID = test.getId().substring(0, 2);
        String courseID = test.getId().substring(2, 4);
        Course testCourse = new Course(subjectID, courseID, test.getSubject(), test.getCourseName());
        stateManagement.setCourse(testCourse);

        //load test's questions
        MsgHandler getTestQuestions = new MsgHandler(TypeMsg.GetTestQuestions, test);
        ClientUI.chat.accept(getTestQuestions);

        ObservableList<TestQuestion> testQuestions = FXCollections.observableArrayList((List) ClientUI.chat.getTestQuestions());
        for (TestQuestion question : testQuestions) {
            stateManagement.setTestQuestions(question);
        }

        //load test's other data
        stateManagement.setTestID(test.getId());
        stateManagement.setTestNum(test.getTestNumber());
        stateManagement.setTestDuration(test.getTestDuration());
        stateManagement.setYear(test.getYear());
        stateManagement.setSession(test.getSession());
        stateManagement.setSemester(test.getSemester());
        stateManagement.setStudentComment(test.getStudentComments().equals("null") ? "" : test.getStudentComments());
        stateManagement.setTeacherComment(test.getTeacherComments().equals("null") ? "" : test.getTeacherComments());

        //when an existing test is opened, no points are available
        stateManagement.totalRemainingPoints = 0;
    }


    /**
     * view the selected active test's current progress and info
     *
     * @param actionEvent the event that triggered the method
     */
    public void viewTestInProgress(ActionEvent actionEvent) {

        if (activeTestRowData == null) {
            showError.showErrorPopup("Select a test to view");
            return;
        }

        //sets data exclusive to activeTest
        ActiveTest curActiveTest = new ActiveTest(
                activeTestRowData.getId(),
                activeTestRowData.getNumOfQuestions(),
                activeTestRowData.getTestDate(),
                activeTestRowData.getStartingTime(),
                activeTestRowData.getTestCode()
        );
        stateManagement.setCurrentActivetest(curActiveTest);

        //gets the matching Test object to the selected active test row
        Test matchingTestFromDB = testsFromDBTableView.getItems()
                .stream()
                .filter(test -> test.getId().equals(activeTestRowData.getId()))
                .findFirst()
                .orElse(null);
        if (matchingTestFromDB != null)
            loadTestState(matchingTestFromDB);

        //ScreenManager.popUpScreen(PathConstants.viewActiveTestPath);
        ScreenManager.goToNewScreen(actionEvent,PathConstants.viewActiveTestPath);
    }

    /**
     * opens a test approval screen for the selected test
     *
     * @param actionEvent the event that triggered the method
     */
    public void viewTestResults(ActionEvent actionEvent) {
        if (testForApprovalRowData == null) {
            showError.showErrorPopup("Select a test to approve");
            return;
        }
        stateManagement.setTestID(testForApprovalRowData.getTestID());
        stateManagement.setStudentID(testForApprovalRowData.getStudentID());
        ScreenManager.goToNewScreen(actionEvent, PathConstants.viewTestAwaitingApprovalPath);
    }

    public void LogOut(ActionEvent event) {
        StateManagement.resetInstance();
       LogOut.logOutToLoginScreen(event);
    }


    public void back(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuPath);
    }

    @FXML
    private void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }


}
