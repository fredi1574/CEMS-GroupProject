package application.ManageQuestionsScreen;

import application.ScreenChanger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

//todo: add the option to get back to this screen after clicking on add question.
public class ManageQuestionsController {

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    public void initialize() {
        ObservableList<String> options = FXCollections.observableArrayList("Software Engineering", "Math", "...");
        comboBox.setItems(options);
    }


    public void LogOut(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml", "C.E.M.S Test Manager");
    }

    public void back(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "MainMenuScreen/MainMenu.fxml", "C.E.M.S Test Manager");
    }

    public void AddQuestion(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "AddAQuestionScreen/AddAQuestion.fxml", "C.E.M.S Test Manager - Add a question");
    }
}
