package application.manageTestsScreen;

import client.ClientUI;
import common.MsgHandler;
import common.TypeMsg;
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
    private TableView<Test> manageTestsTableView;
    @FXML
    private TableView<Test> testApprovalTableView;

    public void initialize() {
        ScreenManager.dragAndDrop(header);

        MsgHandler getTestTable = new MsgHandler(TypeMsg.GetTestTable, null);
        ClientUI.chat.accept(getTestTable);

        //fetches the tests table from the database
        ObservableList<Test> tests = FXCollections.observableArrayList((List) ClientUI.chat.getTests() );

        ObservableList<String> columns = FXCollections.observableArrayList();
        columns.addAll("Test Number", "ID", "Subject", "Course Name", "Year", "Semester", "Session", "Author");
        TableManager.createTable(manageTestsTableView, columns);
        TableManager.importData(manageTestsTableView, tests);
        //TableManager.addDoubleClickFunctionality(testsTableView, PathConstants.updateQuestionPath, this::setFunctions);

        double[] multipliers = {0.15, 0.1, 0.1, 0.13, 0.15,0.1,0.1, 0.162};
        TableManager.resizeColumns(manageTestsTableView, multipliers);

        //filter tests by course name
        FilteredList<Test> filteredData = new FilteredList<>(tests, b -> true);
        TableManager.MakeFilterListForSearch(filteredData, searchField, Test::getCourseName);

        SortedList<Test> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(manageTestsTableView.comparatorProperty());
        manageTestsTableView.setItems(sortedData);
    }

    public void createNewTest(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.createNewTestPath);
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
