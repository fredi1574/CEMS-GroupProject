package application.MainMenuScreen;

import util.*;
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
        ScreenManager.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml");
    }

    public void manageQuestions(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "ManageQuestionsScreen/ManageQuestions.fxml");
    }

    public void ViewReports(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "ViewReportsScreen/ViewReports.fxml");
    }

    public void CreateNewTest(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "CreateNewTestScreen/CreateNewTest.fxml");
    }

    public void addQuestion(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "AddAQuestionScreen/AddAQuestion.fxml");
    }

    @FXML
    private void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}