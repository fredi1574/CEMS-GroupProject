package application.viewTestsStudent;

import client.Client;
import client.ClientUI;
import common.MsgHandler;
import common.TypeMsg;
import entity.Course;
import entity.StudentTest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.*;

import java.util.List;

public class viewTestStudentController {

    @FXML
    private AnchorPane header;

    @FXML
    private Text usernameText;

    @FXML
    private TableView<StudentTest> reportsTableView;

    @FXML
    private ComboBox<Course> courseComboBox;

    @FXML
    private ComboBox<String> yearComboBox;

    @FXML
    private ComboBox<String> semesterComboBox;

    public void initialize() {
        ScreenManager.dragAndDrop(header);
        usernameText.setText(Client.user.getFullName());

        //TODO: fetch all the courses to the combo box

        bindDataToComboBoxes();

        MsgHandler getTable = new MsgHandler(TypeMsg.GetStudentsTests, Client.user.getId());
        ClientUI.chat.accept(getTable);

        //TODO: the current SQL query returns subjectID instead of subjectName.
        ObservableList<StudentTest> tests = FXCollections.observableArrayList((List) ClientUI.chat.getStudentTests());

        buildTable(tests);
    }

    public void bindDataToComboBoxes() {
        ObservableList<String> yearList = FXCollections.observableArrayList("2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015");
        ObservableList<String> semesterList = FXCollections.observableArrayList("A", "B", "Summer");

        // Binds the data into the correct dropdown lists
        yearComboBox.setItems(yearList);
        semesterComboBox.setItems(semesterList);
    }

    public void buildTable(ObservableList<StudentTest> tests) {
        ObservableList<String> columns = FXCollections.observableArrayList();
        columns.addAll("Subject", "Course", "Year", "Semester", "Session", "Score");

        TableManager.createTable(reportsTableView, columns);
        TableManager.importData(reportsTableView, tests);

        double[] multipliers = {0.295, 0.299, 0.1, 0.1, 0.1, 0.1};
        TableManager.resizeColumns(reportsTableView, multipliers);

    }

    @FXML
    public void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.loginPath);
    }

    @FXML
    void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    public void goBackToPreviousScreen(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuPath);
    }

    @FXML
    void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

    @FXML
    void showReports(ActionEvent event) {

    }

}

