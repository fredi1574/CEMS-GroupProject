package application.manageTestsScreen;

import client.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.ExitButton;
import util.MinimizeButton;

import java.awt.event.ActionEvent;

public class viewTestAwaitingApprovalController {

    @FXML
    private TextField GradField;

    @FXML
    private TextField cheatinigField;

    @FXML
    private TextField correcAnswerField;

    @FXML
    private AnchorPane header;

    @FXML
    private Text nameOfuser;

    @FXML
    private TextField studentIDField;

    @FXML
    private TextArea teacherComment;

    @FXML
    private TextField testIDField;

    @FXML
    private TextField totalQuestionsField;

    @FXML
    private Button viewTestInProgressBtn;
    public void initialize() {
        nameOfuser.setText(Client.user.getFullName());

    }
    @FXML
    void BackTOManageTest(ActionEvent event) {

    }

    @FXML
    void VeiwSoluationTest(ActionEvent event) {

    }

    @FXML
    public void closeClient(javafx.event.ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    public void minimizeWindow(javafx.event.ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
    @FXML
    void saveDiscionLecturer(ActionEvent event) {

    }

}
