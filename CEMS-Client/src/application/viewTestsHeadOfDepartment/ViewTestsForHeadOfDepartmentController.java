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

public class ViewTestsForHeadOfDepartmentController {

    @FXML
    private AnchorPane header;

    @FXML
    private Text usernameText;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<Test> manageTestsTableView;

    @FXML
    public void initialize() {
        ScreenManager.dragAndDrop(header);
        usernameText.setText(Client.user.getName());
        MsgHandler getTestTable = new MsgHandler(TypeMsg.GetAllTestsTable, null);
        ClientUI.chat.accept(getTestTable);

        //fetches the tests table from the database
        ObservableList<Test> tests = FXCollections.observableArrayList((List) ClientUI.chat.getTests());
        ObservableList<String> columns = FXCollections.observableArrayList();
        columns.addAll("Test Number", "ID", "Subject", "Course Name", "Year", "Semester", "Session", "Author");
        TableManager.createTable(manageTestsTableView, columns);
        TableManager.importData(manageTestsTableView, tests);
        //TableManager.addDoubleClickFunctionality(testsTableView, PathConstants.updateQuestionPath, this::setFunctions);

        double[] multipliers = {0.15, 0.1, 0.1, 0.13, 0.15, 0.1, 0.1, 0.162};
        TableManager.resizeColumns(manageTestsTableView, multipliers);

        //filter tests by course name
        FilteredList<Test> filteredData = new FilteredList<>(tests, b -> true);
        TableManager.MakeFilterListForSearch(filteredData, searchField, Test::getId);

        SortedList<Test> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(manageTestsTableView.comparatorProperty());
        manageTestsTableView.setItems(sortedData);
    }

    @FXML
    public void LogOut(ActionEvent event) {
        LogOut.logOutToLoginScreen(event);
    }

    @FXML
    public void goBackToPreviousScreen(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuHeadOfDepartPath);
    }

    @FXML
    public void closeClient() {
        ExitButton.closeClient();
    }


    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }


}
