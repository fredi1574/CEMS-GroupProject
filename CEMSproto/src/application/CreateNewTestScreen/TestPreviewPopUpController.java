package application.CreateNewTestScreen;

import application.ScreenChanger;
import javafx.event.ActionEvent;


public class TestPreviewPopUpController {


    public void LogOut(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml", "C.E.M.S Test Manager");

    }

    public void BackTo(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "CreateNewTestScreen/CreateNewTest.fxml", "C.E.M.S Test Manager - Create a new test");
    }

}
