package application.manageQuestionsScreen;

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
import javafx.stage.Stage;
import util.*;

import java.util.ArrayList;
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
    public void initialize() {
        System.out.println("init manage questions");
        ScreenManager.dragAndDrop(header);
        MsgHandler getTable = new MsgHandler(TypeMsg.GetQuestions, null);
        ClientUI.chat.accept(getTable);


        //creates the question table
        ObservableList<Question> questions = FXCollections.observableArrayList((List) ClientUI.chat.getList());
        ObservableList<String> columns = FXCollections.observableArrayList();
        columns.addAll("Question Number", "ID", "Subject", "Course Name", "Question Text", "Lecturer");
        TableManager.createTable(manageQuestionsTableView, columns);
        TableManager.importData(manageQuestionsTableView, questions);
        TableManager.addDoubleClickFunctionality(manageQuestionsTableView, PathConstants.updateQuestionPath, this::setFunctions);
        double[] multipliers = {0.15, 0.1, 0.1, 0.13, 0.35, 0.162};
        TableManager.resizeColumns(manageQuestionsTableView, multipliers);
        //filter result as you search yay
        FilteredList<Question> filteredData = new FilteredList<>(questions, b -> true);
        TableManager.MakeFilterListForSearch(filteredData, searchField, Question::getQuestion_text);

        SortedList<Question> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(manageQuestionsTableView.comparatorProperty());
        manageQuestionsTableView.setItems(sortedData);
    }
    public void setFunctions(String relativePath) {
        ScreenElements<Stage, FXMLLoader> screenElements = ScreenManager.popUpScreen(relativePath);
        FXMLLoader loader = screenElements.getFXMLLoader();
        UpdateQuestionController controller = loader.getController();
        Question rowData = manageQuestionsTableView.getSelectionModel().getSelectedItem();
        controller.setQuestion(rowData);
        controller.setManage((Stage) header.getScene().getWindow());
    }

    /**
     *
     * @param event clicking "delete" on a selected row to delete a question
     * this method deletes the question in the question table not through the DB. In order to delete the permanently delete it,
     * "Save" button needs to be clicked.
     */
    @FXML
    public void DeleteQuestion(ActionEvent event) {
        int selectedQuestionIndex = manageQuestionsTableView.getSelectionModel().getFocusedIndex();
        if (selectedQuestionIndex != -1) {
            Question questionToDelete = manageQuestionsTableView.getItems().get(selectedQuestionIndex);
            if (showError.showConfirmationPopup("Are you sure you want to delete this question?")) {
                ArrayList<Question> arr = new ArrayList<>();
                arr.add(questionToDelete);
                MsgHandler deleteQ = new MsgHandler(TypeMsg.DeleteQuestion, arr);
                ClientUI.chat.accept(deleteQ);
                reloadPage();
            }
        }
    }
    private void reloadPage() {
        Stage currentStage = (Stage) deleteBtn.getScene().getWindow();
        currentStage.close();
        ScreenManager.showStage(PathConstants.manageQuestions, PathConstants.iconPath);
    }
    public void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.loginPath);
    }

    public void back(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuPath);
    }

    public void AddQuestion(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.addQuestionPath);
    }

    public void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

}