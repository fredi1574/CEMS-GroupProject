package application.manageTestsScreen;

import entity.StateManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import util.ExitButton;
import util.MinimizeButton;
import util.PathConstants;
import util.ScreenManager;

import java.awt.*;

public class viewActiveTestController {
    @FXML
    public TextField testIdTextField;
    public Label courseNameLabel;
    public Label subjecteNameLabel;
    public TextField numOfQuestionsTextField;
    public TextField testCodeTextField;
    public Label TimeRemainingText;
    public TextField timeLeftTextField;
    public Label unlockTestField;
    public Label lockTestField;
    public TextArea TestCommentsTextArea;
    public TextField testDateTextField;
    public TextField testDurationTextField;
    public TextField startingTimeTextField;
    @FXML
    private AnchorPane header;
    //private TextField testIdTextField;
    public StateManagement stateManagement;

    public void initialize() {
        ScreenManager.dragAndDrop(header);
        stateManagement = StateManagement.getInstance();

        testIdTextField.setText(stateManagement.getTestID());
        courseNameLabel.setText(stateManagement.course.getCourseName());
        subjecteNameLabel.setText(stateManagement.course.getSubjectName());
        testCodeTextField.setText(stateManagement.getTestCode());

    }

    public void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.loginPath);
    }

    public void back(ActionEvent event) {
        ExitButton.closePopUp(event);
    }
    @FXML
    private void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

    public void lockTest(ActionEvent actionEvent) {
    }

    public void unlockTest(ActionEvent actionEvent) {
    }

    public void sendExtraTimeRequest(ActionEvent actionEvent) {

    }
}
