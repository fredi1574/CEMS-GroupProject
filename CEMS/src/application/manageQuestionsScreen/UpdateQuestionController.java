package application.manageQuestionsScreen;

import client.Client;
import client.ClientUI;
import common.MsgHandler;
import common.TypeMsg;
import entity.Question;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.*;

import java.util.ArrayList;

public class UpdateQuestionController {

    @FXML
    private AnchorPane header;

    @FXML
    private TextField idField;

    @FXML
    private TextField subjectField;

    @FXML
    private TextField courseNameField;

    @FXML
    private TextArea questionTextField;

    @FXML
    private TextField questionNumberField;

    @FXML
    private TextField lecturerField;

    @FXML
    private Button saveButton;

    @FXML
    private Button deleteBtn;

    @FXML
    private TextArea answer1;

    @FXML
    private TextArea answer2;

    @FXML
    private TextArea answer3;

    @FXML
    private TextArea answer4;

    @FXML
    private TextField correctAnswer;

    private Question question;
    private Stage manageQuestions;

    /**
     * Initializes the update question screen.
     * Enables dragging and dropping of the application window using the header pane.
     * Disables editing of certain fields.
     */
    public void initialize() {
        ScreenManager.dragAndDrop(header);
        questionNumberField.setDisable(true);
        lecturerField.setDisable(true);
        subjectField.setDisable(true);
        idField.setDisable(true);
        courseNameField.setDisable(true);
    }

    /**
     * Sets the reference to the manage questions stage.
     * @param manageQuestions The reference to the manage questions stage.
     */
    public void setManage(Stage manageQuestions) {
        this.manageQuestions = manageQuestions;
    }

    /**
     * Handles the save button click event.
     * Saves the updated question data and reloads the manage questions page.
     */
    @FXML
    void onSaveButtonClicked() {
        saveData();
        reloadPage();
    }

    /**
     * Handles the delete button click event.
     * Deletes the question and reloads the manage questions page.
     */
    @FXML
    void onDeleteButtonClicked() {
        DeleteQuestion();
        reloadPage();
    }

    /**
     * Checks if all the fields are filled.
     * @return True if all fields are filled, false otherwise.
     */
    public boolean areAllFieldsFilled() {
        return !questionTextField.getText().isEmpty() &&
                !answer1.getText().isEmpty() &&
                !answer2.getText().isEmpty() &&
                !answer3.getText().isEmpty() &&
                !answer4.getText().isEmpty() &&
                !correctAnswer.getText().isEmpty();
    }

    /**
     * Creates a new Question object with the altered question data.
     * @return The altered question object.
     */
    public Question AlterQuestion(){
        Question alteredQuestion = new Question(
                questionNumberField.getText(),
                question.getId(),
                questionTextField.getText(),
                lecturerField.getText(),
                subjectField.getText(),
                courseNameField.getText(),
                answer1.getText(),
                answer2.getText(),
                answer3.getText(),
                answer4.getText(),
                correctAnswer.getText()
        );
        return alteredQuestion;
    }

    /**
     * Deletes the question from the server.
     */
    public void DeleteQuestion() {
        MsgHandler deleteQ = new MsgHandler(TypeMsg.DeleteQuestion, AlterQuestion());
        ClientUI.chat.accept(deleteQ);
    }

    /**
     * Saves the updated question data to the server.
     */
    private void saveData() {
        if (!checkValidData()) return;
        MsgHandler editQ = new MsgHandler(TypeMsg.EditQuestion, AlterQuestion());
        ClientUI.chat.accept(editQ);
    }

    /**
     * Checks if the entered data is valid.
     * @return True if the data is valid, false otherwise.
     */
    private boolean checkValidData() {
        if (!areAllFieldsFilled()) {
            showError.showErrorPopup("Not all fields are filled!\nno changes were made");
            return false;
        }
        if (!correctAnswer.getText().matches("[1-4]")) {
            showError.showErrorPopup("Choose values from 1 to 4 in Correct Answer field\nno changes were made");
            return false;
        }
        return true;
    }

    /**
     * Reloads the manage questions page.
     */
    private void reloadPage() {
        Stage currentStage = (Stage) saveButton.getScene().getWindow();
        currentStage.close();
        manageQuestions.close();
        ScreenManager.showStage(PathConstants.manageQuestions, PathConstants.iconPath);
    }

    /**
     * Sets the question data in the update question screen.
     * @param question The question object to be displayed and edited.
     */
    public void setQuestion(Question question) {
        this.question = question;
        subjectField.setText(question.getSubject());
        courseNameField.setText(question.getCourseName());
        questionTextField.setText(question.getQuestionText());
        questionNumberField.setText(question.getQuestionNumber());
        lecturerField.setText(question.getAuthor());
        idField.setText(question.getId());
        answer1.setText(question.getAnswer1());
        answer2.setText(question.getAnswer2());
        answer3.setText(question.getAnswer3());
        answer4.setText(question.getAnswer4());
        correctAnswer.setText(question.getCorrectAnswer());
    }

    /**
     * Closes the pop-up window.
     * @param event The event triggered by clicking the close button.
     */
    public void closePopUp(ActionEvent event) {
        ExitButton.closePopUp(event);
    }

    /**
     * Minimizes the pop-up window.
     * @param event The event triggered by clicking the minimize button.
     */
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
