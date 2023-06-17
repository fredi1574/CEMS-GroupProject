package application.Simulation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import Client.ExitButton;
import util.MinimizeButton;
import util.ScreenManager;
/**
 * The controller class for the SMS/Email pop-up window.
 * This class handles the initialization of the pop-up window when sending messages between clients.
 */
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

    /**
     * Initializes the controller.
     * It sets up the drag-and-drop functionality for the header,
     * sets the values of the full name, email address, info text, and phone number in the UI elements.
     */
    public void initialize() {
        ScreenManager.dragAndDrop(header);
        fullNameText.setText(fullName);
        EmailAddressTxt.setText(emailAdd);
        InfoTextField.setText(infoText);
        phoneNumberText.setText(phoneNum);
    }

    /**
     * Closes the pop-up window.
     *
     * @param event The action event triggered by the "Close" button.
     */
    @FXML
    void closeClient(ActionEvent event) {
        ExitButton.closePopUp(event);
    }

    /**
     * Sets the information fields with the provided data.
     *
     * @param info  The information text to be displayed.
     * @param name  The full name to be displayed.
     * @param email The email address to be displayed.
     * @param phone The phone number to be displayed.
     */
    public void SetInfoField(String info, String name, String email, String phone) {
        infoText = info;
        fullName = name;
        emailAdd = email;
        phoneNum = phone;
    }

    /**
     * Minimizes the pop-up window.
     *
     * @param event The action event triggered by the "Minimize" button.
     */
    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

}
