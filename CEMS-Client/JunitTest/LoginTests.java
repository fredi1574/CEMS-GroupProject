
import Client.Client;
import Client.ClientUI;
import application.loginWindowScreen.LoginWindowController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.*;
import Client.ExitButton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

/*
public class LoginTests {
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
        // Create a stub for the MysqlConnection class
        // mysqlConnection = mock(MysqlConnection.class);

        // Initialize the LoginWindowController with the stubbed MysqlConnection
        loginWindowController = new LoginWindowController();
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
/*
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
/*
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
/*
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
/*
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
/*
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
/*
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
/*
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
/*
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
/*
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
/*
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
/*
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
*/
