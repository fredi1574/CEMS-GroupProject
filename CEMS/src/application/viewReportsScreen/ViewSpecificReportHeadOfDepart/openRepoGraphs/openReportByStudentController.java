package application.viewReportsScreen.ViewSpecificReportHeadOfDepart.openRepoGraphs;

import client.Client;
import client.ClientUI;
import common.MsgHandler;
import common.TypeMsg;
import entity.StudentTest;
import entity.TestTypeEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.ExitButton;
import util.MinimizeButton;
import util.PathConstants;
import util.ScreenManager;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class openReportByStudentController {

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
    private BarChart<String,Number> reportGraph;

    @FXML
    private Text studentsNameLabel;

    @FXML
    private Text testsNumberText;

    private static int numberOfStudentTests;
    private static int numberOfCTestTypes;
    private static int numberOfMTestTypes;
    private static double totalScore;
    private static double highestScore;
    private static double averageScore;
    private static double medianScore;
    private static String fullNameStudent;

    private static XYChart.Series<String, Number> series;

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
    @FXML
    void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }
    public void reportCalc(ArrayList<Object> infoOfstudent) {
        numberOfStudentTests = 0;
        numberOfCTestTypes= 0;
        numberOfMTestTypes= 0;
        totalScore= 0;
        highestScore= 0;
        averageScore= 0;
        medianScore= 0;
        series = new XYChart.Series<>();
        StudentTest studentTest = null;
        ArrayList<Double> scoreValues = new ArrayList<>();
        for (Object obj : infoOfstudent) {
            if (obj instanceof StudentTest) {
                studentTest = (StudentTest) obj;
                fullNameStudent =(String) studentTest.getFullname();
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
    @FXML
    public void goBackToPreviousScreen(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.ViewReportHeadOfDepartmentPath);
    }


    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

}
