package application.ViewStatisticsScreen.ReportScreen;

import application.MenuController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;


public class ReportController {
    MenuController menu = new MenuController();


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
        ObservableList<String> semester_options = FXCollections.observableArrayList("A", "B");
        semsterComboBox.setItems(semester_options);
        ObservableList<String> id_options = FXCollections.observableArrayList("020301", "020332");
        idComboBox.setItems(id_options);
        ObservableList<String> subject_options = FXCollections.observableArrayList("SoftWare Engineering", "Math", "...");
        subjectComboBox.setItems(subject_options);
    }

    public void backTo(ActionEvent event) {
        menu.goToNewScreen(event, "MainMenuScreen/MainMenu.fxml", "C.E.M.S Test Manager");
//        try {
//            Parent back_Root = FXMLLoader.load(getClass().getResource("../../MainMenuScreen/MainMenu.fxml"));
//            Scene back_Scene = new Scene(back_Root);
//            Stage back_Stage = new Stage();
//            back_Stage.setTitle("C.E.M.S Test Manager");
//            back_Stage.setScene(back_Scene);
//            back_Stage.show();
//
//            Node source = (Node) e.getSource();
//
//            // Get the Stage object that contains the source node
//            Stage stage = (Stage) source.getScene().getWindow();
//
//            // Close the stage
//            stage.close();
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
    }


    public void showReports(ActionEvent event) {
        menu.goToNewScreen(event, "ViewStatisticsScreen/ViewStatistics.fxml", "C.E.M.S Test Manager - Report");
//        try {
//            Parent showReports_root = FXMLLoader.load(getClass().getResource("../ViewStatistics.fxml"));
//            Scene showReports_Scene = new Scene(showReports_root);
//            Stage showReports_Stage = new Stage();
//            showReports_Stage.setTitle("C.E.M.S Test Manager");
//            showReports_Stage.setScene(showReports_Scene);
//            showReports_Stage.show();
//
//            Node source = (Node) e.getSource();
//
//            // Get the Stage object that contains the source node
//            Stage stage = (Stage) source.getScene().getWindow();
//
//            // Close the stage
//            stage.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
    }

    public void LogOut(ActionEvent event) {
        menu.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml", "C.E.M.S Test Manager");
//        try {
//            Parent logOut_Root = FXMLLoader.load(getClass().getResource("../../LoginWindowScreen/LoginWindow.fxml"));
//            Scene logOut_Scene = new Scene(logOut_Root);
//            Stage logOut_Stage = new Stage();
//            logOut_Stage.setTitle("C.E.M.S Test Manager");
//            logOut_Stage.setScene(logOut_Scene);
//            logOut_Stage.show();
//
//            Node source = (Node) e.getSource();
//
//            // Get the Stage object that contains the source node
//            Stage stage = (Stage) source.getScene().getWindow();
//
//            // Close the stage
//            stage.close();
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
    }
}
