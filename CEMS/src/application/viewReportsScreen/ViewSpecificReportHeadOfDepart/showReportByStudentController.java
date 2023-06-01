package application.viewReportsScreen.ViewSpecificReportHeadOfDepart;

import application.manageRequestsHeadOfDepart.viewRequestController;
import application.viewReportsScreen.ViewSpecificReportHeadOfDepart.openRepoGraphs.openReportByStudentController;
import client.Client;
import client.ClientUI;
import common.MsgHandler;
import common.TypeMsg;
import entity.StudentTest;
import entity.TestRequestForApproval;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.*;

import java.util.ArrayList;
import java.util.List;

public class showReportByStudentController {

    @FXML
    private Text usernameText;
    @FXML
    private AnchorPane header;

    @FXML
    private TextField StudentIDText;


    public void initialize() {
        ScreenManager.dragAndDrop(header);
        usernameText.setText(Client.user.getFullName());
    }
    @FXML
    void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.loginPath);
    }


    @FXML
    void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);

    }

    @FXML
    public void goBackToPreviousScreen(ActionEvent event) {
        //ScreenManager.goToNewScreen(event, PathConstants.EnterReportByStudentPath);
        ScreenManager.goToNewScreen(event, PathConstants.ViewReportHeadOfDepartmentPath);
    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

    @FXML
    void showReportForSpecificStudent(ActionEvent event) {
        String StudentID = StudentIDText.getText();
        ArrayList<String> StudentAndHeadOfDepart = new ArrayList<>();
        StudentAndHeadOfDepart.add(StudentID);
        StudentAndHeadOfDepart.add(Client.user.getId());
        MsgHandler getTable = new MsgHandler(TypeMsg.GetStudentReport,StudentAndHeadOfDepart);
        ClientUI.chat.accept(getTable);
        ScreenManager.goToNewScreen(event, PathConstants.EnterReportByStudentPath);
    }

}
