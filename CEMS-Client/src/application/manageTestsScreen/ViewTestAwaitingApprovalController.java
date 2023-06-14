package application.manageTestsScreen;

import Client.Client;
import Client.ClientUI;
import Client.ExitButton;
import Client.LogOut;
import entity.ApprovalStatus;
import entity.CheatingSuspicion;
import entity.TestForApproval;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.*;

import static util.TextFormatter.formatField;

public class ViewTestAwaitingApprovalController {

    public StateManagement stateManagement;
    ObservableList<TestForApproval> testsForApproval;
    @FXML
    private TextField gradeField;
    @FXML
    private AnchorPane header;
    @FXML
    private Text usernameText;
    @FXML
    private Label studentIDLabel;
    @FXML
    private Label testIDLabel;
    @FXML
    private Label totalQuestionsLabel;
    @FXML
    private Label correctAnswersLabel;
    @FXML
    private Label cheatingLabel;
    @FXML
    private TextArea teacherComment;
    private TestForApproval selectedTest;
    private boolean isSelected = false;
    private int indexOfTest;
    @FXML
    private ComboBox<String> comboBoxApproval;

    public void initialize() {
        ScreenManager.dragAndDrop(header);
        usernameText.setText(Client.user.getName());

        formatField(gradeField, true, 3);

        stateManagement = StateManagement.getInstance();

        testsForApproval = stateManagement.getTestForApproval();
        for (int i = 0; i < testsForApproval.size(); i++) {
            if (stateManagement.getTestID().equals(testsForApproval.get(i).getTestID()) &&
                    stateManagement.getStudentID().equals(testsForApproval.get(i).getStudentID())) {
                selectedTest = stateManagement.getTestForApproval().get(i);
                indexOfTest = i;
                break;
            }
        }
        setAllDataTestForStudent(selectedTest);
        setCombobox();
    }

    private void setCombobox() {
        ObservableList<String> approved = FXCollections.observableArrayList("YES", "NO");
        comboBoxApproval.setItems(approved);

        comboBoxApproval.setOnAction(event1 -> {
            String selectedItem = comboBoxApproval.getValue();
            ApprovalStatus approvedTheLecturer = ApprovalStatus.valueOf(selectedItem);
            isSelected = true;
            selectedTest.setApproved(approvedTheLecturer);
        });

    }

    void setAllDataTestForStudent(TestForApproval testForApproval) {
        studentIDLabel.setText(testForApproval.getStudentID());
        gradeField.setText(testForApproval.getGrade());
        testIDLabel.setText(testForApproval.getTestID());
        String cheatingState = testForApproval.getSuspicionOfCheating().equals(CheatingSuspicion.YES) ? "YES" : "NO";
        cheatingLabel.setText(cheatingState);
        correctAnswersLabel.setText(testForApproval.getCorrectAnswers());
        totalQuestionsLabel.setText(testForApproval.getTotalQuestions());
    }

    @FXML
    void saveDecisionLecturer(ActionEvent event) {
        if (!teacherComment.getText().isEmpty()) {
            selectedTest.setLecturerComments(teacherComment.getText());
        }
        if (!isSelected) {
            showError.showErrorPopup("Select if you approve the test or not before saving!");
            return;
        }
        isSelected = false;
        if (showError.showConfirmationPopup("Are You sure You want to approve the test?")) {
            selectedTest.setGrade(gradeField.getText());

            stateManagement.getTestForApproval().set(indexOfTest, selectedTest);

            MsgHandler updateTheApproval = new MsgHandler(TypeMsg.UpdateTheApproveofLecturer, selectedTest);
            ClientUI.chat.accept(updateTheApproval);
            MsgHandler sendPopUpToStudent = new MsgHandler(TypeMsg.StudentsTestIsApprvoed, studentIDLabel.getText());
            ClientUI.chat.accept(sendPopUpToStudent);

            StateManagement.resetInstance();
            ScreenManager.goToNewScreen(event, PathConstants.manageTestsPath);
        }
    }

    @FXML
    void BackTOManageTest(ActionEvent event) {
        StateManagement.resetInstance();
        ScreenManager.goToNewScreen(event, PathConstants.manageTestsPath);
    }

    @FXML
    void closeClient() {
        ExitButton.closeClient();
    }

    @FXML
    void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

    public void LogOut(ActionEvent event) {
        StateManagement.resetInstance();
        LogOut.logOutToLoginScreen(event);
    }
}


