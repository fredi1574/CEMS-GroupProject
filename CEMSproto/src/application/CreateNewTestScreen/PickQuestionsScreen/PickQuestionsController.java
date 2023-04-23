package application.CreateNewTestScreen.PickQuestionsScreen;

import application.ScreenChanger;
import application.Question;
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
    TableView<Question> tableView = new TableView<>();
    @FXML
    TableColumn<Question, String> questionNumber;
    @FXML
    TableColumn<Question, String> questionID;
    @FXML
    TableColumn<Question, String> questionDescription;
    @FXML
    TableColumn<Question, String> author;

    //TODO: add elements to questions table
    ObservableList<Question> questions = FXCollections.observableArrayList(new Question("1", "023564", "<What is the value of x in this equation>", "Roman"),
            new Question("2", "025324", "<What is the value of y in this equation>", "Abed"));

    public void initialize() {

        questionNumber.setCellValueFactory(new PropertyValueFactory<>("Number"));
        questionID.setCellValueFactory(new PropertyValueFactory<>("Question ID"));
        questionDescription.setCellValueFactory(new PropertyValueFactory<>("Questions"));
        author.setCellValueFactory(new PropertyValueFactory<>("Author"));
        tableView.setItems(questions);
    }

    public void backToCreateTest(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "CreateNewTestScreen/CreateNewTest.fxml", "C.E.M.S Test Manager - Create a new test");
    }

    public void LogOut(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml", "C.E.M.S Test Manager");
    }
}
