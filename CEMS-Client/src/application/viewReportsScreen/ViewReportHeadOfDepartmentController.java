package application.viewReportsScreen;

import Client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.*;
import Client.ExitButton;
import Client.LogOut;

public class ViewReportHeadOfDepartmentController {

    @FXML
    private AnchorPane header;
    @FXML
    private Text FullNameText;

    public void initialize() {
        ScreenManager.dragAndDrop(header);
        FullNameText.setText(Client.user.getName());
    }

    @FXML
    void LogOut(ActionEvent event) {
        LogOut.logOutToLoginScreen(event);
    }

    @FXML
    void closeClient() {
        ExitButton.closeClient();

    }

    @FXML
    void goToReportByLecturer(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.EnterReportByLecturerForHeadOfDepartmentPath);
    }

    @FXML
    void goToReportByCourse(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.EnterReportByCourseForHeadOfDepartmentPath);
    }

    @FXML
    void goToReportByStudent(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.EnterReportByStudentForHeadOfDepartmentPath);
    }

    @FXML
    void goBackToPreviousScreen(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuHeadOfDepartPath);
    }


    @FXML
    void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }


}
