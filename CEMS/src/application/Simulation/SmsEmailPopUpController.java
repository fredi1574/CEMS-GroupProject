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
    private static String infoText;
    private static String fullName;
    private static String emailAdd;

    public void initialize() {
        ScreenManager.dragAndDrop(header);
        fullNameText.setText(fullName);
        EmailAdressTxt.setText(emailAdd);
        InfoTextField.setText(infoText);
    }
    @FXML
    void closeClient(ActionEvent event) {
        ExitButton.closePopUp(event);
    }
    public void SetInfoField(String info,String name,String email){
        infoText = info;
        fullName = name;
        emailAdd = email;


    }


    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

}
