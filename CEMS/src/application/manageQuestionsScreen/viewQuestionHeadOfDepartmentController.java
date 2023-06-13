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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.*;

import java.util.List;

public class viewQuestionHeadOfDepartmentController {

    @FXML
    private AnchorPane header;

    @FXML
    private TableView<Question> manageQuestionsTableView;

    @FXML
    private TextField searchField;

    @FXML
    private Text usernameText;
    @FXML
    public void initialize() {
        ScreenManager.dragAndDrop(header);
        usernameText.setText(Client.user.getFullName());
        MsgHandler getTable = new MsgHandler(TypeMsg.GetQuestionsBySubject, Client.user.getUserName());
        ClientUI.chat.accept(getTable);

        // Creates the question table
        ObservableList<Question> questions = FXCollections.observableArrayList((List) ClientUI.chat.GetQuestionsBySubject());
        ObservableList<String> columns = FXCollections.observableArrayList();
        columns.addAll("Question Number", "ID", "Subject", "Course Name", "Question Text", "Author");
        TableManager.createTable(manageQuestionsTableView, columns);
        TableManager.importData(manageQuestionsTableView, questions);
        TableManager.addDoubleClickFunctionality(manageQuestionsTableView, PathConstants.ViewQuestionPath, this::setFunctions);
        double[] multipliers = {0.15, 0.1, 0.1, 0.13, 0.35, 0.162};
        TableManager.resizeColumns(manageQuestionsTableView, multipliers);

        // Filter the results as you search
        FilteredList<Question> filteredData = new FilteredList<>(questions, b -> true);
        TableManager.MakeFilterListForSearch(filteredData, searchField, Question::getQuestionText);

        SortedList<Question> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(manageQuestionsTableView.comparatorProperty());
        manageQuestionsTableView.setItems(sortedData);
    }

    public void setFunctions(String relativePath) {
        ScreenElements<Stage, FXMLLoader> screenElements = ScreenManager.popUpScreen(relativePath);
        FXMLLoader loader = screenElements.getFXMLLoader();
        ViewQuestionController controller = loader.getController();
        Question rowData = manageQuestionsTableView.getSelectionModel().getSelectedItem();
        controller.setQuestion(rowData);
        controller.setManage((Stage) header.getScene().getWindow());
    }

    /**
     * Logs out the user and navigates to the login screen.
     * @param event The event triggered by clicking the logout button.
     */

    @FXML
    public void LogOut(ActionEvent event) {
       LogOut.logOutToLoginScreen(event);
    }

    /**
     * Navigates back to the previous screen.
     * @param event The event triggered by clicking the back button.
     */
    @FXML
    public void goBackToPreviousScreen(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuHeadOfDepartPath);
    }

    /**
     * Closes the application.
     * @param event The event triggered by clicking the close button.
     */
    @FXML
    public void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    /**
     * Minimizes the application window.
     * @param event The event triggered by clicking the minimize button.
     */
    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
