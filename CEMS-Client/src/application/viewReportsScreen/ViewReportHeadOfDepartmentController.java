package application.viewReportsScreen;

import Client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.*;

public class ViewReportHeadOfDepartmentController {
    @FXML
    private ComboBox<?> RoleCombo;

    @FXML
    private AnchorPane header;
    @FXML
    private Text FullNameText;

    public void initialize() {
        ScreenManager.dragAndDrop(header);
        FullNameText.setText(Client.user.getFullName());
    }

    @FXML
    void LogOut(ActionEvent event) {
        LogOut.logOutToLoginScreen(event);
    }

    @FXML
    void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);

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
