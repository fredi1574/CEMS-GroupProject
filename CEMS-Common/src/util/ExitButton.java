package util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class ExitButton {

    /**
     * closes the entire application
     *
     * @param event the source event that triggered the method
     */
    public static void closeClient(ActionEvent event) {
        LogOut.logOut();
        System.exit(0);
    }

    /**
     * closes the popup window (not the whole application)
     *
     * @param event the source event that triggered the method
     */
    public static void closePopUp(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}