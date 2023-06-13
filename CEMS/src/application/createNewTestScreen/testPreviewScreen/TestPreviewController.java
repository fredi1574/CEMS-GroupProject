package application.createNewTestScreen.testPreviewScreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import util.*;

public class TestPreviewController {

    @FXML
    private AnchorPane header;

    public void initialize() {

        ScreenManager.dragAndDrop(header);
    }

    public void LogOut(ActionEvent event) {
        LogOut.logOutToLoginScreen(event);
    }

    public void closePopUp(ActionEvent event) {
        ExitButton.closePopUp(event);
    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}