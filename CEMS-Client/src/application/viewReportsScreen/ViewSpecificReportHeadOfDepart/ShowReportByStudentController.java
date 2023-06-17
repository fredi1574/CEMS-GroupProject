package application.viewReportsScreen.ViewSpecificReportHeadOfDepart;

import Client.Client;
import Client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.*;
import Client.ExitButton;
import Client.LogOut;

import java.util.ArrayList;

import static util.TextFormatter.formatField;
/**
 * Controller class for the Show Report By Student class.
 * The head of department can insert a student's ID and receive statistic information about his tests
 */
public class ShowReportByStudentController {

    @FXML
    private Text usernameText;
    @FXML
    private AnchorPane header;

    @FXML
    private TextField StudentIDText;

    /**
     * Initializes the Show Report by Student screen.
     * Sets up the UI components and displays the username of the logged-in user.
     */
    public void initialize() {
        ScreenManager.dragAndDrop(header);
        usernameText.setText(Client.user.getName());

        formatField(StudentIDText, true, 9);
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
     * Returns to the previous screen.
     *
     * @param event The action event triggered by the user.
     * This method is called when the user clicks the "Go Back" button.
     * It navigates back to the previous screen by loading the View Report Head of Department screen.
     */
    @FXML
    public void goBackToPreviousScreen(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.ViewReportHeadOfDepartmentPath);
    }

    /**
     * Minimizes the client application window.
     *
     * @param event The action event triggered by the user.
     * This method is called when the user clicks the minimize button on the window.
     * It minimizes the client application window to the taskbar.
     */
    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

    /**
     * Shows the report for a specific student.
     *
     * @param event The action event triggered by the user.
     * This method is called when the user clicks the "Show Report" button.
     * It retrieves the student ID from the input field, sends a request to the server to fetch the report for the specific student,
     * and navigates to the Enter Report by Student screen to display the report.
     */
    @FXML
    void showReportForSpecificStudent(ActionEvent event) {
        String studentID = StudentIDText.getText();
        ArrayList<String> studentAndHeadOfDepart = new ArrayList<>();
        studentAndHeadOfDepart.add(studentID);
        studentAndHeadOfDepart.add(Client.user.getId());
        MsgHandler getTable = new MsgHandler(TypeMsg.GetStudentReport, studentAndHeadOfDepart);
        ClientUI.chat.accept(getTable);
        ScreenManager.goToNewScreen(event, PathConstants.EnterReportByStudentPath);
    }

}
