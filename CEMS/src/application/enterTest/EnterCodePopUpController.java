package application.enterTest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import util.ExitButton;
import util.MinimizeButton;

public class EnterCodePopUpController {

    @FXML
    private AnchorPane header;

    @FXML
    private void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }


    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}

