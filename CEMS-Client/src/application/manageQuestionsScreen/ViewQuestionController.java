package application.manageQuestionsScreen;
import Client.ExitButton;
import entity.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import util.*;

public class ViewQuestionController {

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

    @FXML
    private TextField courseNameField;

    @FXML
    private AnchorPane header;

    @FXML
    private TextField idField;

    @FXML
    private TextField lecturerField;

    @FXML
    private TextField questionNumberField;

    @FXML
    private TextArea questionTextField;

    @FXML
    private TextField subjectField;

    /**
     * Initializes the update question screen.
     * Enables dragging and dropping of the application window using the header pane.
     * Disables editing of certain fields.
     */
    public void initialize(){

        ScreenManager.dragAndDrop(header);
        questionNumberField.setDisable(true);
        lecturerField.setDisable(true);
        subjectField.setDisable(true);
        idField.setDisable(true);
        courseNameField.setDisable(true);
        questionTextField.setDisable(true);
        lecturerField.setDisable(true);
        answer1.setDisable(true);
        answer2.setDisable(true);
        answer3.setDisable(true);
        answer4.setDisable(true);
        correctAnswer.setDisable(true);

    }

    /**
     * Sets the question data in the update question screen.
     * @param question The question object to be displayed and edited.
     */
    public void setQuestion(Question question) {
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
