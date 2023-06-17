package application.viewReportsScreen.ViewSpecificReportHeadOfDepart.openRepoGraphs;

import entity.StudentTest;
import entity.TestTypeEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import Client.ExitButton;
import util.MinimizeButton;
import util.PathConstants;
import util.ScreenManager;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Controller class for the Open Report By Student class.
 * Displays a bar chart representing the report data for a specific student.
 */
public class OpenReportByStudentController {

    @FXML
    private Text CtestNumberText;

    @FXML
    private Text MtestNumberText;

    @FXML
    private NumberAxis YAxis;

    @FXML
    private Text averageText;

    @FXML
    private AnchorPane header;

    @FXML
    private Text highestText;

    @FXML
    private Text medianText;

    @FXML
    private BarChart<String, Number> reportGraph;

    @FXML
    private Text studentsNameLabel;

    @FXML
    private Text testsNumberText;

    private static int numberOfStudentTests;
    private static int numberOfCTestTypes;
    private static int numberOfMTestTypes;
    private static double highestScore;
    private static double averageScore;
    private static double medianScore;
    private static String fullNameStudent;

    private static XYChart.Series<String, Number> series;

    /**
     * Initializes the Open Report by Student screen.
     * Sets up the UI components and displays the calculated report data.
     */
    public void initialize() {
        ScreenManager.dragAndDrop(header);
        testsNumberText.setText(String.valueOf(numberOfStudentTests));
        CtestNumberText.setText(String.valueOf(numberOfCTestTypes));
        MtestNumberText.setText(String.valueOf(numberOfMTestTypes));
        averageText.setText(String.valueOf(averageScore));
        medianText.setText(String.valueOf(medianScore));
        highestText.setText(String.valueOf(highestScore));
        reportGraph.getData().add(series);
        studentsNameLabel.setText(fullNameStudent);
    }

    /**
     * Closes the client application.
     * This method is called when the user clicks the close button on the window.
     * It terminates the client application.
     */
    @FXML
    void closeClient() {
        ExitButton.closeClient();
    }

    /**
     * Calculates the report data for a specific student.
     * @param infoOfStudent The list of student test objects representing the tests taken by the student.
     * This method calculates the number of tests, number of 'C' and 'M' test types, highest score, average score, median score,
     * and creates a series for the bar chart. It also sets the student's full name for display.
     */
    public void reportCalc(ArrayList<Object> infoOfStudent) {
        numberOfStudentTests = 0;
        numberOfCTestTypes = 0;
        numberOfMTestTypes = 0;
        double totalScore = 0;
        highestScore = 0;
        averageScore = 0;
        medianScore = 0;
        series = new XYChart.Series<>();
        StudentTest studentTest;
        ArrayList<Double> scoreValues = new ArrayList<>();

        for (Object obj : infoOfStudent) {
            if (obj instanceof StudentTest) {
                studentTest = (StudentTest) obj;
                fullNameStudent = studentTest.getFullname();
                double score = Double.parseDouble(studentTest.getScore());
                totalScore += score;
                scoreValues.add(score);

                if (score > highestScore) {
                    highestScore = score;
                }

                numberOfStudentTests++;

                // Count the test types 'C' and 'M'
                if (studentTest.getTestType() == TestTypeEnum.C) {
                    numberOfCTestTypes++;
                } else if (studentTest.getTestType() == TestTypeEnum.M) {
                    numberOfMTestTypes++;
                }

                String testID = studentTest.getTestID();
                series.getData().add(new XYChart.Data<>(testID, score));
            }
        }

        averageScore = totalScore / numberOfStudentTests;
        averageScore = Double.parseDouble(new DecimalFormat("##.##").format(averageScore));
        Collections.sort(scoreValues);

        medianScore = (numberOfStudentTests % 2 == 0)
                ? (scoreValues.get(numberOfStudentTests / 2 - 1) + scoreValues.get(numberOfStudentTests / 2)) / 2.0
                : scoreValues.get(numberOfStudentTests / 2);
    }

    /**
     * Navigates back to the previous screen.
     *
     * @param event The action event triggered by the user.
     * This method is called when the user clicks the "Back" button.
     * It navigates back to the View Report Head of Department screen.
     */
    @FXML
    public void goBackToPreviousScreen(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.ViewReportHeadOfDepartmentPath);
    }

    /**
     * Minimizes the client application window.
     *
     * @param event The action event triggered by the user.
     * This method is called when the user clicks the "Minimize" button.
     * It minimizes the client application window.
     */
    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
