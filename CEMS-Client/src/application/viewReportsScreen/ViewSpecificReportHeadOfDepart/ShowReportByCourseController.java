package application.viewReportsScreen.ViewSpecificReportHeadOfDepart;

import Client.Client;
import Client.ClientUI;
import entity.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.*;
import Client.ExitButton;
import Client.LogOut;

import java.util.List;
/**
 * Controller class for the Show Report By Course class.
 * The head of department can choose a course in his department and receive statistic information about it's tests
 */
public class ShowReportByCourseController {
    ObservableList<Course> coursesList;
    @FXML
    private Text usernameText;
    @FXML
    private AnchorPane header;
    @FXML
    private ComboBox<String> CourseCombo;

    /**
     * Creates the course combo box.
     *
     * @param username The username of the logged-in user.
     *
     * This method retrieves the courses associated with the user from the server and populates the combo box with the course names.
     */
    private void createCourseCombo(String username) {
        MsgHandler getCourses = new MsgHandler(TypeMsg.ImportCourses, username);
        ClientUI.chat.accept(getCourses);
        List<Object> courseObjectsList = ClientUI.chat.getCourses();
        coursesList = FXCollections.observableArrayList((List) courseObjectsList);
        ObservableList<String> courseNames = FXCollections.observableArrayList();

        for (Course course : coursesList) {
            String courseName = course.getCourseName();
            courseNames.add(courseName);
        }
        CourseCombo.setItems(courseNames);
    }

    /**
     * Initializes the Show Report by Course screen.
     * Sets up the UI components and displays the username of the logged-in user.
     */
    public void initialize() {
        ScreenManager.dragAndDrop(header);
        usernameText.setText(Client.user.getName());
        createCourseCombo(Client.user.getUserName());
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
     * Shows the report for a specific course.
     *
     * @param event The action event triggered by the user.
     * This method is called when the user clicks the "Show Report" button.
     * It retrieves the selected course from the combo box and sends a request to the server to get the corresponding reports.
     * It then navigates to the Enter Report by Course screen to display the report.
     */
    @FXML
    void showReportForSpecificCourse(ActionEvent event) {
        String chosenCourse = CourseCombo.getSelectionModel().getSelectedItem();
        MsgHandler getData = new MsgHandler(TypeMsg.GetTestsByCourse, chosenCourse);
        ClientUI.chat.accept(getData);
        ScreenManager.goToNewScreen(event, PathConstants.EnterReportByCoursePath);
    }
}
