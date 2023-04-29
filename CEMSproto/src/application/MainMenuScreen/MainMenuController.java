package application.MainMenuScreen;

import application.ExitButton;
import application.MinimizeButton;
import application.ScreenChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainMenuController {

    public void LogOut(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml");
    }

    public void manageQuestions(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "ManageQuestionsScreen/ManageQuestions.fxml");
    }

    public void ViewReports(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "ViewReportsScreen/ViewReports.fxml");
    }

    public void CreateNewTest(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "CreateNewTestScreen/CreateNewTest.fxml");
    }

    public void addQuestion(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "AddAQuestionScreen/AddAQuestion.fxml");
    }

    @FXML
    private void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    private void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}