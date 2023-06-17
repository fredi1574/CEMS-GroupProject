package application.createNewTestScreen.pickQuestionsScreen;

import Client.Client;
import Client.ClientUI;
import application.createNewTestScreen.CreateTestController;
import entity.Question;
import entity.TestQuestion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.*;
import Client.ExitButton;
import Client.LogOut;

import java.util.ArrayList;
import java.util.List;

import static util.TextFormatter.formatField;
/**
 * The `PickQuestionsController` class is a controller that manages the "Pick Questions" screen in a test creation application.
 * This screen allows the user (lecturer) to select questions from a database and add them to a test. The screen displays two tables:
 * one showing the available questions from the database, and the other displaying the selected questions for the current test.
 * The class initializes and manages JavaFX components such as text fields, buttons, and table views.
 */
public class PickQuestionsController {
    public ArrayList<TestQuestion> questions;
    public StateManagement stateManagement;
    @FXML
    public TextField searchField;
    Question rowData;
    TestQuestion testQuestions;
    TestQuestion rowDataForQuestionsSelected;
    CreateTestController createTestController = new CreateTestController();
    ObservableList<TestQuestion> selectedQuestions = FXCollections.observableArrayList();
    @FXML
    private Text nameAuthor;
    @FXML
    private TextField totalRemainingPointsField;
    @FXML
    private TextField pointsField;
    @FXML
    private AnchorPane header;
    @FXML
    private Button saveButtonTestQuestions;
    @FXML
    private TableColumn<TestQuestion, String> points;
    @FXML
    private TableView<Question> questionDBTableView;
    @FXML
    private TableView<TestQuestion> selectedQuestionsTableView;

    /**
     * Initializes the controller.
     */
    public void initialize() {
        ScreenManager.dragAndDrop(header);
        nameAuthor.setText(Client.user.getName());

        formatField(pointsField, true, 3);
        stateManagement = StateManagement.getInstance();

        displayQuestionsDBTable();
        displaySelectedQuestionsTable();

        totalRemainingPointsField.setText(String.valueOf(stateManagement.getTotalRemainingPoints()));
    }

    /**
     * Displays the table containing every question in the database relevant to the lecturer's subject.
     */
    private void displayQuestionsDBTable() {
        MsgHandler questionsDBTable = new MsgHandler(TypeMsg.GetQuestionsByLecturer, Client.user.getFullName());
        ClientUI.chat.accept(questionsDBTable);
        // creates the question table
        ObservableList<Question> questions = FXCollections.observableArrayList((List) ClientUI.chat.GetQuestionsBySubject());

        // creates a table of questions the author can see
        ObservableList<String> questionDBTableColumns = FXCollections.observableArrayList();
        questionDBTableColumns.addAll("Question Number", "ID", "Subject", "Course Name", "Question Text", "Author");
        TableManager.createTable(questionDBTableView, questionDBTableColumns);
        TableManager.importData(questionDBTableView, questions);

        // resizes the columns of the table
        double[] multipliers = {0.15, 0.1, 0.1, 0.13, 0.35, 0.162};
        TableManager.resizeColumns(questionDBTableView, multipliers);

        // makes the elements in the database questions table clickable
        questionDBTableView.setOnMouseClicked((e) -> rowData = questionDBTableView.getSelectionModel().getSelectedItem());

        FilteredList<Question> filteredData = new FilteredList<>(questions, b -> true);
        TableManager.MakeFilterListForSearch(filteredData, searchField, Question::getQuestionText);
        SortedList<Question> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(questionDBTableView.comparatorProperty());
        questionDBTableView.setItems(sortedData);
    }

    /**
     * Displays the table containing every question selected for the current test.
     */
    private void displaySelectedQuestionsTable() {
        // creates a table of selected questions
        ObservableList<String> selectedQuestionsTableColumns = FXCollections.observableArrayList();
        selectedQuestionsTableColumns.addAll("Question Number", "Question ID", "Subject", "Course Name", "Question Text", "Author", "Points");
        TableManager.createTable(selectedQuestionsTableView, selectedQuestionsTableColumns);
        selectedQuestions = selectedQuestionsTableView.getItems();

        // resizes the columns of the table
        double[] multipliers = {0.15, 0.1, 0.1, 0.13, 0.162, 0.162, 0.182};
        TableManager.resizeColumns(selectedQuestionsTableView, multipliers);

        // reloads the selected questions table state if questions were added to this test
        if (stateManagement.getTestQuestions().size() != 0) {
            //totalRemainingPoints = stateManagment.getTotalRemainingPoints();
            TableManager.importData(selectedQuestionsTableView, stateManagement.getTestQuestions());
        }

        // makes the elements in the selected questions table clickable
        selectedQuestionsTableView.setOnMouseClicked((e) -> rowDataForQuestionsSelected = selectedQuestionsTableView.getSelectionModel().getSelectedItem());
    }

    /**
     * Searches for the given question in the SelectedQuestions table.
     *
     * @param question the question object we wish to find in selectedQuestionsTableView
     * @return true if the question was found, false otherwise
     */
    public boolean searchInSelectedQuestionsTable(Question question) {
        for (TestQuestion item : selectedQuestionsTableView.getItems()) {
            // Compare the search term with the values in each cell of the current row
            if (item.getQuestionID().equals(question.getId())) {
                return true; // Stop searching after the first match if desired
            }
        }
        return false;
    }

    /**
     * Adds the selected question to the selected questions table.
     *
     * @param event1 the event that triggered the method
     */
    @FXML
    void addSelectedQuestion(ActionEvent event1) {

        if (rowData == null || pointsField.getText().isEmpty()) {
            showError.showErrorPopup("Select a question and points first");
            return;
        }
        if (searchInSelectedQuestionsTable(rowData)) {
            showError.showErrorPopup("This question already exists. Select another question");
            return;
        }
        if (Integer.parseInt(pointsField.getText()) <= 0 || Integer.parseInt(pointsField.getText()) > 100) {
            showError.showErrorPopup("Please select a valid amount of points (1-100)");
            return;
        }

        stateManagement.subtractTotalRemainingPoints(Integer.parseInt(pointsField.getText()));
        if (stateManagement.getTotalRemainingPoints() < 0) {
            showError.showErrorPopup("Not enough total points");
            stateManagement.addTotalRemainingPoints(Integer.parseInt(pointsField.getText()));
            return;
        }

        totalRemainingPointsField.setText(String.valueOf(stateManagement.getTotalRemainingPoints()));
        setTestQuestionData();

        ObservableList<TestQuestion> selectedQuestions = FXCollections.observableArrayList(testQuestions);
        questionDBTableView.getSelectionModel().clearSelection();

        try {
            TableManager.importData(selectedQuestionsTableView, selectedQuestions);
        } catch (Exception ignored) {
        }
        rowData = null; // resets the selected row value after the current one has been added
    }

    /**
     * Sets the data for the TestQuestion object based on the selected question and points.
     */
    public void setTestQuestionData() {
        testQuestions = new TestQuestion();
        testQuestions.setQuestion(rowData);
        testQuestions.setPoints(Integer.parseInt(pointsField.getText()));
        testQuestions.setQuestionID(rowData.getId());
        testQuestions.setAuthor(rowData.getAuthor());
        testQuestions.setCourseName(rowData.getCourseName());
        testQuestions.setQuestionNumber(rowData.getQuestionNumber());
        testQuestions.setSubject(rowData.getSubject());
        testQuestions.setQuestionText(rowData.getQuestionText());
        testQuestions.setTestID(stateManagement.getTestID());
    }

    /**
     * Deselects the selected question in the questionDBTableView.
     */
    public void Deselect() {
        Question desSelected = questionDBTableView.getSelectionModel().getSelectedItem();
        if (desSelected == null)
            showError.showErrorPopup("You Want To Select Before Press Deselect !");
        else {
            questionDBTableView.getSelectionModel().clearSelection();
            rowData = null;
        }
    }

    /**
     * Removes the selected question from the selected questions table.
     */
    public void removeSelectedQuestion() {
        ObservableList<TestQuestion> data = selectedQuestionsTableView.getItems();
        if (data == null) {
            showError.showErrorPopup("You Don't Have Any Questions to Remove");
        } else if (rowDataForQuestionsSelected != null) {
            selectedQuestionsTableView.getItems().remove(rowDataForQuestionsSelected);
            stateManagement.getTestQuestions().remove(rowDataForQuestionsSelected);
            selectedQuestionsTableView.getSelectionModel().clearSelection();
            stateManagement.addTotalRemainingPoints(rowDataForQuestionsSelected.getPoints());
            totalRemainingPointsField.setText(String.valueOf(stateManagement.getTotalRemainingPoints()));
            rowData = null;

        } else {
            showError.showErrorPopup("Select Questions to Remove");
        }
        rowDataForQuestionsSelected = null;
    }

    /**
     * Navigates to the third stage of test creation.
     *
     * @param event the event that triggered the method (clicking the add comments button)
     */
    public void goToAddNotes(ActionEvent event) {
        stateManagement = StateManagement.getInstance();
        saveTestQuestionsTable();
        ScreenManager.goToNewScreen(event, PathConstants.addChangeNotes);
    }

    /**
     * Navigates back to the first stage of test creation.
     *
     * @param event the event that triggered the method (clicking the back button)
     */
    public void backToCreateTest(ActionEvent event) {
        stateManagement = StateManagement.getInstance();
        saveTestQuestionsTable();
        ScreenManager.goToNewScreen(event, PathConstants.createNewTestPath);
    }

    /**
     * Saves the elements of the selected questions table (used when switching screens).
     */
    public void saveTestQuestionsTable() {
        ObservableList<TestQuestion> selectedQuestionsList = selectedQuestionsTableView.getItems();
        ObservableList<TestQuestion> testQuestionsState = stateManagement.getTestQuestions();

        // Selected questions that are already in the list are filtered out to avoid being added again
        testQuestionsState.addAll(selectedQuestionsList.filtered(question -> !testQuestionsState.contains(question)));
    }

    /**
     * Logs out the user and navigates back to the login screen.
     *
     * @param event the event that triggered the method (clicking the logout button)
     */
    public void LogOut(ActionEvent event) {
        LogOut.logOutToLoginScreen(event);
    }

    /**
     * Closes the client application.
     */
    public void closeClient() {
        ExitButton.closeClient();
    }

    /**
     * Minimizes the application window.
     *
     * @param event the event that triggered the method (clicking the minimize button)
     */
    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
