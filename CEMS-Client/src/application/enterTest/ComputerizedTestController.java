package application.enterTest;

import Client.Client;
import entity.ActiveTest;
import entity.StudentCourse;
import entity.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.*;
import Client.ClientUI;
import Client.ExitButton;

import java.util.ArrayList;
import java.util.List;
/**
 * Controller for the "Computerized Test" screen.
 * Manages the validation of student ID, retrieval and display of Computerized test data.
 * After this the id validation the student can begin his computerized test
 *
 */

@SuppressWarnings("unchecked")
public class ComputerizedTestController {

    @FXML
    private Text CourseNameText;
    @FXML
    private TextField NumberText;

    @FXML
    private TextField StudentIDText;

    @FXML
    private TextArea TestComments;

    @FXML
    private TextField TestIdText;

    @FXML
    private TextField TimeRem;

    @FXML
    private Text fullNameText;
    @FXML
    private AnchorPane header;

    /**
     * Initializes the controller.
     * Enables dragging and dropping of the application window using the header pane.
     * Sets the full name of the user and initializes the data for the test.
     */
    public void initialize() {
        ScreenManager.dragAndDrop(header);
        fullNameText.setText(Client.user.getName());
        setData();
    }

    /**
     * Validates the student ID and checks if the student is assigned to the test.
     * If the ID is correct and the student is assigned to the test, the test starts automatically.
     * Otherwise, an error message is displayed.
     *
     * @param event The action event triggered by the approve ID button.
     */
    @FXML
    private void approveID(ActionEvent event) {
        if (StudentIDText.getText().isEmpty()) {
            ShowMessage.showErrorPopup("Please enter your ID");
            return;
        }
        ArrayList<String> StudentAndCourse = new ArrayList<>();
        StudentAndCourse.add(StudentIDText.getText());
        StudentAndCourse.add(CourseNameText.getText());
        MsgHandler checkStudentRegistered = new MsgHandler(TypeMsg.CheckStudentRegisteredCourse, StudentAndCourse);
        ClientUI.chat.accept(checkStudentRegistered);
        StudentCourse verifyStudent = (StudentCourse) ClientUI.chat.getUserAndCourse();
        if (!(StudentIDText.getText().equals(Client.user.getId()))) {
            ShowMessage.showInfoPopup("Incorrect ID\nPlease try again");
        } else if (verifyStudent != null) {
            ShowMessage.showInfoPopup("Student is assigned to the test \nAfter clicking OK the test will start automatically");
            ScreenManager.goToNewScreen(event, PathConstants.StartComputerizedTestPath);
        } else {
            ShowMessage.showInfoPopup("Student is not assigned to the test! \nPlease try again");
        }
    }

    /**
     * Retrieves the test data based on the test ID.
     *
     * @return The Test object containing the test data.
     */
    private Test getTestData() {
        MsgHandler getTestInformation = new MsgHandler(TypeMsg.GetTestByID, EnterCodePopUpController.testID);
        ClientUI.chat.accept(getTestInformation);
        return (Test) ClientUI.chat.getSingleTest();
    }

    /**
     * Sets the data for the test.
     * Retrieves the test data and populates the corresponding fields in the UI.
     */
    private void setData() {
        Test test = getTestData();
        TestIdText.setText(test.getId());
        TestIdText.setDisable(true);
        TestComments.setText(test.getStudentComments().equals("null") ? "" : test.getStudentComments());
        TestComments.setDisable(true);
        CourseNameText.setText(test.getCourseName());
        startTimer(test);
        MsgHandler getActiveTestTable = new MsgHandler(TypeMsg.GetActiveTests, null);
        ClientUI.chat.accept(getActiveTestTable);
        ObservableList<ActiveTest> activeTests = FXCollections.observableArrayList((List) ClientUI.chat.getActiveTests());
        for (ActiveTest activeTest : activeTests) {
            if (activeTest.getId().equals(test.getId())) {
                int numOfQuestions = activeTest.getNumOfQuestions();
                NumberText.setText(String.valueOf(numOfQuestions));
                NumberText.setDisable(true);
            }
        }
    }

    /**
     * Logs out the user and navigates back to the main menu.
     *
     * @param event The action event triggered by the Log Out button.
     */
    public void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuStudentPath);
    }

    /**
     * Closes the client application.
     */
    public void closeClient() {
        ExitButton.closeClient();
    }

    /**
     * Minimizes the application window.
     *
     * @param event The action event triggered by the Minimize button.
     */
    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

    /**
     * Starts the countdown timer for the test.
     * Updates the remaining time display based on the test duration.
     *
     * @param test The Test object containing the test duration.
     */
    private void startTimer(Test test) {
        int remainingMinutes = Integer.parseInt(test.getTestDuration());
        int totalSeconds = remainingMinutes * 60;
        TimeRem.setText(formatTime(totalSeconds));
        TimeRem.setDisable(true);
    }

    /**
     * Formats the time in seconds to a string representation in the format HH:MM:SS.
     *
     * @param seconds The time in seconds to be formatted.
     * @return The formatted time string.
     */
    private String formatTime(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }

}
