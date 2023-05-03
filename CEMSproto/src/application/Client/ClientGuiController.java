package application.Client;

import application.ExitButton;
import application.MinimizeButton;
import application.ScreenManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class ClientGuiController {

    @FXML
    private AnchorPane header;

    public void initialize() {
        ScreenManager.dragAndDrop(header);
    }

    public void logIN(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml", false);
    }

    public void closeClient(ActionEvent event){
        ExitButton.closeClient(event);
    }

    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}