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

        ObservableList<String> course_options = FXCollections.observableArrayList("Algorithms", "Databases ", "Operating System call");
        courseComboBox.setItems(course_options);
        ObservableList<String> year_options = FXCollections.observableArrayList("2020", "2021", "2022", "2023");
        yearComboBox.setItems(year_options);
        ObservableList<String> semster_options = FXCollections.observableArrayList("A", "B");
        semsterComboBox.setItems(semster_options);
        ObservableList<String> id_options = FXCollections.observableArrayList("020301", "020332");
        idComboBox.setItems(id_options);
        ObservableList<String> subject_options = FXCollections.observableArrayList("SoftWare Engineering", "Math", "...");
        subjectComboBox.setItems(subject_options);
    }
    public void backTo(ActionEvent e) {
        try {
        	 Parent back_Root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
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


    public void showReports(ActionEvent e) {
        try {
            Parent showReports_root = FXMLLoader.load(getClass().getResource("Statistics.fxml"));
            Scene showReports_Scene = new Scene(showReports_root);
            Stage showReports_Stage = new Stage();
            showReports_Stage.setTitle("C.E.M.S Test Manager");
            showReports_Stage.setScene(showReports_Scene);
            showReports_Stage.show();

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
