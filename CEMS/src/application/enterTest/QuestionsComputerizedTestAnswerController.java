package application.enterTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import client.ClientUI;
import common.MsgHandler;
import common.TypeMsg;
import entity.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.util.*;

import client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.*;

import javax.swing.*;

import static application.enterTest.EnterCodePopUpController.testID;

public class QuestionsComputerizedTestAnswerController {
    private int testDurationMinutes;
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
    private Timeline timer;
    @FXML
    private Label timerLabel;
    @FXML
    private ProgressIndicator points;
    private final Map<Integer, Integer> markings = new HashMap<>();
    private int currentQuestionIndex = 0;
    private int remainingMinutes;
    private int grade;
    private int correctAnswers;
    private int numberOfQuestions;
    private static int selectedCount = 0;
    private static ObservableList<TestQuestion> testQuestions;


    public void initialize() throws SQLException {
        // Enables dragging and dropping of the application window using the header pane
        MsgHandler totalStudentIncrease = new MsgHandler(TypeMsg.IcreaseStudentsEnteringTest, EnterCodePopUpController.testID);
        ClientUI.chat.accept(totalStudentIncrease);
        ScreenManager.dragAndDrop(header);
        fullNameText.setText(Client.user.getFullName());
        fetchCourseNameAndTestId();
        fetchQuestion();
        fetchTestDuration();  // Call fetchTestDuration() before startTimer()
        startTimer();

    }

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
            showError.showErrorPopup("Please select only one answer");
        } else {
            // Update the marking for the current question
            markings.put(currentQuestionIndex, marking);
        }
    }

    private Test getTestData() {
        MsgHandler getTestInformation = new MsgHandler(TypeMsg.GetTestByID, EnterCodePopUpController.testID);
        ClientUI.chat.accept(getTestInformation);
        Test test = (Test) ClientUI.chat.getSingleTest();
        return test;
    }

    private void fetchCourseNameAndTestId() {
        Test test = getTestData();
        courseNameTestIdText.setText("Course: " + test.getCourseName() + " | Test ID: " + test.getId());
    }


    private void fetchQuestion() throws SQLException {
        MsgHandler getQuestionInformation = new MsgHandler(TypeMsg.GetTestQuestionsById, EnterCodePopUpController.testID);
        ClientUI.chat.accept(getQuestionInformation);
        testQuestions = FXCollections.observableArrayList((List) ClientUI.chat.getTestQuestions());
        int totalQuestions = testQuestions.size();
        if (currentQuestionIndex < 1) {
            previousButton.setDisable(true);
        } else {
            previousButton.setDisable(false);
        }
        if (currentQuestionIndex == totalQuestions - 1) {
            myButton.setDisable(true);
        } else {
            myButton.setDisable(false);
        }
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

    @FXML
    public void logOut(ActionEvent event) {
        LogOut.logOutToLoginScreen(event);
    }

    @FXML
    public void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

    @FXML
    public void handlePreviousButtonClick() throws SQLException {
        saveMarkingWithValidation();
        if (selectedCount <= 1) {
            currentQuestionIndex -= 1; // Go back two questions (currentQuestionIndex - 1)
            fetchQuestion();
        }
    }

    @FXML
    public void handleButtonClick() throws SQLException {
        saveMarkingWithValidation();
        if (selectedCount <= 1) {
            currentQuestionIndex++;
            fetchQuestion();
        }
    }

    @FXML
    public void SubmitTest(ActionEvent event) throws SQLException {
        if (showError.showConfirmationPopup("Are you sure you want to submit the test?\nYou won't be able to make further changes")) {
            saveMarkingWithValidation();
            if (selectedCount <= 1) {
                saveFinalAnswers();
                MsgHandler finshedStudentsIncrease = new MsgHandler(TypeMsg.IcreaseStudentsFinishedTest, EnterCodePopUpController.testID);
                ClientUI.chat.accept(finshedStudentsIncrease);
                ScreenManager.goToNewScreen(event, PathConstants.mainMenuStudentPath);
            }

        }
        saveMarkingWithValidation();
        fetchQuestion();
    }

    public void saveFinalAnswers() {
        int i = 0;
        numberOfQuestions = testQuestions.size();
        for (TestQuestion answer : testQuestions) {

            AnswerOfStudent answerForSpecificQ = new AnswerOfStudent(Client.user.getId(), testID, testQuestions.get(i).getQuestionID(), markings.get(i));
            MsgHandler addAnswerOfTestFromStudent = new MsgHandler(TypeMsg.AddStudentAnswer, (AnswerOfStudent) answerForSpecificQ);
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

    public void saveStudentsTest(int score, int correctAnswers, int totalQuestions) {
        Test test = getTestData();
        StudentTest StudentsCopy = new StudentTest(Client.user.getId(), test.getId(), test.getSubject(), test.getCourseName(), Integer.toString(score),
                Client.user.getFullName(), test.getYear(), test.getSemester(), test.getSession(), CheatingSuspicion.NO, Integer.toString(correctAnswers),
                Integer.toString(totalQuestions), "", ApprovalStatus.N, test.getTestType());
        MsgHandler AddNewTest = new MsgHandler(TypeMsg.AddNewTestOfStudent, StudentsCopy);
        ClientUI.chat.accept(AddNewTest);

    }


    private void fetchTestDuration() {
        Test test = getTestData();
        testDurationMinutes = Integer.parseInt(test.getTestDuration());
        remainingMinutes = testDurationMinutes;
    }

    private void startTimer() {
        int totalSeconds = remainingMinutes * 60;
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
                        timerLabel.setText(formatTime(seconds[0]));
                    }
                })
        );
        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();

        // Update the timer label immediately
        timerLabel.setText(formatTime(seconds[0]));
    }


    private String formatTime(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }
}