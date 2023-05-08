package application.CreateNewTestScreen.TestPreviewScreen;

import application.ExitButton;
import application.MinimizeButton;
import application.ScreenManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class TestPreviewController {

    @FXML
    private AnchorPane header;

    public void initialize() {
        ScreenManager.dragAndDrop(header);
    }

    public void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml");
    }

    public void goBackToPreviousScreen(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "CreateNewTestScreen/CreateNewTest.fxml");
    }

    public void closePopUp(ActionEvent event) {
        ExitButton.closePopUp(event);
    }

    @FXML
    private void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}