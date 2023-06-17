package application.manageQuestionsScreen;

import Client.Client;
import entity.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.*;
import Client.ClientUI;
import Client.ExitButton;
import Client.LogOut;

import java.util.List;
/**
 * The controller for viewing a table of questions for the head of department.
 * The head of department can view all the course's questions
 * in his department
 */
public class ViewQuestionHeadOfDepartmentController {

    @FXML
    private AnchorPane header;

    @FXML
    private TableView<Question> manageQuestionsTableView;

    @FXML
    private TextField searchField;

    @FXML
    private Text usernameText;

    /**
     * Initializes the View Question Head of Department screen.
     * Sets up the header, username text, and builds the question table.
     */
    @FXML
    public void initialize() {
        ScreenManager.dragAndDrop(header);
        usernameText.setText(Client.user.getName());

        ObservableList<Question> questions = buildTable();
        filterTable(questions);
    }

    /**
     * Builds the question table by retrieving questions from the server and populating the table with the data.
     *
     * @return The observable list of questions used to populate the table.
     */
    private ObservableList<Question> buildTable() {
        MsgHandler getTable = new MsgHandler(TypeMsg.GetQuestionsBySubject, Client.user.getUserName());
        ClientUI.chat.accept(getTable);

        ObservableList<Question> questions = FXCollections.observableArrayList((List) ClientUI.chat.GetQuestionsBySubject());
        ObservableList<String> columns = FXCollections.observableArrayList();
        columns.addAll("Question Number", "ID", "Subject", "Course Name", "Question Text", "Author");

        TableManager.createTable(manageQuestionsTableView, columns);
        TableManager.importData(manageQuestionsTableView, questions);
        TableManager.addDoubleClickFunctionality(manageQuestionsTableView, PathConstants.ViewQuestionPath, this::setFunctions);

        double[] multipliers = {0.15, 0.1, 0.1, 0.13, 0.35, 0.162};
        TableManager.resizeColumns(manageQuestionsTableView, multipliers);

        return questions;
    }

    /**
     * Filters the question table based on the search field input.
     *
     * @param questions The observable list of questions used to populate the table.
     */
    private void filterTable(ObservableList<Question> questions) {
        FilteredList<Question> filteredData = new FilteredList<>(questions, b -> true);
        TableManager.MakeFilterListForSearch(filteredData, searchField, Question::getQuestionText);

        SortedList<Question> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(manageQuestionsTableView.comparatorProperty());
        manageQuestionsTableView.setItems(sortedData);
    }

    /**
     * Sets the necessary functions for the update question screen.
     *
     * @param relativePath The relative path to the update question screen FXML file.
     */
    public void setFunctions(String relativePath) {
        ScreenElements<Stage, FXMLLoader> screenElements = ScreenManager.popUpScreen(relativePath);
        FXMLLoader loader = screenElements.getFXMLLoader();
        ViewQuestionController controller = loader.getController();
        Question rowData = manageQuestionsTableView.getSelectionModel().getSelectedItem();
        controller.setQuestion(rowData);
    }

    /**
     * Logs out the user and navigates to the login screen.
     *
     * @param event The event triggered by clicking the logout button.
     */
    @FXML
    public void LogOut(ActionEvent event) {
        LogOut.logOutToLoginScreen(event);
    }

    /**
     * Navigates back to the previous screen.
     *
     * @param event The event triggered by clicking the back button.
     */
    @FXML
    public void goBackToPreviousScreen(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuHeadOfDepartPath);
    }

    /**
     * Closes the application.
     */
    @FXML
    public void closeClient() {
        ExitButton.closeClient();
    }

    /**
     * Minimizes the application window.
     *
     * @param event The event triggered by clicking the minimize button.
     */
    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
