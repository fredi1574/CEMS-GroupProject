package application.enterTest;

import client.Client;
import client.ClientUI;
import common.MsgHandler;
import common.TypeMsg;
import entity.ActiveTest;
import entity.StudentCourse;
import entity.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.*;

import java.util.ArrayList;
import java.util.List;

public class ComputerizedTestController {
    @FXML
    private TextField EndTimeText;

    @FXML
    private Text CourseNameText;
    @FXML
    private TextField NumberText;

    @FXML
    private TextField StartTimeText;

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
    @FXML
    private Button btnN;

    public void initialize() {
        // Enables dragging and dropping of the application window using the header pane
        ScreenManager.dragAndDrop(header);
        fullNameText.setText(Client.user.getFullName());
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
        StudentCourse verifyStudent = (StudentCourse)ClientUI.chat.getUserAndCourse();
        if (verifyStudent!=null){
            showError.showInfoPopup("Student is assigned to the test \nPlease click the NEXT button to enter the test");
            btnN.setDisable(false);
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
        TestComments.setText(test.getTeacherComments());
        CourseNameText.setText(test.getCourseName());
        MsgHandler getActiveTestTable = new MsgHandler(TypeMsg.GetActiveTests, null);
        ClientUI.chat.accept(getActiveTestTable);
        ObservableList<ActiveTest> activeTests = FXCollections.observableArrayList((List) ClientUI.chat.getActiveTests());
        for (ActiveTest activeTest: activeTests){
            if (activeTest.getId().equals(test.getId()))
            {
                NumberText.setText(activeTest.getNumOfQuestions());
                StartTimeText.setText(activeTest.getStartingTime());
                TimeRem.setText(activeTest.getTimeLeft());
            }
        }
        btnN.setDisable(true);



    }
    public void NextBtn(ActionEvent event) {//Next to see questions in exam
        ScreenManager.goToNewScreen(event, PathConstants.StartComputerizedTestPath);
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

}
