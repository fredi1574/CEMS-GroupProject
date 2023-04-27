package application.ViewReportsScreen.GraphScreen;

import application.ScreenChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

public class ViewGraphController {

    @FXML
    BarChart<String, Number> reportGraph;

    //todo: add the value of each bar above it
    public void initialize() {

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        int[] gradeRanges = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        int[] numOfStudentsPerGroup = {5, 16, 17, 14, 12, 1, 17, 13, 11, 7};

        // Add the data points to the series
        for (int i = 0; i < gradeRanges.length - 1; i++) {
            String group = gradeRanges[i] + " - " + gradeRanges[i + 1];
            series.getData().add(new XYChart.Data<>(group, numOfStudentsPerGroup[i]));
        }
        reportGraph.getData().add(series);
        //TODO: change the color of the axis title
    }

    public void LogOut(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml", "C.E.M.S Test Manager");
    }

    public void goBackToPreviousScreen(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "ViewReportsScreen/ViewReports.fxml", "C.E.M.S Test Manager - Create a new test");
    }
}
