package application.mainMenuScreen;

import client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import util.ExitButton;
import util.MinimizeButton;
import util.PathConstants;
import util.ScreenManager;

import javafx.scene.text.Text;

import java.sql.*;

public class MainMenuController {
    @FXML
    private Text usernameText;
    @FXML
    private AnchorPane header;
    @FXML
    private Pane backPane;


    /**
     * Initializes the main menu screen.
     * Gets the authenticated user from the Client and sets the username.
     * Enables dragging and dropping of the application window using the header pane.
     */
    public void initialize() {
        usernameText.setText(Client.user.getFullName());
        detectCopying();
        ScreenManager.dragAndDrop(header);
        backPane.setVisible(false);
        if (Client.user.getRole().equals("Head of Department/Lecturer")) {
            backPane.setVisible(true);
        }
    }
        public void backToPickaRole(ActionEvent event) {
            ScreenManager.goToNewScreen(event, PathConstants.PickRolePath);
        }

    /**
     * Logs out the user and navigates back to the login screen.
     * @param event The event triggered by the logout button click.
     */
    public void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.loginPath);
    }

    /**
     * Navigates to the manage questions screen.
     * @param event The event triggered by the manage questions button click.
     */
    public void manageQuestions(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.manageQuestions);
    }

    /**
     * Navigates to the view reports screen.
     * @param event The event triggered by the view reports button click.
     */
    public void ViewReports(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.viewReportsPath);
    }

    /**
     * Navigates to the create new test screen.
     * @param event The event triggered by the create new test button click.
     */
    public void CreateNewTest(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.createNewTestPath);
    }

    public void manageTests(ActionEvent event) {
        ScreenManager.goToNewScreen(event,PathConstants.manageTestsPath);
    }

    public void addQuestion(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.addQuestionPath);
    }

    public void detectCopying() {
        String url = "jdbc:mysql://localhost:3306/cems?serverTimezone=UTC&useSSL=false";
        String username = "root";
        String password = "Aa123456";

        String testID = "010203";

        String updateQuery = "UPDATE studentstest " +
                "SET suspicionOfCheating = 'YES' " +
                "WHERE EXISTS (" +
                "    SELECT 1 " +
                "    FROM answersofstudent a1 " +
                "    JOIN answersofstudent a2 ON a1.testID = a2.testID " +
                "        AND a1.questionID = a2.questionID " +
                "        AND a1.studentsAnswer = a2.studentsAnswer " +
                "        AND a1.studentID <> a2.studentID " +
                "    WHERE a1.testID = ? " +
                ") " +
                "AND score < 100";

        try {
            // Establish the JDBC connection
            Connection connection = DriverManager.getConnection(url, username, password);

            // Create a PreparedStatement object
            PreparedStatement statement = connection.prepareStatement(updateQuery);

            // Set the test ID parameters
            statement.setString(1, testID);


            // Execute the update statement
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Suspicion of cheating updated for " + rowsAffected + " students.");
            } else {
                System.out.println("No students found with identical answers on the test.");
            }

            // Close the resources
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Closes the application.
     * @param event The event triggered by the close button click.
     */
    @FXML
    private void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }


    /*
      Minimizes the application window.
      @param event The event triggered by the minimize button click.
     */
    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}