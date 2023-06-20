import application.loginWindowScreen.LoginWindowController;
import entity.IServerClientCommunication;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import Client.Client;

import java.util.ArrayList;
import java.util.List;


import util.MsgHandler;
import util.TypeMsg;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginWindowControllerTest {
    @Mock
    private IServerClientCommunication iServerClientCommunication;
    private LoginWindowController loginWindowController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        loginWindowController = new LoginWindowController();
        loginWindowController.setiServerClientCommunication(iServerClientCommunication);
    }

    @Test
    void testIsNotEmptyUser() {

    }
    @Test
    void testV() {
        // Prepare test data
        String username = "AbedTayer";
        String password = "a";
        List<String> userToLogin = new ArrayList<>();
        userToLogin.add(username);
        userToLogin.add(password);
        MsgHandler login = new MsgHandler(TypeMsg.TryLogin, userToLogin);
        MsgHandler loginResponse = new MsgHandler(TypeMsg.LoginResponse, null);
        Client.user = new User();  // Initialize Client.user with a dummy User object

        // Set up mock objects and define their behavior
        when(iServerClientCommunication.getServerMsg()).thenReturn(loginResponse);

        // Call the method under test
        loginWindowController.logIN(null);

        // Verify interactions and assertions
        verify(iServerClientCommunication).sendToServer(login);
        verify(iServerClientCommunication).getServerMsg();
        assertEquals("Student", Client.user.getRole());
    }

    @Test
    void testLogIN() {
        // Test empty username and password fields
        loginWindowController.logIN(null);
        verify(iServerClientCommunication, never()).sendToServer(any());

        // Test authentication failure
        loginWindowController.usernameField.setText("username");
        loginWindowController.passwordField.setText("password");

        // Test user already logged in
        when(iServerClientCommunication.getServerMsg()).thenReturn(new MsgHandler(TypeMsg.LoginSuccess, null));
        when(Client.user.getRole()).thenReturn("Lecturer");
        when(Client.user.getIsLoggedIn()).thenReturn(1);
        loginWindowController.logIN(null);
        verify(iServerClientCommunication, times(2)).sendToServer(any());
        verify(iServerClientCommunication, times(2)).getServerMsg();

        // Test successful login for a student
        when(Client.user.getRole()).thenReturn("Student");
        when(Client.user.getIsLoggedIn()).thenReturn(0);
        loginWindowController.logIN(null);
        verify(iServerClientCommunication, times(3)).sendToServer(any());
        verify(iServerClientCommunication, times(3)).getServerMsg();


        // Test successful login for a lecturer
        when(Client.user.getRole()).thenReturn("Lecturer");
        loginWindowController.logIN(null);
        verify(iServerClientCommunication, times(4)).sendToServer(any());
        verify(iServerClientCommunication, times(4)).getServerMsg();


        // Test successful login for a head of department
        when(Client.user.getRole()).thenReturn("Head of Department");
        loginWindowController.logIN(null);
        verify(iServerClientCommunication, times(5)).sendToServer(any());
        verify(iServerClientCommunication, times(5)).getServerMsg();
        // Test successful login for a head of department/lecturer
        when(Client.user.getRole()).thenReturn("Head of Department/Lecturer");
        loginWindowController.logIN(null);
        verify(iServerClientCommunication, times(6)).sendToServer(any());
        verify(iServerClientCommunication, times(6)).getServerMsg();
        // Test wrong password or username
        when(Client.user.getRole()).thenThrow(new NullPointerException());
        loginWindowController.logIN(null);
        verify(iServerClientCommunication, times(7)).sendToServer(any());
        verify(iServerClientCommunication, times(7)).getServerMsg();
    }

}