package application.manageTestsScreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import util.ExitButton;
import util.MinimizeButton;
import util.PathConstants;
import util.ScreenManager;

public class viewActiveTestController {

    public void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.loginPath);
    }

    public void back(ActionEvent event) {
        ExitButton.closePopUp(event);
    }
    @FXML
    private void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

    public void lockTest(ActionEvent actionEvent) {
    }

    public void unlockTest(ActionEvent actionEvent) {
    }

    public void sendExtraTimeRequest(ActionEvent actionEvent) {

    }
}
