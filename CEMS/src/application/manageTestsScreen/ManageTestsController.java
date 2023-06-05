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
import javafx.stage.Stage;
import util.*;

import java.util.List;


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
    private TableView<Test> testApprovalTableView;
    @FXML
    private TableView<ActiveTest> activeTestsTableView;
    @FXML
    private TableView<Test> approvedTestsTableView; //contains every test that has been verified by the lecturer
    public StateManagement stateManagement;
    Test testRowData;
    ActiveTest activeTestRowData;


    public void initialize() {
        ScreenManager.dragAndDrop(header);

        displayDbTestsTable();
        displayActiveTestsTable();
    }

    /**
     * fetches the tests table from the database
     * only tests belonging to the lecturer's subjects are shown
     */
    private void displayDbTestsTable() {

        MsgHandler getDbTestTable = new MsgHandler(TypeMsg.GetTestsBySubject, Client.user.getUserName());
        ClientUI.chat.accept(getDbTestTable);

        ObservableList<Test> dbTests = FXCollections.observableArrayList((List) ClientUI.chat.getTests());

        ObservableList<String> columns = FXCollections.observableArrayList();
        columns.addAll("Test Number", "ID", "Test Code", "Subject", "Course Name", "Year", "Semester", "Session", "Author");
        TableManager.createTable(testsFromDBTableView, columns);
        TableManager.importData(testsFromDBTableView, dbTests);


        double[] dbTestsMultipliers = {0.1, 0.08, 0.1, 0.1, 0.175, 0.1, 0.1, 0.1, 0.14};
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
     * fetches the active tests table from the database
     */
    private void displayActiveTestsTable() {

        MsgHandler getActiveTestsTable = new MsgHandler(TypeMsg.GetActiveTests, Client.user.getUserName());
        ClientUI.chat.accept(getActiveTestsTable);

        ObservableList<ActiveTest> activeTests = FXCollections.observableArrayList((List) ClientUI.chat.getActiveTests());

        ObservableList<String> columns = FXCollections.observableArrayList();
        columns.addAll("ID", "Starting Time", "Time Left");
        TableManager.createTable(activeTestsTableView, columns);
        TableManager.importData(activeTestsTableView, activeTests);

        double[] activeTestsMultipliers = {0.22, 0.39,0.39};
        TableManager.resizeColumns(activeTestsTableView, activeTestsMultipliers);

        //makes the elements in the database questions table clickable
        activeTestsTableView.setOnMouseClicked((e) -> {
            activeTestRowData = activeTestsTableView.getSelectionModel().getSelectedItem();
        });
    }

    public void createNewTest(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.createNewTestPath);
    }

    /**
     * switches to the edit test screen (identical to stage 1 of test creation)
     * the relevant fields in stateManagement are updated with the values
     * of the test that was selected
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

        ScreenManager.goToNewScreen(event,PathConstants.createNewTestPath);
    }


    /**
     * view the selected active test's current progress and info
     * @param actionEvent the event that triggered the method
     */
    public void viewTestInProgress(ActionEvent actionEvent) {
        stateManagement = StateManagement.getInstance();

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
                activeTestRowData.getTimeLeft()
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

        ScreenManager.popUpScreen(PathConstants.viewActiveTestPath);
    }

    /**
     * loads a given test's data into StateManagement
     * @param test the test we wish to load
     */
    public void loadTestState(Test test) {
        //load test's course
        String subjectID = test.getId().substring(0,2);
        String courseID = test.getId().substring(2,4);
        Course testCourse = new Course(subjectID,courseID, test.getSubject(), test.getCourseName());
        stateManagement.setCourse(testCourse);

        //load test's questions
        MsgHandler getTestQuestions = new MsgHandler(TypeMsg.GetTestQuestions, test);
        ClientUI.chat.accept(getTestQuestions);

        ObservableList<TestQuestion> testQuestions = FXCollections.observableArrayList((List) ClientUI.chat.getTestQuestions());
        for (TestQuestion question: testQuestions) {
            stateManagement.setTestQuestions(question);
        }

        //load test's other data
        stateManagement.setTestID(test.getId());
        stateManagement.setTestNum(test.getTestNumber());
        stateManagement.setTestDuration(test.getTestDuration());
        stateManagement.setYear(test.getYear());
        stateManagement.setSession(test.getSession());
        stateManagement.setSemester(test.getSemester());
        stateManagement.setTestCode(test.getTestCode());
        stateManagement.setStudentComment(test.getStudentComments().equals("null") ? "" : test.getStudentComments());
        stateManagement.setTeacherComment(test.getTeacherComments().equals("null") ? "" : test.getTeacherComments());

        //when an existing test is opened, no points are available
        stateManagement.totalRemainingPoints = 0;
    }

    public void viewTestResults(ActionEvent actionEvent) {
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

    public void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.loginPath);
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
