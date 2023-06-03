package application.enterTest;

import client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.MinimizeButton;
import util.PathConstants;
import util.ScreenManager;
import util.showError;

public class EnterCodePopUpController {

    @FXML
    private AnchorPane header;
    @FXML
    private Text fullNameText;
    @FXML
    private TextField CodeText;
    public void initialize() {
        // Enables dragging and dropping of the application window using the header pane
        ScreenManager.dragAndDrop(header);
        fullNameText.setText(Client.user.getFullName());
    }
    @FXML
    public void EnterCode(ActionEvent event) {
        String codet = CodeText.getText();
        if (codet.isEmpty()) {
            showError.showErrorPopup("Please enter code.");
        } else if (!codet.matches("\\d+")) {
            showError.showErrorPopup("Code should contain only numbers.");
        } else if (codet.length() != 6) {
            showError.showErrorPopup("Code should have exactly 6 numbers.");
        } else {
        }
    }
    @FXML
    private void closeClient(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuStudentPath);
    }


    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}

