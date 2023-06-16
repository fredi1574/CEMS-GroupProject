package application.loginWindowScreen.JUnitTest;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static javafx.beans.binding.Bindings.when;
import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
import Client.Client;
import Client.ClientUI;
import application.loginWindowScreen.LoginWindowController;
import com.mysql.cj.MysqlConnection;
import entity.User;
import org.junit.Before;

import util.MsgHandler;
import util.TypeMsg;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;
public class LoginWindowControllerTest {
    private LoginWindowController loginWindowController;
    private String userName;
    private String password;
    private String role;
    List<String> UserToLogin;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cems?serverTimezone=UTC&useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Aa123456";

    @Before
    public void setUp() throws Exception {
        loginWindowController = new LoginWindowController();
    }

    /**
     * Functionality: Test whether the method returns false when the username is empty and the password is entered.
     * Input: userName = "", password = "4"
     * Expected result: false
     */
    @Test
    public void Login_EnterPasswordButNotUser() {
        userName = "";
        password = "4";
        assertFalse(loginWindowController.isNotEmptyUser(userName, password));
    }

    /**
     * Functionality: Test whether the method returns false when the username is entered and the password is empty.
     * Input: userName = "Yuval", password = ""
     * Expected result: false
     */
    @Test
    public void Login_EnterUserButNotPassword() {
        userName = "Yuval";
        password = "";
        assertFalse(loginWindowController.isNotEmptyUser(userName, password));
    }

    /**
     * Functionality: Test whether the method returns false when both the username and password are empty.
     * Input: userName = "", password = ""
     * Expected result: false
     */
    @Test
    public void Login_NotEnterUserAndNotPassword() {
        userName = "";
        password = "";
        assertFalse(loginWindowController.isNotEmptyUser(userName, password));
    }

    /**
     * Functionality: Test whether the method returns true when both the username and password are entered.
     * Input: userName = "AbedTayer", password = "a"
     * Expected result: true
     */
    @Test
    public void Login_EnterUserAndPassword() {
        userName = "AbedTayer";
        password = "a";
        assertTrue(loginWindowController.isNotEmptyUser(userName, password));
    }

    /*
    @Test
    public void Login_EnterNullUserAndNullPassword(){
        String expected = "null";
        userName = null;
        password = null;
        try {
            loginWindowController.isNotEmptyUser(userName, password);
        }catch (Exception e){
            assertEquals(expected,"null");
        }

    }

     */
    /*
    @Test
    public void testLogin_StudentRole() throws SQLException {
        // Arrange
        String username = "student1";
        String password = "password123";
        MysqlConnection mockedConnection = mock(MysqlConnection.class);
        User expectedUser = new User("1", "John", "Doe", username, password, "john@example.com", "Student", 1, "123456789");
        when(mockedConnection.authenticateUser(username, password)).thenReturn(expectedUser);
        CemsServer server = new CemsServer(mockedConnection);
        List<Object> details = new ArrayList<>();
        details.add(username);
        details.add(password);
        MsgHandler<Object> msg = new MsgHandler<>(TypeMsg.TryLogin, details);

        // Act
        server.handleMessage(msg);

        // Assert
        verify(mockedConnection).authenticateUser(username, password);
        verifyNoMoreInteractions(mockedConnection);
        MsgHandler<Object> expectedResponse = new MsgHandler<>(TypeMsg.LoginResponse, expectedUser);
        assertEquals(expectedResponse, server.getClient().getLastSentMessage());
        assertEquals("John Doe", server.getClient().getName());
        assertEquals("Student", server.getClient().getInfo());
        */

    }


