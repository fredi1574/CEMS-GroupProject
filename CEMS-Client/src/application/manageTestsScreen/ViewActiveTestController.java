package application.manageTestsScreen;

import Client.Client;
import Client.ClientUI;
import Client.ExitButton;
import Client.LogOut;
import application.Simulation.SmsEmailPopUpController;
import entity.TestRequestForApproval;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import util.*;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * Handles the functionality of the active test screen
 * Displays information about active test
 * In this screen the lecturer can lock a test and send a time extension request to the Head of Department
 */
public class ViewActiveTestController {
    static boolean requestSent;
    @FXML
    private static Button extraTimeBtn;
    public final SmsEmailPopUpController smsEmailPopUpController = new SmsEmailPopUpController();
    @FXML
    public TextField testIdTextField;
    public Label courseNameLabel;
    public Label subjectNameLabel;
    public TextField numOfQuestionsTextField;
    public TextField testCodeTextField;
    public Label timeLeftLabel;
    public TextArea testCommentsTextArea;
    public TextField testDateTextField;
    public TextField testDurationTextField;
    public TextField startingTimeTextField;
    public TextField extraTimeTextField;
    public Button lockBtn;
    public Label lockTestLabel;
    public StateManagement stateManagement;
    @FXML
    private AnchorPane header;
    private Timeline timer;
    private int remainingSeconds;

    /**
     * Initializes the UI components and sets the relevant field values based on the state management data.
     * It sets up the drag-and-drop functionality for the header,
     * retrieves the state management instance,
     * and populates the text fields and labels with the corresponding data.
     */
    public void initialize() {
        ScreenManager.dragAndDrop(header);
        stateManagement = StateManagement.getInstance();

        //sets the relevant field values
        testIdTextField.setText(stateManagement.getTestID());
        courseNameLabel.setText(stateManagement.getCourse().getCourseName());
        subjectNameLabel.setText(stateManagement.getCourse().getSubjectName());
        testCodeTextField.setText(stateManagement.getCurrentActivetest().getTestCode());
        numOfQuestionsTextField.setText(String.valueOf(stateManagement.getCurrentActivetest().getNumOfQuestions()));
        testDateTextField.setText(stateManagement.getCurrentActivetest().getTestDate());
        testDurationTextField.setText(stateManagement.getTestDuration());
        startingTimeTextField.setText(stateManagement.getCurrentActivetest().getStartingTime());
    }

    /**
     * formats the time as a hh:mm:ss string
     *
     * @param seconds time in seconds
     * @return the formatted string
     */
    private String formatTime(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }

    /**
     * locks the test for all clients - the timer is stopped, and the timeLeft value
     * in the activeTest table in the DB is updated
     *
     * @param actionEvent the event that triggered the method
     */
    public void lockTest(ActionEvent actionEvent) {
        //switches the visible buttons
        if (showError.showConfirmationPopup("Are you sure you want to lock the test?\nPlease notice that test will become inactive")) {
            MsgHandler lockTest = new MsgHandler(TypeMsg.LecturerClickedLockTest, testIdTextField.getText());
            ClientUI.chat.accept(lockTest);
            back(actionEvent);
        }


    }

    /**
     * Displays a pop-up indicating that a time change request has been declined.
     * It sets the appropriate information in the pop-up controller and shows the pop-up screen.
     */
    public void showRequestDeclinedPopUp() {
        Platform.runLater(() -> {
            smsEmailPopUpController.SetInfoField("Time change request was declined", Client.user.getFullName(), Client.user.getEmail(),
                    Client.user.getPhone());
            ScreenManager.popUpScreen(PathConstants.SmsEmailPopUp);
        });
    }

    /**
     * Displays a pop-up indicating that a time change request has been approved.
     * It sets the appropriate information in the pop-up controller and shows the pop-up screen.
     */
    public void showRequestApprovedPopUp() {
        Platform.runLater(() -> {
            smsEmailPopUpController.SetInfoField("Time change request was approved", Client.user.getFullName(), Client.user.getEmail(),
                    Client.user.getPhone());
            ScreenManager.popUpScreen(PathConstants.SmsEmailPopUp);
        });
    }

    /**
     * Sends a request for extra time for a test.
     * It creates a TestRequestForApproval object with the necessary information,
     * sends the request to the server, and displays a notification to the user.
     */
    @FXML
    public void sendExtraTimeRequest() {
        TestRequestForApproval request = new TestRequestForApproval(testIdTextField.getText(), stateManagement.course.getSubjectID(),
                courseNameLabel.getText(), extraTimeTextField.getText(), testCommentsTextArea.getText(), Client.user.getFullName());
        MsgHandler newRequest = new MsgHandler(TypeMsg.RequestExtraTime, request);
        ClientUI.chat.accept(newRequest);
        showError.showInfoPopup("Request was sent to the Head Of Department");
        //extraTimeBtn.setDisable(true);
        requestSent = true;
    }

    /**
     * Logs out the current user and navigates back to the login screen.
     *
     * @param event The action event triggered by the "Logout" button.
     */
    public void LogOut(ActionEvent event) {
        LogOut.logOutToLoginScreen(event);
    }

    /**
     * Navigates back to the manage tests screen.
     * It resets the state management instance and navigates to the specified screen.
     *
     * @param event The action event triggered by the "Back" button.
     */
    public void back(ActionEvent event) {
        StateManagement.resetInstance();
        ScreenManager.goToNewScreen(event, PathConstants.manageTestsPath);
        //ExitButton.closePopUp(event);
    }
    /**
     * Minimizes the client application window.
     *
     * @param event The action event triggered by the "Minimize" button.
     */
    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }


}
