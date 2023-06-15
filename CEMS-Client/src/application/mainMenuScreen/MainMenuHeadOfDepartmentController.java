package application.mainMenuScreen;

import Client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import util.*;
import Client.ExitButton;
import Client.LogOut;

public class MainMenuHeadOfDepartmentController {

    @FXML
    private AnchorPane header;
    @FXML
    private Text fullNameText;
    @FXML
    private Pane backPane;

    /**
     * Initializes the main menu screen for the head of department.
     * Enables dragging and dropping of the application window using the header pane.
     * Sets the full name of the authenticated user.
     */
    public void initialize() {
        ScreenManager.dragAndDrop(header);
        fullNameText.setText(Client.user.getFullName());
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
     *
     * @param event The event triggered by the logout button click.
     */
    public void LogOut(ActionEvent event) {
        LogOut.logOutToLoginScreen(event);
    }

    /**
     * Navigates to the view reports screen for the head of department.
     *
     * @param event The event triggered by the view reports button click.
     */
    @FXML
    public void ViewReportsHeadOfDepart(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.ViewReportHeadOfDepartmentPath);
    }

    /**
     * Navigates to the view tests screen for the head of department.
     *
     * @param event The event triggered by the view tests button click.
     */
    @FXML
    public void ViewTestsHeadOfDepart(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.ViewTestHeadOfDepartmentPath);
    }

    /**
     * Navigates to the view questions screen for the head of department.
     *
     * @param event The event triggered by the view questions button click.
     */
    @FXML
    public void viewQuestionsHeadOfDepart(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.ViewQuestionsHeadOfDepartmentPath);
    }

    @FXML
    public void enterManageRequestHeadOfDepart(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.EnterManageRequestsHeadOfDepart);
    }


    /**
     * Closes the application.
     */
    @FXML
    private void closeClient() {
        ExitButton.closeClient();
    }


    /**
     * Minimizes the application window.
     *
     * @param event The event triggered by the minimize button click.
     */
    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
