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

public class OpenReportByLecturerController {

    @FXML
    private Text averageText;

    @FXML
    private AnchorPane header;

    @FXML
    private Text medianText;

    @FXML
    private BarChart<String,Number> reportGraph;

    @FXML
    private Text studentsNameLabel;

    @FXML
    private Text testsNumberText;

    private static XYChart.Series<String, Number> series;
    private static int totalTests;
    private static double medianScore;
    private static int AverageScore;
    public static final ShowReportByLecturerController takeChosenfromController= new ShowReportByLecturerController();
    @FXML
    public void initialize() {
        testsNumberText.setText(String.valueOf(totalTests));
        medianText.setText(String.valueOf(medianScore));
        averageText.setText(String.valueOf(AverageScore));
        studentsNameLabel.setText(ShowReportByLecturerController.chosenLecturer);
        reportGraph.getData().add(series);
        ScreenManager.dragAndDrop(header);

    }

    public void reportCalc(ArrayList<Object> testsByLecturer) {
        totalTests = testsByLecturer.size();
        AverageScore = 0;
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
        AverageScore = totalScoreSum / totalTests;

        // Calculate the median score
        List<Double> scores = testsByLecturer.stream()
                .filter(obj -> obj instanceof StudentTest)
                .map(test -> Double.parseDouble(((StudentTest) test).getScore()))
                .sorted()
                .collect(Collectors.toList());

        medianScore = totalTests % 2 == 0 ?
                (scores.get(totalTests / 2 - 1) + scores.get(totalTests / 2)) / 2 :
                scores.get(totalTests / 2);

        System.out.println("Total number of student tests: " + totalTests);
        System.out.println("Total average score: " + AverageScore);
        System.out.println("Total median score: " + medianScore);
        System.out.println("Courses: " + courses);
        System.out.println("Average scores for each course: " + coursesAverage);
    }


    @FXML
    void closeClient() {
        ExitButton.closeClient();
    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }


    public void goBackToPreviousScreen(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.ViewReportHeadOfDepartmentPath);
    }
}
