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

import java.util.ArrayList;
import java.util.List;

public class showReportByLecturerController {

    @FXML
    private ComboBox<String> LecturerCombo;

    @FXML
    private AnchorPane header;

    @FXML
    private Text usernameText;
    private static ObservableList<String> LecturerNames;
    private static ArrayList<String> LecturersID;
    public static String chosenLecturer;
    public void initialize() {
        ScreenManager.dragAndDrop(header);
        usernameText.setText(Client.user.getFullName());
        MsgHandler getLecutrers = new MsgHandler(TypeMsg.GetUser,Client.user.getId());
        ClientUI.chat.accept(getLecutrers);
        LecturerCombo.setItems(LecturerNames);

    }

    @FXML
    void LogOut(ActionEvent event) {
       LogOut.logOutToLoginScreen(event);
    }


    @FXML
    void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);

    }
    public void setLecturerCombo(ArrayList<Object> Lecturers){
        ObservableList lectureList = FXCollections.observableArrayList((List) Lecturers);
        LecturerNames = FXCollections.observableArrayList();
        for (Object user : lectureList) {
            if (user instanceof User) {
                    String lecturerName = ((User) user).getFullName();
                    LecturerNames.add(lecturerName);
            }
        }
    }
    @FXML
    public void goBackToPreviousScreen(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.ViewReportHeadOfDepartmentPath);
    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

    @FXML
    void showReportForSpecificLecturer(ActionEvent event) {
        chosenLecturer = LecturerCombo.getSelectionModel().getSelectedItem().toString();
        MsgHandler getLecutrers = new MsgHandler(TypeMsg.GetTestsByLecutrer,chosenLecturer);
        ClientUI.chat.accept(getLecutrers);
        ScreenManager.goToNewScreen(event, PathConstants.EnterReportByLecturerPath);
    }

}
