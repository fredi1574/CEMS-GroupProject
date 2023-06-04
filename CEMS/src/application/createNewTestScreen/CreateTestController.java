package application.createNewTestScreen;
import javafx.scene.text.Text;
import client.Client;
import client.ClientUI;
import common.MsgHandler;
import common.TypeMsg;
import entity.Course;
import entity.StateManagement;
import entity.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import util.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CreateTestController {
    Course rowData = null;
    @FXML
    private Text nameAuthor;
    @FXML
    private TextField semesterTextField;
    @FXML
    private TextField sessionTextField;
    @FXML
    private TextField  yearField;
    @FXML
    private AnchorPane header;
    @FXML
    private TextField courseIDField;
    @FXML
    private TextField subjectIDField;
    @FXML
    private TextField testIDField;
    @FXML
    private TextField testNumberField;
    @FXML
    private TextField testDurationField;
    public StateManagement stateManagement;
    @FXML
    private TableView<Course> courseTableView;



    public void initialize() {
        ScreenManager.dragAndDrop(header);

        nameAuthor.setText(Client.user.getFullName());

        MsgHandler getTableCourse = new MsgHandler(TypeMsg.GetCourseTable, null);
        ClientUI.chat.accept(getTableCourse);
        List<Object> courseObjectsList = ClientUI.chat.getCourses();

        ObservableList<Course> questionList = FXCollections.observableArrayList((List) courseObjectsList);

        ObservableList<String> columnList = FXCollections.observableArrayList();
        //TODO: fix the names of the table columns
        columnList.addAll("Course Name", "Subject Name");
        TableManager.createTable(courseTableView, columnList);
        TableManager.importData(courseTableView, questionList);
        //TableManager.addDoubleClickFunctionality(courseTableView,"ManageQuestionsScreen/UpdateQuestion.fxml",true);


        double[] multipliers = {0.7, 0.295};
        TableManager.resizeColumns(courseTableView, multipliers);

        //Adds a colon between the two digits and limits the field to 4 digits
//        correctDuration(testDurationField);

        courseTableView.setOnMouseClicked((e) -> {
            rowData = (Course) courseTableView.getSelectionModel().getSelectedItem();
            if(rowData !=null) {
                setTestData();
            }

        });
        retrieveSavedData();
    }

    /**
     * sets the initial values of the text fields based on the selected course
     */
    public void setTestData() {
        courseIDField.setText(rowData.getCourseID());
        subjectIDField.setText(rowData.getSubjectID());

        MsgHandler getTestTable = new MsgHandler(TypeMsg.GetAllTestsTable, null);
        ClientUI.chat.accept(getTestTable);
        List<Object> testTableList = ClientUI.chat.getTests();

        String newTestNumber = correctTestNumber(findFirstFreeIndex(testTableList));
        String testID = rowData.getSubjectID() +rowData.getCourseID() +  newTestNumber;

        testIDField.setText(testID);
        testNumberField.setText(newTestNumber);
    }

    /**
     * finds the first free test number in the given table
     * by scanning the table from its beginning
     *
     * @param testTableList the table that will be scanned
     * @return the free index
     */
    public int findFirstFreeIndex(List<Object> testTableList) {

        //if the test table is empty
        if (testTableList.isEmpty()) {
            return 1;
        }

        Set<Integer> indexSet = new HashSet<>();
        for (Object test : testTableList) {
            if (test instanceof Test) {
                Test testObject = (Test) test;
                indexSet.add(Integer.parseInt(testObject.getTestNumber()));
            }
        }

        int minIndex = Collections.min(indexSet);
        int maxIndex = Collections.max(indexSet);

        for (int i = 1; i <= maxIndex; i++) {
            if (!indexSet.contains(i)) {
                return i;
            }
        }

        return maxIndex + 1;
    }


    public String correctTestNumber(int newTestIndex) {

        if (newTestIndex >= 0 && newTestIndex < 100) {
            if (newTestIndex < 10) {
                return "0" + newTestIndex;
            }
            return String.valueOf(newTestIndex);
        }
        throw new IllegalArgumentException("Test number is out of bounds: " + newTestIndex);
    }
    @FXML
    public void SaveExam() {
        if (testDurationField.getText().isEmpty()) {
            showError.showErrorPopup("Please Enter The Duration Time of test!");
        }
        if (rowData == null) {
            showError.showErrorPopup("Please Press To Select Subject and Course!");
        }
    }

    /**
     * retrieves the saved data from stateManagement and updates the fields
     * on the screen
     *
     */
    public void retrieveSavedData(){
        stateManagement = StateManagement.getInstance();
        if(!stateManagement.testID.equals("-1")) {
            ObservableList<Course> list = courseTableView.getItems();
            int indexInTable = 0;
            for(int i=0; i<list.size(); i++){
                if(list.get(i).getCourseID().equals(stateManagement.getCourse().getCourseID()))
                    indexInTable = i;

            }
            rowData = stateManagement.course;
            courseTableView.getSelectionModel().select(indexInTable);
            courseIDField.setText(stateManagement.course.getCourseID());
            subjectIDField.setText(stateManagement.course.getSubjectID());
            testIDField.setText(stateManagement.getTestID());
            testNumberField.setText(String.valueOf(stateManagement.getTestNum()));
            testDurationField.setText(stateManagement.getDurationTimeOfTest());
            yearField.setText(stateManagement.getYear());
            sessionTextField.setText(stateManagement.getSession());
            semesterTextField.setText(stateManagement.getSemester());
        }
    }


    public void BackToMenu(ActionEvent event) {
        stateManagement.resetInstance();
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuPath);
    }


    public void goToPickQuestions(ActionEvent event) {
        stateManagement = StateManagement.getInstance();
        try {
            if (!sessionTextField.getText().isEmpty()) {
                stateManagement.setSession(sessionTextField.getText());
            }else
                stateManagement.setSession("");

            if (!yearField.getText().isEmpty()) {
                stateManagement.setYear(yearField.getText());
            }else
                stateManagement.setYear("");

            if (!semesterTextField.getText().isEmpty()) {
                stateManagement.setSemester(semesterTextField.getText());
            }else
                stateManagement.setSemester("");

            if (!testDurationField.getText().isEmpty()) {
                stateManagement.setDurationTimeOfTest(testDurationField.getText());
            }else
                stateManagement.setDurationTimeOfTest("");
        }catch(Exception exception){

        }
        stateManagement.setDataOfTest(rowData, testNumberField.getText(), testIDField.getText());
        if (rowData == null) {
            showError.showErrorPopup("Choose subject and course first");
        } else {
            ScreenManager.goToNewScreen(event, PathConstants.pickQuestionsPath);
        }
    }

    public void openPreview() {
        stateManagement = StateManagement.getInstance();
       // stateManagment.setDataofTest(rowData,testNumberField.getText(),testIDField.getText(),testDurationField.getText());
        ScreenManager.popUpScreen(PathConstants.testPreviewPath);
    }

    public void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.loginPath);
    }

    public void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
