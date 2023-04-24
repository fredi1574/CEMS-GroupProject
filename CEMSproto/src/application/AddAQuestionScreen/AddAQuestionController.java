
package application.AddAQuestionScreen;

import application.ScreenChanger;
import javafx.event.ActionEvent;

public class AddAQuestionController {

    public void BackToMenu(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "MainMenuScreen/MainMenu.fxml", "C.E.M.S Test Manager");
    }

    public void pickQuestion(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "PickQuestions.fxml", "C.E.M.S Test Manager - Pick Questions");
    }

    public void LogOut(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml", "C.E.M.S Test Manager");
    }
}