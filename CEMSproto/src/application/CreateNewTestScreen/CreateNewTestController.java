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
            Parent backToMenu_Root = FXMLLoader.load(getClass().getResource("../MainMenuScreen/MainMenu.fxml"));
            Scene backToMenu_Scene = new Scene(backToMenu_Root);
            Stage backToMenu_Stage = new Stage();
            backToMenu_Stage.setTitle("CMES Main Menu");
            backToMenu_Stage.setScene(backToMenu_Scene);
            backToMenu_Stage.show();

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
            Parent pickQuestion_Root = FXMLLoader.load(getClass().getResource("../PickQuestions.fxml"));
            Scene pickQuestion_Scene = new Scene(pickQuestion_Root);
            Stage pickQuestion_Stage = new Stage();
            pickQuestion_Stage.setTitle("Pick Questions");
            pickQuestion_Stage.setScene(pickQuestion_Scene);
            pickQuestion_Stage.show();

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
            preview_Stage.setTitle("C.E.M.S Test Manager");
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
    public void LogOut(ActionEvent e) {
        try {

            Parent logOut_Root = FXMLLoader.load(getClass().getResource("../LoginWindowScreen/LoginWindow.fxml"));
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
