package application.ViewReportsScreen;

import application.ExitButton;
import application.MinimizeButton;
import application.ScreenChanger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

//todo: add the option to double click as an alternative to clicking on a button
public class ViewReportsController {

    @FXML
    private TableView<Report> reportsTableView;
    @FXML
    private TableColumn<Report, String> yearColumn;
    @FXML
    private TableColumn<Report, String> semesterColumn;
    @FXML
    private TableColumn<Report, String> subjectColumn;
    @FXML
    private TableColumn<Report, String> courseColumn;
    @FXML
    private TableColumn<Report, String> testIdColumn;

    @FXML
    public void initialize() {

        ObservableList<Report> reportList = FXCollections.observableArrayList(
                new Report(2023, 'A', "Computer Science", "Automatons", 42069),
                new Report(2021, 'B', "Mathematics", "Statistics", 13096)
        );

        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        semesterColumn.setCellValueFactory(new PropertyValueFactory<>("semester"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));
        testIdColumn.setCellValueFactory(new PropertyValueFactory<>("testId"));

        reportsTableView.setItems(reportList);
    }

    public void goBackToPreviousScreen(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "MainMenuScreen/MainMenu.fxml");
    }
    
    public void showReports(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "ViewReportsScreen/GraphScreen/ViewGraph.fxml");
    }

    public void LogOut(ActionEvent event) {
        ScreenChanger.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml");
    }

    public void closeClient(ActionEvent event){
        ExitButton.closeClient(event);
    }

    @FXML
    private void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
