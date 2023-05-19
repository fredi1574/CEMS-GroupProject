package application.AddAQuestionScreen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import util.*;

public class AddAQuestionController {

    @FXML
    private AnchorPane header;
    @FXML
    private TableView<String> courseTableView;

    public void initialize() {
        ScreenManager.dragAndDrop(header);

        ObservableList<String> columns = FXCollections.observableArrayList();
        columns.addAll("Course", "Subject");
        double[] multipliers = {0.75, 0.25};

        TableManager.createTable(courseTableView, columns);
        TableManager.resizeColumns(courseTableView, multipliers);
    }

    public void BackToMenu(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "MainMenuScreen/MainMenu.fxml");
    }

    public void LogOut(ActionEvent event) {
        LogOut.logOutToLoginScreen(event);
    }

    @FXML
    private void minimize(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

    @FXML
    public void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }
}