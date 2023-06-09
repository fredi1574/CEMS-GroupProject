package application.Simulation;

import client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.ExitButton;
import util.MinimizeButton;
import util.ScreenManager;

public class SmsEmailPopUpController {

    @FXML
    private Text EmailAdressTxt;

    @FXML
    private TextField InfoTextField;

    @FXML
    private Text fullNameText;

    @FXML
    private AnchorPane header;

    public void initialize() {
        ScreenManager.dragAndDrop(header);
        fullNameText.setText(Client.user.getFullName());
        EmailAdressTxt.setText(Client.user.getEmail());
    }
    @FXML
    void closeClient(ActionEvent event) {
        ExitButton.closePopUp(event);
    }
    public void SetInfoField(String info){
        InfoTextField.setText(info);
    }


    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

}
