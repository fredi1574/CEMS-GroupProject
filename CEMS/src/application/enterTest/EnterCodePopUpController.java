package application.enterTest;

import client.Client;
import client.ClientUI;
import common.MsgHandler;
import common.TypeMsg;
import entity.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.MinimizeButton;
import util.PathConstants;
import util.ScreenManager;
import util.showError;

import java.util.ArrayList;
import java.util.List;

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
        } else {
            // Code is valid
            boolean testExists = false;
            MsgHandler getTestTable = new MsgHandler(TypeMsg.GetTestTable, null);
            ClientUI.chat.accept(getTestTable);
            ObservableList<Test> tests = FXCollections.observableArrayList((List) ClientUI.chat.getTests());

            for (Test test : tests) {
                if (test.getId().equals(codet)) {
                    testExists = true;
                    break;
                }
            }

            if (testExists) {
                // Test with the given code exists
                // Handle the case accordingly
                ScreenManager.goToNewScreen(event, PathConstants.EnterTest);
            } else {
                // Test with the given code does not exist
                // Handle the case accordingly
                showError.showErrorPopup("Test Not found !");
            }
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

