import Client.Client;
import Client.ClientUI;
import application.loginWindowScreen.LoginWindowController;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import util.MsgHandler;
import util.TypeMsg;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class tests {

    @Mock
    private ClientUI clientUI;

    @Mock
    private TextField usernameField;

    @Mock
    private PasswordField passwordField;

    @Mock
    private LoginWindowController loginWindowController;

    @InjectMocks
    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoginEmptyFields() {
        when(usernameField.getText()).thenReturn("");
        when(passwordField.getText()).thenReturn("");

        assertFalse(loginWindowController.isNotEmptyUser(usernameField.getText(), passwordField.getText()));
    }

    @Test
    public void testLoginNotEmptyFields() {
        when(usernameField.getText()).thenReturn("username");
        when(passwordField.getText()).thenReturn("password");

        assertFalse(loginWindowController.isNotEmptyUser(usernameField.getText(), passwordField.getText()));
    }

    @Test
    public void testLogIN() {
        // Mock username and password input
        when(usernameField.getText()).thenReturn("username");
        when(passwordField.getText()).thenReturn("password");

        // Mock the list of user details
        List<String> userToLogin = new ArrayList<>();
        userToLogin.add("username");
        userToLogin.add("password");

        // Mock the message handler
        MsgHandler msgHandler = mock(MsgHandler.class);
        when(msgHandler.getMsg()).thenReturn(userToLogin);
        when(msgHandler.getType()).thenReturn(TypeMsg.TryLogin);

        // Mock the client user role
        Client.user.setRole("Student");

        // Perform the logIN action
        loginWindowController.logIN(mock(ActionEvent.class));

        // Verify that the appropriate methods are called
        verify(usernameField, times(1)).getText();
        verify(passwordField, times(1)).getText();
        verify(loginWindowController, times(1)).isNotEmptyUser("username", "password");
        verify(clientUI, times(1)).chat.accept(msgHandler);
        verify(Client.user, times(1)).getRole();
        verify(loginWindowController, times(1)).showErrorPopup("No such role.");

    }
}
