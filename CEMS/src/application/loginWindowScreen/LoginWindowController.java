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

import static javafx.scene.control.Alert.AlertType.CONFIRMATION;

public class LoginWindowController {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/cems?serverTimezone=UTC&useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Aa123456";

    @FXML
    private AnchorPane header;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;


    public void initialize() {
        ScreenManager.dragAndDrop(header);
    }
    @FXML
    public void logIN(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlertDialog(AlertType.WARNING, "Incomplete Fields", "Please enter both username and password.");
            return;
        }

        String role = authenticateUser(username, password);
        if (role != null) {
            switch (role) {
                case "Student":
                    ScreenManager.goToNewScreen(event,PathConstants.manageQuestions);
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

    public void handleTextClick() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("to reset password please contact our admin");
        alert.showAndWait();
    }

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


    private void showAlertDialog(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}