package application.ViewReportsScreen.GraphScreen;

import application.ScreenChanger;
import javafx.event.ActionEvent;

public class ViewGraphController {

    public void backToReport(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "ViewStatisticsScreen/ViewGraph.fxml", "C.E.M.S Test Manager - View statistics");
    }

    public void LogOut(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml", "C.E.M.S Test Manager");
    }

    public void BackTo(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "ViewReportsScreen/ViewReports.fxml", "C.E.M.S Test Manager - Create a new test");
    }
}
