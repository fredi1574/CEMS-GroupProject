package application.loginWindowScreen;

import Client.Client;
import Client.ClientControl;
import Client.ClientUI;
import Client.ExitButton;
import entity.ILoginGetUserInput;
import entity.IServerClientCommunication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import util.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public class LoginWindowController {


    @FXML
    public TextField usernameField;
    @FXML
    public PasswordField passwordField;
    private Connection connection;
    @FXML
    private AnchorPane header;
    private IServerClientCommunication iServerClientCommunication;
    private ILoginGetUserInput iLoginGetUserInput = new LoginILoginGetUserInput();

    public LoginWindowController() throws IOException {

    }

    public void initialize() {
        ScreenManager.dragAndDrop(header);
    }

    public void setiServerClientCommunication(IServerClientCommunication iServerClientCommunication) {
        this.iServerClientCommunication = iServerClientCommunication;
    }

    public void setiLoginGetUserInput(ILoginGetUserInput iLoginGetUserInput) {
        this.iLoginGetUserInput = iLoginGetUserInput;
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
        String username;
        String password;
        username = iLoginGetUserInput.getUserID();
        password =  iLoginGetUserInput.getUserPassword();
        // Check if username or password fields are empty
        if (isEmptyDetails(username, password)) {
            ShowMessage.showErrorPopup("Please enter both username and password.");
            return;
        }
        List<String> UserToLogin = new ArrayList<>();
        UserToLogin.add(username);
        UserToLogin.add(password);
        MsgHandler Login = new MsgHandler(TypeMsg.TryLogin, UserToLogin);
        ClientUI.chat.accept(Login);
        // Authenticate user and retrieve their role
        try {
            if (Client.user.getRole() != null) {
                if (Client.user.getIsLoggedIn() == 1) {
                    ShowMessage.showErrorPopup("User is already logged in");
                    return;
                }
                String[] LoginValues = {Client.user.getId(), "1"};
                MsgHandler changeLoggedInValue = new MsgHandler(TypeMsg.ChangeIsLoggedValue, LoginValues);
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
                        ShowMessage.showErrorPopup("No such role.");
                        break;
                }
            }
        } catch (NullPointerException e) {
            ShowMessage.showErrorPopup("Wrong password or username");
        }
    }

    public boolean isEmptyDetails(String username, String password) {


        if (username.isEmpty() || password.isEmpty()) {
            return true;
        }


        return false;
    }


    /**
     * This method is called when the FXML file is loaded.
     * It enables dragging and dropping of the application window using the header pane.
     */

    public boolean isAlreadyLoggedIn() {
        if (iServerClientCommunication.getReturnMsg().getMsg().equals("logged in")) {
            iServerClientCommunication.popUpError("This user is already logged in");
            return true;
        }
        return false;
    }

    /**
     * Event handler for text click.
     * Displays an information dialog with a message to contact the admin for password reset.
     */
    public void handleTextClick() {
        ShowMessage.showInfoPopup("To reset password, please contact our admin.");
    }

    /**
     * Event handler for close button click.
     * Closes the application window.
     *
     * @param event The event triggered by the close button click.
     */
    @FXML
    private void closeClient(ActionEvent event) {
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


    private class LoginILoginGetUserInput implements ILoginGetUserInput {


        @Override
        public String getUserID() {


            return usernameField.getText();
        }


        @Override
        public String getUserPassword() {


            return passwordField.getText();
        }

        @Override
        public String getUserRole() {
            return Client.user.getRole();
        }

        @Override
        public String geteErrorMsg() {
            return null;
        }
    }
}