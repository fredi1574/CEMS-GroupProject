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
        columns.addAll("ID", "Starting Time");
        TableManager.createTable(activeTestsTableView, columns);
        TableManager.importData(activeTestsTableView, activeTests);

        double[] activeTestsMultipliers = {0.40, 0.60};
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

        //load test's course
        String subjectID = testRowData.getId().substring(0,2);
        String courseID = testRowData.getId().substring(2,4);
        Course testCourse = new Course(subjectID,courseID, testRowData.getSubject(), testRowData.getCourseName());

        MsgHandler getTestQuestions = new MsgHandler(TypeMsg.GetTestQuestions, testRowData);
        ClientUI.chat.accept(getTestQuestions);
        stateManagement.setCourse(testCourse);

        //load test's questions
        ObservableList<TestQuestion> testQuestions = FXCollections.observableArrayList((List) ClientUI.chat.getTestQuestions());
        for (TestQuestion question: testQuestions) {
            stateManagement.setTestQuestions(question);
        }

        //load test's other data
        stateManagement.setTestID(testRowData.getId());
        stateManagement.setTestNum(testRowData.getTestNumber());
        stateManagement.setTestDuration(testRowData.getTestDuration());
        stateManagement.setYear(testRowData.getYear());
        stateManagement.setSession(testRowData.getSession());
        stateManagement.setSemester(testRowData.getSemester());
        stateManagement.setStudentComment(testRowData.getStudentComments().equals("null") ? "" : testRowData.getStudentComments());
        stateManagement.setTeacherComment(testRowData.getTeacherComments().equals("null") ? "" : testRowData.getTeacherComments());

        //when an existing test is opened, no points are available
        stateManagement.totalRemainingPoints = 0;

        ScreenManager.goToNewScreen(event,PathConstants.createNewTestPath);
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
               // activeTestRowData.getTimeLeft(),
                activeTestRowData.getTestCode()
        );
        stateManagement.setCurrentActivetest(curActiveTest);

        //gets the matching Test object to the selected active test row
        Test matchingTestFromDB = testsFromDBTableView.getItems()
                .stream()
                .filter(test -> test.getId().equals(activeTestRowData.getId()))
                .findFirst()
                .orElse(null);

        //TODO: seperate method for stateManagement loading

        //load test's course
        String subjectID = matchingTestFromDB.getId().substring(0,2);
        String courseID = matchingTestFromDB.getId().substring(2,4);
        Course testCourse = new Course(subjectID,courseID, matchingTestFromDB.getSubject(), matchingTestFromDB.getCourseName());

        MsgHandler getTestQuestions = new MsgHandler(TypeMsg.GetTestQuestions, matchingTestFromDB);
        ClientUI.chat.accept(getTestQuestions);
        stateManagement.setCourse(testCourse);

        //load test's questions
        ObservableList<TestQuestion> testQuestions = FXCollections.observableArrayList((List) ClientUI.chat.getTestQuestions());
        for (TestQuestion question: testQuestions) {
            stateManagement.setTestQuestions(question);
        }

        //load test's other data
        stateManagement.setTestID(matchingTestFromDB.getId());
        stateManagement.setTestNum(matchingTestFromDB.getTestNumber());
        stateManagement.setTestDuration(matchingTestFromDB.getTestDuration());
        stateManagement.setYear(matchingTestFromDB.getYear());
        stateManagement.setSession(matchingTestFromDB.getSession());
        stateManagement.setSemester(matchingTestFromDB.getSemester());
        stateManagement.setTestCode(matchingTestFromDB.getTestCode());
        stateManagement.setStudentComment(matchingTestFromDB.getStudentComments().equals("null") ? "" : matchingTestFromDB.getStudentComments());
        stateManagement.setTeacherComment(matchingTestFromDB.getTeacherComments().equals("null") ? "" : matchingTestFromDB.getTeacherComments());

        //when an existing test is opened, no points are available
        stateManagement.totalRemainingPoints = 0;

        ScreenManager.popUpScreen(PathConstants.viewActiveTestPath);
    }

    public void viewTestResults(ActionEvent actionEvent) {
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
