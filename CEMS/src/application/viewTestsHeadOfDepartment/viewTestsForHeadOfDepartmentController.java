package application.viewTestsHeadOfDepartment;

import client.Client;
import client.ClientUI;
import common.MsgHandler;
import common.TypeMsg;
import entity.Question;
import entity.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.*;

import java.util.List;

public class viewTestsForHeadOfDepartmentController {

    @FXML
    private AnchorPane header;

    @FXML
    private TableView<Test> reportsTableView;

    @FXML
    private Text usernameText;
    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<String> SelectCourseCombo;

    @FXML
    public void initialize() {
        usernameText.setText(Client.user.getFullName());
        // Creates the question table

        //ObservableList<Test> testsBySubject = FXCollections.observableArrayList((List) ClientUI.chat.getQuestions());
        ObservableList<String> columns = FXCollections.observableArrayList();
        columns.addAll("Test Number", "ID", "Subject","Course Name", "Lecturer");
        TableManager.createTable(reportsTableView, columns);
        //TableManager.importData(reportsTableView, questions);
        double[] multipliers = {0.2, 0.1, 0.2, 0.3, 0.2};
        TableManager.resizeColumns(reportsTableView, multipliers);
        // Filter the results as you search
//        FilteredList<Test> filteredData = new FilteredList<>(testsBySubject, b -> true);
//        TableManager.MakeFilterListForSearch(filteredData, searchField, Test::getId);
//
//        SortedList<Test> sortedData = new SortedList<>(filteredData);
//        sortedData.comparatorProperty().bind(reportsTableView.comparatorProperty());
//        reportsTableView.setItems(sortedData);
    }

    @FXML
    public void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.loginPath);
    }

    @FXML
    public void goBackToPreviousScreen(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuHeadOfDepartPath);
    }

    @FXML
    public void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }


    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }


}
