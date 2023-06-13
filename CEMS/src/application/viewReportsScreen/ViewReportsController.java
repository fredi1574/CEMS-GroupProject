package application.viewReportsScreen;

import application.viewReportsScreen.graphScreen.ViewGraphController;
import client.Client;
import client.ClientUI;
import common.MsgHandler;
import common.TypeMsg;
import entity.StudentTest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ViewReportsController {

    private static ObservableList<StudentTest> listOfTestsNoDuplicates;
    private static ObservableList<StudentTest> FullListOfTests;
    @FXML
    private AnchorPane header;
    @FXML
    private ComboBox<String> yearComboBox;
    @FXML
    private ComboBox<String> semesterComboBox;
    @FXML
    private ComboBox<String> subjectComboBox;
    @FXML
    private TableView<StudentTest> reportsTableView;
    @FXML
    private Text usernameText;
    private FilteredList<StudentTest> filteredData;


    @FXML
    public void initialize() {
        ScreenManager.dragAndDrop(header);
        usernameText.setText(Client.user.getFullName());
        MsgHandler getTable = new MsgHandler(TypeMsg.GetTestsByLecutrerForLecturerReport, Client.user.getFullName());
        ClientUI.chat.accept(getTable);
        // Temporary lists, for presentation
        ObservableList<String> yearList = FXCollections.observableArrayList("2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015");
        ObservableList<String> semesterList = FXCollections.observableArrayList("A", "B", "Summer");
        // Binds the data into the correct dropdown lists
        yearComboBox.setItems(yearList);
        semesterComboBox.setItems(semesterList);

        // Sets the column name
        ObservableList<String> columnList = FXCollections.observableArrayList();
        columnList.addAll("Year", "Semester", "Subject ID", "Course", "Test ID");
        // Creates the report table with the correct data
        TableManager.createTable(reportsTableView, columnList);
        TableManager.importData(reportsTableView, listOfTestsNoDuplicates);
        TableManager.addDoubleClickFunctionality(reportsTableView, PathConstants.viewGraphPath, this::viewReport);
        double[] multipliers = {0.07, 0.1, 0.2, 0.525, 0.1};
        TableManager.resizeColumns(reportsTableView, multipliers);

        yearComboBox.valueProperty().addListener((observable, oldValue, newValue) -> filterTable());
        semesterComboBox.valueProperty().addListener((observable, oldValue, newValue) -> filterTable());
        filteredData = new FilteredList<>(listOfTestsNoDuplicates, b -> true);
        reportsTableView.setItems(filteredData);
    }

    public ObservableList<StudentTest> getListOfTests() {
        return FullListOfTests;
    }

    private void filterTable() {
        String selectedYear = yearComboBox.getValue();
        String selectedSemester = semesterComboBox.getValue();

        filteredData.setPredicate(item -> {
            boolean yearMatch = selectedYear == null || selectedYear.isEmpty() || item.getYear().equals(selectedYear);
            boolean semesterMatch = selectedSemester == null || selectedSemester.isEmpty() || item.getSemester().equals(selectedSemester);

            return yearMatch && semesterMatch;
        });

        SortedList<StudentTest> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(reportsTableView.comparatorProperty());
        reportsTableView.setItems(sortedData);
    }


    public void viewReport(String relativePath) {
        ViewGraphController controller = new ViewGraphController();
        StudentTest rowData = reportsTableView.getSelectionModel().getSelectedItem();
        controller.setReport(rowData);
        controller.setViewReport((Stage) header.getScene().getWindow());
        showReport();
    }

    public void goBackToPreviousScreen(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuPath);
    }

    public void setTestsTable(ArrayList<Object> Tests) {
        Set<String> uniqueTestIDs = new HashSet<>();
        List<Object> uniqueTests = new ArrayList<>();

        for (Object test : Tests) {
            String testID = ((StudentTest) test).getTestID();
            if (!uniqueTestIDs.contains(testID)) {
                uniqueTestIDs.add(testID);
                uniqueTests.add(test);
            }
        }
        listOfTestsNoDuplicates = FXCollections.observableArrayList((List) uniqueTests);
        FullListOfTests = FXCollections.observableArrayList((List) Tests);
    }

    public void showReport() {
        ViewGraphController controller = new ViewGraphController();
        StudentTest rowData = reportsTableView.getSelectionModel().getSelectedItem();
        controller.setReport(rowData);
        ScreenManager.popUpScreen(PathConstants.viewGraphPath);

    }

    public void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.loginPath);
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
