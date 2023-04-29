package application.CreateNewTestScreen.TestPreviewScreen;

import application.ExitButton;
import application.MinimizeButton;
import application.ScreenChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class TestPreviewController {

    public void LogOut(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml");

    }

    public void goBackToPreviousScreen(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "CreateNewTestScreen/CreateNewTest.fxml");
    }

    public void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    private void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}