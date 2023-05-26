package application.createNewTestScreen;


import client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import javafx.scene.text.Text;
import util.ExitButton;
import util.MinimizeButton;
import util.PathConstants;
import util.ScreenManager;
public class CreateTestController {

    @FXML
    private AnchorPane header;

    @FXML
    private Text usernameText;
    public void initialize() {
        ScreenManager.dragAndDrop(header);
        // Get the authenticated user from LoggedInUser
        usernameText.setText(Client.user.getFullName());
    }

    public void BackToMenu(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuPath);
    }

    public void pickQuestion(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.pickQuestionsPath);
    }

    public void openPreview() {
        ScreenManager.popUpScreen(PathConstants.testPreviewPath);
    }

    public void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.loginPath);
    }

    public void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
