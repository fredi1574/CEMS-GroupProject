package application.CreateNewTestScreen.TestPreviewScreen;

import application.ScreenChanger;
import javafx.event.ActionEvent;

public class TestPreviewController {


    public void LogOut(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml", "C.E.M.S Test Manager");

    }

    public void goBackToPreviousScreen(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "CreateNewTestScreen/CreateNewTest.fxml", "C.E.M.S Test Manager - Create a new test");
    }
}