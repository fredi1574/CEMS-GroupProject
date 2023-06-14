package application.viewReportsScreen.graphScreen;

import Client.ClientUI;
import Client.ExitButton;
import Client.LogOut;
import application.viewReportsScreen.ViewReportsController;
import entity.StudentTest;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class ViewGraphController {


    @FXML
    private Text NumberText;
    @FXML
    private Text CourseYearSessionText;

    @FXML
    private Text averageText;

    @FXML
    private AnchorPane header;

    @FXML
    private Text medianText;

    @FXML
    private BarChart<String,Number> reportGraph;

    @FXML
    private Text semesterText;

    @FXML
    private Text testIDtext;
    private Stage reports;
    private ViewReportsController VRC = new ViewReportsController();
    private static int numberOfStudentTests;

    private static double totalScore;
    private static double averageScore;
    private static double medianScore;
    private static String CourseYearSession;
    private static String semester;
    private static String testID;

    private static XYChart.Series<String, Number> series;
    private static ObservableList<StudentTest> infoAboutTest;

    //todo: add the value of each bar above it
    public void initialize() {

        ScreenManager.dragAndDrop(header);
        ArrayList<StudentTest> SpecificTestInfo = new ArrayList<>();
        for (Object obj : infoAboutTest) {
            if (obj instanceof StudentTest) {
                if (((StudentTest) obj).getTestID().equals(testID)){
                    SpecificTestInfo.add((StudentTest) obj);
                }
            }
        }
        if (!(SpecificTestInfo.isEmpty())) {
            reportCalc(SpecificTestInfo);
        }
        averageText.setText(String.valueOf(averageScore));
        medianText.setText(String.valueOf(medianScore));
        reportGraph.getData().add(series);
        NumberText.setText(String.valueOf(numberOfStudentTests));
        CourseYearSessionText.setText(CourseYearSession);
        semesterText.setText(semester);
        testIDtext.setText(testID);

    }
    public void setViewReport(Stage reports) {
        this.reports = reports;
    }
    public void LogOut(ActionEvent event) {
       LogOut.logOutToLoginScreen(event);
    }

    public void closePopUp(ActionEvent event) {
        ExitButton.closePopUp(event);
    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
    public void reportCalc(ArrayList<StudentTest> SpecificTest) {
        numberOfStudentTests = 0;
        totalScore = 0;
        averageScore = 0;
        medianScore = 0;
        series = new XYChart.Series<>();
        StudentTest studentTest = null;
        int[] gradeRanges = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        ArrayList<Integer> numOfStudentsPerGroup = new ArrayList<>();
        ArrayList<Double> scoreValues = new ArrayList<>();

        // Calculate the total score and collect score values
        for (Object obj : SpecificTest) {
            if (obj instanceof StudentTest) {
                studentTest = (StudentTest) obj;
                double score = Double.parseDouble(studentTest.getScore());
                totalScore += score;
                scoreValues.add(score);
                numberOfStudentTests++;
                String testID = studentTest.getTestID();
               // series.getData().add(new XYChart.Data<>(testID, score));
            }
        }

        // Count the number of students per grade range
        for (int i = 0; i < gradeRanges.length - 1; i++) {
            int lowerRange = gradeRanges[i];
            int upperRange = gradeRanges[i + 1];
            int count = countStudentsInRange(scoreValues, lowerRange, upperRange);
            numOfStudentsPerGroup.add(count);
        }

        // Add the data points to the series
        for (int i = 0; i < gradeRanges.length - 1; i++) {
            String group = gradeRanges[i] + " - " + gradeRanges[i + 1];
            series.getData().add(new XYChart.Data<>(group, numOfStudentsPerGroup.get(i)));
        }

        averageScore = totalScore / numberOfStudentTests;

        averageScore = Double.parseDouble(new DecimalFormat("##.##").format(averageScore));
        Collections.sort(scoreValues);
        ArrayList<String> testAverage = new ArrayList<>();

        testAverage.add(String.valueOf(averageScore));
        testAverage.add(testID);

        MsgHandler setAverage = new MsgHandler(TypeMsg.SetTestAverage, testAverage);
        ClientUI.chat.accept(setAverage);

        medianScore = (numberOfStudentTests % 2 == 0)
                ? (scoreValues.get(numberOfStudentTests / 2 - 1) + scoreValues.get(numberOfStudentTests / 2)) / 2.0
                : scoreValues.get(numberOfStudentTests / 2);


    }
    private int countStudentsInRange(ArrayList<Double> scoreValues, int lowerRange, int upperRange) {
        int count = 0;
        for (double score : scoreValues) {
            if (score >= lowerRange && score <= upperRange) {
                count++;
            }
        }
        return count;
    }
    public void setReport(StudentTest report)
    {
        CourseYearSession = report.getCourse() + "-" + report.getYear() + report.getSession();
        semester = report.getSemester();
        testID = report.getTestID();
        infoAboutTest = VRC.getListOfTests();

    }
}
