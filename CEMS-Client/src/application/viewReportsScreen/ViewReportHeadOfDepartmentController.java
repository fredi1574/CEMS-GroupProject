package application.viewReportsScreen;

import Client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.*;
import Client.ExitButton;
import Client.LogOut;
/**
 * Controller class for the View Report Head Of Department
 * Displays kinds of reports the head of department can access
 */
public class ViewReportHeadOfDepartmentController {

    @FXML
    private AnchorPane header;
    @FXML
    private Text FullNameText;

    /**
     * Initializes the View Report Head of Department screen.
     * Sets up the UI components and displays the full name of the logged-in user.
     */
    public void initialize() {
        ScreenManager.dragAndDrop(header);
        FullNameText.setText(Client.user.getName());
    }

    /**
     * Logs out the user and redirects to the login screen.
     *
     * @param event The action event triggered by the user.
     * This method is called when the user clicks the "Logout" button.
     * It logs out the current user and redirects to the login screen.
     */
    @FXML
    void LogOut(ActionEvent event) {
        LogOut.logOutToLoginScreen(event);
    }

    /**
     * Closes the client application.
     * This method is called when the user clicks the close button on the window.
     * It terminates the client application.
     */
    @FXML
    void closeClient() {
        ExitButton.closeClient();
    }

    /**
     * Navigates to the Enter Report by Lecturer screen.
     * @param event The action event triggered by the user.
     * This method is called when the user clicks the "Report by Lecturer" button.
     * It navigates to the Enter Report by Lecturer screen.
     */
    @FXML
    void goToReportByLecturer(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.EnterReportByLecturerForHeadOfDepartmentPath);
    }

    /**
     * Navigates to the Enter Report by Course screen.
     * @param event The action event triggered by the user.
     * This method is called when the user clicks the "Report by Course" button.
     * It navigates to the Enter Report by Course screen.
     */
    @FXML
    void goToReportByCourse(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.EnterReportByCourseForHeadOfDepartmentPath);
    }

    /**
     * Navigates to the Enter Report by Student screen.
     * @param event The action event triggered by the user.
     * This method is called when the user clicks the "Report by Student" button.
     * It navigates to the Enter Report by Student screen.
     */
    @FXML
    void goToReportByStudent(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.EnterReportByStudentForHeadOfDepartmentPath);
    }

    /**
     * Returns to the previous screen.
     * @param event The action event triggered by the user.
     * This method is called when the user clicks the "Go Back" button.
     * It navigates back to the previous screen by loading the main menu for the Head of Department.
     */
    @FXML
    void goBackToPreviousScreen(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuHeadOfDepartPath);
    }

    /**
     * Minimizes the client application window.
     * @param event The action event triggered by the user.
     * This method is called when the user clicks the minimize button on the window.
     * It minimizes the client application window to the taskbar.
     */
    @FXML
    void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
