package application.manageTestsScreen;

import client.Client;
import client.ClientUI;
import common.MsgHandler;
import common.TypeMsg;
import entity.ApprovalStatus;
import entity.StateManagement;
import entity.TestForApproval;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.*;

public class viewTestAwaitingApprovalController {

    @FXML
    private TextField GradField;

    @FXML
    private TextField cheatinigField;
    @FXML
    private TextField correcAnswerField;
    @FXML
    private AnchorPane header;
    @FXML
    private Text nameOfuser;
    public StateManagement stateManagement;
    @FXML
    private Button saveDiscionLecturer;
    @FXML
    private TextField studentIDField;

    @FXML
    private TextArea teacherComment;

    @FXML
    private TextField testIDField;
    private TestForApproval test;

    private boolean isSelected = false;
    private int indexOfTest;
    @FXML
    private TextField totalQuestionsField;
    @FXML
    private ComboBox<String> comboBoxApproval;
    ObservableList<TestForApproval> testForApprovals;
    public void initialize() {
        nameOfuser.setText(Client.user.getFullName());
        stateManagement =  StateManagement.getInstance();
        testForApprovals = stateManagement.getTestForApproval();
        for(int i=0; i <testForApprovals.size();i++){
            if(stateManagement.getTestID().equals(testForApprovals.get(i).getTestID())){
                test = stateManagement.getTestForApproval().get(i);
                indexOfTest = i;
                break;
            }
        }
        setAllDataTestForStudent(test);
        ObservableList<String> approved = FXCollections.observableArrayList("YES","NO");
        comboBoxApproval.setItems(approved);

        comboBoxApproval.setOnAction(event1 -> {
            String selectedItem = comboBoxApproval.getValue();
            ApprovalStatus approvedTheLecturer = ApprovalStatus.valueOf(selectedItem);
            isSelected =true;
            test.setApproved(approvedTheLecturer);
        });

    }
    void setAllDataTestForStudent(TestForApproval testForApproval){
        studentIDField.setText(testForApproval.getStudentID());
        GradField.setText(testForApproval.getGrade());
        testIDField.setText(testForApproval.getTestID());
        String yes = testForApproval.getSuspicionOfCheating().equals("Y") ? "Y":"N";
        cheatinigField.setText(yes);
        correcAnswerField.setText(testForApproval.getCorrectAnswers());
        totalQuestionsField.setText(testForApproval.getTotalQuestions());

    }
    @FXML
    void BackTOManageTest(ActionEvent event) {
        StateManagement.resetInstance();
        ScreenManager.goToNewScreen(event, PathConstants.manageTestsPath);
    }

    @FXML
    void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
    @FXML
    void saveDiscionLecturer(ActionEvent event) {
        if(!teacherComment.getText().isEmpty()) {
            test.setLecturerComments(teacherComment.getText());
        }
        if(!isSelected){
            showError.showErrorPopup("Must to select if you approve the test or not !");
            return;
        }
        isSelected = false;
        if( showError.showConfirmationPopup("Are You Sure Want to Save it !")) {
            stateManagement.getTestForApproval().set(indexOfTest, test);
            MsgHandler updateTheApproval = new MsgHandler(TypeMsg.UpdateTheApproveofLecturer, test);
            ClientUI.chat.accept(updateTheApproval);
            ScreenManager.goToNewScreen(event, PathConstants.manageTestsPath);
        }
    }

}


