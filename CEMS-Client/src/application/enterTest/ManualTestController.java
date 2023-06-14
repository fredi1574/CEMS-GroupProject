package application.enterTest;


import Client.Client;
import Client.ClientUI;
import entity.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.*;
import Client.ExitButton;
import Client.LogOut;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ManualTestController {
    public static Timeline timerC;
    private static Test test;
    private static Thread timerThread;
    private static int totalSecondsRemaining;
    @FXML
    private static int[] seconds;
    @FXML
    private Text OneMinLeft;
    private static int TotalStudents;
    private static boolean testIsLockedManual;
    @FXML
    public Text durationChangeddText;
    StateManagement stateManagement = StateManagement.getInstance();
    ActiveTest testActive;
    File selectedFile;
    @FXML
    private AnchorPane header;
    @FXML
    private Text addtionalTimeForSubmitTEXT;
    private boolean isSubmit = true;
    private boolean isTimerRunning;
    @FXML
    private TextField EndTimeText;
    @FXML
    private TextField FileSubmissionsText;
    @FXML
    private TextField StartTimeText;
    @FXML
    private TextField SubmissionStatusText;
    @FXML
    private Label timerLabel;
    @FXML
    private Text fullNameText;
    private int remainingMinutes;
    private boolean ExtraOneMin;

    public void initialize() {

        // Enables dragging and dropping of the application window using the header pane
        ScreenManager.dragAndDrop(header);
        OneMinLeft.setVisible(false);
        fullNameText.setText(Client.user.getName());
        MsgHandler getActiveTestTable = new MsgHandler(TypeMsg.GetActiveTests, null);
        ClientUI.chat.accept(getActiveTestTable);
        ObservableList<ActiveTest> activeTests = FXCollections.observableArrayList((List) ClientUI.chat.getActiveTests());
        test = getTestData();
        MsgHandler getAllTestTable = new MsgHandler(TypeMsg.GetAllTestsTable, null);
        ClientUI.chat.accept(getAllTestTable);
        ObservableList<Test> allTests = FXCollections.observableArrayList((List) ClientUI.chat.getTests());
        MsgHandler totalStudentIncrease = new MsgHandler(TypeMsg.IcreaseStudentsEnteringTest, test.getId());
        ClientUI.chat.accept(totalStudentIncrease);
        for (ActiveTest activeTest : activeTests) {
            if (test.getId().equals(activeTest.getId())) {
                testActive = activeTest;
                StartTimeText.setText(testActive.getStartingTime());
                FinishedTime(test);
                break;
            }


        }
        if (test == null) {
            showError.showErrorPopup("No Test Now");
            return;
        }
        fetchTestDuration();

    }

    private Test getTestData() {
        MsgHandler getTestInformation = new MsgHandler(TypeMsg.GetTestByID, EnterCodePopUpController.testID);
        ClientUI.chat.accept(getTestInformation);
        Test test = (Test) ClientUI.chat.getSingleTest();
        return test;
    }

    private String formatTime(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }

    private void fetchTestDuration() {
        int testDurationMinutes = Integer.parseInt(test.getTestDuration());
        remainingMinutes = testDurationMinutes;
    }

    private void startTimer() {
        int totalSeconds = remainingMinutes * 60;
        seconds = new int[]{totalSeconds};  // Create a final array to hold the remaining seconds

        timerC = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    seconds[0]--;
                    if (seconds[0] == 59)
                    {
                        OneMinLeft.setVisible(true);
                    }// Decrement the remaining seconds
                    if ((seconds[0] <= 0) && (!ExtraOneMin)){
                        ExtraOneMin = true;
                        seconds[0] = 60;
                    }
                    else if (seconds[0] <= 0) {
                        // Timer has ended, perform necessary actions
                        timerC.stop();
                        Stage currentStage = (Stage) header.getScene().getWindow();
                        if (currentStage.isShowing()) {
                            if (checkLockTest()) {
                                saveAfterTestInfoAndDeleteFromActive();
                            }

                            Platform.runLater(() -> {

                                if (currentStage.isShowing()) {
                                    showError.showInfoPopup("Test is over");
                                    currentStage.close();
                                    ScreenManager.showStage(PathConstants.mainMenuStudentPath, PathConstants.iconPath);
                                }
                            });
                        }
                        // Additional logic...
                    } else {
                        // Update the timer display on the screen
                        timerLabel.setText(formatTime(seconds[0]));
                    }
                })
        );
        timerC.setCycleCount(Animation.INDEFINITE);
        timerC.play();

        // Update the timer label immediately
        timerLabel.setText(formatTime(seconds[0]));
    }

    public void lockTest() {
        timerC.stop();
        Platform.runLater(() -> showError.showInfoPopup("Test is locked\nPlease submit the test and exit"));
        testIsLockedManual = true;
    }

    // Method to set the isActive flag and stop the checkLockThread
    public void showNotificationAndChangeDuration(int newDuration) {
        int remainingSeconds = remainingMinutes * 60;  // Convert the remaining minutes to seconds
        seconds[0] += newDuration * 60;  // Add the new duration in seconds
        Platform.runLater(() -> showError.showInfoPopup("Test time increased by " + newDuration + " minutes"));
    }

    void FinishedTime(Test test) {
        int hour = Integer.parseInt(StartTimeText.getText().substring(0, 2));
        int min = Integer.parseInt(StartTimeText.getText().substring(3, 5));
        int durationTime = Integer.parseInt(test.getTestDuration());

        int totalMinutes = min + durationTime;
        hour += totalMinutes / 60;
        min = totalMinutes % 60;

        EndTimeText.setText(String.format("%02d:%02d", hour, min));

    }

    public void saveStudentsTest(int score, int correctAnswers, int totalQuestions) {
        int timeInSeconds = seconds[0];
        int testDuration = (Integer.parseInt(test.getTestDuration()) * 60) - timeInSeconds;
        String decimalFormat = formatTime(testDuration);
        StudentTest StudentsCopy = new StudentTest(Client.user.getId(), test.getId(), test.getSubjectID(), test.getCourseName(), Integer.toString(score),
                Client.user.getFullName(), test.getYear(), test.getSemester(), test.getSession(), CheatingSuspicion.NO, Integer.toString(correctAnswers),
                Integer.toString(totalQuestions), "", ApprovalStatus.YES, test.getTestType(), decimalFormat);
        MsgHandler AddNewTest = new MsgHandler(TypeMsg.AddNewTestOfStudent, StudentsCopy);
        ClientUI.chat.accept(AddNewTest);
    }


    @FXML
    public void uploadFileBTN(ActionEvent event) {
        boolean notUpload = true;
        if (notUpload) {
            FileChooser fc = new FileChooser();
            selectedFile = fc.showOpenDialog(null);
            if (selectedFile != null) {
                FileSubmissionsText.setText(selectedFile.getName());
                SubmissionStatusText.setText("The solve is submitedd file : " + selectedFile.getName());
            }
        } else {

            showError.showErrorPopup("Can not do upload now the time has finished");
        }
    }

    @FXML
    public void DownloadFileBTN(ActionEvent event) {
        try {
            FileChooser fc = new FileChooser();
            fc.setTitle("Download File");
            fc.setInitialFileName(test.getId());
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Word Files", "*.docx"));
            File downloadedFile = fc.showSaveDialog(null);// UserController.currentStage
            System.out.println("Downloaded");
            startTimer();
            if (downloadedFile == null)
                return;
            File ManualTest = new File(downloadedFile.getAbsolutePath());
            FileOutputStream fos = new FileOutputStream(ManualTest);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            bos.flush();
            fos.flush();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public int CalculateTotalForcedFinished() {

        MsgHandler numberOfFinished = new MsgHandler(TypeMsg.CountNumberOfFinished, test.getId());
        ClientUI.chat.accept(numberOfFinished);
        int NumberOfFinishedCounter = (int) (ClientUI.chat.getNumberOfFinished());
        int TotalForcedFinished = TotalStudents - NumberOfFinishedCounter;
        return TotalForcedFinished;


    }

    public boolean checkLockTest() {
        MsgHandler numberOfRegistered = new MsgHandler(TypeMsg.CountRegisteredStudents, test.getCourseName());
        ClientUI.chat.accept(numberOfRegistered);
        int NumberOfRegisteredCounter = (int) (ClientUI.chat.getNumberOfRegistered());

        MsgHandler totalStudentAttended = new MsgHandler(TypeMsg.NumberOfAttendedCounter, test.getId());
        ClientUI.chat.accept(totalStudentAttended);
        int NumberOfAttendedCounter = (int) (ClientUI.chat.getNumberOfAttended());
        if (NumberOfRegisteredCounter - NumberOfAttendedCounter == 0) {
            TotalStudents = NumberOfAttendedCounter;
            return true;

        }
        return false;
    }

    public void saveAfterTestInfoAndDeleteFromActive() {
        int totalForcedFinished;
        if (testIsLockedManual) {
            MsgHandler totalStudentAttended = new MsgHandler(TypeMsg.NumberOfAttendedCounter, test.getId());
            ClientUI.chat.accept(totalStudentAttended);
            int NumberOfAttendedCounter = (int) (ClientUI.chat.getNumberOfAttended());
            MsgHandler numberOfFinished = new MsgHandler(TypeMsg.CountNumberOfFinished, test.getId());
            ClientUI.chat.accept(numberOfFinished);
            int NumberOfFinishedCounter = (int) (ClientUI.chat.getNumberOfFinished());
            totalForcedFinished = NumberOfAttendedCounter - NumberOfFinishedCounter;
        } else {
            totalForcedFinished = CalculateTotalForcedFinished();
        }
        String[] afterTestInfo = {test.getTestDuration(), String.valueOf(totalForcedFinished), test.getId()};
        MsgHandler addAfterTestInfo = new MsgHandler(TypeMsg.FinishAfterTestInfo, afterTestInfo);
        ClientUI.chat.accept(addAfterTestInfo);
        MsgHandler deleteFromActive = new MsgHandler(TypeMsg.UnActivateTest, test.getId());
        ClientUI.chat.accept(deleteFromActive);

    }

    @FXML
    public void LogOut(ActionEvent event) {
        LogOut.logOutToLoginScreen(event);
    }

    @FXML
    private void closeClient() {
        ExitButton.closeClient();
    }

    @FXML
    void onSaveButton(ActionEvent event) {
        boolean forText = true;
        if (forText) {
            if (FileSubmissionsText.getText().isEmpty()) {
                if (showError.showConfirmationPopup("Are you sure want to submit an empty test?")) {
                    StateManagement.resetInstance();
                    if (!testIsLockedManual) {
                        MsgHandler finshedStudentsIncrease = new MsgHandler(TypeMsg.IcreaseStudentsFinishedTest, test.getId());
                        ClientUI.chat.accept(finshedStudentsIncrease);
                    }

                    if (checkLockTest() || (testIsLockedManual)) {
                        saveAfterTestInfoAndDeleteFromActive();
                    }

                    saveStudentsTest(0, 0, 0);
                    testIsLockedManual = false;
                    ScreenManager.goToNewScreen(event, PathConstants.mainMenuStudentPath);
                } else {
                    return;
                }

            }
            else {
                if (showError.showConfirmationPopup("Are you sure want to save your test")) {
                    StateManagement.resetInstance();

                    if (!testIsLockedManual) {
                        MsgHandler finshedStudentsIncrease = new MsgHandler(TypeMsg.IcreaseStudentsFinishedTest, test.getId());
                        ClientUI.chat.accept(finshedStudentsIncrease);
                    }

                    if ((checkLockTest() || (testIsLockedManual))) {
                        saveAfterTestInfoAndDeleteFromActive();
                    }

                    saveStudentsTest(100, 5, 5);
                    testIsLockedManual = false;
                    ScreenManager.goToNewScreen(event, PathConstants.mainMenuStudentPath);
                } else
                    return;
            }

        } else {
            showError.showInfoPopup("Test is over");
            if ((checkLockTest() || (testIsLockedManual))) {
                saveAfterTestInfoAndDeleteFromActive();
            }
            saveStudentsTest(75, 3, 5);
            testIsLockedManual = false;
            ScreenManager.goToNewScreen(event, PathConstants.mainMenuStudentPath);
        }
    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

}


