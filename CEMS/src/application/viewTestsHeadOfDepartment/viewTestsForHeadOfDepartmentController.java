package application.viewTestsHeadOfDepartment;

import client.Client;
import entity.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.*;

public class viewTestsForHeadOfDepartmentController {

    @FXML
    private AnchorPane header;

    @FXML
    private TableView<Test> reportsTableView;

    @FXML
    private Text usernameText;
    @FXML
    public void initialize() {
        ScreenManager.dragAndDrop(header);
        usernameText.setText(Client.user.getFullName());
        ObservableList<String> columns = FXCollections.observableArrayList();
        columns.addAll("Test Number", "Test ID", "Subject","Course Name","Lecturer");
        TableManager.createTable(reportsTableView, columns);
        double[] multipliers = {0.15, 0.1, 0.1, 0.13, 0.35};
        TableManager.resizeColumns(reportsTableView, multipliers);
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
