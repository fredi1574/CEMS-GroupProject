package application.viewReportsScreen.ViewSpecificReportHeadOfDepart;

import Client.Client;
import Client.ClientUI;
import entity.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.*;
import Client.ExitButton;
import Client.LogOut;

import java.util.List;

public class ShowReportByCourseController {
    ObservableList<Course> coursesList;
    @FXML
    private Text usernameText;
    @FXML
    private AnchorPane header;
    @FXML
    private ComboBox<String> CourseCombo;

    private void createCourseCombo(String username) {
        MsgHandler getCourses = new MsgHandler(TypeMsg.importCourses, username);
        ClientUI.chat.accept(getCourses);
        List<Object> courseObjectsList = ClientUI.chat.getCourses();
        coursesList = FXCollections.observableArrayList((List) courseObjectsList);
        ObservableList<String> courseNames = FXCollections.observableArrayList();

        for (Course course : coursesList) {
            String courseName = course.getCourseName();
            courseNames.add(courseName);
        }
        CourseCombo.setItems(courseNames);
    }

    public void initialize() {
        ScreenManager.dragAndDrop(header);
        usernameText.setText(Client.user.getName());
        createCourseCombo(Client.user.getUserName());
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
    public void goBackToPreviousScreen(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.ViewReportHeadOfDepartmentPath);
    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

    @FXML
    void showReportForSpecificCourse(ActionEvent event) {
        String chosenCourse = CourseCombo.getSelectionModel().getSelectedItem();
        MsgHandler getData = new MsgHandler(TypeMsg.GetTestsByCourse, chosenCourse);
        ClientUI.chat.accept(getData);
        ScreenManager.goToNewScreen(event, PathConstants.EnterReportByCoursePath);
    }

}
