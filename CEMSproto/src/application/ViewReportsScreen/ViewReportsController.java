package application.ViewReportsScreen;

import application.ExitButton;
import application.MinimizeButton;
import application.ScreenManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

//todo: add the option to double click as an alternative to clicking on a button
public class ViewReportsController {

    @FXML
    private AnchorPane header;

    @FXML
    private ComboBox<Integer> yearComboBox;
    @FXML
    private ComboBox<Character> semesterComboBox;
    @FXML
    private ComboBox<String> subjectComboBox;

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
        ScreenManager.dragAndDrop(header);

        ObservableList<Integer> yearList = FXCollections.observableArrayList(2023, 2022, 2021, 2020, 2019, 2018, 2017, 2016, 2015);
        ObservableList<Character> semesterList = FXCollections.observableArrayList('A', 'B', 'S');
        ObservableList<String> subjectList = FXCollections.observableArrayList("Computer Science", "Mathematics",
                "Algorithms", "Statistics", "Bananas and monkeys", "Introduction to Databases");

        yearComboBox.setItems(yearList);
        semesterComboBox.setItems(semesterList);
        subjectComboBox.setItems(subjectList);

        ObservableList<Report> reportList = FXCollections.observableArrayList(
                new Report(2023, 'A', "Computer Science", "Automatons", 42069),
                new Report(2021, 'B', "Mathematics", "Statistics", 13096)
        );

        reportsTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !reportsTableView.getSelectionModel().isEmpty()) { //check whether the event was double click and the row contains a question

                //Get selected row data
                //Question rowData = questionsTableView.getSelectionModel().getSelectedItem();

                // Create new pop-up window
                ScreenManager.popUpScreen("ViewReportsScreen/GraphScreen/ViewGraph.fxml");
            }
        });

        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        semesterColumn.setCellValueFactory(new PropertyValueFactory<>("semester"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));
        testIdColumn.setCellValueFactory(new PropertyValueFactory<>("testId"));

        reportsTableView.setItems(reportList);
    }

    public void goBackToPreviousScreen(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "MainMenuScreen/MainMenu.fxml");
    }

    public void showReports() {
        ScreenManager.popUpScreen("ViewReportsScreen/GraphScreen/ViewGraph.fxml");
    }

    public void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml");
    }

    @FXML
    public void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    private void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
