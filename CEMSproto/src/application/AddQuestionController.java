package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddQuestionController {
    public void BackToMenu(ActionEvent e) {
        try {
            Parent QuestionManagement_Root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            Scene QuestionManagement_Scene = new Scene(QuestionManagement_Root);
            Stage QuestionManagement_Stage = new Stage();
            QuestionManagement_Stage.setTitle("C.E.M.S Main Menu");
            QuestionManagement_Stage.setScene(QuestionManagement_Scene);
            QuestionManagement_Stage.show();

            Node source = (Node) e.getSource();

            // Get the Stage object that contains the source node
            Stage stage = (Stage) source.getScene().getWindow();

            // Close the stage
            stage.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void pickQuestion(ActionEvent e) {


        try {
            Parent QuestionManagement_Root = FXMLLoader.load(getClass().getResource("PickQuestions.fxml"));
            Scene QuestionManagement_Scene = new Scene(QuestionManagement_Root);
            Stage QuestionManagement_Stage = new Stage();
            QuestionManagement_Stage.setTitle("Pick Questions");
            QuestionManagement_Stage.setScene(QuestionManagement_Scene);
            QuestionManagement_Stage.show();

            Node source = (Node) e.getSource();

            // Get the Stage object that contains the source node
            Stage stage = (Stage) source.getScene().getWindow();

            // Close the stage
            stage.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public void LogOut(ActionEvent e) {
        try {

            Parent logOut_Root = FXMLLoader.load(getClass().getResource("LoginWindow.fxml"));
            Scene logOut_Scene = new Scene(logOut_Root);
            Stage logOut_Stage = new Stage();
            logOut_Stage.setTitle("C.E.M.S Test Manager");
            logOut_Stage.setScene(logOut_Scene);
            logOut_Stage.show();

            Node source = (Node) e.getSource();

            // Get the Stage object that contains the source node
            Stage stage = (Stage) source.getScene().getWindow();

            // Close the stage
            stage.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
