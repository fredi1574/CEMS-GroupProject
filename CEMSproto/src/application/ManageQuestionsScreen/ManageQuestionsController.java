package application.ManageQuestionsScreen;

import application.ExitButton;
import application.MinimizeButton;
import application.ScreenChanger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class ManageQuestionsController {

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    public void initialize() {
        ObservableList<String> options = FXCollections.observableArrayList("Software Engineering", "Math", "...");
        comboBox.setItems(options);
    }

    public void LogOut(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml");
    }

    public void back(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "MainMenuScreen/MainMenu.fxml");
    }

    public void AddQuestion(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "AddAQuestionScreen/AddAQuestion.fxml");
    }

    public void closeClient(ActionEvent event){
        ExitButton.closeClient(event);
    }

    @FXML
    private void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
