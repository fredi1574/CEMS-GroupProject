package application.MainMenuScreen;

import application.ScreenChanger;
import javafx.event.ActionEvent;

public class MainMenuController {

    public void backTo(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml", "C.E.M.S Test Manager - Login");
    }

    public void manageQuestions(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "ManageQuestionsScreen/ManageQuestions.fxml", "C.E.M.S Test Manager - Manage Questions");
    }

    public void ViewStatistics(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "ViewStatisticsScreen/ViewStatistics.fxml", "C.E.M.S Test Manager - Reports");
    }

    public void CreateNewTest(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "CreateNewTestScreen/CreateNewTest.fxml", "C.E.M.S Test Manager - Create a new test");
    }

    public void addQuestion(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "AddAQuestionScreen/AddAQuestion.fxml", "C.E.M.S Test Manager - Add a new question");
    }
}