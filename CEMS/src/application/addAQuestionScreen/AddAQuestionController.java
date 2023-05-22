package application.addAQuestionScreen;

import client.ClientUI;
import common.MsgHandler;
import common.TypeMsg;
import entity.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import util.*;

import java.util.ArrayList;



public class AddAQuestionController {
    @FXML
    private ComboBox<String> CourseCombo;
    @FXML
    private ComboBox<String>SubjectCombo;
    @FXML
    private AnchorPane header;
    @FXML
    private TableView<String> courseTableView;
    @FXML
    private TextArea answer1;

    @FXML
    private TextArea answer2;

    @FXML
    private TextArea answer3;

    @FXML
    private TextArea answer4;

    @FXML
    private TextField questionNumber;

    @FXML
    private TextArea questionTextField;
    @FXML
    private TextField CorrectAnswer;

    public void initialize() {
        ScreenManager.dragAndDrop(header);

    }
    public boolean areAllFieldsFilled() {
        return //!CourseCombo.getSelectionModel().isEmpty() &&
               // !SubjectCombo.getSelectionModel().isEmpty() && (wait for login info)
                !questionNumber.getText().isEmpty() &&
                !questionTextField.getText().isEmpty() &&
                !answer1.getText().isEmpty() &&
                !answer2.getText().isEmpty() &&
                !answer3.getText().isEmpty() &&
                !answer4.getText().isEmpty() &&
                !CorrectAnswer.getText().isEmpty();
    }
    @FXML
    private void saveData(ActionEvent event) {
       if (!(areAllFieldsFilled())){
           showError.showErrorPopup("Not all fields are filled!");
           return;
        }
        Question newQuestion= new Question(
                "01" + questionNumber.getText(),
                questionNumber.getText(),
                questionTextField.getText(),
                "May",
                "01",
                "Algebra",
                //subjectField.getText(),
                //courseNameField.getText(),
                answer1.getText(),
                answer2.getText(),
                answer3.getText(),
                answer4.getText(),
                CorrectAnswer.getText()

        );
        ArrayList<Question> arr = new ArrayList<>();
        arr.add(newQuestion);
        MsgHandler addNewQuestion = new MsgHandler(TypeMsg.AddNewQuestion, arr);
        ClientUI.chat.accept(addNewQuestion);
        showError.showInfoPopup("Added question successfully");
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuPath);
    }

    public void BackToMenu(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuPath);
    }

    public void LogOut(ActionEvent event) {
        LogOut.logOutToLoginScreen(event);
    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

    @FXML
    public void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }
}