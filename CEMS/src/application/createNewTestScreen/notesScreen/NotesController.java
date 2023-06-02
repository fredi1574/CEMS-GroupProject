package application.createNewTestScreen.notesScreen;
import java.util.UUID;

import client.ClientUI;
import common.MsgHandler;
import common.TypeMsg;
import entity.StateManagement;
import entity.Test;
import entity.TestQuestion;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import util.ExitButton;
import util.MinimizeButton;
import util.PathConstants;
import util.ScreenManager;

import java.util.ArrayList;
import java.util.List;

public class NotesController {
    public StateManagement stateManagement = StateManagement.getInstance();
    @FXML
    private TextArea studentNote;
    private List<Test> newTest = new ArrayList<>();
    @FXML
    private TextArea teacherNote;
    @FXML
    private AnchorPane header;
    public void initialize(){
        ScreenManager.dragAndDrop(header);
        if(stateManagement.getStudentComment() != null)
            studentNote.setText(stateManagement.studentComment);
        if (stateManagement.getTeacherComment() != null)
            teacherNote.setText(stateManagement.teacherComment);
    }
    public String createTestCodeForExam(){
        UUID uuid = UUID.randomUUID();
        String randomString = uuid.toString().substring(0, 4);
        return randomString;
    }

    /**
     * navigates back to the second stage of test creation
     * @param event the event that triggered the method (clicking the back button)
     */
    @FXML
    void backToPickQuestions(ActionEvent event) {

        if(!studentNote.getText().isEmpty()){
            stateManagement.setStudentComment(studentNote.getText());
        }
        if (!teacherNote.getText().isEmpty()){
            stateManagement.setTeacherComment(teacherNote.getText());
        }
        ScreenManager.goToNewScreen(event, PathConstants.pickQuestionsPath);
    }

    /**
     * finalizes the test creation process -
     * adds a test row to the tests table and adds rows for each selected question
     * to the testQuestion table
     * also calls the clearTestState method to reset all fields in the test creation screens
     * @param event the event that triggered the method (clicking the submit button)
     */
    @FXML
    void createTest(ActionEvent event){

        if(!studentNote.getText().isEmpty()){
            stateManagement.setStudentComment(studentNote.getText());
        }
        if (!teacherNote.getText().isEmpty()){
            stateManagement.setTeacherComment(teacherNote.getText());
        }
        //save the testCode for test
        stateManagement.setTestCode(createTestCodeForExam());
        //adds a test to the DB
        stateManagement.SaveTest();
        MsgHandler addNewTest = new MsgHandler(TypeMsg.AddNewTest, stateManagement.newTest);
        ClientUI.chat.accept(addNewTest);

        //adds the test's questions to the DB
        addAllTestQuestions();

        stateManagement.clearTestState();

        ScreenManager.goToNewScreen(event, PathConstants.manageTestsPath);
    }

    public void addAllTestQuestions() {
        ObservableList<TestQuestion> testQuestions = stateManagement.getTestQuestions();
        System.out.println("number of test questions: " + stateManagement.getTestQuestions().size());
        System.out.println("question 1: " + testQuestions.get(0));
        for (int i=0; i<stateManagement.getTestQuestions().size(); i++) {
            MsgHandler addNewTestQuestion = new MsgHandler(TypeMsg.AddNewTestQuestion, testQuestions.get(i));
            ClientUI.chat.accept(addNewTestQuestion);
        }
    }
    @FXML
    void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

}
