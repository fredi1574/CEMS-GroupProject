package application;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class ExitButton {

    public static void closeClient(ActionEvent event) {
        LogOut.logOut();
        System.exit(0);
    }

    public static void closePopUp(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}