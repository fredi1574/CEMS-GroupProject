package application.addAQuestionScreen;
import client.Client;
import client.ClientUI;
import common.MsgHandler;
import common.TypeMsg;
import entity.*;
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

import java.util.List;


public class AddAQuestionController {
    public ComboBox subjectCombo;
    @FXML
    private ComboBox CourseCombo;
    @FXML
    private AnchorPane header;
    @FXML
    private TextField questionID;
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
    public String newQuestionNumber;
    public String QuestionID;
    private String selectedCourse;
    public static ObservableList<Course> coursesList;


    public void initialize() {
        ScreenManager.dragAndDrop(header);
        usernameText.setText(Client.user.getFullName());
        createSubjectCombo(Client.user.getUserName());
        createCourseCombo(Client.user.getUserName());
        questionNumber.setVisible(false);
        questionID.setVisible(false);
        subjectCombo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            checkFieldsAndExecuteActions();
        });

        CourseCombo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            checkFieldsAndExecuteActions();
        });

        checkFieldsAndExecuteActions();
    }

    private void checkFieldsAndExecuteActions() {
        if (!subjectCombo.getSelectionModel().isEmpty() && !CourseCombo.getSelectionModel().isEmpty()) {
            setQuestionID();
            questionNumber.setVisible(true);
            questionID.setVisible(true);
        }
    }
    private void createSubjectCombo(String username) {
        MsgHandler getSubject = new MsgHandler(TypeMsg.importSubjects, username);
        ClientUI.chat.accept(getSubject);
        List<Object> subjectObjectsList = ClientUI.chat.getSubjects();
        ObservableList<Subject>subjectsList = FXCollections.observableArrayList((List) subjectObjectsList);
        ObservableList<String> subjectNames = FXCollections.observableArrayList();

        for (Subject subject : subjectsList) {
            String subjectNameandID = subject.getSubjectName() + " -" + subject.getSubjectID();
            subjectNames.add(subjectNameandID);
        }
        subjectCombo.setItems(subjectNames);
    }
    private void createCourseCombo(String username) {
        MsgHandler getCourses = new MsgHandler(TypeMsg.importCourses, username);
        ClientUI.chat.accept(getCourses);
        List<Object> courseObjectsList = ClientUI.chat.getCourses();
        coursesList = FXCollections.observableArrayList((List) courseObjectsList);
        ObservableList<String> courseNames = FXCollections.observableArrayList();

        for (Course course : coursesList) {
            String courseName = course.getCourseName();
            courseNames.add(courseName);
        }
        CourseCombo.setItems(courseNames);
    }
    public boolean areAllFieldsFilled() {
        return  !CourseCombo.getSelectionModel().isEmpty() &&
                !subjectCombo.getSelectionModel().isEmpty() &&
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
        String Subject = subjectCombo.getSelectionModel().getSelectedItem().toString();
        String Course = CourseCombo.getSelectionModel().getSelectedItem().toString();
        if (!checkValidData()){return;}
        Question newQuestion= new Question(
                questionNumber.getText(),
                QuestionID,
                questionTextField.getText(),
                usernameText.getText(),
                Subject.substring(Subject.indexOf("-")+1),
                Course,
                answer1.getText(),
                answer2.getText(),
                answer3.getText(),
                answer4.getText(),
                CorrectAnswer.getText()

        );
        MsgHandler addNewQuestion = new MsgHandler(TypeMsg.AddNewQuestion, newQuestion);
        ClientUI.chat.accept(addNewQuestion);
        showError.showInfoPopup("Added question successfully");
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuPath);
    }
    public void setQuestionID() {
        String Subject = subjectCombo.getSelectionModel().getSelectedItem().toString();
        String Course = CourseCombo.getSelectionModel().getSelectedItem().toString();
        MsgHandler getQuestionTable = new MsgHandler(TypeMsg.GetAllQuestions, null);
        ClientUI.chat.accept(getQuestionTable);
        List<Question> questions =((List) ClientUI.chat.getAllOfQuestions());
        selectedCourse = getSelectedID(Course,CourseCombo,coursesList);
        newQuestionNumber = correctQuestionNumber(findFirstFreeIndex(questions));
        QuestionID = ((Subject.substring(Subject.indexOf("-")+1)) + selectedCourse+newQuestionNumber);
        System.out.println(QuestionID);
        questionNumber.setText(newQuestionNumber);
        questionID.setText(QuestionID);
        questionID.setDisable(true);
        questionNumber.setDisable(true);
    }
    public int findFirstFreeIndex(List<Question> questionTableList) {

        //if the test table is empty
        if (questionTableList.isEmpty()) {
            return 1;
        }
        //if hadn't found a free index, returns the last index of the table
        return questionTableList.size() + 1;
    }
    public String getSelectedID(String choice,ComboBox comboBox,ObservableList<Course> listToCheck) {
        choice = comboBox.getSelectionModel().getSelectedItem().toString();
        for (Course item : listToCheck) {
            String course = item.getCourseName();
            if (choice.equals(course)) {
                return item.getCourseID();
            }
        }
        return null;
    }
    public String correctQuestionNumber(int newQuestionIndex) {

        if (newQuestionIndex >= 0 && newQuestionIndex < 100) {
            if (newQuestionIndex< 10) {
                return "0" + newQuestionIndex;
            }
            return String.valueOf(newQuestionIndex);
        }
        throw new IllegalArgumentException("Test number is out of bounds: " + newQuestionIndex);
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