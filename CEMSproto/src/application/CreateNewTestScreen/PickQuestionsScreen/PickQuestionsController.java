package application.CreateNewTestScreen.PickQuestionsScreen;

import application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class PickQuestionsController {

    @FXML
    private AnchorPane header;

    @FXML
    private TableView<Question> questionsTableView;

    public void initialize() {
        ScreenManager.dragAndDrop(header);

        ObservableList<Question> questionList = FXCollections.observableArrayList(
                new Question(78, "1A23B", "what is 2+2?", "Fredi"),
                new Question(420, "42040", "what is the meaning of life?", "Fredi")
        );

        ObservableList<String> columnList = FXCollections.observableArrayList();
        columnList.addAll("Number", "Question ID", "Question Text", "Author");

        ObservableList<TableColumn<Question, ?>> columns = TableManager.createTable(questionsTableView, columnList, questionList);

        TableManager.addCheckBoxesToTable(questionsTableView);
        TableManager.addDoubleClickFunctionality(questionsTableView, "CreateNewTestScreen/PickQuestionsScreen/QuestionPreviewPopup/questionPreview.fxml");

        //todo: find a cleaner way to do change the width of every column
        for (TableColumn<Question, ?> column : columns) {
            if (column.getText().equals("Question Text")) {
                column.prefWidthProperty().bind(questionsTableView.widthProperty().multiply(0.625));
            } else {
                column.prefWidthProperty().bind(questionsTableView.widthProperty().multiply(0.1));
            }
        }
    }

    public void backToCreateTest(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "CreateNewTestScreen/CreateNewTest.fxml");
    }

    public void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml");
    }

    public void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    private void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
