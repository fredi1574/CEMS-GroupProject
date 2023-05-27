package application.mainMenuScreen;

import client.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.*;

public class mainMenuHeadOfDepartmentController {

    @FXML
    private AnchorPane header;
    @FXML
    private Text fullNameText;
    public void initialize() {
        // Enables dragging and dropping of the application window using the header pane
        ScreenManager.dragAndDrop(header);
        fullNameText.setText(Client.user.getFullName());

    }
    public void LogOut(ActionEvent event) {
        LogOut.logOutToLoginScreen(event);
    }
    @FXML
    public void ViewReportsHeadOfDepart(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.ViewReportHeadOfDepartmentPath);
    }

    @FXML
    public void ViewTestsHeadOfDepart(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.ViewTestHeadOfDepartmentPath);
    }



    @FXML
    public void viewQuestionsHeadOfDepart(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.ViewQuestionsHeadOfDepartmentPath);
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
