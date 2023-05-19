package application.createNewTestScreen;

import util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class CreateTestController {

    @FXML
    private AnchorPane header;

    public void initialize() {
        ScreenManager.dragAndDrop(header);
    }

    public void BackToMenu(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "/application/mainMenuScreen/MainMenu.fxml");
    }

    public void pickQuestion(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "/application/createNewTestScreen/pickQuestionsScreen/PickQuestions.fxml");
    }

    public void openPreview() {
        ScreenManager.popUpScreen("/application/createNewTestScreen/testPreviewScreen/TestPreview.fxml");
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
