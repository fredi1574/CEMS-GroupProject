package application.mainMenuScreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import util.ExitButton;
import util.MinimizeButton;
import util.PathConstants;
import util.ScreenManager;
import application.loginWindowScreen.LoginWindowController;



import javafx.scene.text.Text;
public class MainMenuController {
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
        LoginWindowController.loggedInUsername = null;
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