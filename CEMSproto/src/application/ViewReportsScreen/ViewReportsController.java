package application.ViewReportsScreen;

import application.ExitButton;
import application.MinimizeButton;
import application.ScreenManager;
import application.TableManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
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

        ObservableList<Integer> yearList = FXCollections.observableArrayList(2023, 2022, 2021, 2020, 2019, 2018, 2017, 2016, 2015);
        ObservableList<Character> semesterList = FXCollections.observableArrayList('A', 'B', 'S');
        ObservableList<String> subjectList = FXCollections.observableArrayList("Computer Science", "Mathematics",
                "Algorithms", "Statistics", "Bananas n' monkeys", "Introduction to Databases");

        yearComboBox.setItems(yearList);
        semesterComboBox.setItems(semesterList);
        subjectComboBox.setItems(subjectList);

        ObservableList<Report> reportList = FXCollections.observableArrayList(
                new Report(2023, "A", "Computer Science", "Automatons", 42069),
                new Report(2021, "B", "Mathematics", "Statistics", 13096),
                new Report(2021, "Summer", "Statistics", "Bananas n' monkeys", 13099)
        );

        ObservableList<String> columnList = FXCollections.observableArrayList();
        columnList.addAll("Year", "Semester", "Subject", "Course", "Test ID");

        ObservableList<TableColumn<Report, ?>> columns = TableManager.createTable(reportsTableView, columnList, reportList);
        TableManager.addDoubleClickFunctionality(reportsTableView, "ViewReportsScreen/GraphScreen/ViewGraph.fxml");

        //todo: find a cleaner way to do change the width of every column
        for (TableColumn<Report, ?> column : columns) {
            if (column.getText().equals("Course")) {
                column.prefWidthProperty().bind(reportsTableView.widthProperty().multiply(0.544));
            } else if (column.getText().equals("Subject")) {
                column.prefWidthProperty().bind(reportsTableView.widthProperty().multiply(0.15));
            } else {
                column.prefWidthProperty().bind(reportsTableView.widthProperty().multiply(0.1));
            }
        }

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
    private void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
