package application.mainMenuScreen;

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
        ScreenManager.goToNewScreen(event, "/application/loginWindowScreen/LoginWindow.fxml");
    }

    public void manageQuestions(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "/application/manageQuestionsScreen/ManageQuestions.fxml");
    }

    public void ViewReports(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "/application/viewReportsScreen/ViewReports.fxml");
    }

    public void CreateNewTest(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "/application/createNewTestScreen/CreateNewTest.fxml");
    }

    public void addQuestion(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "/application/addAQuestionScreen/AddAQuestion.fxml");
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