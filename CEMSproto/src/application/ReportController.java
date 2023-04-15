package application;

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

public class ReportController {
    @FXML
    private ComboBox<String> courseComboBox;
    @FXML
    private ComboBox<String> yearComboBox;
    @FXML
    private ComboBox<String> semsterComboBox;
    @FXML
    private ComboBox<String> idComboBox;
    @FXML
    private ComboBox<String> subjectComboBox;

    @FXML
    public void initialize() {

        ObservableList<String> course_options = FXCollections.observableArrayList( "Algorithms","Databases ","Operating System call");
        courseComboBox .setItems(course_options);
        ObservableList<String> year_options = FXCollections.observableArrayList( "2020","2021","2022","2023");
        yearComboBox.setItems(year_options);
        ObservableList<String> semster_options = FXCollections.observableArrayList( "A","B");
        semsterComboBox.setItems(semster_options);
        ObservableList<String> id_options = FXCollections.observableArrayList( "020301","020332");
        idComboBox.setItems(id_options);
        ObservableList<String> subject_options = FXCollections.observableArrayList( "SoftWare Engineering","Math","...");
        subjectComboBox.setItems(subject_options);
    }
    public void backToMenu(ActionEvent e) {
        try {
            Parent static_Root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            Scene static_Scene = new Scene(static_Root);
            Stage static_Root_Stage = new Stage();
            static_Root_Stage .setTitle("CMES Main Menu");
            static_Root_Stage.setScene(static_Scene);
            static_Root_Stage.show();

            Node source = (Node) e.getSource();

            // Get the Stage object that contains the source node
            Stage stage = (Stage) source.getScene().getWindow();

            // Close the stage
            stage.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void showReports (ActionEvent e){
         try {
            Parent StatRoot = FXMLLoader.load(getClass().getResource("Statistics.fxml"));
            Scene statScene = new Scene(StatRoot);
            Stage statStage = new Stage();
            statStage.setTitle("Statistics");
            statStage.setScene(statScene);
            statStage.show();

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
