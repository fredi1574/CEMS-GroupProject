package application.viewTestsStudent;

import Client.Client;
import application.viewTestsStudent.TestDetailsScreen.ViewTestDetailsController;
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
import Client.ClientUI;
import Client.ExitButton;
import Client.LogOut;

import java.util.List;
/**
 * Controller for the View Test Student screen.
 * This screen allows students to view their approved tests and apply filtering based on year, semester, and course.
 */
public class ViewTestStudentController {

    public ObservableList<StudentTest> tests;
    public final ObservableList<StudentTest> approvedTests = FXCollections.observableArrayList();

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
    /**
     * Initializes the controller.
     * This method is automatically called after the FXML file has been loaded.
     * It initializes the UI components, retrieves data from the server, and sets up filtering for the table.
     */
    public void initialize() {
        ScreenManager.dragAndDrop(header);
        usernameText.setText(Client.user.getName());

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
    /**
     * Creates the course combo box and populates it with the available courses for the student.
     */
    private void createCourseComboBox() {
        MsgHandler getCourse = new MsgHandler(TypeMsg.GetStudentCourses, Client.user.getId());
        ClientUI.chat.accept(getCourse);

        ObservableList<String> courseObjectsList = FXCollections.observableArrayList((List) ClientUI.chat.getCourses());
        courseComboBox.setItems(courseObjectsList);
    }

    /**
     * Binds data to the year and semester combo boxes.
     */

    public void bindDataToComboBoxes() {
        ObservableList<String> yearList = FXCollections.observableArrayList("2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015");
        ObservableList<String> semesterList = FXCollections.observableArrayList("A", "B", "C");

        // Binds the data into the correct dropdown lists
        yearComboBox.setItems(yearList);
        semesterComboBox.setItems(semesterList);
    }
    /**
     * Adds filtering functionality to the combo boxes.
     */
    private void addFiltering() {
        yearComboBox.valueProperty().addListener((observable, oldValue, newValue) -> filterTable());
        semesterComboBox.valueProperty().addListener((observable, oldValue, newValue) -> filterTable());
        courseComboBox.valueProperty().addListener((observable, oldValue, newValue) -> filterTable());
    }

    /**
     * Filters the table based on the selected criteria.
     */
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
    /**
     * Builds the table with the provided student tests.
     *
     * @param tests The list of student tests to display in the table.
     */
    public void buildTable(ObservableList<StudentTest> tests) {
        ObservableList<String> columns = FXCollections.observableArrayList();
        columns.addAll("Year", "Semester", "Session", "Course", "Score");

        TableManager.createTable(testsTableView, columns);
        TableManager.importData(testsTableView, tests);
        TableManager.addDoubleClickFunctionality(testsTableView, PathConstants.viewGraphPath, this::viewTest);

        double[] multipliers = {0.1, 0.1, 0.1, 0.594, 0.1};
        TableManager.resizeColumns(testsTableView, multipliers);
    }
    /**
     * Opens the details view for the selected test.
     *
     * @param ignored The parameter is ignored.
     */
    public void viewTest(String ignored) {
        StudentTest rowData = testsTableView.getSelectionModel().getSelectedItem();

        MsgHandler getTestDetails = new MsgHandler(TypeMsg.GetTestByID, rowData.getTestID());
        ClientUI.chat.accept(getTestDetails);

        ScreenElements<Stage, FXMLLoader> screenElements = ScreenManager.popUpScreen(PathConstants.viewStudentTestDetails);
        FXMLLoader loader = screenElements.getFXMLLoader();
        ViewTestDetailsController controller = loader.getController();

        controller.setTest(rowData);
    }
    /**
     * Logs out the user and navigates to the login screen.
     *
     * @param event The action event triggered by the logout button.
     */
    @FXML
    public void LogOut(ActionEvent event) {
        LogOut.logOutToLoginScreen(event);
    }
    /**
     * Closes the client application.
     * This method is called when the user clicks the close button.
     */
    @FXML
    void closeClient() {
        ExitButton.closeClient();
    }
    /**
     * Navigates to the previous screen.
     *
     * @param event The action event triggered by the back button.
     */
    @FXML
    public void goBackToPreviousScreen(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuStudentPath);
    }
    /**
     * Minimizes the window.
     *
     * @param event The action event triggered by the minimize button.
     */
    @FXML
    void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}

