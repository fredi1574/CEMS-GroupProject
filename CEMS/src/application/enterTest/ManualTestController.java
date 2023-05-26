package application.enterTest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import util.ExitButton;
import util.MinimizeButton;
import util.ScreenManager;

public class ManualTestController {

    @FXML
    private AnchorPane header;

    @FXML
    void BackToMenu(ActionEvent event) {

    }

    @FXML
    public void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "/application/loginWindowScreen/LoginWindow.fxml");
    }
    @FXML
    private void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }


    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

}


