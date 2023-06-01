package application.viewReportsScreen.ViewSpecificReportHeadOfDepart.openRepoGraphs;

import client.Client;
import entity.StudentTest;
import entity.TestTypeEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.ExitButton;
import util.MinimizeButton;
import util.PathConstants;
import util.ScreenManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class openReportByStudentController {
    @FXML
    private Text CtestNumberText;

    @FXML
    private Text MtestNumberText;

    @FXML
    private Text averageText;

    @FXML
    private AnchorPane header;

    @FXML
    private Text highestText;

    @FXML
    private Text medianText;

    @FXML
    private BarChart<?, ?> reportGraph;

    @FXML
    private Text testsNumberText;

    public void initialize() {
        ScreenManager.dragAndDrop(header);
    }
    @FXML
    void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }
    public void reportCalc(ArrayList<Object> infoOfstudent) {
        int numberOfStudentTests = 0;
        int numberOfCTestTypes = 0;
        int numberOfMTestTypes = 0;
        double totalScore = 0;
        double highestScore = Double.MIN_VALUE;
        ArrayList<Double> scoreValues = new ArrayList<>();

        for (Object obj : infoOfstudent) {
            if (obj instanceof StudentTest) {
                StudentTest studentTest = (StudentTest) obj;
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
            }
        }

        double averageScore = totalScore / numberOfStudentTests;
        Collections.sort(scoreValues);

        double medianScore = (numberOfStudentTests % 2 == 0)
                ? (scoreValues.get(numberOfStudentTests / 2 - 1) + scoreValues.get(numberOfStudentTests / 2)) / 2.0
                : scoreValues.get(numberOfStudentTests / 2);
        testsNumberText.setText(String.valueOf(numberOfStudentTests));
        CtestNumberText.setText(String.valueOf(numberOfCTestTypes));
        MtestNumberText.setText(String.valueOf(numberOfMTestTypes));
        averageText.setText(String.valueOf(averageScore));
        medianText.setText(String.valueOf(medianScore));
        highestText.setText(String.valueOf(highestScore));
        System.out.println("Number of student tests: " + numberOfStudentTests);
        System.out.println("Number of C test types: " + numberOfCTestTypes);
        System.out.println("Number of M test types: " + numberOfMTestTypes);
        System.out.println("Average score: " + averageScore);
        System.out.println("Median score: " + medianScore);
        System.out.println("Highest score: " + highestScore);

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
