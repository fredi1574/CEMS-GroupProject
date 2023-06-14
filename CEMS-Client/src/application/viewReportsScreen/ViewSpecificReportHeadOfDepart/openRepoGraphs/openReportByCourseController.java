package application.viewReportsScreen.ViewSpecificReportHeadOfDepart.openRepoGraphs;

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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class openReportByCourseController {


    @FXML
    private Text averageText;

    @FXML
    private Text courseNameLabel;

    @FXML
    private AnchorPane header;

    @FXML
    private Text medianText;

    @FXML
    private BarChart<String, Number> reportGraph;

    @FXML
    private Text testsNumberText;
    private static int numberOfStudentTests;

    private static double highestScore;
    private static double averageScore;
    private static double medianScore;
    private static XYChart.Series<String, Number> series;
    private static String courseName;


    @FXML
    void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }
    public void initialize() {
        ScreenManager.dragAndDrop(header);
        testsNumberText.setText(String.valueOf(numberOfStudentTests));
        averageText.setText(String.valueOf(averageScore));
        medianText.setText(String.valueOf(medianScore));
        reportGraph.getData().add(series);
        courseNameLabel.setText(courseName);

    }
    public void reportCalc(ArrayList<Object> infoOfstudent) {
        numberOfStudentTests = 0;
        double totalScore = 0;
        averageScore= 0;
        medianScore= 0;
        series = new XYChart.Series<>();
        StudentTest studentTest = null;
        ArrayList<Double> scoreValues = new ArrayList<>();
        for (Object obj : infoOfstudent) {
            if (obj instanceof StudentTest) {
                studentTest = (StudentTest) obj;
                courseName = studentTest.getCourse();
                double score = Double.parseDouble(studentTest.getScore());
                totalScore += score;
                scoreValues.add(score);

                if (score > highestScore) {
                    highestScore = score;
                }
                numberOfStudentTests++;
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

        System.out.println("Total number of student tests: " + numberOfStudentTests);
        System.out.println("Total average score: " + averageScore);
        System.out.println("Total median score: " + medianScore);

    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
    @FXML
    public void goBackToPreviousScreen(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.ViewReportHeadOfDepartmentPath);
    }





}