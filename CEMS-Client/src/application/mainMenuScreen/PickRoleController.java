package application.mainMenuScreen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import util.*;

public class PickRoleController {

    @FXML
    private ComboBox<String> RoleComboBox;

    @FXML
    private AnchorPane header;

    /**
     * Initializes the pick role screen.
     * Enables dragging and dropping of the application window using the header pane.
     * Populates the role combo box with available roles.
     */
    public void initialize() {
        ScreenManager.dragAndDrop(header);
        ObservableList<String> Roles = FXCollections.observableArrayList("Lecturer", "Head Of Department");
        RoleComboBox.setItems(Roles);
    }

    @FXML
    /**
     * Closes the application.
     * @param event The event triggered by the close button click.
     */
    void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    /**
     * Logs in with the selected role and navigates to the corresponding main menu screen.
     * @param event The event triggered by the login button click.
     */
    void logIN(ActionEvent event) {
        if (!RoleComboBox.getSelectionModel().isEmpty()) {
            String rolePicked = RoleComboBox.getSelectionModel().getSelectedItem().toString();
            System.out.println(rolePicked);
            switch (rolePicked) {
                case "Lecturer":
                    ScreenManager.goToNewScreen(event, PathConstants.mainMenuPath);
                    break;
                case "Head Of Department":
                    ScreenManager.goToNewScreen(event, PathConstants.mainMenuHeadOfDepartPath);
                    break;
            }
        } else {
            showError.showErrorPopup("Please choose a role to login");
        }
    }

    /**
     * Logs out the user and navigates back to the login screen.
     * @param event The event triggered by the logout button click.
     */
    public void LogOut(ActionEvent event) {
        LogOut.logOutToLoginScreen(event);
    }


    /**
     * Minimizes the application window.
     * @param event The event triggered by the minimize button click.
     */
    @FXML
    void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
