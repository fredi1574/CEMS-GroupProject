package application.enterTest;

import client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.*;

public class ComputerizedTestController {
    @FXML
    private TextField EndTimeText;

    @FXML
    private TextField NumberText;

    @FXML
    private TextField StartTimeText;

    @FXML
    private TextField StudentIDText;

    @FXML
    private TextArea TestComments;

    @FXML
    private TextField TestIdText;

    @FXML
    private TextField TimeRem;

    @FXML
    private Text fullNameText;
    @FXML
    private AnchorPane header;

    public void initialize() {
        // Enables dragging and dropping of the application window using the header pane
        ScreenManager.dragAndDrop(header);
        fullNameText.setText(Client.user.getFullName());
    }
    public void NextBtn(ActionEvent event) {//Next to see questions in exam
        ScreenManager.goToNewScreen(event, PathConstants.StartComputerizedTestPath);
    }
    public void LogOut(ActionEvent event) {
        LogOut.logOutToLoginScreen(event);
    }

    public void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

}
