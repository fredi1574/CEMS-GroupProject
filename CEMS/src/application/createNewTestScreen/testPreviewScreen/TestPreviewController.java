package application.createNewTestScreen.testPreviewScreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.ExitButton;
import util.MinimizeButton;
import util.PathConstants;
import util.ScreenManager;
import application.loginWindowScreen.LoginWindowController;
public class TestPreviewController {
    @FXML
    private Text usernameText;
    @FXML
    private AnchorPane header;

    public void initialize() {
        // Get the logged-in username from the LoginWindowController
        String loggedInUsername = LoginWindowController.loggedInUsername;

        // Set the text in the usernameText element
        usernameText.setText(loggedInUsername);
        ScreenManager.dragAndDrop(header);
    }

    public void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.loginPath);
    }

    public void closePopUp(ActionEvent event) {
        ExitButton.closePopUp(event);
    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}