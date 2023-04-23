package application.MainMenuScreen;

import application.ScreenChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainMenuController {

    public void backTo(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml", "C.E.M.S Test Manager - Login");
    }

    public void manageQuestions(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "ManageQuestionsScreen/ManageQuestions.fxml", "C.E.M.S Test Manager - Manage Questions");
    }

    public void ViewReports(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "ViewReportsScreen/ViewReports.fxml", "C.E.M.S Test Manager - Reports");

//        try {
//            FXMLLoader loader = new FXMLLoader(ScreenChanger.class.getResource("../ViewReportsScreen/GraphScreen/ViewGraph.fxml"));
//            Parent root = loader.load();
//
//            Scene scene = new Scene(root);
//            Stage stage = new Stage();
//            stage.setTitle("title");
//            stage.getIcons().add(new Image("application/images/Title_icon.png"));
//
//            stage.setScene(scene);
//            stage.show();
//
//            // Get the Stage object that contains the source node
//            Node source = (Node) event.getSource();
//
//            Stage stage1 = (Stage) source.getScene().getWindow();
//
//            // Close the stage
//            stage1.close();
//
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
    }

    public void CreateNewTest(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "CreateNewTestScreen/CreateNewTest.fxml", "C.E.M.S Test Manager - Create a new test");
    }

    public void addQuestion(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "AddAQuestionScreen/AddAQuestion.fxml", "C.E.M.S Test Manager - Add a new question");
    }
}