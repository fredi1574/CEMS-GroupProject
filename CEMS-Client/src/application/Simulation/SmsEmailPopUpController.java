package application.Simulation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import Client.ExitButton;
import util.MinimizeButton;
import util.ScreenManager;

public class SmsEmailPopUpController {

    @FXML
    private Text EmailAddressTxt;

    @FXML
    private TextField InfoTextField;

    @FXML
    private Text fullNameText;
    @FXML
    private Text phoneNumberText;

    @FXML
    private AnchorPane header;
    private static String infoText;
    private static String fullName;
    private static String emailAdd;
    private static String phoneNum;


    public void initialize() {
        ScreenManager.dragAndDrop(header);
        fullNameText.setText(fullName);
        EmailAddressTxt.setText(emailAdd);
        InfoTextField.setText(infoText);
        phoneNumberText.setText(phoneNum);
    }
    @FXML
    void closeClient(ActionEvent event) {
        ExitButton.closePopUp(event);
    }
    public void SetInfoField(String info,String name,String email,String phone){
        infoText = info;
        fullName = name;
        emailAdd = email;
        phoneNum = phone;


    }


    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

}
