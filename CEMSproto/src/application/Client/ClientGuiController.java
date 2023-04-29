package application.Client;

import application.ExitButton;
import application.MinimizeButton;
import application.ScreenChanger;
import javafx.event.ActionEvent;

public class ClientGuiController {

    public void logIN(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml");
    }

    public void closeClient(ActionEvent event){
        ExitButton.closeClient(event);
    }

    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}