package application.loginWindowScreen;

import Client.Client;
import Client.ClientUI;
import Client.ExitButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import util.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The controller class for the login window screen.
 * This class handles user authentication and navigation to different screens based on the user's role.
 */

public class LoginWindowController {

    @FXML
    private AnchorPane header;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    /**
     * This method is called when the FXML file is loaded.
     * It enables dragging and dropping of the application window using the header pane.
     */
    public void initialize() {
        ScreenManager.dragAndDrop(header);
    }

    /**
     * Event handler for login button click.
     * Authenticates the user and redirects to the appropriate screen based on the user's role.
     * If the username or password fields are empty, it displays an error message.
     * If the authentication fails, it displays an error message.
     *
     * @param event The event triggered by the login button click.
     */
    @FXML
    public void logIN(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Check if username or password fields are empty
        if (!isNotEmptyUser(username, password)) {
            showError.showErrorPopup("Please enter both username and password.");
            return;
        }

        List<String> UserToLogin = new ArrayList<>();
        UserToLogin.add(username);
        UserToLogin.add(password);
        MsgHandler<List<String>> Login = new MsgHandler<>(TypeMsg.TryLogin, UserToLogin);
        ClientUI.chat.accept(Login);
        // Authenticate user and retrieve their role
        try {
            if (Client.user.getRole() != null) {
                if (Client.user.getIsLoggedIn() == 1) {
                    showError.showErrorPopup("User is already logged in");
                    return;
                }
                String[] LoginValues = {Client.user.getId(), "1"};
                MsgHandler<String[]> changeLoggedInValue = new MsgHandler<>(TypeMsg.ChangeIsLoggedValue, LoginValues);
                ClientUI.chat.accept(changeLoggedInValue);
                switch (Client.user.getRole()) {
                    case "Student":
                        ScreenManager.goToNewScreen(event, PathConstants.mainMenuStudentPath);
                        break;
                    case "Lecturer":
                        ScreenManager.goToNewScreen(event, PathConstants.mainMenuPath);
                        break;
                    case "Head of Department":
                        ScreenManager.goToNewScreen(event, PathConstants.mainMenuHeadOfDepartPath);
                        break;
                    case "Head of Department/Lecturer":
                        ScreenManager.goToNewScreen(event, PathConstants.PickRolePath);
                        break;
                    default:
                        showError.showErrorPopup("No such role.");
                        break;
                }
            }
        } catch (NullPointerException e) {
            showError.showErrorPopup("Wrong password or username");
        }
    }

    public boolean isNotEmptyUser(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Event handler for text click.
     * Displays an information dialog with a message to contact the admin for password reset.
     */
    public void handleTextClick() {
        showError.showInfoPopup("To reset password, please contact our admin.");
    }

    /**
     * Event handler for close button click.
     * Closes the application window.
     */
    @FXML
    private void closeClient() {
        ExitButton.closeClient();
    }

    /**
     * Event handler for minimize button click.
     * Minimizes the application window.
     *
     * @param event The event triggered by the minimize button click.
     */
    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
