package application.viewTestsHeadOfDepartment;

import Client.Client;
import Client.ClientUI;
import entity.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.*;
import Client.ExitButton;
import Client.LogOut;

import java.util.List;
/**

 Controller class for the "View Tests" screen for the Head of Department.
 In this screen the head of department can view tests of his department
 */
public class ViewTestsForHeadOfDepartmentController {

    @FXML
    private AnchorPane header;

    @FXML
    private Text usernameText;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<Test> manageTestsTableView;

    /**

     Initializes the View Tests screen.

     This method is automatically called after the FXML file has been loaded.

     It initializes the UI elements, fetches the tests table from the database,

     and sets up filtering and sorting functionalities for the table.
     */
    @FXML
    public void initialize() {
        ScreenManager.dragAndDrop(header);
        usernameText.setText(Client.user.getName());
        MsgHandler getTestTable = new MsgHandler(TypeMsg.GetTestsBySubject,Client.user.getUserName());
        ClientUI.chat.accept(getTestTable);

// Fetch the tests table from the database
        ObservableList<Test> tests = FXCollections.observableArrayList((List) ClientUI.chat.getTests());
        ObservableList<Test> subjectsTests = FXCollections.observableArrayList();
        ObservableList<String> columns = FXCollections.observableArrayList();
        columns.addAll("Test Number", "ID", "Subject", "Course Name", "Year", "Semester", "Session", "Author");
        TableManager.createTable(manageTestsTableView, columns);
        TableManager.importData(manageTestsTableView, tests);

        double[] multipliers = {0.15, 0.1, 0.1, 0.13, 0.15, 0.1, 0.1, 0.162};
        TableManager.resizeColumns(manageTestsTableView, multipliers);

// Filter tests by course name
        FilteredList<Test> filteredData = new FilteredList<>(tests, b -> true);
        TableManager.MakeFilterListForSearch(filteredData, searchField, Test::getId);

        SortedList<Test> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(manageTestsTableView.comparatorProperty());
        manageTestsTableView.setItems(sortedData);
    }

    /**

     Logs out the user and returns to the login screen.
     @param event The action event triggered by the user.
     */
    @FXML
    public void LogOut(ActionEvent event) {
        LogOut.logOutToLoginScreen(event);
    }
    /**

     Returns to the previous screen (Main Menu for Head of Department).
     @param event The action event triggered by the user.
     */
    @FXML
    public void goBackToPreviousScreen(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuHeadOfDepartPath);
    }
    /**

     Closes the client application.
     */
    @FXML
    public void closeClient() {
        ExitButton.closeClient();
    }
    /**

     Minimizes the client application window.
     @param event The action event triggered by the user.
     */
    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
