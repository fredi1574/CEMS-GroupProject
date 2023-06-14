package application.enterTest;

import Client.Client;
import entity.ActiveTest;
import entity.StudentCourse;
import entity.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.*;
import Client.ClientUI;
import Client.ExitButton;

import java.util.ArrayList;
import java.util.List;

public class ComputerizedTestController {

    @FXML
    private Text CourseNameText;
    @FXML
    private TextField NumberText;

    @FXML
    private TextField StudentIDText;

    @FXML
    private TextArea TestComments;

    @FXML
    private TextField TestIdText;


    @FXML
    private TextField TimeRem;

    @FXML
    private Text fullNameText;
    @FXML
    private AnchorPane header;


    public void initialize() {
        // Enables dragging and dropping of the application window using the header pane
        ScreenManager.dragAndDrop(header);
        fullNameText.setText(Client.user.getName());
        setData();
    }
    @FXML
    private void approveID(ActionEvent event){
        if (StudentIDText.getText().isEmpty()){
            showError.showErrorPopup("Please enter your ID");
            return;
        }
        ArrayList<String> StudentAndCourse = new ArrayList<>();
        StudentAndCourse.add(StudentIDText.getText());
        StudentAndCourse.add(CourseNameText.getText());
        MsgHandler checkStudentRegistered = new MsgHandler(TypeMsg.CheckStudentRegisteredCourse,StudentAndCourse);
        ClientUI.chat.accept(checkStudentRegistered);
        StudentCourse verifyStudent = (StudentCourse) ClientUI.chat.getUserAndCourse();
        if(!(StudentIDText.getText().equals(Client.user.getId()))){
            showError.showInfoPopup("Incorrect ID\nPlease try again");
        }
        else if (verifyStudent!=null){
            showError.showInfoPopup("Student is assigned to the test \nAfter clicking OK the test will start automatically");
            ScreenManager.goToNewScreen(event, PathConstants.StartComputerizedTestPath);
        }
        else {
            showError.showInfoPopup("Student is not assigned to the test! \nPlease try again");
        }


    }
    private Test getTestData()
    {
        MsgHandler getTestInformation = new MsgHandler(TypeMsg.GetTestByID, EnterCodePopUpController.testID);
        ClientUI.chat.accept(getTestInformation);
        Test test = (Test) ClientUI.chat.getSingleTest();
        return test;
    }
    private void setData(){
        Test test = getTestData();
        TestIdText.setText(test.getId());
        TestIdText.setDisable(true);
        TestComments.setText(test.getStudentComments());
        TestComments.setDisable(true);
        CourseNameText.setText(test.getCourseName());
        startTimer(test);
        MsgHandler getActiveTestTable = new MsgHandler(TypeMsg.GetActiveTests, null);
        ClientUI.chat.accept(getActiveTestTable);
        ObservableList<ActiveTest> activeTests = FXCollections.observableArrayList((List) ClientUI.chat.getActiveTests());
        for (ActiveTest activeTest: activeTests){
            if (activeTest.getId().equals(test.getId()))
            {
                int numOfQuestions = activeTest.getNumOfQuestions();
                NumberText.setText(String.valueOf(numOfQuestions));
                NumberText.setDisable(true);
            }
        }
    }
    public void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuStudentPath);
    }

    public void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
    private void startTimer(Test test) {
        int remainingMinutes = Integer.parseInt(test.getTestDuration());
        int totalSeconds = remainingMinutes * 60;
        TimeRem.setText(formatTime(totalSeconds));
        TimeRem.setDisable(true);
    }
    private String formatTime(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }

}
