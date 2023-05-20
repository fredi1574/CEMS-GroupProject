package application.addAQuestionScreen;

import client.ClientUI;
import common.MsgHandler;
import common.TypeMsg;
import entity.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import util.*;

import java.util.ArrayList;

public class AddAQuestionController {

    @FXML
    private AnchorPane header;
    @FXML
    private TableView<String> courseTableView;
    @FXML
    private TextArea answer1;

    @FXML
    private TextArea answer2;

    @FXML
    private TextArea answer3;

    @FXML
    private TextArea answer4;

    @FXML
    private TextField questionID;

    @FXML
    private TextArea questionTextField;
    @FXML
    private TextField CorrectAnswer;

    public void initialize() {
        ScreenManager.dragAndDrop(header);

        ObservableList<String> columns = FXCollections.observableArrayList();
        columns.addAll("Course", "Subject");
        double[] multipliers = {0.75, 0.25};

        TableManager.createTable(courseTableView, columns);
        TableManager.resizeColumns(courseTableView, multipliers);
    }
    @FXML
    private void saveData(ActionEvent event) {
        Question newQuestion= new Question(
                "123",
                questionID.getText(),
                questionTextField.getText(),
                "May",
                "Math",
                "Algebra",
                //subjectField.getText(),
                //courseNameField.getText(),
                answer1.getText(),
                answer2.getText(),
                answer3.getText(),
                answer4.getText(),
                CorrectAnswer.getText()
        );

        ArrayList<Question> arr = new ArrayList<>();
        arr.add(newQuestion);
        MsgHandler addNewQuestion = new MsgHandler(TypeMsg.AddNewQuestion, arr);
        ClientUI.chat.accept(addNewQuestion);
    }
    public void BackToMenu(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "/application/mainMenuScreen/MainMenu.fxml");
    }

    public void LogOut(ActionEvent event) {
        LogOut.logOutToLoginScreen(event);
    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

    @FXML
    public void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }
}