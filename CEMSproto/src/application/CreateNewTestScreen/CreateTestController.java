package application.CreateNewTestScreen;
//TODO: change the style of pick subject sub menus and its functionality

import application.ExitButton;
import application.MinimizeButton;
import application.ScreenChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CreateTestController {

    public void BackToMenu(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "MainMenuScreen/MainMenu.fxml");
    }

    public void pickQuestion(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "CreateNewTestScreen/PickQuestionsScreen/PickQuestions.fxml");
    }

    public void openPreview(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "CreateNewTestScreen/TestPreviewScreen/TestPreview.fxml");
    }

    public void LogOut(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml");
    }

    public void closeClient(ActionEvent event){
        ExitButton.closeClient(event);
    }

    @FXML
    private void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
