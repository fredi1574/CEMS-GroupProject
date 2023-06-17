package application.viewReportsScreen.ViewSpecificReportHeadOfDepart;

import Client.Client;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.*;
import Client.ClientUI;
import Client.ExitButton;
import Client.LogOut;

import java.util.ArrayList;
/**
 * Controller class for the Show Report By Lecturer class.
 * The head of department can choose a lecturer in his department and receive statistic information about his tests
 */
public class ShowReportByLecturerController {

    @FXML
    private ComboBox<String> LecturerCombo;

    @FXML
    private AnchorPane header;

    @FXML
    private Text usernameText;
    private static ObservableList<String> LecturerNames;
    public static String chosenLecturer;

    /**
     * Initializes the Show Report by Lecturer screen.
     * Sets up the UI components and displays the username of the logged-in user.
     */
    public void initialize() {
        ScreenManager.dragAndDrop(header);
        usernameText.setText(Client.user.getName());
        MsgHandler getLecturers = new MsgHandler(TypeMsg.GetUser, Client.user.getId());
        ClientUI.chat.accept(getLecturers);
        LecturerCombo.setItems(LecturerNames);
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
     * Shows the report for a specific lecturer.
     *
     * @param event The action event triggered by the user.
     * This method is called when the user clicks the "Show Report" button.
     * It retrieves the chosen lecturer from the combo box, sends a request to the server to fetch the reports for the specific lecturer,
     * and navigates to the Enter Report by Lecturer screen to display the reports.
     */
    @FXML
    void showReportForSpecificLecturer(ActionEvent event) {
        chosenLecturer = LecturerCombo.getSelectionModel().getSelectedItem();
        MsgHandler getLecturers = new MsgHandler(TypeMsg.GetTestsByLecutrer, chosenLecturer);
        ClientUI.chat.accept(getLecturers);
        ScreenManager.goToNewScreen(event, PathConstants.EnterReportByLecturerPath);
    }

    /**
     * Sets the available lecturers in the combo box.
     *
     * @param lecturers The list of lecturers retrieved from the server.
     * This method is called after receiving the list of lecturers from the server.
     * It populates the combo box with the names of the available lecturers.
     */
    public void setLecturerCombo(ArrayList<Object> lecturers) {
        ObservableList lectureList = FXCollections.observableArrayList(lecturers);
        LecturerNames = FXCollections.observableArrayList();
        for (Object user : lectureList) {
            if (user instanceof User) {
                String lecturerName = ((User) user).getFullName();
                LecturerNames.add(lecturerName);
            }
        }
    }

}
