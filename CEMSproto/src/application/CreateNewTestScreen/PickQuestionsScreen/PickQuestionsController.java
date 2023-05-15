package application.CreateNewTestScreen.PickQuestionsScreen;

import application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class PickQuestionsController {

    @FXML
    private AnchorPane header;

    @FXML
    private TableView<Question> questionsTableView;
    @FXML
    private TableView<Question> selectedQuestionsTableView;

    public void initialize() {
        ScreenManager.dragAndDrop(header);

        ObservableList<Question> questionList = FXCollections.observableArrayList(
                new Question("78", "1A23B", "what is 2+2?", "Fredi","Math","Algebra"),
                new Question("420", "42040", "what is the meaning of life?", "Fredi","Software Engineering","Data Structures")
        );

        ObservableList<String> questionColumnList = FXCollections.observableArrayList();
        //questionColumnList.addAll("id", "question_text", "question_number", "lecturer", "course_name","subject");
        questionColumnList.addAll("Question Number","ID", "Question Text" , "Lecturer");

        TableManager.createTable(questionsTableView, questionColumnList);
        TableManager.importData(questionsTableView, questionList);

        //TableManager.addCheckBoxesToTable(questionsTableView);
        TableManager.addDoubleClickFunctionality(questionsTableView, "CreateNewTestScreen/PickQuestionsScreen/QuestionPreviewPopup/questionPreview.fxml");

        //double[] multipliers = {0.071, 0.1, 0.1, 0.625, 0.1}; //proper values (when checkbox is included)
        double[] multipliers = {0.16, 0.05, 0.61,0.175};
        TableManager.resizeColumns(questionsTableView, multipliers);


        ObservableList<String> selectedQuestionColumnList = FXCollections.observableArrayList();
        selectedQuestionColumnList.addAll("Number", "Question ID", "Question Text", "Points");

        TableManager.createTable(selectedQuestionsTableView, selectedQuestionColumnList);

        multipliers = new double[]{0.1, 0.1, 0.7, 0.1};
        TableManager.resizeColumns(selectedQuestionsTableView, multipliers);
    }

    public void backToCreateTest(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "CreateNewTestScreen/CreateNewTest.fxml");
    }

    public void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml");
    }

    public void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
