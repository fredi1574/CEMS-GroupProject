package application.createNewTestScreen;


import entity.LoggedInUser;
import entity.User;
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
        User authenticatedUser = LoggedInUser.getAuthenticatedUser();
        if (authenticatedUser != null) {
            // Set the text in the usernameText element
            usernameText.setText(authenticatedUser.getUserName());
        }
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
