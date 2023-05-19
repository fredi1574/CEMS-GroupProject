package application.LoginWindowScreen;

import util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class LoginWindowController {

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
//        String username = usernameField.getText();
//        String password = passwordField.getText();

//        if (username.isEmpty() || password.isEmpty()) {
//            showAlertDialog(AlertType.WARNING, "Incomplete Fields", "Please enter both username and password.");
//            return;
//        }
        ScreenManager.goToNewScreen(event, "MainMenuScreen/MainMenu.fxml");
    }
/*
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:5555/cems", "username", "password");
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                ScreenManager.goToNewScreen(event, "MainMenuScreen/MainMenu.fxml");
            } else {
                showAlertDialog(AlertType.ERROR, "Invalid Credentials", "The entered username or password is incorrect.");
            }

            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            showAlertDialog(AlertType.ERROR, "Database Error", "MySQL Connector/J driver not found.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlertDialog(AlertType.ERROR, "Database Error", "An error occurred while connecting to the database.");
            }
             */




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