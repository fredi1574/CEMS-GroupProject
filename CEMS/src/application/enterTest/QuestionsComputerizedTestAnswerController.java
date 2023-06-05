package application.enterTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.util.Duration;
import java.util.Map;
import java.util.HashMap;
import client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.ExitButton;
import util.MinimizeButton;
import util.ScreenManager;
import util.LogOut;

public class QuestionsComputerizedTestAnswerController {
    private int testDurationMinutes;
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
    private Timeline timer;
    @FXML
    private Label timerLabel;
    private final Map<Integer, Integer> markings = new HashMap<>();
    private int currentQuestionIndex = 0;
    private int remainingMinutes;

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public void initialize() {
        // Enables dragging and dropping of the application window using the header pane
        ScreenManager.dragAndDrop(header);
        fullNameText.setText(Client.user.getFullName());
        connectToDatabase();
        fetchQuestion();
        fetchTestDuration();  // Call fetchTestDuration() before startTimer()
        startTimer();

    }

    private void connectToDatabase() {
        // Establish database connection
        String url = "jdbc:mysql://localhost:3306/cems?serverTimezone=UTC";
        String username = "root";
        String password = "Aa123456";

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle connection error
        }
    }
    private void saveMarking() {
        // Get the selected answer
        int marking = 0;
        if (answer1CheckBox.isSelected()) {
            marking = 1;
        } else if (answer2CheckBox.isSelected()) {
            marking = 2;
        } else if (answer3CheckBox.isSelected()) {
            marking = 3;
        } else if (answer4CheckBox.isSelected()) {
            marking = 4;
        }

        // Update the marking for the current question
        markings.put(currentQuestionIndex, marking);
    }
    @FXML
    public void calculateGrade(ActionEvent event) {
        int totalQuestions = markings.size();
        int correctAnswers = 0;

        // Print the markings map for debugging purposes
        System.out.println("Markings map: " + markings);

        // Iterate through the markings and check if they match the correct answer
        for (Map.Entry<Integer, Integer> entry : markings.entrySet()) {
            int questionNumber = entry.getKey();
            int marking = entry.getValue();

            // Retrieve the correct answer index for the question from the database
            String correctAnswerQuery = "SELECT correctAnswer FROM question q INNER JOIN testquestion tq ON q.id = tq.questionID WHERE tq.testID = ? AND tq.questionNumber = ?";
            try {
                PreparedStatement answerStatement = connection.prepareStatement(correctAnswerQuery);
                answerStatement.setString(1, testId);
                answerStatement.setInt(2, questionNumber);
                ResultSet answerResultSet = answerStatement.executeQuery();

                if (answerResultSet.next()) {
                    int correctAnswerIndex = answerResultSet.getInt("correctAnswer");

                    if (marking == correctAnswerIndex) {
                        correctAnswers++;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle database error
            }
        }

        double grade = (correctAnswers / (double) totalQuestions) * 100;
        System.out.println("Grade: " + grade);
        // Perform further actions with the grade, such as displaying it to the user
    }


    private void fetchQuestion() {
        try {
           // String testId = EnterCodePopUpController.test.getId();
            String testDurationQuery = "SELECT testDuration FROM test WHERE id = ?";
            PreparedStatement durationStatement = connection.prepareStatement(testDurationQuery);
            durationStatement.setString(1, "010101");
            ResultSet durationResultSet = durationStatement.executeQuery();

            if (durationResultSet.next()) {
                String testDuration = durationResultSet.getString("testDuration");
                testDurationMinutes = Integer.parseInt(testDuration);
            }
            String query = "SELECT COUNT(*) AS totalQuestions FROM question q INNER JOIN testquestion tq ON q.id = tq.questionID WHERE tq.testID = ?";
            PreparedStatement countStatement = connection.prepareStatement(query);
            countStatement.setString(1, "010101");
            ResultSet countResultSet = countStatement.executeQuery();

            int totalQuestions = 0;
            if (countResultSet.next()) {
                totalQuestions = countResultSet.getInt("totalQuestions");
            }

            String questionQuery = "SELECT tq.points, q.questionText, q.answer1, q.answer2, q.answer3, q.answer4 FROM question q INNER JOIN testquestion tq ON q.id = tq.questionID WHERE tq.testID = ? ORDER BY tq.questionNumber LIMIT ?, 1";
            statement = connection.prepareStatement(questionQuery);
            statement.setString(1, "010101");
            statement.setInt(2, currentQuestionIndex);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                myButton.setDisable(false);
                currentQuestionIndex++;
                int points = resultSet.getInt("points");
                String question = resultSet.getString("questionText");
                String answer1 = resultSet.getString("answer1");
                String answer2 = resultSet.getString("answer2");
                String answer3 = resultSet.getString("answer3");
                String answer4 = resultSet.getString("answer4");

                // Update FXML elements with question and answer data
                questionNumberLabel.setText("Question " + currentQuestionIndex + " of " + totalQuestions + " (" + points + " points)");
                questionText.setText(question);
                answer1CheckBox.setText(answer1);
                answer2CheckBox.setText(answer2);
                answer3CheckBox.setText(answer3);
                answer4CheckBox.setText(answer4);

                // Check if there is a marking for the current question
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
            } else {
                myButton.setDisable(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database error
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
    public void handlePreviousButtonClick() {
        if (currentQuestionIndex > 1) {
            saveMarking();
            currentQuestionIndex -= 2; // Go back two questions (currentQuestionIndex - 1)
            fetchQuestion();
        }
    }

    @FXML
    public void handleButtonClick() {
        // Get the selected answer
        saveMarking();
        fetchQuestion();
    }

    private void fetchTestDuration() {
        try {
            String testDurationQuery = "SELECT testDuration FROM test WHERE id = ?";
            PreparedStatement durationStatement = connection.prepareStatement(testDurationQuery);
            durationStatement.setString(1, "010101");
            ResultSet durationResultSet = durationStatement.executeQuery();

            if (durationResultSet.next()) {
                String testDuration = durationResultSet.getString("testDuration");
                testDurationMinutes = Integer.parseInt(testDuration);
                remainingMinutes = testDurationMinutes;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database error
        }
    }

    private void startTimer() {
        int totalSeconds = remainingMinutes * 60;
        final int[] seconds = { totalSeconds };  // Create a final array to hold the remaining seconds

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