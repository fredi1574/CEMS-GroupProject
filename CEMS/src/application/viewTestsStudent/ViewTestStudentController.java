package application.viewTestsStudent;

import application.viewTestsStudent.TestDetailsScreen.ViewTestDetailsController;
import client.Client;
import client.ClientUI;
import common.MsgHandler;
import common.TypeMsg;
import entity.ApprovalStatus;
import entity.StudentTest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.*;

import java.util.List;

public class ViewTestStudentController {

    public ObservableList<StudentTest> tests;

    @FXML
    private AnchorPane header;
    @FXML
    private Text usernameText;

    @FXML
    private TableView<StudentTest> testsTableView;

    @FXML
    private ComboBox<String> yearComboBox;
    @FXML
    private ComboBox<String> semesterComboBox;
    @FXML
    private ComboBox<String> courseComboBox;

    private FilteredList<StudentTest> filteredData;

    public void initialize() {
        ScreenManager.dragAndDrop(header);
        usernameText.setText(Client.user.getFullName());

        MsgHandler getTable = new MsgHandler(TypeMsg.GetStudentsTests, Client.user.getId());
        ClientUI.chat.accept(getTable);

        tests = FXCollections.observableArrayList((List) ClientUI.chat.getStudentTests());
        for (StudentTest test : tests) {
            if (test.getApproved().equals(ApprovalStatus.YES)) {
                approvedTests.add(test);
            }
        }

        buildTable(approvedTests);

        createCourseComboBox();
        bindDataToComboBoxes();

        filteredData = new FilteredList<>(approvedTests, b -> true);
        addFiltering();

        testsTableView.setItems(filteredData);
    }

    private void createCourseComboBox() {
        MsgHandler getCourse = new MsgHandler(TypeMsg.GetStudentCourses, Client.user.getId());
        ClientUI.chat.accept(getCourse);

        ObservableList<String> courseObjectsList = FXCollections.observableArrayList((List) ClientUI.chat.getCourses());
        courseComboBox.setItems(courseObjectsList);
    }

    public void bindDataToComboBoxes() {
        ObservableList<String> yearList = FXCollections.observableArrayList("2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015");
        ObservableList<String> semesterList = FXCollections.observableArrayList("A", "B", "C");

        // Binds the data into the correct dropdown lists
        yearComboBox.setItems(yearList);
        semesterComboBox.setItems(semesterList);
    }

    private void addFiltering() {
        yearComboBox.valueProperty().addListener((observable, oldValue, newValue) -> filterTable());
        semesterComboBox.valueProperty().addListener((observable, oldValue, newValue) -> filterTable());
        courseComboBox.valueProperty().addListener((observable, oldValue, newValue) -> filterTable());
    }

    private void filterTable() {
        String selectedYear = yearComboBox.getValue();
        String selectedSemester = semesterComboBox.getValue();
        String selectedCourse = courseComboBox.getValue();

        filteredData.setPredicate(item -> {
            boolean yearMatch = selectedYear == null || selectedYear.isEmpty() || item.getYear().equals(selectedYear);
            boolean semesterMatch = selectedSemester == null || selectedSemester.isEmpty() || item.getSemester().equals(selectedSemester);
            boolean courseMatch = selectedCourse == null || selectedCourse.isEmpty() || item.getCourse().equals(selectedCourse);

            return yearMatch && semesterMatch && courseMatch;
        });

        SortedList<StudentTest> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(testsTableView.comparatorProperty());
        testsTableView.setItems(sortedData);
    }

    public void buildTable(ObservableList<StudentTest> tests) {
        ObservableList<String> columns = FXCollections.observableArrayList();
        columns.addAll("Year", "Semester", "Session", "Course", "Score");

        TableManager.createTable(testsTableView, columns);
        TableManager.importData(testsTableView, tests);
        TableManager.addDoubleClickFunctionality(testsTableView, PathConstants.viewGraphPath, this::viewTest);

        double[] multipliers = {0.1, 0.1, 0.1, 0.594, 0.1};
        TableManager.resizeColumns(testsTableView, multipliers);
    }
    public void viewTest(String ignored) {
        StudentTest rowData = testsTableView.getSelectionModel().getSelectedItem();

        MsgHandler getTestDetails = new MsgHandler(TypeMsg.GetTestByID, rowData.getTestID());
        ClientUI.chat.accept(getTestDetails);

        ScreenElements<Stage, FXMLLoader> screenElements = ScreenManager.popUpScreen(PathConstants.viewStudentTestDetails);
        FXMLLoader loader = screenElements.getFXMLLoader();
        ViewTestDetailsController controller = loader.getController();

        controller.setTest(rowData);
        controller.setController((Stage) header.getScene().getWindow());
    }

    @FXML
    public void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.loginPath);
    }

    @FXML
    void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    public void goBackToPreviousScreen(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuPath);
    }

    @FXML
    void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}

