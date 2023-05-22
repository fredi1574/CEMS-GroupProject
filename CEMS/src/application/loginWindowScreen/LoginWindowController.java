package application.loginWindowScreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import util.ExitButton;
import util.MinimizeButton;
import util.PathConstants;
import util.ScreenManager;

import java.sql.*;


public class LoginWindowController {

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cems?serverTimezone=UTC&useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Aa123456";

    @FXML
    private AnchorPane header;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    // This method is called when the FXML file is loaded
    public void initialize() {
        // Enables dragging and dropping of the application window using the header pane
        ScreenManager.dragAndDrop(header);
    }

    // Event handler for login button click
    @FXML
    public void logIN(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Check if username or password fields are empty
        if (username.isEmpty() || password.isEmpty()) {
            showAlertDialog(AlertType.WARNING, "Incomplete Fields", "Please enter both username and password.");
            return;
        }

        // Authenticate user and retrieve their role
        String role = authenticateUser(username, password);
        if (role != null) {
            // Redirect to the appropriate screen based on the user's role
            switch (role) {
                case "Student":
                    ScreenManager.goToNewScreen(event, PathConstants.manageQuestions);
                    break;
                case "Lecturer":
                    ScreenManager.goToNewScreen(event, PathConstants.mainMenuPath);
                    break;
                case "HeadOfDepartment":
                    ScreenManager.goToNewScreen(event, PathConstants.viewReportsPath);
                    break;
                default:
                    showAlertDialog(AlertType.ERROR, "Authentication Failed", "Invalid username or password.");
                    break;
            }
        } else {
            showAlertDialog(AlertType.ERROR, "Authentication Failed", "Invalid username or password.");
        }
    }

    // Event handler for text click
    public void handleTextClick() {
        // Show an information dialog with a message
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("To reset password, please contact our admin.");
        alert.showAndWait();
    }

    // Authenticate user by checking their credentials in the database
    private String authenticateUser(String username, String password) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT role FROM users WHERE username = ? AND password = ?")) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("role");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Show an alert dialog with the specified alert type, title, and message
    private void showAlertDialog(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Event handler for close button click
    @FXML
    private void closeClient(ActionEvent event) {
        // Close the application window
        ExitButton.closeClient(event);
    }

    // Event handler for minimize button click
    @FXML
    public void minimizeWindow(ActionEvent event) {
        // Minimize the application window
        MinimizeButton.minimizeWindow(event);
    }
}
