package application.viewReportsScreen.ViewSpecificReportHeadOfDepart;

import client.Client;
import client.ClientUI;
import common.MsgHandler;
import common.TypeMsg;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.*;

import java.util.ArrayList;

import static util.TextFormatter.formatField;

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

        formatField(StudentIDText,true,9);
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
