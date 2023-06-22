package application.enterTest;

import Client.Client;
import Client.ClientUI;
import Client.ExitButton;
import Client.LogOut;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static application.enterTest.EnterCodePopUpController.testID;
/**
 * The QuestionsComputerizedTestAnswerController class controls the computerized test answer interface for a student.
 * Once the student submits his test his grade and other information are calculated and saved in the DB
 */
public class QuestionsComputerizedTestAnswerController {
    private static final Map<Integer, Integer> markings = new HashMap<>();
    static int totalQuestions;
    @FXML
    private static int[] seconds;
    private static Timeline timer;
    private static int selectedCount = 0;
    private static int TotalStudents;
    private static ObservableList<TestQuestion> testQuestions;
    private static boolean testIsLockedComputerized;
    @FXML
    private Text courseNameTestIdText;
    private String testId;
    @FXML
    private AnchorPane header;
    @FXML
    private Text fullNameText;
    @FXML
    private Label questionNumberLabel;
    @FXML
    private Text questionText;
    @FXML
    private Button gradeButton;
    @FXML
    private CheckBox answer1CheckBox;
    @FXML
    private CheckBox answer2CheckBox;
    @FXML
    private CheckBox answer3CheckBox;
    @FXML
    private CheckBox answer4CheckBox;
    @FXML
    private Button myButton;
    @FXML
    private Button previousButton;
    @FXML
    private Label timerLabel;
    @FXML
    private ProgressIndicator points;
    private int currentQuestionIndex = 0;
    private int remainingMinutes;
    private int grade;
    private int correctAnswers;
    /**
     * Initializes the markings map with zero values.
     * The markings map is used to store the scores for each question.
     * This method sets all the initial scores to zero.
     */
    private static void initializeMapWithZeros() {
        for (int i = 0; i < totalQuestions; i++) {
            markings.put(i, 0);
        }
    }
    /**
     * Initializes the test details screen.
     * This method is automatically called after the FXML file has been loaded.
     * It sets up the necessary components, retrieves data from the server,
     * and initializes the test timer and question markings.
     */
    public void initialize() {

        // Enables dragging and dropping of the application window using the header pane
        MsgHandler totalStudentIncrease = new MsgHandler(TypeMsg.IcreaseStudentsEnteringTest, testID);
        ClientUI.chat.accept(totalStudentIncrease);
        ScreenManager.dragAndDrop(header);
        fullNameText.setText(Client.user.getName());
        fetchCourseNameAndTestId();
        fetchQuestion();
        fetchTestDuration();  // Call fetchTestDuration() before startTimer()
        startTimer();
        initializeMapWithZeros();


    }
    /**
     * Shows a notification about time extension to the student and updates the test duration.
     *
     * @param newDuration The new duration to be added to the test duration, in minutes.
     */

    public void showNotificationAndChangeDuration(int newDuration) {
        seconds[0] += newDuration * 60;  // Add the new duration in seconds
        Platform.runLater(() -> ShowMessage.showInfoPopup("Test time increased by" + newDuration + "minutes"));

    }
    /**
     * Saves the marking for the current question with validation.
     * If a single answer is selected, it updates the marking for the current question in the {@code markings} map.
     * If multiple answers are selected, it displays an error message.
     */

    private void saveMarkingWithValidation() {
        CheckBox[] checkboxes = {answer1CheckBox, answer2CheckBox, answer3CheckBox, answer4CheckBox};

        int marking = 0;
        selectedCount = 0;
        for (CheckBox checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                selectedCount++;
                marking = Arrays.asList(checkboxes).indexOf(checkbox) + 1;
            }
            checkbox.setSelected(false);
        }

        if (selectedCount > 1) {
            ShowMessage.showErrorPopup("Please select only one answer");
        } else {
            // Update the marking for the current question
            markings.put(currentQuestionIndex, marking);
        }
    }
    /**
     * Retrieves the test data for the current test.
     *
     * @return The Test object containing the test data.
     */
    private Test getTestData() {
        MsgHandler getTestInformation = new MsgHandler(TypeMsg.GetTestByID, testID);
        ClientUI.chat.accept(getTestInformation);
        return (Test) ClientUI.chat.getSingleTest();
    }
    /**
     * Fetches the course name and test ID from the test data and displays it in the UI.
     */
    private void fetchCourseNameAndTestId() {
        Test test = getTestData();
        courseNameTestIdText.setText("Course: " + test.getCourseName() + " | Test ID: " + test.getId());
    }
    /**
     * Fetches the question and answer options for the current question from the server and displays them in the UI.
     */
    private void fetchQuestion() {
        MsgHandler getQuestionInformation = new MsgHandler(TypeMsg.GetTestQuestionsById, testID);
        ClientUI.chat.accept(getQuestionInformation);

        testQuestions = FXCollections.observableArrayList((List) ClientUI.chat.getTestQuestions());
        totalQuestions = testQuestions.size();

        previousButton.setDisable(currentQuestionIndex < 1);
        myButton.setDisable(currentQuestionIndex == totalQuestions - 1);

        if (currentQuestionIndex < totalQuestions) {
            TestQuestion question = testQuestions.get(currentQuestionIndex);
            MsgHandler getQuestionAndAnswer = new MsgHandler(TypeMsg.getQuestionAndAnswerFromTest, question.getQuestionID());
            ClientUI.chat.accept(getQuestionAndAnswer);

            Question questionFromTest = (Question) ClientUI.chat.getSingleQuestion();
            questionNumberLabel.setText("Question " + (currentQuestionIndex + 1) + " of " + totalQuestions + " (" + question.getPoints() + " points)");
            questionText.setText(questionFromTest.getQuestionText());

            answer1CheckBox.setText(questionFromTest.getAnswer1());
            answer2CheckBox.setText(questionFromTest.getAnswer2());
            answer3CheckBox.setText(questionFromTest.getAnswer3());
            answer4CheckBox.setText(questionFromTest.getAnswer4());

            points.setProgress(((double) question.getPoints() / 100));

            if (markings.containsKey(currentQuestionIndex)) {
                int marking = markings.get(currentQuestionIndex);
                // Set the checkboxes based on the marking
                answer1CheckBox.setSelected(marking == 1);
                answer2CheckBox.setSelected(marking == 2);
                answer3CheckBox.setSelected(marking == 3);
                answer4CheckBox.setSelected(marking == 4);
            } else {
                // No marking found, clear the checkboxes
                answer1CheckBox.setSelected(false);
                answer2CheckBox.setSelected(false);
                answer3CheckBox.setSelected(false);
                answer4CheckBox.setSelected(false);
            }
        }
    }

    /**
     * Logs out the user and navigates to the login screen.
     *
     * @param event The event triggered by clicking the logout button.
     */
    @FXML
    public void logOut(ActionEvent event) {
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
     * Handles the event when the previous button is clicked.
     * Saves the answer of the student and moves to the previous question if available.
     * Displays an error message if the test is locked or multiple answers are selected.
     */
    @FXML
    public void handlePreviousButtonClick() {
        saveMarkingWithValidation();
        if (testIsLockedComputerized) {
            ShowMessage.showErrorPopup("Test is locked please press submit to enter the test");
        } else if (selectedCount <= 1) {
            currentQuestionIndex -= 1; // Go back two questions (currentQuestionIndex - 1)
            fetchQuestion();
        }
    }
    /**
     * Handles the event when the next button is clicked.
     * Saves the answer of the student and moves to the next question if available.
     * Displays an error message if the test is locked or multiple answers are selected.
     */

    @FXML
    public void handleButtonClick() {
        saveMarkingWithValidation();
        if (testIsLockedComputerized) {
            ShowMessage.showErrorPopup("Test is locked please press submit to enter the test");
        } else if (selectedCount <= 1) {
            currentQuestionIndex++;
            fetchQuestion();
        }
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
        Test test = getTestData();
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
     * Calculates the total number of students who have not finished the test forcefully.
     * Retrieves the test data and the number of finished students from the server.
     * Subtracts the number of finished students from the total number of students who attended the test
     * and returns the remaining count, indicating the total number of students who have not finished the test forcefully.
     *
     * @return The total number of students who have not finished the test forcefully.
     */

    public int CalculateTotalForcedFinished() {
        Test test = getTestData();
        MsgHandler numberOfFinished = new MsgHandler(TypeMsg.CountNumberOfFinished, test.getId());
        ClientUI.chat.accept(numberOfFinished);
        int NumberOfFinishedCounter = (int) (ClientUI.chat.getNumberOfFinished());
        return TotalStudents - NumberOfFinishedCounter;
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
        Test test = getTestData();
        int totalForcedFinished;
        if (testIsLockedComputerized) {
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
        MsgHandler deleteFromActive = new MsgHandler(TypeMsg.DeactivateTest, test.getId());
        ClientUI.chat.accept(deleteFromActive);

    }
    /**
     * Handles the event when the user submits the test. Saves the marking for the current question
     * and performs additional actions based on the test lock status. If the test is locked or computerized,
     * the after-test information is saved and the test is deactivated. The user is then redirected to the main menu.
     *
     * @param event The event triggered by the submission of the test.
     */

    @FXML
    public void SubmitTest(ActionEvent event) {
        if (ShowMessage.showConfirmationPopup("Are you sure you want to submit the test?\nYou won't be able to make further changes")) {
            saveMarkingWithValidation();
            if (selectedCount <= 1) {
                saveFinalAnswers();
                if (!testIsLockedComputerized) {
                    MsgHandler finishedStudentsIncrease = new MsgHandler(TypeMsg.IcreaseStudentsFinishedTest, testID);
                    ClientUI.chat.accept(finishedStudentsIncrease);
                }

                if (checkLockTest() || (testIsLockedComputerized)) {
                    saveAfterTestInfoAndDeleteFromActive();
                }
                testIsLockedComputerized = false;
                ScreenManager.goToNewScreen(event, PathConstants.mainMenuStudentPath);
            }

        }
        saveMarkingWithValidation();
        fetchQuestion();
    }

    /**
     * Saves the final answers of the student for each question in the test. It iterates through the test questions,
     * retrieves the student's answer marking, and saves it. Additionally, it compares the student's answer with the correct
     * answer for each question to calculate the number of correct answers and the overall grade. Finally, it calls the
     * {@link #saveStudentsTest(int, int, int)} method to save the student's test results.
     */
    public void saveFinalAnswers() {
        int i = 0;
        int numberOfQuestions = testQuestions.size();
        for (TestQuestion answer : testQuestions) {
            AnswerOfStudent answerForSpecificQ = new AnswerOfStudent(Client.user.getId(), testID, testQuestions.get(i).getQuestionID(), markings.get(i));
            MsgHandler addAnswerOfTestFromStudent = new MsgHandler(TypeMsg.AddStudentAnswer, answerForSpecificQ);
            ClientUI.chat.accept(addAnswerOfTestFromStudent);
            MsgHandler getCorrectAnswerOfQ = new MsgHandler(TypeMsg.getQuestionAndAnswerFromTest, testQuestions.get(i).getQuestionID());
            ClientUI.chat.accept(getCorrectAnswerOfQ);
            Question question_i = (Question) (ClientUI.chat.getSingleQuestion());
            int getCorrect = Integer.parseInt(question_i.getCorrectAnswer());
            if (getCorrect == markings.get(i)) {
                correctAnswers++;
                grade += answer.getPoints();
            }
            i++;
        }
        saveStudentsTest(grade, correctAnswers, numberOfQuestions);

    }
    /**
     * Locks the test, preventing further interactions by stopping the timer and displaying a notification to the student.
     * The {@code testIsLockedComputerized} flag is set to indicate that the test is locked.
     * The student is informed that the test was locked by the lecturer and is advised to press the submit button to exit the test.
     */

    public void lockTest() {
        timer.stop();
        Platform.runLater(() -> ShowMessage.showInfoPopup("Test was locked by lecturer\nPlease press submit to exit the test"));
        testIsLockedComputerized = true;
    }
    /**
     * Saves the student's test results and information.
     *
     * @param score           The score achieved by the student in the test.
     * @param correctAnswers  The number of correct answers given by the student.
     * @param totalQuestions  The total number of questions in the test.
     */
    public void saveStudentsTest(int score, int correctAnswers, int totalQuestions) {
        Test test = getTestData();
        int timeInSeconds = seconds[0];
        int testDuration = (Integer.parseInt(test.getTestDuration()) * 60) - timeInSeconds;
        String decimalFormat = formatTime(testDuration);
        StudentTest StudentsCopy = new StudentTest(Client.user.getId(), test.getId(), test.getSubjectID(), test.getCourseName(), Integer.toString(score),
                Client.user.getFullName(), test.getYear(), test.getSemester(), test.getSession(), CheatingSuspicion.NO, Integer.toString(correctAnswers),
                Integer.toString(totalQuestions), "", ApprovalStatus.NO, test.getTestType(), decimalFormat);
        MsgHandler AddNewTest = new MsgHandler(TypeMsg.AddNewTestOfStudent, StudentsCopy);
        ClientUI.chat.accept(AddNewTest);
    }
    /**
     * Fetches the test duration from the test data and initializes the remaining minutes variable.
     */

    private void fetchTestDuration() {
        Test test = getTestData();
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

        timer = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    seconds[0]--;  // Decrement the remaining seconds
                    if (seconds[0] <= 0) {
                        // Timer has ended, perform necessary actions
                        timer.stop();
                        Stage currentStage = (Stage) header.getScene().getWindow();
                        if (currentStage.isShowing()) {
                            saveFinalAnswers();
                            if (checkLockTest() || (testIsLockedComputerized)) {
                                saveAfterTestInfoAndDeleteFromActive();
                            }

                            Platform.runLater(() -> {

                                if (currentStage.isShowing()) {
                                    ShowMessage.showInfoPopup("Test is over");
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
        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();

        // Update the timer label immediately
        timerLabel.setText(formatTime(seconds[0]));
    }
    /**
     * Formats the given time in seconds into the HH:mm:ss format.
     *
     * @param seconds The time in seconds to format.
     * @return The formatted time string in the HH:mm:ss format.
     */

    private String formatTime(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }
}