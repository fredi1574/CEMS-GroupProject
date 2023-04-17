package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CreateTestController {
    public void BackToMenu(ActionEvent e) {
        try {
            Parent QuestionManagement_Root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            Scene QuestionManagement_Scene = new Scene(QuestionManagement_Root);
            Stage QuestionManagement_Stage = new Stage();
            QuestionManagement_Stage.setTitle("CMES Main Menu");
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
    public void openPreview(ActionEvent e)  {
        try {
            Parent preview_Root = FXMLLoader.load(getClass().getResource("TestPreviewPopUp.fxml"));
            Scene preview_Scene = new Scene(preview_Root);
            Stage preview_Stage = new Stage();
            preview_Stage.setTitle("Pick Questions");
            preview_Stage.setScene(preview_Scene);
            preview_Stage.show();

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
