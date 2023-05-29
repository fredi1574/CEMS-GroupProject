package application.createNewTestScreen;
import application.createNewTestScreen.pickQuestionsScreen.PickQuestionsController;
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

import java.util.List;
public class CreateTestController {
    Course rowData = null;
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
        MsgHandler getTableCourse = new MsgHandler(TypeMsg.GetCourseTable, null);
        ClientUI.chat.accept(getTableCourse);
        List<Object> courseObjectsList = ClientUI.chat.getCourses();
        ObservableList<Course> questionList = FXCollections.observableArrayList((List) courseObjectsList);

        ObservableList<String> columnList = FXCollections.observableArrayList();
        columnList.addAll("Course", "Subject");
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

        MsgHandler getTestTable = new MsgHandler(TypeMsg.GetTestTable, null);
        ClientUI.chat.accept(getTestTable);
        List<Object> testTableList = ClientUI.chat.getTests();

        String newTestNumber = correctTestNumber(findFirstFreeIndex(testTableList));
        String testID = rowData.getCourseID() + rowData.getSubjectID() + newTestNumber;

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

        //iterates over the testTableList and checks if the current index is available
        for (int i = 0; i < testTableList.size() - 1; i++) {
            if (((Test) testTableList.get(i)).getTestNumber() + 1 != ((Test) testTableList.get(i + 1)).getTestNumber()) {
                return i + 1;
            }
        }
        //if hadn't found a free index, returns the last index of the table
        return testTableList.size() + 1;
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
        stateManagement.setTestID("-1");
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuPath);
    }


    public void goToPickQuestions(ActionEvent event) {
        stateManagement = StateManagement.getInstance();
        stateManagement.setDataOfTest(rowData,testNumberField.getText(),testIDField.getText(),testDurationField.getText(),yearField.getText(),
                                     sessionTextField.getText(),semesterTextField.getText());

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
