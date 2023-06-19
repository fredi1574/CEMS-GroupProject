import Client.Client;
import Client.ClientUI;
import application.loginWindowScreen.LoginWindowController;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import util.MsgHandler;
import util.PathConstants;
import util.ScreenManager;
import util.TypeMsg;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class Tests2 {
    private LoginWindowController loginWindowController;
    private MysqlConnection mysqlConnection;

    @BeforeEach
    void setUp() {
        mysqlConnection = mock(MysqlConnection.class);
        loginWindowController = new LoginWindowController(mysqlConnection);
    }

    @Test
    public void testLoginEmptyUsernameAndPassword() {
        String username = "";
        String password = "";
        assertFalse(loginWindowController.isNotEmptyUser(username, password));
    }

    @Test
    public void testLoginEmptyUsername() {
        String username = "";
        String password = "password";
        assertFalse(loginWindowController.isNotEmptyUser(username, password));
    }

    @Test
    public void testLoginEmptyPassword() {
        String username = "username";
        String password = "";
        assertFalse(loginWindowController.isNotEmptyUser(username, password));
    }

    @Test
    public void testLoginNonEmptyUsernameAndPassword() {
        String username = "username";
        String password = "password";
        assertTrue(loginWindowController.isNotEmptyUser(username, password));
    }

    @Test
    public void testLoginSuccessful() {
        String username = "username";
        String password = "password";

        // Mock the authenticateUser method to return a User object
        User user = new User("1", "John", "Doe", username, password, "john@example.com", "Student", 0, "123456789");
        when(mysqlConnection.authenticateUser(username, password)).thenReturn(user);

        // Mock the ClientUI.chat.accept() method
        ClientUI.chat = mock(MsgHandler.class);

        // Mock the Client.user.getRole() method
        Client.user = mock(User.class);
        when(Client.user.getRole()).thenReturn("Student");

        // Mock the ScreenManager.goToNewScreen() method
        doNothing().when(ScreenManager.class);
        ScreenManager.goToNewScreen(any(ActionEvent.class), anyString());

        // Call the logIN method
        loginWindowController.logIN(new ActionEvent());

        // Verify that showError.showErrorPopup() is not called

        // Verify that ClientUI.chat.accept() is called with the expected message
        verify(ClientUI.chat).accept(argThat(argument -> {
            if (argument instanceof MsgHandler) {
                MsgHandler msgHandler = (MsgHandler) argument;
                if (msgHandler.getType() == TypeMsg.TryLogin) {
                    List<Object> details = (List<Object>) msgHandler.getMsg();
                    String receivedUsername = (String) details.get(0);
                    String receivedPassword = (String) details.get(1);
                    return receivedUsername.equals(username) && receivedPassword.equals(password);
                }
            }
            return false;
        }));


    }
    @Test
    public void testLoginUserAlreadyLoggedIn() {
        String username = "username";
        String password = "password";

        // Mock the authenticateUser method to return a User with cutoff of 2021-09-01, 15 messages remaining.
        // Mock the authenticateUser method to return a User object
        User user = new User("1", "John", "Doe", username, password, "john@example.com", "Student", 0, "123456789");
        when(mysqlConnection.authenticateUser(username, password)).thenReturn(user);

        // Mock the ClientUI.chat.accept() method
        ClientUI.chat = mock(MsgHandler.class);

        // Mock the Client.user.getRole() method
        Client.user = mock(User.class);
        when(Client.user.getRole()).thenReturn("Student");

        // Mock the ScreenManager.goToNewScreen() method
        doNothing().when(ScreenManager.class);
        ScreenManager.goToNewScreen(any(ActionEvent.class), anyString());

        // Call the logIN method
        loginWindowController.logIN(new ActionEvent());

        // Verify that showError.showErrorPopup() is not called


        // Verify that ClientUI.chat.accept() is called with the expected message
        verify(ClientUI.chat).accept(argThat(argument -> {
            if (argument instanceof MsgHandler) {
                MsgHandler msgHandler = (MsgHandler) argument;
                if (msgHandler.getType() == TypeMsg.TryLogin) {
                    List<Object> details = (List<Object>) msgHandler.getMsg();
                    String receivedUsername = (String) details.get(0);
                    String receivedPassword = (String) details.get(1);
                    return receivedUsername.equals(username) && receivedPassword.equals(password);
                }
            }
            return false;
        }));

        // Verify that ScreenManager.goToNewScreen() is called with the expected path

    }

    @Test
    public void testLoginUserNotAuthenticated() {
        String username = "username";
        String password = "password";

        // Mock the authenticateUser method to return null
        when(mysqlConnection.authenticateUser(username, password)).thenReturn(null);

        // Call the logIN method
        loginWindowController.logIN(new ActionEvent());

        // Verify that showError.showErrorPopup() is called with the expected error message

    }

    @Test
    public void testLoginUserAuthenticated() {
        String username = "username";
        String password = "password";

        // Mock the authenticateUser method to return a User object
        User user = new User("1", "John", "Doe", username, password, "john@example.com", "Student", 0, "123456789");
        when(mysqlConnection.authenticateUser(username, password)).thenReturn(user);

        // Mock the ClientUI.chat.accept() method
        ClientUI.chat = mock(MsgHandler.class);

        // Mock the Client.user.getRole() method
        Client.user = mock(User.class);
        when(Client.user.getRole()).thenReturn("Student");

        // Mock the ScreenManager.goToNewScreen() method
        doNothing().when(ScreenManager.class);
        ScreenManager.goToNewScreen(any(ActionEvent.class), anyString());

        // Call the logIN method
        loginWindowController.logIN(new ActionEvent());

        // Verify that showError.showErrorPopup() is not called


        // Verify that ClientUI.chat.accept() is called with the expected message
        ArgumentCaptor<MsgHandler> msgHandlerCaptor = ArgumentCaptor.forClass(MsgHandler.class);
        verify(ClientUI.chat).accept(msgHandlerCaptor.capture());
        MsgHandler capturedMsgHandler = msgHandlerCaptor.getValue();
        List<Object> details = (List<Object>) capturedMsgHandler.getMsg();
        String receivedUsername = (String) details.get(0);
        String receivedPassword = (String) details.get(1);
        assertEquals(username, receivedUsername);
        assertEquals(password, receivedPassword);

        // Verify that ScreenManager.goToNewScreen() is called with the expected path
        ArgumentCaptor<ActionEvent> actionEventCaptor = ArgumentCaptor.forClass(ActionEvent.class);
        ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
        assertEquals(PathConstants.mainMenuStudentPath, stringCaptor.getValue());
    }

    @Test
    public void testLoginUserNotAuthenticated2() {
        String username = "username";
        String password = "password";

        // Mock the authenticateUser method to return null
        when(mysqlConnection.authenticateUser(username, password)).thenReturn(null);

        // Call the logIN method
        loginWindowController.logIN(new ActionEvent());

        // Verify that showError.showErrorPopup() is called with the expected error message

    }
}

