package application.createNewTestScreen.notesScreen;
import java.util.UUID;

import client.Client;
import client.ClientUI;
import common.MsgHandler;
import common.TypeMsg;
import entity.StateManagement;
import entity.Test;
import entity.TestQuestion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import util.*;

import java.util.List;

public class NotesController {
    public StateManagement stateManagement = StateManagement.getInstance();
    @FXML
    private Text nameAuthor;
    @FXML
    private TextArea studentNote;
    @FXML
    private TextArea teacherNote;
    @FXML
    private AnchorPane header;
    public void initialize(){
        ScreenManager.dragAndDrop(header);

        nameAuthor.setText(Client.user.getFullName());

        if(stateManagement.getStudentComment() != null)
            studentNote.setText(stateManagement.studentComment);
        if (stateManagement.getTeacherComment() != null)
            teacherNote.setText(stateManagement.teacherComment);
    }

    /**
     * Generates a random 4-character string used as the test code
     * The test code is used by the student to access a test
     * @return the tet code string
     */
    public String generateTestCode(){
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
     * finalizes the test creation process:
     * - deletes a test with an identical id if one is found
     * - adds a test row to the tests table
     * - adds rows for each selected question to the testQuestion table
     * - calls the resetInstance method to reset all fields in the test creation screens
     * @param event the event that triggered the method (clicking the submit button)
     */
    @FXML
    void createTest(ActionEvent event){
        if(stateManagement.semester == ""||stateManagement.year == "" ||stateManagement.session ==""
                || stateManagement.durationTimeOfTest == ""){
            showError.showErrorPopup("Go to page 1 and complete the data of test");
            return;
        }
        if(stateManagement.getTestQuestions().size() == 0){
            showError.showErrorPopup("Select Questions for test from page 2");
            return;
        }
        if(stateManagement.getTotalRemainingPoints() > 0){
            showError.showErrorPopup("Points for the questions do not add up to 100 in page 2");
            return;
        }
        if(!studentNote.getText().isEmpty()){
            stateManagement.setStudentComment(studentNote.getText());
        }
        if (!teacherNote.getText().isEmpty()){
            stateManagement.setTeacherComment(teacherNote.getText());
        }
        //save the testCode for test
        stateManagement.setTestCode(generateTestCode());

        deleteTestIfAlreadyExists();
        addTestToDB();
        addAllTestQuestionsToDB();

        stateManagement.resetInstance();

        ScreenManager.goToNewScreen(event, PathConstants.manageTestsPath);
    }

    /**
     * Checks if a test with the current testID already exists in the db
     * if it does, it gets deleted
     * Used for editing tests (Allows the lecturer to override an existing test's data)
     */
    public void deleteTestIfAlreadyExists() {
        MsgHandler getDbTestTable = new MsgHandler(TypeMsg.GetTestsBySubject, Client.user.getUserName());
        ClientUI.chat.accept(getDbTestTable);
        ObservableList<Test> dbTests = FXCollections.observableArrayList((List) ClientUI.chat.getTests());

        for (int i = 0; i < dbTests.size(); i++) {
            if(stateManagement.getTestID().equals(dbTests.get(i).getId())) {
                MsgHandler clearTestMsg = new MsgHandler(TypeMsg.DeleteTest, dbTests.get(i));
                ClientUI.chat.accept(clearTestMsg);
                break;
            }
        }
    }

    /**
     * adds a test to the DB
     */
    public void addTestToDB() {
        stateManagement.SaveTest();
        MsgHandler addNewTest = new MsgHandler(TypeMsg.AddNewTest, stateManagement.newTest);
        ClientUI.chat.accept(addNewTest);
    }

    /**
     * adds all the test's questions to the DB
     */
    public void addAllTestQuestionsToDB() {
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
