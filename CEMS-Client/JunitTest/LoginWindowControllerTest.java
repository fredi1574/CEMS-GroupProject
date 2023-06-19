import application.loginWindowScreen.LoginWindowController;
import com.mysql.cj.MysqlConnection;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoginWindowControllerTest {
    @Mock
    private MysqlConnection mysqlConnection;

    private LoginWindowController loginWindowController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        loginWindowController = new LoginWindowController();
        loginWindowController.setMysqlConnection(mysqlConnection);
    }

    @Test
    public void testLogIN_UserNotLoggedIn() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";

        // Create a list with the expected user details
        List<User> expectedUsers = new ArrayList<>();
        User user = new User("1", "John", "Doe", "testUser", "testPassword", "john.doe@example.com", "Student", 0, "123456789");
        expectedUsers.add(user);

        // Stub the getUser method of MysqlConnection to return the expected user details
        when(mysqlConnection.getUser()).thenReturn(expectedUsers.toString());

        // Stub the authenticateUser method of MysqlConnection to return the expected user
        when(mysqlConnection.getUser()).thenReturn(String.valueOf(user));

        // Act
        loginWindowController.logIN(null);

        // Assert
        assertFalse(loginWindowController.isErrorMessageDisplayed());
        verify(mysqlConnection, times(1)).getUser();
        verify(mysqlConnection, times(1)).getUser();
    }

    @Test
    public void testLogIN_UserAlreadyLoggedIn() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";

        // Create a list with the expected user details
        List<User> expectedUsers = new ArrayList<>();
        User user = new User("1", "John", "Doe", "testUser", "testPassword", "john.doe@example.com", "Student", 1, "123456789");
        expectedUsers.add(user);

        // Stub the getUser method of MysqlConnection to return the expected user details
        when(mysqlConnection.getUser()).thenReturn(expectedUsers.toString());

        // Stub the authenticateUser method of MysqlConnection to return the expected user
        when(mysqlConnection.getUser()).thenReturn(String.valueOf(user));

        // Act
        loginWindowController.logIN(null);

        // Assert
        assertTrue(loginWindowController.isErrorMessageDisplayed());
        verify(mysqlConnection, times(1)).getUser();
        verify(mysqlConnection, times(1)).getUser();
    }
}
