package application.manageQuestionsScreen;

import client.ClientUI;
import common.MsgHandler;
import common.TypeMsg;
import entity.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.ExitButton;
import util.MinimizeButton;
import util.ScreenManager;

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

    private Question question;
    private Stage manageQuestions;

    public void initialize() {
        ScreenManager.dragAndDrop(header);
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
    public Question AlterQuestion(){
        Question alteredQuestion= new Question(
                questionNumberField.getText(),
                question.getId(),
                questionTextField.getText(),
                lecturerField.getText(),
                subjectField.getText(),
                courseNameField.getText()
        );
        return alteredQuestion;
    }
    public void DeleteQuestion() {
            ArrayList<Question> arr = new ArrayList<>();
            arr.add(AlterQuestion());
            MsgHandler deleteQ = new MsgHandler(TypeMsg.DeleteQuestion, arr);
            ClientUI.chat.accept(deleteQ);
        }
    private void saveData() {
        ArrayList<Question> arr = new ArrayList<>();
        arr.add(AlterQuestion());
        MsgHandler editQ = new MsgHandler(TypeMsg.EditQuestion, arr);
        ClientUI.chat.accept(editQ);
    }

    private void reloadPage() {
        Stage currentStage = (Stage) saveButton.getScene().getWindow();
        currentStage.close();

        manageQuestions.close();
        ScreenManager.showStage("/application/manageQuestionsScreen/ManageQuestions.fxml", "images/Icon.png");
    }

    public void setQuestion(Question question) {
        this.question = question;
        subjectField.setText(question.getSubject());
        courseNameField.setText(question.getCourse_name());
        questionTextField.setText(question.getQuestion_text());
        questionNumberField.setText(question.getQuestion_number());
        lecturerField.setText(question.getLecturer());
        idField.setText(question.getId());
    }

    public void closePopUp(ActionEvent event) {
        ExitButton.closePopUp(event);
    }

    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
