package application.CreateNewTestScreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestPreviewPopUpController {

    public void LogOut(ActionEvent e) {
        try {

            Parent CMEMS_Root = FXMLLoader.load(getClass().getResource("CEMS.fxml"));
            Scene CMEMS_Scene = new Scene(CMEMS_Root);
            Stage CMEMS_Stage = new Stage();
            CMEMS_Stage.setTitle("C.E.M.S Test Manager");
            CMEMS_Stage.setScene(CMEMS_Scene);
            CMEMS_Stage.show();

            Node source = (Node) e.getSource();

            // Get the Stage object that contains the source node
            Stage stage = (Stage) source.getScene().getWindow();

            // Close the stage
            stage.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void BackTo(ActionEvent e) {
        try {
            Parent QuestionManagement_Root = FXMLLoader.load(getClass().getResource("CreateNewTest.fxml"));
            Scene QuestionManagement_Scene = new Scene(QuestionManagement_Root);
            Stage QuestionManagement_Stage = new Stage();
            QuestionManagement_Stage.setTitle("C.E.M.S Create Test");
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

}
