package application.enterTest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import util.ExitButton;
import util.MinimizeButton;
import util.ScreenManager;

public class QuestionsComputerizedTestAnswerController {

    @FXML
    private AnchorPane header;

    @FXML
    void BackToMenu(ActionEvent event) {

    }
    public void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "/application/loginWindowScreen/LoginWindow.fxml");
    }

    public void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

}
