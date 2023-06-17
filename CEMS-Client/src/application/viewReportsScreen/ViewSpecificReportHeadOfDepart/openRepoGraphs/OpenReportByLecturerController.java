package application.viewReportsScreen.ViewSpecificReportHeadOfDepart.openRepoGraphs;

import application.viewReportsScreen.ViewSpecificReportHeadOfDepart.ShowReportByLecturerController;
import entity.StudentTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import Client.ExitButton;
import util.MinimizeButton;
import util.PathConstants;
import util.ScreenManager;

import java.util.*;
import java.util.stream.Collectors;
/**
 * Controller class for the Open Report By Lecturer class.
 * Displays a bar chart representing the report data for a specific lecturer.
 */
public class OpenReportByLecturerController {

    @FXML
    private Text averageText;

    @FXML
    private AnchorPane header;

    @FXML
    private Text medianText;

    @FXML
    private BarChart<String, Number> reportGraph;

    @FXML
    private Text studentsNameLabel;

    @FXML
    private Text testsNumberText;

    private static XYChart.Series<String, Number> series;
    private static int totalTests;
    private static double medianScore;
    private static int averageScore;
    public static final ShowReportByLecturerController takeChosenfromController = new ShowReportByLecturerController();

    /**
     * Initializes the Open Report by Lecturer screen.
     * Sets up the UI components and displays the calculated report data.
     */
    @FXML
    public void initialize() {
        testsNumberText.setText(String.valueOf(totalTests));
        medianText.setText(String.valueOf(medianScore));
        averageText.setText(String.valueOf(averageScore));
        studentsNameLabel.setText(ShowReportByLecturerController.chosenLecturer);
        reportGraph.getData().add(series);
        ScreenManager.dragAndDrop(header);
    }

    /**
     * Calculates the report data for tests taken by a specific lecturer.
     *
     * @param testsByLecturer The list of tests taken by the lecturer.
     * This method calculates the total number of tests, average score, median score, and course-wise average scores.
     * It also creates a series for the bar chart to display the course-wise average scores.
     */
    public void reportCalc(ArrayList<Object> testsByLecturer) {
        totalTests = testsByLecturer.size();
        averageScore = 0;
        medianScore = 0;
        int totalScoreSum = 0;
        StudentTest studentTest;
        series = new XYChart.Series<>();

        // Create a map to store course-wise score sum and count
        Map<String, Double> courseScoreSumMap = new HashMap<>();
        Map<String, Integer> courseCountMap = new HashMap<>();

        for (Object testCount : testsByLecturer) {
            if (testCount instanceof StudentTest) {
                studentTest = (StudentTest) testCount;
                double score = Double.parseDouble(studentTest.getScore());
                totalScoreSum += score;

                String course = studentTest.getCourse();

                // Update the score sum and count for the current course
                courseScoreSumMap.put(course, courseScoreSumMap.getOrDefault(course, 0.0) + score);
                courseCountMap.put(course, courseCountMap.getOrDefault(course, 0) + 1);
            }
        }

        // Calculate the average score for each course
        List<String> courses = new ArrayList<>();
        List<Double> coursesAverage = new ArrayList<>();
        for (String course : courseScoreSumMap.keySet()) {
            double scoreSum = courseScoreSumMap.get(course);
            int count = courseCountMap.get(course);
            double courseAverage = count > 0 ? scoreSum / count : 0.0;
            courses.add(course);
            coursesAverage.add(courseAverage);
            series.getData().add(new XYChart.Data<>(course, courseAverage));
        }

        // Calculate the overall average score
        averageScore = totalScoreSum / totalTests;

        // Calculate the median score
        List<Double> scores = testsByLecturer.stream()
                .filter(obj -> obj instanceof StudentTest)
                .map(test -> Double.parseDouble(((StudentTest) test).getScore()))
                .sorted()
                .collect(Collectors.toList());

        medianScore = totalTests % 2 == 0 ?
                (scores.get(totalTests / 2 - 1) + scores.get(totalTests / 2)) / 2 :
                scores.get(totalTests / 2);
    }

    /**
     * Closes the client application.
     * This method is called when the user clicks the "Close" button.
     * It closes the client application.
     */
    @FXML
    void closeClient() {
        ExitButton.closeClient();
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

    /**
     * Navigates back to the previous screen.
     *
     * @param event The action event triggered by the user.
     * This method is called when the user clicks the "Back" button.
     * It navigates back to the View Report Head of Department screen.
     */
    public void goBackToPreviousScreen(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.ViewReportHeadOfDepartmentPath);
    }
}
