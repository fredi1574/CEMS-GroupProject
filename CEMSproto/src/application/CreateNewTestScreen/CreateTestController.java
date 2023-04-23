package application.CreateNewTestScreen;

import application.ScreenChanger;
import javafx.event.ActionEvent;

public class CreateTestController {

    public void BackToMenu(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "MainMenuScreen/MainMenu.fxml", "C.E.M.S Test Manager");
    }

    public void pickQuestion(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "CreateNewTestScreen/PickQuestionsScreen/PickQuestions.fxml", "C.E.M.S Test Manager - Pick Questions");
    }

    public void openPreview(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "CreateNewTestScreen/TestPreviewScreen/TestPreview.fxml", "C.E.M.S Test Manager - Test Preview");
    }

    public void LogOut(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml", "C.E.M.S Test Manager");
    }
}
