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
import javafx.scene.control.Button;
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
/**
 * The ManualTestController class controls the manual test interface for a student.
 * Once the student downloaded the test file, the counter begins.
 * The student need to submit the test in order to save data about his test's duration
 */
@SuppressWarnings("unchecked")
public class ManualTestController {
    public static Timeline timerC;
    private static Test test;
    @FXML
    private static int[] seconds;
    @FXML
    private Text OneMinLeft;
    private static int TotalStudents;
    private static boolean testIsLockedManual;
    @FXML
    private Button downloadBtn;

    StateManagement stateManagement = StateManagement.getInstance();
    ActiveTest testActive;
    File selectedFile;
    @FXML
    private AnchorPane header;

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
    /**
     * Initializes the controller and sets up the initial state of the UI components.
     * This method is automatically called by JavaFX after the FXML file has been loaded.
     */
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
    /**
     * Retrieves the test data for the current test.
     *
     * @return The Test object containing the test data.
     */

    private Test getTestData() {
        MsgHandler getTestInformation = new MsgHandler(TypeMsg.GetTestByID, EnterCodePopUpController.testID);
        ClientUI.chat.accept(getTestInformation);
        return (Test) ClientUI.chat.getSingleTest();
    }

    private String formatTime(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }
    /**
     * Fetches the test duration from the test data and initializes the remaining minutes variable.
     */

    private void fetchTestDuration() {
        remainingMinutes = Integer.parseInt(test.getTestDuration());
    }
    /**
     * Starts the timer for the test.
     * The timer counts down the remaining time in seconds and updates the timer display on the screen.
     * If the timer reaches 0, it stops and performs necessary actions such as saving final answers and handling test completion.
     */
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
                    else if (seconds[0] <= 0 || (testIsLockedManual)) {
                        // Timer has ended, perform necessary actions
                        timerC.stop();
                        Stage currentStage = (Stage) header.getScene().getWindow();
                        if (currentStage.isShowing()) {
                            if ((checkLockTest() || (testIsLockedManual))) {
                                saveAfterTestInfoAndDeleteFromActive();
                            }

                            Platform.runLater(() -> {
                                if (currentStage.isShowing()) {
                                    showError.showInfoPopup("Test is over");
                                    saveStudentsTest(0, 0, 5);
                                    testIsLockedManual = false;
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
    /**
     * Locks the test, preventing further interactions by stopping the timer and displaying a notification to the student.
     * The {@code testIsLockedComputerized} flag is set to indicate that the test is locked.
     * The student is informed that the test was locked by the lecturer and is advised to press the submit button to exit the test.
     */

    public void lockTest() {
        timerC.stop();
        Platform.runLater(() -> showError.showInfoPopup("Test is locked\nPlease submit the test and exit"));
        testIsLockedManual = true;
    }

    /**
     * Shows a notification about time extension to the student and updates the test duration.
     *
     * @param newDuration The new duration to be added to the test duration, in minutes.
     */
    public void showNotificationAndChangeDuration(int newDuration) {
        seconds[0] += newDuration * 60;  // Add the new duration in seconds
        Platform.runLater(() -> showError.showInfoPopup("Test time increased by " + newDuration + " minutes"));
    }
    /**
     * Calculates the time the test is due to finish according to it's duration.
     *
     * @param test The test details.
     */
    void FinishedTime(Test test) {
        int hour = Integer.parseInt(StartTimeText.getText().substring(0, 2));
        int min = Integer.parseInt(StartTimeText.getText().substring(3, 5));
        int durationTime = Integer.parseInt(test.getTestDuration());

        int totalMinutes = min + durationTime;
        hour += totalMinutes / 60;
        min = totalMinutes % 60;

        EndTimeText.setText(String.format("%02d:%02d", hour, min));

    }
    /**
     * Saves the student's test results and information.
     *
     * @param score           The score achieved by the student in the test.
     * @param correctAnswers  The number of correct answers given by the student.
     * @param totalQuestions  The total number of questions in the test.
     */

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
    /**
     * Uploading student's test
     */

    @FXML
    public void uploadFileBTN() {
        boolean notUpload = true;
        if (notUpload) {
            FileChooser fc = new FileChooser();
            selectedFile = fc.showOpenDialog(null);
            if (selectedFile != null) {
                FileSubmissionsText.setText(selectedFile.getName());
                SubmissionStatusText.setText("The solve is submitted file : " + selectedFile.getName());
            }
        } else {

            showError.showErrorPopup("Can not upload now, the time has finished");
        }
    }
    /**
     * Downloading student's test
     */
    @FXML
    public void DownloadFileBTN() {
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
            downloadBtn.setDisable(true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Calculates the total number of students who have not finished the test forcefully.
     * Retrieves the test data and the number of finished students from the server.
     * Subtracts the number of finished students from the total number of students who attended the test
     * and returns the remaining count, indicating the total number of students who have not finished the test forcefully.
     *
     * @return The total number of students who have not finished the test forcefully.
     */

    public int CalculateTotalForcedFinished() {

        MsgHandler numberOfFinished = new MsgHandler(TypeMsg.CountNumberOfFinished, test.getId());
        ClientUI.chat.accept(numberOfFinished);
        int NumberOfFinishedCounter = (int) (ClientUI.chat.getNumberOfFinished());
        return TotalStudents - NumberOfFinishedCounter;


    }
    /**
     * Checks if the test is locked based on the number of registered and attended students.
     * Retrieves the test data and the number of registered and attended students from the server.
     * If the difference between the number of registered and attended students is zero,
     * sets the TotalStudents variable to the number of attended students and returns true,
     * indicating that the test is locked.
     * Otherwise, returns false.
     *
     * @return true if the test is locked, false otherwise.
     */


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
    /**
     * Saves the after-test information and deactivates the test.
     * Retrieves the test data and calculates the total number of students who have not finished the test forcefully.
     * If the test is computerized and locked, the attended and finished student counters are obtained from the server.
     * Otherwise, the total number of forced finished students is calculated using the CalculateTotalForcedFinished() method.
     * The after-test information, including the test duration, total forced finished count, and test ID, is then sent to the server.
     * Finally, the test is deactivated and removed from the active tests list.
     */


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
        Test updatedTest = getTestData();
        String[] afterTestInfo = {updatedTest.getTestDuration(), String.valueOf(totalForcedFinished), test.getId()};
        MsgHandler addAfterTestInfo = new MsgHandler(TypeMsg.FinishAfterTestInfo, afterTestInfo);
        ClientUI.chat.accept(addAfterTestInfo);
        MsgHandler deleteFromActive = new MsgHandler(TypeMsg.DeactivateTest, test.getId());
        ClientUI.chat.accept(deleteFromActive);

    }

    /**
     * Logs out the user and navigates to the login screen.
     *
     * @param event The event triggered by clicking the logout button.
     */
    @FXML
    public void LogOut(ActionEvent event) {
        LogOut.logOutToLoginScreen(event);
    }

    /**
     * Closes the client application.
     */
    @FXML
    public void closeClient() {
        ExitButton.closeClient();
    }

    /**
     * Minimizes the client application window.
     *
     * @param event The event triggered by clicking the minimize button.
     */
    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

    /**
     * Handles the action when the "Save" button is clicked and test is over.
     *
     * @param event The action event associated with the button click.
     */
    @FXML
    void onSaveButton(ActionEvent event) {
        boolean forText = true;
        if (forText) {
            if (FileSubmissionsText.getText().isEmpty()) {
                if (showError.showConfirmationPopup("Are you sure want to submit an empty test?")) {
                    StateManagement.resetInstance();
                    if (!testIsLockedManual) {
                        MsgHandler finishedStudentsIncrease = new MsgHandler(TypeMsg.IcreaseStudentsFinishedTest, test.getId());
                        ClientUI.chat.accept(finishedStudentsIncrease);
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
                        MsgHandler finishedStudentsIncrease = new MsgHandler(TypeMsg.IcreaseStudentsFinishedTest, test.getId());
                        ClientUI.chat.accept(finishedStudentsIncrease);
                    }

                    if ((checkLockTest() || (testIsLockedManual))) {
                        saveAfterTestInfoAndDeleteFromActive();
                    }

                    saveStudentsTest(100, 5, 5);
                    testIsLockedManual = false;
                    ScreenManager.goToNewScreen(event, PathConstants.mainMenuStudentPath);
                }
            }

        } else {
            showError.showInfoPopup("Test is over");

        }
    }


}


