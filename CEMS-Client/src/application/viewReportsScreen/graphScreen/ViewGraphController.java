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
import util.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Controller class for the View Graph screen for lecturer report.
 * Displays a bar chart representing the report data for a specific test.
 */
public class ViewGraphController {
    @FXML
    private Text finishedText;

    @FXML
    private Text ForcedFinishedText;

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
    @FXML
    private Text DurationText;
    private final ViewReportsController VRC = new ViewReportsController();
    private static int numberOfStudentTests;

    private static double averageScore;
    private static double medianScore;
    private static String CourseYearSession;
    private static String semester;
    private static String testID;

    private static XYChart.Series<String, Number> series;
    private static ObservableList<StudentTest> infoAboutTest;

    /**
     * Initializes the View Graph screen.
     * Sets up the UI components and displays the calculated report data.
     */
    public void initialize() {
        ScreenManager.dragAndDrop(header);
        ArrayList<StudentTest> SpecificTestInfo = new ArrayList<>();
        for (StudentTest test : infoAboutTest) {
            if (test != null) {
                if (test.getTestID().equals(testID)){
                    SpecificTestInfo.add(test);
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

        MsgHandler getInfoAfterTest = new MsgHandler(TypeMsg.getTestAfterTestInfo, testIDtext.getText());
        ClientUI.chat.accept(getInfoAfterTest);

        ArrayList<String> AdditionalInformation = (ArrayList)ClientUI.chat.getInfoAboutTest();
        DurationText.setText(AdditionalInformation.get(0));
        finishedText.setText(AdditionalInformation.get(1));
        ForcedFinishedText.setText(AdditionalInformation.get(2));
    }

    /**
     * Logs out the user and navigates back to the login screen.
     *
     * @param event The action event triggered by the user.
     *
     * This method is called when the user clicks the "Log Out" button.
     * It logs out the user and navigates back to the login screen.
     */
    public void LogOut(ActionEvent event) {
        LogOut.logOutToLoginScreen(event);
    }

    /**
     * Closes the pop-up window.
     *
     * @param event The action event triggered by the user.
     *
     * This method is called when the user clicks the "Close" button in the pop-up window.
     * It closes the pop-up window.
     */
    public void closePopUp(ActionEvent event) {
        ExitButton.closePopUp(event);
    }

    /**
     * Minimizes the client application window.
     *
     * @param event The action event triggered by the user.
     *
     * This method is called when the user clicks the "Minimize" button.
     * It minimizes the client application window.
     */
    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

    /**
     * Calculates the report data for the specific test.
     *
     * @param SpecificTest The list of StudentTest objects for the specific test.
     *
     * This method calculates the average score, median score, and creates the data points for the bar chart.
     */
    public void reportCalc(ArrayList<StudentTest> SpecificTest) {
        numberOfStudentTests = 0;
        double totalScore = 0;
        averageScore = 0;
        medianScore = 0;
        series = new XYChart.Series<>();
        StudentTest studentTest;
        int[] gradeRanges = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        ArrayList<Integer> numOfStudentsPerGroup = new ArrayList<>();
        ArrayList<Double> scoreValues = new ArrayList<>();

        // Calculate the total score and collect score values
        for (StudentTest test : SpecificTest) {
            if (test != null) {
                studentTest = test;
                double score = Double.parseDouble(studentTest.getScore());
                totalScore += score;
                scoreValues.add(score);
                numberOfStudentTests++;
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

    /**
     * Counts the number of students with scores within a specific range.
     *
     * @param scoreValues The list of score values.
     * @param lowerRange The lower range of the grade.
     * @param upperRange The upper range of the grade.
     * @return The count of students within the specified grade range.
     */
    private int countStudentsInRange(ArrayList<Double> scoreValues, int lowerRange, int upperRange) {
        int count = 0;
        for (double score : scoreValues) {
            if (score == 100){
                if (score >= lowerRange && score <= upperRange) {
                    count++;
                }
            }
            else if (score >= lowerRange && score < upperRange) {
                count++;
            }
        }
        return count;
    }

    /**
     * Sets the report information for the specific test.
     *
     * @param report The StudentTest object representing the specific test.
     *
     * This method sets the report information, including the course, year, session, semester, and test ID.
     */
    public void setReport(StudentTest report)
    {
        CourseYearSession = report.getCourse() + "-" + report.getYear() + report.getSession();
        semester = report.getSemester();
        testID = report.getTestID();
        infoAboutTest = VRC.getListOfTests();
    }
}
