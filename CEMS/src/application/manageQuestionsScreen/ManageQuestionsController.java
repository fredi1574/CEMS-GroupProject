package application.manageQuestionsScreen;

import client.Client;
import client.ClientUI;
import common.MsgHandler;
import common.TypeMsg;
import entity.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.*;

import java.util.List;

public class ManageQuestionsController {

    public TextField searchField;
    @FXML
    private AnchorPane header;
    @FXML
    private Button deleteBtn;

    @FXML
    private TableView<Question> manageQuestionsTableView;
    @FXML
    private Text usernameText;

    public static ManageQuestionsController ManageQuestionsControl;

    /**
     * Initializes the manage questions screen.
     * Enables dragging and dropping of the application window using the header pane.
     * Retrieves the questions from the server and populates the question table.
     * Sets up filtering and searching functionality for the table.
     */
    @FXML
    public void initialize() {
        ScreenManager.dragAndDrop(header);
        usernameText.setText(Client.user.getFullName());
        MsgHandler getTable = new MsgHandler(TypeMsg.GetQuestions, Client.user.getUserName());
        ClientUI.chat.accept(getTable);

        // Creates the question table
        ObservableList<Question> questions = FXCollections.observableArrayList((List) ClientUI.chat.getQuestions());
        ObservableList<String> columns = FXCollections.observableArrayList();
        columns.addAll("Question Number", "ID", "Subject", "Course Name", "Question Text", "Lecturer");
        TableManager.createTable(manageQuestionsTableView, columns);
        TableManager.importData(manageQuestionsTableView, questions);
        TableManager.addDoubleClickFunctionality(manageQuestionsTableView, PathConstants.updateQuestionPath, this::setFunctions);
        double[] multipliers = {0.15, 0.1, 0.1, 0.13, 0.35, 0.162};
        TableManager.resizeColumns(manageQuestionsTableView, multipliers);

        // Filter the results as you search
        FilteredList<Question> filteredData = new FilteredList<>(questions, b -> true);
        TableManager.MakeFilterListForSearch(filteredData, searchField, Question::getQuestionText);

        SortedList<Question> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(manageQuestionsTableView.comparatorProperty());
        manageQuestionsTableView.setItems(sortedData);
    }

    /**
     * Sets the necessary functions for the update question screen.
     * @param relativePath The relative path to the update question screen FXML file.
     */
    public void setFunctions(String relativePath) {
        ScreenElements<Stage, FXMLLoader> screenElements = ScreenManager.popUpScreen(relativePath);
        FXMLLoader loader = screenElements.getFXMLLoader();
        UpdateQuestionController controller = loader.getController();
        Question rowData = manageQuestionsTableView.getSelectionModel().getSelectedItem();
        controller.setQuestion(rowData);
        controller.setManage((Stage) header.getScene().getWindow());
    }

    /**
     * Deletes the selected question from the question table.
     * @param event The event triggered by clicking the "Delete" button.
     */
    @FXML
    public void DeleteQuestion(ActionEvent event) {
        int selectedQuestionIndex = manageQuestionsTableView.getSelectionModel().getFocusedIndex();
        if (selectedQuestionIndex != -1) {
            Question questionToDelete = manageQuestionsTableView.getItems().get(selectedQuestionIndex);
            if (showError.showConfirmationPopup("Are you sure you want to delete this question?")) {
                MsgHandler deleteQ = new MsgHandler(TypeMsg.DeleteQuestion, questionToDelete);
                ClientUI.chat.accept(deleteQ);
                reloadPage();
            }
        }
    }

    /**
     * Reloads the manage questions page.
     */
    private void reloadPage() {
        Stage currentStage = (Stage) deleteBtn.getScene().getWindow();
        currentStage.close();
        ScreenManager.showStage(PathConstants.manageQuestions, PathConstants.iconPath);
    }

    /**
     * Logs out the user and navigates back to the login screen.
     * @param event The event triggered by clicking the "Logout" button.
     */
    public void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.loginPath);
    }

    /**
     * Navigates back to the main menu screen.
     * @param event The event triggered by clicking the "Back" button.
     */
    public void back(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuPath);
    }

    /**
     * Navigates to the add question screen.
     * @param event The event triggered by clicking the "Add Question" button.
     */
    public void AddQuestion(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.addQuestionPath);
    }

    /**
     * Closes the application.
     * @param event The event triggered by the close button click.
     */
    @FXML

    public void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }


    /**
     * Minimizes the application window.
     * @param event The event triggered by the minimize button click.
     */
    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
