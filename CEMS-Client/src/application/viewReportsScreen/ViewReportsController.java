package application.viewReportsScreen;

import Client.Client;
import application.viewReportsScreen.graphScreen.ViewGraphController;
import entity.ApprovalStatus;
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
import util.*;
import Client.ClientUI;
import Client.ExitButton;
import Client.LogOut;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * Controller class for the View Reports screen for lecturer's reports about his tests.
 * Displays a table of approved test.
 * The lecturer can open a test and receive a full report about it
 */
public class ViewReportsController {

    private static ObservableList<StudentTest> listOfTestsNoDuplicates;
    private static ObservableList<StudentTest> fullListOfTests;
    @FXML
    private AnchorPane header;
    @FXML
    private ComboBox<String> yearComboBox;
    @FXML
    private ComboBox<String> semesterComboBox;
    @FXML
    private TableView<StudentTest> reportsTableView;
    @FXML
    private Text usernameText;
    private FilteredList<StudentTest> filteredData;

    /**
     * Initializes the View Reports screen.
     * Sets up the UI components, binds the data, and initializes the table.
     * Also sets event listeners for filtering the table based on year and semester selections.
     */
    @FXML
    public void initialize() {
        ScreenManager.dragAndDrop(header);
        usernameText.setText(Client.user.getName());
        MsgHandler getTable = new MsgHandler(TypeMsg.GetTestsByLecturerForLecturerReport, Client.user.getFullName());
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
        TableManager.addDoubleClickFunctionality(reportsTableView, PathConstants.viewGraphPath, this::setFunctions);
        double[] multipliers = {0.07, 0.1, 0.2, 0.525, 0.1};
        TableManager.resizeColumns(reportsTableView, multipliers);

        yearComboBox.valueProperty().addListener((observable, oldValue, newValue) -> filterTable());
        semesterComboBox.valueProperty().addListener((observable, oldValue, newValue) -> filterTable());
        filteredData = new FilteredList<>(listOfTestsNoDuplicates, b -> true);
        reportsTableView.setItems(filteredData);


    }
    /**
     * Retrieves the list of all student tests.
     *
     * @return The observable list of student tests.
     */
    public ObservableList<StudentTest> getListOfTests() {
        return fullListOfTests;
    }
    /**
     * Filters the table based on the selected year and semester.
     * This method is called when the user selects a different year or semester from the combo boxes.
     * It filters the table data based on the selected values and updates the table view accordingly.
     */
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

    /**
     * Sets the functions for the ViewGraphController and shows the report.
     *
     * @param ignored The ignored parameter.
     * This method is called when the user double-clicks on a table row.
     * It retrieves the selected student test from the table and passes it to the ViewGraphController
     * to display the corresponding report in a pop-up window.
     */
    public void setFunctions(String ignored) {
        ViewGraphController controller = new ViewGraphController();
        StudentTest rowData = reportsTableView.getSelectionModel().getSelectedItem();
        controller.setReport(rowData);
        showReport();
    }
    /**
     * Returns to the previous screen.
     *
     * @param event The action event triggered by the user.
     * This method is called when the user clicks the "Go Back" button.
     * It navigates back to the previous screen by loading the main menu view.
     */

    public void goBackToPreviousScreen(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuPath);
    }
    /**
     * Sets the table with the provided student tests.
     *
     * @param Tests The list of student tests.
     * This method receives a list of student tests and filters out duplicate tests in order to show single report of each ID
     * We save the duplicates list in order to use in the graph screen
     * It populates the table view with the unique tests and stores the full list of approved tests.
     */
    public void setTestsTable(ArrayList<Object> Tests) {
        Set<String> uniqueTestIDs = new HashSet<>();
        List<Object> uniqueTests = new ArrayList<>();
        List<Object> approvedTest = new ArrayList<>();
        for (Object test : Tests) {
            String testID = ((StudentTest) test).getTestID();
            if (!uniqueTestIDs.contains(testID)) {
                if ((((StudentTest) test).getApproved() == ApprovalStatus.YES)) {
                    uniqueTestIDs.add(testID);
                    uniqueTests.add(test);

                }

            }
            if ((((StudentTest) test).getApproved() == ApprovalStatus.YES)) {//get rid of duplicate if
                approvedTest.add(test);
            }
        }

        listOfTestsNoDuplicates = FXCollections.observableArrayList((List) uniqueTests);
        fullListOfTests = FXCollections.observableArrayList((List) approvedTest);
    }
    /**
     * Shows the detailed report in a separate graph screen.
     * This method is called when the user clicks the "Show Report" button.
     * It retrieves the selected student test from the table and passes it to the ViewGraphController
     * to display the corresponding report in a pop-up window.
     */
    @FXML
    public void showReport() {
        ViewGraphController controller = new ViewGraphController();
        StudentTest rowData = reportsTableView.getSelectionModel().getSelectedItem();
        if (rowData != null) {
            controller.setReport(rowData);
            ScreenManager.popUpScreen(PathConstants.viewGraphPath);
        }
    }
    /**
     * Logs out the user and redirects to the login screen.
     *
     * @param event The action event triggered by the user.
     * This method is called when the user clicks the "Logout" button.
     * It logs out the current user and redirects to the login screen.
     */

    public void LogOut(ActionEvent event) {
        LogOut.logOutToLoginScreen(event);
    }
    /**
     * Closes the client application.
     * This method is called when the user clicks the close button on the window.
     * It terminates the client application.
     */
    @FXML
    public void closeClient() {
        ExitButton.closeClient();
    }
    /**
     * Minimizes the client application window.
     *
     * @param event The action event triggered by the user.
     * This method is called when the user clicks the minimize button on the window.
     * It minimizes the client application window to the taskbar.
     */
    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

}
