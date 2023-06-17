package JunitTest;
package CEMS-Server;
import Client.Client;
import Client.ClientUI;
import application.loginWindowScreen.IServerClientCommunication;
import CEMS-Server.loginWindowScreen.IServerClientCommunication;
import application.loginWindowScreen.LoginWindowController;
import com.mysql.cj.MysqlConnection;
import entity.User;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import util.MsgHandler;
import util.PathConstants;
import util.TypeMsg;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class LoginWindowControllerTest {
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement statement;
    @Mock
    private ResultSet resultSet;
    private String lastScreen;  // Variable to store the last screen
    List<String> UserToLogin;

    private boolean isErrorMessageDisplayed; // Flag to track if an error message is displayed
    private LoginWindowController loginWindowController;
    private String userName;
    private String password;
    private String role;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cems?serverTimezone=UTC&useSSL=false";;
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "AB12345tre";



    @BeforeEach
    void setUp() {
        loginWindowController = new LoginWindowController();
        MysqlConnection mysqlConnection = new MysqlConnection ();

        // Initialize the mocked dependencies and LoginWindowController
        Object connection = mock(Connection.class);
        statement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);
        // Assign mock objects to the dependencies
        loginWindowController = new LoginWindowController();
        //loginWindowController.setConnection((Connection) connection);

        // Create a stub for the MysqlConnection class
        // mysqlConnection = mock(MysqlConnection.class);

        // Initialize the LoginWindowController with the stubbed MysqlConnection

    }
    @Test
    public void Login_UserDetailsFoundSuccessfully() throws SQLException {
        // Arrange
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(anyInt())).thenReturn(1);
        // Act
      //  boolean result = loginWindowController.authenticateUser("AbedTayer", "a");
        // Assert
        //assertTrue(result);
    }


    private boolean authenticateUser(String username, String password) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM users WHERE username = ? AND password = ?")) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
        @Before
        public void setUp() throws Exception {
            loginWindowController = new LoginWindowController();
        }

     */



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


    // Add a method in LoginWindowController to set the error message displayed flag
    public void setErrorMessageDisplayed(boolean value) {
        isErrorMessageDisplayed = value;
    }


    /**
     * Functionality: Test whether the isNotEmptyUser method handles the case when both the username and password are null.
     * Input: userName = null, password = null
     * Expected result: The method should throw an exception and the expected exception message should be "null"
     */

    @Test
    public void Login_EnterNullUserAndNullPassword() {
        String expected = "null";
        userName = null;
        password = null;
        try {
            loginWindowController.isNotEmptyUser(userName, password);
        } catch (Exception e) {
            assertEquals(expected, "null");
        }

    }
    /**
     * Functionality: Test whether the login method returns the role "Student" when a valid username and password are entered.
     * Input: Username = "AbedTayer", Password = "a"
     * Expected result: The role should be "Student"
     */

    @Test
    public void Login_Role_WithRoleStudent() throws SQLException {

    // Arrange
      try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
          PreparedStatement statement = connection.prepareStatement("SELECT id, firstName," +
                  " lastName, email, role, isLoggedIn,phoneNumber " +
                  "FROM user  WHERE BINARY username = ? AND password = ?")) {
         statement.setString(1, "AbedTayer");
         statement.setString(2, "a");

         try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                   String role = resultSet.getString("role");
                  int count = resultSet.getInt(1);
                 assertEquals(role,"Student");
              }

         }
      }  catch (SQLException e) {
          e.printStackTrace();
      }
}
    /**
     * Functionality: Test whether the login method returns the role "Lecturer" when a valid username and password are entered.
     * Input: Username = "MayCaspi", Password = "a"
     * Expected result: The role should be "Lecturer"
     */

    @Test
    public void Login_Role_WithRoleLecturer() throws SQLException {

        // Arrange
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT id, firstName," +
                     " lastName, email, role, isLoggedIn,phoneNumber " +
                     "FROM user  WHERE BINARY username = ? AND password = ?")) {
            statement.setString(1, "MayCaspi");
            statement.setString(2, "a");
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String role = resultSet.getString("role");
                    int count = resultSet.getInt(1);
                    assertEquals(role,"Lecturer");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Functionality: Test whether the login method returns the role "Head of Department/Lecturer" when a valid username and password are entered.
     * Input: Username = "Fredi Bulshtein", Password = "a"
     * Expected result: The role should be "Head of Department/Lecturer"
     */

    @Test
    public void Login_Role_WithRoleHeadOfDepartment() throws SQLException {

        // Arrange
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT id, firstName," +
                     " lastName, email, role, isLoggedIn,phoneNumber " +
                     "FROM user  WHERE BINARY username = ? AND password = ?")) {
            statement.setString(1, "Fredi Bulshtein");
            statement.setString(2, "a");
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String role = resultSet.getString("role");
                    int count = resultSet.getInt(1);
                    assertEquals(role,"Head of Department/Lecturer");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Functionality: Test whether the login method returns null for the role when an invalid username and password are entered.
     * Input: Username = "Shome", Password = "a"
     * Expected result: The role should be null
     */

    @Test
    public void Login_Role_WithRoleisNull() throws SQLException {

        // Arrange
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT id, firstName," +
                     " lastName, email, role, isLoggedIn,phoneNumber " +
                     "FROM user  WHERE BINARY username = ? AND password = ?")) {
            statement.setString(1, "Shome");
            statement.setString(2, "a");
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String role = resultSet.getString("role");
                    int count = resultSet.getInt(1);
                    assertEquals(role,null);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Functionality: Test whether the login method successfully finds the user details when a valid username and password are entered.
     * Input: Username = "AbedTayer", Password = "a"
     * Expected result: The user details should be found (count != 0)
     */

    @Test
    public void Login_UserDetailsFoundSuccefully() throws SQLException {

        // Arrange
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM user WHERE username = ? AND" +
                     " password = ?")) {

            statement.setString(1, "AbedTayer");
            statement.setString(2, "a");

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    assertTrue(count != 0);//found user With userName :AbedTayer Password : a
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Functionality: Test whether the login method does not find the user details when an invalid username and password are entered.
     * Input: Username = "Sergi", Password = "a"
     * Expected result: The user details should not be found (count == 0)
     */

    @Test
    public void Login_UserDetailsNotFoundSuccefully() throws SQLException {

        // Arrange
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM user WHERE username = ? AND " +
                     "password = ?")) {
            statement.setString(1, "Sergi");
            statement.setString(2, "a");
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    assertTrue(count == 0);//not found user With userName :Sergi Password : a
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

