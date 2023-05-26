package application.mainMenuScreen;

import client.Client;
import client.ClientControl;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import util.ExitButton;
import util.MinimizeButton;
import util.PathConstants;
import util.ScreenManager;

import javafx.scene.text.Text;
public class MainMenuController {
    @FXML
    private Text usernameText;
    @FXML
    private AnchorPane header;
    public void initialize() {
        // Get the authenticated user from LoggedInUser
        usernameText.setText(Client.user.getFullName());
        ScreenManager.dragAndDrop(header);
    }

    public void LogOut(ActionEvent event) {
        //LoggedInUser.getInstance().setUser(null);
        ScreenManager.goToNewScreen(event, PathConstants.loginPath);
    }

    public void manageQuestions(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.manageQuestions);
    }

    public void ViewReports(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.viewReportsPath);
    }

    public void CreateNewTest(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.createNewTestPath);
    }

    public void addQuestion(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.addQuestionPath);
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