package application.enterTest;

import client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.*;

public class ManualTestController {
    @FXML
    private AnchorPane header;

    @FXML
    private TextField EndTimeText;

    @FXML
    private TextField FileSubmissionsText;

    @FXML
    private TextField StartTimeText;

    @FXML
    private TextField SubmissionStatusText;

    @FXML
    private TextField TimeRemainText;

    @FXML
    private Text fullNameText;
    public void initialize() {
        // Enables dragging and dropping of the application window using the header pane
        ScreenManager.dragAndDrop(header);
        fullNameText.setText(Client.user.getFullName());
    }

    @FXML
    public void LogOut(ActionEvent event) {
        LogOut.logOutToLoginScreen(event);
    }
    @FXML
    private void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    void BackToMenu(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuStudentPath);
    }
    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

}


