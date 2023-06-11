package application.manageTestsScreen;

import application.Simulation.SmsEmailPopUpController;
import client.Client;
import client.ClientUI;
import common.MsgHandler;
import common.TypeMsg;
import entity.ActiveTest;
import entity.StateManagement;
import entity.TestRequestForApproval;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import util.*;
import javafx.animation.Timeline;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class viewActiveTestController {
    @FXML
    private Pane requestDeclinedPane;

    @FXML
    private Label requestPendingText;
    @FXML
    private Pane requestApprovedPane;

    @FXML
    private static Button extraTimeBtn;
    @FXML
    public TextField testIdTextField;
    public Label courseNameLabel;
    public Label subjecteNameLabel;
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

    public Button unlockBtn;
    public Label unlockTestLabel;
    @FXML
    private AnchorPane header;

    private Timeline timer;
    private int testDurationMinutes;
    private int remainingSeconds;
    static boolean requestSent;

    public StateManagement stateManagement;
    public SmsEmailPopUpController smsEmailPopUpController = new SmsEmailPopUpController();

    public void initialize() {
        ScreenManager.dragAndDrop(header);
        stateManagement = StateManagement.getInstance();

        //sets the relevant field values
        testIdTextField.setText(stateManagement.getTestID());
        courseNameLabel.setText(stateManagement.course.getCourseName());
        subjecteNameLabel.setText(stateManagement.course.getSubjectName());
        testCodeTextField.setText(stateManagement.getTestCode());
        numOfQuestionsTextField.setText(String.valueOf(stateManagement.getCurrentActivetest().getNumOfQuestions()));
        testDateTextField.setText(stateManagement.getCurrentActivetest().getTestDate());
        testDurationTextField.setText(stateManagement.getTestDuration());
        startingTimeTextField.setText(stateManagement.getCurrentActivetest().getStartingTime());
    }

    /**
     * calculates the remaining time for the test based on the test's start time and duration
     */
    private void updateRemainingTestTime() {
        int testDurationInSeconds = Integer.parseInt(stateManagement.getTestDuration()) * 60;

        // Calculate the remaining seconds based on the current time and the test's starting time
        LocalTime startTime = LocalTime.parse(stateManagement.getCurrentActivetest().getStartingTime());
        LocalTime endTime = startTime.plus(testDurationInSeconds, ChronoUnit.SECONDS);
        LocalTime currentTime = LocalTime.now();
        remainingSeconds = (int) ChronoUnit.SECONDS.between(currentTime, endTime);
        if (remainingSeconds < 0) {
            remainingSeconds = 0;
        }
    }

    /**
     * updates the timer object to correctly display a test's remaining time
     */
    private void startTimer() {
        int totalSeconds = remainingSeconds;
        final int[] seconds = {totalSeconds};  // Create a final array to hold the remaining seconds

        timer = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    seconds[0]--;  // Decrement the remaining seconds
                    if (seconds[0] <= 0) {
                        // Timer has ended, perform necessary actions
                        timer.stop();
                        // Additional logic...
                    } else {
                        // Update the timer display on the screen
                        timeLeftLabel.setText(formatTime(seconds[0]));
                    }
                })
        );
        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();

        // Update the timer label immediately
        timeLeftLabel.setText(formatTime(seconds[0]));
    }

    /**
     * formats the time  as a hh:mm:ss string
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
     * locks the test for all clients - the timer is stopped and the timeLeft value
     * in the activeTest table in the DB is updated
     * @param actionEvent the event that triggered the method
     */

    /**
     * locks the test for all clients - the timer is stopped and the timeLeft value
     * in the activeTest table in the DB is updated
     *
     * @param actionEvent the event that triggered the method
     */
    public void lockTest(ActionEvent actionEvent) {
        //switches the visible buttons
        if(showError.showConfirmationPopup("Are you sure you want to lock the test?\nPlease notice that test will become inactive")){
            MsgHandler lockTest = new MsgHandler(TypeMsg.LecturerCllickedLockTest, null);
            ClientUI.chat.accept(lockTest);
            back(actionEvent);
        }


    }

        public void showRequestDeclinedPopUp () {
            Platform.runLater(() -> {
                smsEmailPopUpController.SetInfoField("Time change request was declined",Client.user.getFullName(),Client.user.getEmail());
                ScreenManager.popUpScreen(PathConstants.SmsEmailPopUp);
            });
        }

        public void showRequestApprovedPopUp () {
            Platform.runLater(() -> {
                smsEmailPopUpController.SetInfoField("Time change request was approved",Client.user.getFullName(),Client.user.getEmail());
                ScreenManager.popUpScreen(PathConstants.SmsEmailPopUp);
            });
        }

        @FXML
        public void sendExtraTimeRequest (ActionEvent actionEvent){
            TestRequestForApproval request = new TestRequestForApproval(testIdTextField.getText(), stateManagement.course.getSubjectID(),
                    courseNameLabel.getText(), extraTimeTextField.getText(), testCommentsTextArea.getText(), Client.user.getFullName());
            MsgHandler newRequest = new MsgHandler(TypeMsg.RequestExtraTime, request);
            ClientUI.chat.accept(newRequest);
            showError.showInfoPopup("Request was sent to the Head Of Department");
            //extraTimeBtn.setDisable(true);
            requestSent = true;


        }

        public void LogOut (ActionEvent event){
            ScreenManager.goToNewScreen(event, PathConstants.loginPath);
        }

        public void back (ActionEvent event){
            StateManagement.resetInstance();
            ScreenManager.goToNewScreen(event,PathConstants.manageTestsPath);
        //ExitButton.closePopUp(event);
        }

        @FXML
        private void closeClient (ActionEvent event){
            ExitButton.closePopUp(event);
        }

        @FXML
        public void minimizeWindow (ActionEvent event){
            MinimizeButton.minimizeWindow(event);
        }


    }
