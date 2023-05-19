package application.ViewReportsScreen;

import util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ViewReportsController {

    @FXML
    private AnchorPane header;

    @FXML
    private ComboBox<Integer> yearComboBox;
    @FXML
    private ComboBox<String> semesterComboBox;
    @FXML
    private ComboBox<String> subjectComboBox;

    @FXML
    private TableView<Report> reportsTableView;

    @FXML
    public void initialize() {
        ScreenManager.dragAndDrop(header);

        // Temporary lists, for presentation
        ObservableList<Integer> yearList = FXCollections.observableArrayList(2023, 2022, 2021, 2020, 2019, 2018, 2017, 2016, 2015);
        ObservableList<String> semesterList = FXCollections.observableArrayList("A", "B", "Summer");
        ObservableList<String> subjectList = FXCollections.observableArrayList("Computer Science", "Mathematics",
                "Algorithms", "Statistics", "Bananas n' monkeys", "Introduction to Databases");

        // Binds the data into the correct dropdown lists
        yearComboBox.setItems(yearList);
        semesterComboBox.setItems(semesterList);
        subjectComboBox.setItems(subjectList);

        // Temporary lists, for presentation
        ObservableList<Report> reportList = FXCollections.observableArrayList(
                new Report(2023, "A", "Computer Science", "Automatons", 42069),
                new Report(2021, "B", "Mathematics", "Statistics", 13096),
                new Report(2021, "Summer", "Statistics", "Bananas n' monkeys", 13099)
        );

        // Sets the column name
        ObservableList<String> columnList = FXCollections.observableArrayList();
        columnList.addAll("Year", "Semester", "Subject", "Course", "Test ID");
        // Creates the report table with the correct data
        TableManager.createTable(reportsTableView, columnList);
        TableManager.importData(reportsTableView, reportList);

        TableManager.addDoubleClickFunctionality(reportsTableView, "ViewReportsScreen/GraphScreen/ViewGraph.fxml");

        double[] multipliers = {0.07, 0.1, 0.2, 0.525, 0.1};
        TableManager.resizeColumns(reportsTableView, multipliers);
    }

    public void goBackToPreviousScreen(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "MainMenuScreen/MainMenu.fxml");
    }

    public void showReports() {
        ScreenManager.popUpScreen("ViewReportsScreen/GraphScreen/ViewGraph.fxml");
    }

    public void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml");
    }

    @FXML
    public void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
