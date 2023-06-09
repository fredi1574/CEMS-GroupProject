package application.Simulation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.MinimizeButton;

public class SmsEmailPopUpController {

    @FXML
    private Text EmailAdressTxt;

    @FXML
    private TextField InfoTextField;

    @FXML
    private Text fullNameText;

    @FXML
    private AnchorPane header;

    @FXML
    void closeClient(ActionEvent event) {

    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

}
