package application.CreateNewTestScreen.PickQuestionsScreen;

import application.ExitButton;
import application.MinimizeButton;
import application.Question;
import application.ScreenManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

//TODO: change add/delete buttons of questions
public class PickQuestionsController {

    @FXML
    private AnchorPane header;

    @FXML
    private TableView<Question> questionsTableView;
    @FXML
    private TableColumn<Question, CheckBox> questionCheckBoxColumn;
    @FXML
    private TableColumn<Question, String> questionNumberColumn;
    @FXML
    private TableColumn<Question, String> questionIdColumn;
    @FXML
    private TableColumn<Question, String> questionTextColumn;
    @FXML
    private TableColumn<Question, String> questionAuthorColumn;

    public void initialize() {
        ScreenManager.dragAndDrop(header);

        ObservableList<Question> questionList = FXCollections.observableArrayList(
                new Question(78, "1A23B", "what is 2+2?", "Fredi"),
                new Question(420, "42040", "what is the meaning of life?", "Fredi")
        );

        questionsTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !questionsTableView.getSelectionModel().isEmpty()) { //check whether the event was double click and the row contains a question
                // Get selected row data
//                    Question rowData = questionsTableView.getSelectionModel().getSelectedItem();
                // Create new pop-up window
                ScreenManager.getStage("CreateNewTestScreen/PickQuestionsScreen/QuestionPreviewPopup/questionPreview.fxml");
            }
        });

        questionCheckBoxColumn.setCellFactory(column -> new TableCell<>() {
            private final CheckBox checkBox = new CheckBox();

            @Override
            protected void updateItem(CheckBox item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(checkBox);
                }
            }
        });


        questionNumberColumn.setCellValueFactory(new PropertyValueFactory<>("questionNumber"));
        questionIdColumn.setCellValueFactory(new PropertyValueFactory<>("questionId"));
        questionTextColumn.setCellValueFactory(new PropertyValueFactory<>("questionText"));
        questionAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        questionsTableView.setItems(questionList);
    }

    public void backToCreateTest(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "CreateNewTestScreen/CreateNewTest.fxml", false);
    }

    public void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml", false);
    }

    public void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    private void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
