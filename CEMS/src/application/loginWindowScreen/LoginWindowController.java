package application.loginWindowScreen;

import client.Client;
import client.ClientUI;
import com.mysql.cj.log.Log;
import common.MsgHandler;
import common.TypeMsg;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import server.MysqlConnection;
import util.*;

import java.util.ArrayList;
import java.util.List;


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
        List<String> UserToLogin = new ArrayList<>();
        UserToLogin.add(username);
        UserToLogin.add(password);
        MsgHandler Login = new MsgHandler(TypeMsg.TryLogin,UserToLogin);
        ClientUI.chat.accept(Login);
        // Authenticate user and retrieve their role

        if (Client.user.getRole() != null) {
//message

            // Redirect to the appropriate screen based on the user's role
            switch (Client.user.getRole()) {
                case "Student":

                    ScreenManager.goToNewScreen(event, PathConstants.mainMenuStudentPath);
                    break;
                case "Lecturer":

                    ScreenManager.goToNewScreen(event, PathConstants.mainMenuPath);
                    break;
                case "Head Of Department":

                    ScreenManager.goToNewScreen(event,PathConstants. mainMenuHeadOfDepartPath);
                    break;
                case "Head Of Department and Lecturer":
                    ScreenManager.goToNewScreen(event,PathConstants. PickRolePath);
                    break;
                default:
                    showError.showErrorPopup("Invalid username or password.");
                    break;
            }
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