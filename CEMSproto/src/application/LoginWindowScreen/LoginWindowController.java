package application.LoginWindowScreen;

import application.ScreenChanger;
import javafx.event.ActionEvent;

public class LoginWindowController {


    public void logIN(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "MainMenuScreen/MainMenu.fxml", "C.E.M.S Test Manager");
    }
}