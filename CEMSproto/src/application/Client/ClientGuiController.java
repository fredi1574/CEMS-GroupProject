package application.Client;

import application.ScreenChanger;
import javafx.event.ActionEvent;


public class ClientGuiController {
    public void Close(ActionEvent e) {
        System.exit(0);
    }

    public void logIN(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml", "C.E.M.S Test Manager - Login");
    }
}