package application.ManageQuestionsScreen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class ManageQuestionsController {

    @FXML
    private ComboBox<String> combox;


    @FXML
    public void initialize() {
        ObservableList<String> options = FXCollections.observableArrayList("Software Enginering", "Math", "...");
        combox.setItems(options);
    }


    public void LogOut(ActionEvent e) {
        try {

            Parent CMEMS_Root = FXMLLoader.load(getClass().getResource("../LoginWindowScreen/LoginWindow.fxml"));
            Scene CMEMS_Scene = new Scene(CMEMS_Root);
            Stage CMEMS_Stage = new Stage();
            CMEMS_Stage.setTitle("Login Window");
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

    public void back(ActionEvent e) {
        try {
            Parent back_Root = FXMLLoader.load(getClass().getResource("../MainMenuScreen/MainMenu.fxml"));
            Scene back_Scene = new Scene(back_Root);
            Stage back_Stage = new Stage();
            back_Stage.setTitle("C.E.M.S Test Manager");
            back_Stage.setScene(back_Scene);
            back_Stage.show();

            Node source = (Node) e.getSource();

            // Get the Stage object that contains the source node
            Stage stage = (Stage) source.getScene().getWindow();

            // Close the stage
            stage.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void AddQuestion(ActionEvent e) {
        try {
            Parent back_Root = FXMLLoader.load(getClass().getResource("../AddAQuestionScreen/AddAQuestion.fxml"));
            Scene back_Scene = new Scene(back_Root);
            Stage back_Stage = new Stage();
            back_Stage.setTitle("C.E.M.S AddQuestion");
            back_Stage.setScene(back_Scene);
            back_Stage.show();

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
