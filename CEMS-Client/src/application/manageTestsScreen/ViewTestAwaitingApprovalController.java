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
/**
 * Handles the functionality of the view test waiting for approval screen
 * Displays information about students test
 * In this screen the lecturer can change the student's grade, leave comment to the student, and view other details about the test
 */
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

    /**
     * Initializes the controller.
     * It sets up the drag-and-drop functionality for the header,
     * retrieves the current user's name and displays it in the usernameText field,
     * formats the gradeField,
     * retrieves the list of tests awaiting approval from the state management,
     * finds the selected test based on the test ID and student ID,
     * sets the data of the selected test in the UI elements,
     * and sets up the combobox for selecting the approval status.
     */
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

    /**
     * Sets up the combobox for selecting the approval status.
     * It populates the combobox with the options for approval (e.g., "YES"),
     * and handles the selection event to update the approval status of the selected test.
     */
    private void setCombobox() {
        ObservableList<String> approved = FXCollections.observableArrayList("YES");
        comboBoxApproval.setItems(approved);

        comboBoxApproval.setOnAction(event1 -> {
            String selectedItem = comboBoxApproval.getValue();
            ApprovalStatus approvedTheLecturer = ApprovalStatus.valueOf(selectedItem);
            isSelected = true;
            selectedTest.setApproved(approvedTheLecturer);
        });
    }

    /**
     * Sets the data of the selected test in the UI elements.
     *
     * @param testForApproval The test for which to display the data.
     */
    void setAllDataTestForStudent(TestForApproval testForApproval) {
        studentIDLabel.setText(testForApproval.getStudentID());
        gradeField.setText(testForApproval.getGrade());
        testIDLabel.setText(testForApproval.getTestID());
        String cheatingState = testForApproval.getSuspicionOfCheating().equals(CheatingSuspicion.YES) ? "YES" : "NO";
        cheatingLabel.setText(cheatingState);
        correctAnswersLabel.setText(testForApproval.getCorrectAnswers());
        totalQuestionsLabel.setText(testForApproval.getTotalQuestions());
    }

    /**
     * Saves the decision made by the lecturer regarding the test approval.
     * It updates the lecturer's comment if provided,
     * checks if an approval selection has been made,
     * confirms the approval with a popup,
     * updates the grade of the test,
     * updates the test in the test for approval list in the state management,
     * sends an update message to the server,
     * sends a popup message to the student,
     * resets the state management instance,
     * and navigates back to the manage tests screen.
     *
     * @param event The action event triggered by the "Save Decision" button.
     */
    @FXML
    void saveDecisionLecturer(ActionEvent event) {
        if (!teacherComment.getText().isEmpty()) {
            selectedTest.setLecturerComments(teacherComment.getText());
        }
        if (!isSelected) {
            ShowMessage.showErrorPopup("Select if you approve the test or not before saving!");
            return;
        }
        isSelected = false;
        if (ShowMessage.showConfirmationPopup("Are You sure You want to approve the test?")) {
            selectedTest.setGrade(gradeField.getText());

            stateManagement.getTestForApproval().set(indexOfTest, selectedTest);

            MsgHandler updateTheApproval = new MsgHandler(TypeMsg.UpdateTheApprovalOfLecturer, selectedTest);
            ClientUI.chat.accept(updateTheApproval);
            MsgHandler sendPopUpToStudent = new MsgHandler(TypeMsg.StudentsTestIsApproved, studentIDLabel.getText());
            ClientUI.chat.accept(sendPopUpToStudent);

            StateManagement.resetInstance();
            ScreenManager.goToNewScreen(event, PathConstants.manageTestsPath);
        }
    }

    /**
     * Navigates back to the manage tests screen.
     * It resets the state management instance and navigates to the specified screen.
     *
     * @param event The action event triggered by the "Back" button.
     */
    @FXML
    void BackTOManageTest(ActionEvent event) {
        StateManagement.resetInstance();
        ScreenManager.goToNewScreen(event, PathConstants.manageTestsPath);
    }

    /**
     * Closes the client application.
     */
    @FXML
    void closeClient() {
        ExitButton.closeClient();
    }

    /**
     * Minimizes the client application window.
     *
     * @param event The action event triggered by the "Minimize" button.
     */
    @FXML
    void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

    /**
     * Logs out the current user and navigates back to the login screen.
     *
     * @param event The action event triggered by the "Logout" button.
     */
    public void LogOut(ActionEvent event) {
        StateManagement.resetInstance();
        LogOut.logOutToLoginScreen(event);
    }
}
