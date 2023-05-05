package application.CreateNewTestScreen;
//TODO: change the style of pick subject sub menus and its functionality

import application.ExitButton;
import application.MinimizeButton;
import application.ScreenManager;
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
        ScreenManager.goToNewScreen(event, "MainMenuScreen/MainMenu.fxml");
    }

    public void pickQuestion(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "CreateNewTestScreen/PickQuestionsScreen/PickQuestions.fxml");
    }

    public void openPreview(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "CreateNewTestScreen/TestPreviewScreen/TestPreview.fxml");
    }

    public void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml");
    }

    public void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    private void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
