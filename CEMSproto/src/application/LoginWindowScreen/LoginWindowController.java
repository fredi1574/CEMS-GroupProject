package application.LoginWindowScreen;

import application.ExitButton;
import application.MinimizeButton;
import application.ScreenManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class LoginWindowController {

    @FXML
    private AnchorPane header;

    public void initialize() {
        ScreenManager.dragAndDrop(header);
    }

    public void logIN(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "MainMenuScreen/MainMenu.fxml");
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