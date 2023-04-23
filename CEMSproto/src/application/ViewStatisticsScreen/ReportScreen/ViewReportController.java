package application.ViewStatisticsScreen.ReportScreen;

import application.ScreenChanger;
import javafx.event.ActionEvent;

public class ViewReportController {

    public void backToReport(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "ViewStatisticsScreen/ViewStatistics.fxml", "C.E.M.S Test Manager - View statistics");
    }

    public void LogOut(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml", "C.E.M.S Test Manager");
    }

    public void BackTo(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "CreateNewTestScreen/CreateNewTest.fxml", "C.E.M.S Test Manager - Create a new test");
    }
}
