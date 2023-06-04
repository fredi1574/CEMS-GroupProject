package application.manageTestsScreen;

import application.createNewTestScreen.CreateTestController;
import application.manageQuestionsScreen.UpdateQuestionController;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.*;

import javax.swing.*;
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
    private TableView<Test> activeTestsTableView;
    public StateManagement stateManagement;
    Test rowData;


    public void initialize() {
        ScreenManager.dragAndDrop(header);

        displayDbTestsTable();
    }

    private void displayDbTestsTable() {
        //fetches the tests table from the database
        //only tests belonging to the lecturer's subjects are shown
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
            rowData = testsFromDBTableView.getSelectionModel().getSelectedItem();
        });

        //filter tests by course name
        FilteredList<Test> filteredData = new FilteredList<>(dbTests, b -> true);
        TableManager.MakeFilterListForSearch(filteredData, searchField, Test::getCourseName);

        SortedList<Test> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(testsFromDBTableView.comparatorProperty());
        testsFromDBTableView.setItems(sortedData);
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

        //TODO: get Course object matching selected test (or alter StateManagement)
        //stateManagement.setCourse();
        String subjectID = rowData.getId().substring(0,2);
        String courseID = rowData.getId().substring(2,4);
        Course testCourse = new Course(subjectID,courseID,rowData.getSubject(), rowData.getCourseName());

        MsgHandler getTestQuestions = new MsgHandler(TypeMsg.GetTestQuestions, rowData);
        ClientUI.chat.accept(getTestQuestions);
        List<Object> testQuestions = ClientUI.chat.getTestQuestions();
        System.out.println(testQuestions);

        stateManagement.setCourse(testCourse);
        stateManagement.setTestID(rowData.getId());
        stateManagement.setTestNum(rowData.getTestNumber());
        stateManagement.setDurationTimeOfTest(rowData.getTestDuration());
        stateManagement.setYear(rowData.getYear());
        stateManagement.setSession(rowData.getSession());
        stateManagement.setSemester(rowData.getSemester());

        ScreenManager.goToNewScreen(event,PathConstants.createNewTestPath);
    }

    /**
     * Deletes the selected test from the manageTests table.
     *
     * @param event The event triggered by clicking the "Delete" button.
     */
    public void deleteTest(ActionEvent event) {
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
