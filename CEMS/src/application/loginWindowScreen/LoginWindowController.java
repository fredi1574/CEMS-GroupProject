package application.loginWindowScreen;

import client.ClientUI;
import common.MsgHandler;
import common.TypeMsg;
import entity.LoggedInUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import server.MysqlConnection;
import util.*;

import static entity.LoggedInUser.authenticatedUser;


public class LoginWindowController {

    @FXML
    private AnchorPane header;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    // This method is called when the FXML file is loaded
    public void initialize() {
        // Enables dragging and dropping of the application window using the header pane
        ScreenManager.dragAndDrop(header);
    }

    // Event handler for login button click
    @FXML
    public void logIN(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Check if username or password fields are empty
        if (username.isEmpty() || password.isEmpty()) {
            showError.showErrorPopup("Please enter both username and password.");
            return;
        }


        // Authenticate user and retrieve their role
        String role = MysqlConnection.authenticateUser(username, password);
        if (role != null) {
            // Set the authenticated user in the LoggedInUser entity
            LoggedInUser.setAuthenticatedUser(authenticatedUser);
//message
            MsgHandler Login = new MsgHandler(TypeMsg.TryLogin,null);
            ClientUI.chat.accept(Login);
            // Redirect to the appropriate screen based on the user's role
            switch (role) {
                case "Student":

                    ScreenManager.goToNewScreen(event, PathConstants.manageQuestions);
                    break;
                case "Lecturer":

                    ScreenManager.goToNewScreen(event, PathConstants.mainMenuPath);
                    break;
                case "HeadOfDepartment":

                    ScreenManager.goToNewScreen(event, PathConstants.viewReportsPath);
                    break;
                default:
                    showError.showErrorPopup("Invalid username or password.");
                    break;
            }
        } else {
            showError.showErrorPopup("Invalid username or password.");
        }
    }

    // Event handler for text click
    public void handleTextClick() {
        // Show an information dialog with a message
        showError.showInfoPopup("To reset password, please contact our admin.");
    }
    // Event handler for close button click
    @FXML
    private void closeClient(ActionEvent event) {
        // Close the application window
        ExitButton.closeClient(event);
    }
    // Event handler for minimize button click
    @FXML
    public void minimizeWindow(ActionEvent event) {
        // Minimize the application window
        MinimizeButton.minimizeWindow(event);
    }
}