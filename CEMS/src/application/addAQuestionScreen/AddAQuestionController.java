package application.addAQuestionScreen;

import application.loginWindowScreen.LoginWindowController;
import client.ClientUI;
import common.MsgHandler;
import common.TypeMsg;
import entity.Course;
import entity.Question;
import entity.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.layout.AnchorPane;
import util.*;

import java.util.ArrayList;
import java.util.List;


public class AddAQuestionController {
    @FXML
    private ComboBox<Course> CourseCombo;
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
    @FXML
    private Text usernameText;
    public void initialize() {
        ScreenManager.dragAndDrop(header);
        String loggedInUsername = LoginWindowController.loggedInUsername;
        usernameText.setText(loggedInUsername);
        List<String> username = new ArrayList<>();
        username.add(loggedInUsername);
        MsgHandler getSubject = new MsgHandler(TypeMsg.importSubjects,username);
        ClientUI.chat.accept(getSubject);
        ObservableList<Subject> subjectsList = FXCollections.observableArrayList((List)ClientUI.chat.getSubjects());
        ObservableList<String> subjectsName = FXCollections.observableArrayList();
        for (Subject subject : subjectsList) {
            String subjectName = subject.getSubjectName();
            subjectsName.add(subjectName);
        }
        SubjectCombo.setItems(subjectsName);

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
    private boolean checkValidData() {
        if (!(areAllFieldsFilled())) {
            showError.showErrorPopup("Not all fields are filled!");
            return false;
        }
        if (!(CorrectAnswer.getText().matches("[1-4]"))) {
            showError.showErrorPopup("Choose values from 1 to 4 in Correct Answer field");
            return false;
        }
        if (!(questionNumber.getText().matches("\\d+"))) { //checks if only digits
            showError.showErrorPopup("Question number contains NUMBERS only");
            return false;
        }
        return true;
    }
    @FXML
    private void saveData(ActionEvent event) {
       if (!checkValidData()){return;}
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