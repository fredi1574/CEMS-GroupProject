package application.mainMenuScreen;

import client.Client;
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
    public void initialize() {
        // Enables dragging and dropping of the application window using the header pane
        ScreenManager.dragAndDrop(header);
        ObservableList<String> Roles = FXCollections.observableArrayList("Lecturer","Head Of Department");
        RoleComboBox.setItems(Roles);
    }
    @FXML
    void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
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
        }
        else {
            showError.showErrorPopup("Please choose a role to login");
        }

    }
    public void LogOut(ActionEvent event) {
        LogOut.logOutToLoginScreen(event);
    }

    @FXML
    void minimizeWindow(ActionEvent event) {
            MinimizeButton.minimizeWindow(event);
        }
    }

