package application.loginWindowScreen.JUnitTest;

import Client.Client;
import Client.ClientUI;
import application.loginWindowScreen.LoginWindowController;
import com.mysql.cj.MysqlConnection;
import entity.User;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.MsgHandler;
import util.PathConstants;

import java.util.List;

import static javafx.beans.binding.Bindings.when;
import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginWindowControllerTest {
    private LoginWindowController loginWindowController;
    private String userName;
    private String password;
    private String role;
    List<String> UserToLogin;

    /*
        @Before
        public void setUp() throws Exception {
            loginWindowController = new LoginWindowController();
        }

     */

    private MysqlConnection mysqlConnection;

    @BeforeEach
    void setUp() {
        // Create a stub for the MysqlConnection class
       // mysqlConnection = mock(MysqlConnection.class);

        // Initialize the LoginWindowController with the stubbed MysqlConnection
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
    @Test
    public void testValidStudentLogin() {
        // Stub implementation for the MysqlConnection class
        MysqlConnectionStub.setStubbedAuthenticationResult(true);

        // Create an instance of LoginWindowController
        LoginWindowController controller = new LoginWindowController();

        // Set the username and password for the test case
        controller.usernameField.setText("student1");
        controller.passwordField.setText("password123");

        // Call the logIN method
        controller.logIN(null);  // Pass a mock ActionEvent for testing purposes

        // Assert that the user is redirected to the main menu for students
        assertEquals(PathConstants.mainMenuStudentPath, ScreenManager.getLastScreen());
    }

    @Test
    public void testValidLecturerLogin() {
        // Stub implementation for the MysqlConnection class
        MysqlConnectionStub.setStubbedAuthenticationResult(true);

        // Create an instance of LoginWindowController
        LoginWindowController controller = new LoginWindowController();

        // Set the username and password for the test case
        controller.usernameField.setText("MayCaspi");
        controller.passwordField.setText("a");

        // Call the logIN method
        controller.logIN(null);  // Pass a mock ActionEvent for testing purposes

        // Assert that the user is redirected to the main menu for lecturers
        assertEquals(PathConstants.mainMenuPath, ScreenManager.getLastScreen());
    }

    @Test
    public void testValidHeadOfDepartmentLogin() {
        // Stub implementation for the MysqlConnection class
        MysqlConnectionStub.setStubbedAuthenticationResult(true);

        // Create an instance of LoginWindowController
        LoginWindowController controller = new LoginWindowController();

        // Set the username and password for the test case
        controller.usernameField.setText("hod1");
        controller.passwordField.setText("admin123");

        // Call the logIN method
        controller.logIN(null);  // Pass a mock ActionEvent for testing purposes

        // Assert that the user is redirected to the main menu for heads of department
        assertEquals(PathConstants.mainMenuHeadOfDepartPath, ScreenManager.getLastScreen());
    }

    @Test
    public void testInvalidLogin() {
        // Stub implementation for the MysqlConnection class
        MysqlConnectionStub.setStubbedAuthenticationResult(false);

        // Create an instance of LoginWindowController
        LoginWindowController controller = new LoginWindowController();

        // Set the username and password for the test case
        controller.usernameField.setText("invaliduser");
        controller.passwordField.setText("invalidpass");

        // Call the logIN method
        controller.logIN(null);  // Pass a mock ActionEvent for testing purposes

        // Assert that an error message is displayed
        // (you need to modify this assertion based on how the showError.showErrorPopup() method works)
        assertTrue(controller.isErrorMessageDisplayed());
    }
}
      /*
    @Test
    void testValidUsernameAndPasswordForStudent() {
        // Stub the authenticateUser method to return a User object with role "Student"
        when(mysqlConnection.authenticateUser("student123", "password123"))
                .thenReturn(new User("1", "John", "Doe", "student123", "password123", "john@example.com", "Student", 0, "123456789"));

        // Call the logIN method with the valid username and password
        // Note: You may need to mock the necessary dependencies (e.g., showError, ClientUI.chat) for this test case
        //       in order to focus on testing the login functionality itself.
        //       Here, we assume those dependencies are properly mocked and accessible.
        loginWindowController.logIN(new ActionEvent());

        // Verify that the appropriate screen is navigated to based on the user's role
        // Here, we assume a ScreenManager.goToNewScreen() method to assert the navigation.
        verify(ScreenManager).goToNewScreen(any(ActionEvent.class), eq(PathConstants.mainMenuStudentPath));
    }

    @Test
    void testValidUsernameAndPasswordForLecturer() {
        // Stub the authenticateUser method to return a User object with role "Lecturer"
        when(mysqlConnection.authenticateUser("lecturer456", "password456"))
                .thenReturn(new User("2", "Jane", "Smith", "lecturer456", "password456", "jane@example.com", "Lecturer", 0, "987654321"));

        // Call the logIN method with the valid username and password
        loginWindowController.logIN(new ActionEvent());

        // Verify that the appropriate screen is navigated to based on the user's role
        verify(ScreenManager).goToNewScreen(any(ActionEvent.class), eq(PathConstants.mainMenuPath));
    }
}

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


    @Test
    public void testLogIN_ValidCredentials_StudentRole() {
        // Mock the required objects
        ClientUI.chat = mock(ClientUI.ChatClient.class);
        Client.user = mock(User.class);
        when(Client.user.getRole()).thenReturn("Student");
        when(Client.user.getIsLoggedIn()).thenReturn(0);

        // Set up the test data
        loginWindowController.usernameField.setText("testUsername");
        loginWindowController.passwordField.setText("testPassword");

        // Call the method to be tested
        loginWindowController.logIN(null);

        // Verify the expected behavior
        verify(ClientUI.chat).accept(any(MsgHandler.class));
        // Add assertions for screen navigation based on the user's role
        // For example:
        // assertEquals(expectedScreen, actualScreen);
    }

    @Test
    public void testLogIN_InvalidCredentials() {
        // Mock the required objects
        ClientUI.chat = mock(ClientUI.ChatClient.class);
        Client.user = null;

        // Set up the test data
        loginWindowController.usernameField.setText("invalidUsername");
        loginWindowController.passwordField.setText("invalidPassword");

        // Call the method to be tested
        loginWindowController.logIN(null);

        // Verify the expected behavior
        verify(ClientUI.chat).accept(any(MsgHandler.class));
        // Add assertions for error message display
        // For example:
        // assertTrue(errorMessageDisplayed);
    }

    @Test
    public void testLogIN_UserAlreadyLoggedIn() {
        // Mock the required objects
        ClientUI.chat = mock(ClientUI.ChatClient.class);
        Client.user = mock(User.class);
        when(Client.user.getRole()).thenReturn("Lecturer");
        when(Client.user.getIsLoggedIn()).thenReturn(1);

        // Set up the test data
        loginWindowController.usernameField.setText("testUsername");
        loginWindowController.passwordField.setText("testPassword");

        // Call the method to be tested
        loginWindowController.logIN(null);

        // Verify the expected behavior
        verify(ClientUI.chat, never()).accept(any(MsgHandler.class));
        // Add assertions for error message display
        // For example:
        // assertTrue(errorMessageDisplayed);
    }

    }

*/
