package application.MainMenuScreen;

import application.ExitButton;
import application.MinimizeButton;
import application.ScreenManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class MainMenuController {

    @FXML
    private AnchorPane header;

    public void initialize() {
        ScreenManager.dragAndDrop(header);
    }

    public void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml", false);
    }

    public void manageQuestions(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "ManageQuestionsScreen/ManageQuestions.fxml", false);
    }

    public void ViewReports(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "ViewReportsScreen/ViewReports.fxml", false);
    }

    public void CreateNewTest(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "CreateNewTestScreen/CreateNewTest.fxml", false);
    }

    public void addQuestion(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "AddAQuestionScreen/AddAQuestion.fxml", false);
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