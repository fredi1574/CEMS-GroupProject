package application.ViewReportsScreen;

import application.ScreenChanger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;


public class ViewReportsController {


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
        ScreenChanger.goToNewScreen(event, "MainMenuScreen/MainMenu.fxml", "C.E.M.S Test Manager");
    }


    public void showReports(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "ViewReportsScreen/GraphScreen/ViewGraph.fxml", "C.E.M.S Test Manager - Report");
    }

    public void LogOut(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml", "C.E.M.S Test Manager");
    }
}
