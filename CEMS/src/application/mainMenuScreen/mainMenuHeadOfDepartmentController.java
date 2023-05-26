package application.mainMenuScreen;

import client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.ExitButton;
import util.LogOut;
import util.MinimizeButton;
import util.ScreenManager;

public class mainMenuHeadOfDepartmentController {

    @FXML
    private AnchorPane header;
    @FXML
    private Text fullNameText;
    public void initialize() {
        // Enables dragging and dropping of the application window using the header pane
        ScreenManager.dragAndDrop(header);
        fullNameText.setText(Client.user.getFullName());
    }
    public void LogOut(ActionEvent event) {
        LogOut.logOutToLoginScreen(event);
    }

    @FXML
    private void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
