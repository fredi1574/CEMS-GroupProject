package application.LoginWindowScreen;

import application.ExitButton;
import application.MinimizeButton;
import application.ScreenChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LoginWindowController {

    public void logIN(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "MainMenuScreen/MainMenu.fxml");
    }

    @FXML
    private void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    private void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
