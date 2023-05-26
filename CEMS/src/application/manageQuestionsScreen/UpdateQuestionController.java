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

    public void initialize() {
        ScreenManager.dragAndDrop(header);
        questionNumberField.setDisable(true);
        lecturerField.setDisable(true);
        subjectField.setDisable(true);
        idField.setDisable(true);
    }
    public void setManage(Stage manageQuestions) {
        this.manageQuestions = manageQuestions;
    }
    @FXML
    void onSaveButtonClicked() {
        saveData();
        reloadPage();
    }
    @FXML
    void onDeleteButtonClicked() {
        DeleteQuestion();
        reloadPage();
    }
    public boolean areAllFieldsFilled() {
        return !questionTextField.getText().isEmpty() &&
                !answer1.getText().isEmpty() &&
                !answer2.getText().isEmpty() &&
                !answer3.getText().isEmpty() &&
                !answer4.getText().isEmpty() &&
                !correctAnswer.getText().isEmpty();
    }

    public Question AlterQuestion(){

        Question alteredQuestion= new Question(
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

    public void DeleteQuestion() {

            MsgHandler deleteQ = new MsgHandler(TypeMsg.DeleteQuestion, AlterQuestion());
            ClientUI.chat.accept(deleteQ);
        }
    private void saveData() {
        if (!checkValidData())return;
        MsgHandler editQ = new MsgHandler(TypeMsg.EditQuestion, AlterQuestion());
        ClientUI.chat.accept(editQ);
    }
    private boolean checkValidData() {
        if (!(areAllFieldsFilled())) {
            showError.showErrorPopup("Not all fields are filled!\nno changes were made");
            return false;
        }
        if (!(correctAnswer.getText().matches("[1-4]"))) {
            showError.showErrorPopup("Choose values from 1 to 4 in Correct Answer field\nno changes were made");
            return false;
        }
        return true;

    }
    private void reloadPage() {
        Stage currentStage = (Stage) saveButton.getScene().getWindow();
        currentStage.close();

        manageQuestions.close();
        ScreenManager.showStage(PathConstants.manageQuestions, PathConstants.iconPath);
    }

    public void setQuestion(Question question) {
        this.question = question;
        subjectField.setText(question.getSubject());
        courseNameField.setText(question.getCourse_name());
        questionTextField.setText(question.getQuestion_text());
        questionNumberField.setText(question.getQuestion_number());
        lecturerField.setText(question.getLecturer());
        idField.setText(question.getId());
        answer1.setText(question.getAnswer1());
        answer2.setText(question.getAnswer2());
        answer3.setText(question.getAnswer3());
        answer4.setText(question.getAnswer4());
        correctAnswer.setText(question.getCorrectAnswer());


    }

    public void closePopUp(ActionEvent event) {
        ExitButton.closePopUp(event);
    }

    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
