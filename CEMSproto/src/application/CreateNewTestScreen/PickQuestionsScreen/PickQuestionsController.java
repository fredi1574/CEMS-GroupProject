package application.CreateNewTestScreen.PickQuestionsScreen;

import application.Question;
import application.ScreenChanger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

//TODO: change add/delete buttons of questions
public class PickQuestionsController {

    @FXML
    private TableView<Question> questionsTableView;
    @FXML
    private TableColumn<Question,String> questionNumberColumn;
    @FXML
    private TableColumn<Question,String> questionIdColumn;
    @FXML
    private TableColumn<Question,String> questionTextColumn;
    @FXML
    private TableColumn<Question,String> questionAuthorColumn;

    public void initialize() {

    ObservableList<Question> questionList = FXCollections.observableArrayList(
            new Question(69,"1A23B","what is 2+2?","Fredi"),
            new Question(420,"420690","what is the meaning of life?","Fredi")
    );

        questionNumberColumn.setCellValueFactory(new PropertyValueFactory<>("questionNumber"));
        questionIdColumn.setCellValueFactory(new PropertyValueFactory<>("questionId"));
        questionTextColumn.setCellValueFactory(new PropertyValueFactory<>("questionText"));
        questionAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        questionsTableView.setItems(questionList);
    }

    public void backToCreateTest(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "CreateNewTestScreen/CreateNewTest.fxml", "C.E.M.S Test Manager - Create a new test");
    }

    public void LogOut(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml", "C.E.M.S Test Manager");
    }
}
