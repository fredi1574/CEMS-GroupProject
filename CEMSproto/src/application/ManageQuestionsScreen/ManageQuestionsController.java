package application.ManageQuestionsScreen;
import application.*;
import application.Client.ClientUI;
import application.common.MsgHandler;
import application.common.TypeMsg;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
public class ManageQuestionsController {

    public TextField searchField;
    @FXML
    private AnchorPane header;

    @FXML
    private TableView<Question> manageQuestionsTableView;

    @FXML
    public void initialize() {
        System.out.println("init manage questions");
        ScreenManager.dragAndDrop(header);

//        ObservableList<String> options = FXCollections.observableArrayList("Software Engineering", "Math", "...");
//        comboBox.setItems(options);

        MsgHandler getTable = new MsgHandler(TypeMsg.GetQuestions, null);
        ClientUI.chat.accept(getTable);


//        //creates the question table
        //TODO: fix this mess
        ArrayList<Object> questionObjectsList = ClientUI.chat.getList();
        ObservableList<Question> questionList = FXCollections.observableArrayList((List)questionObjectsList);
        //ArrayList<Question> questionList = ClientUI.chat.getList();

        ObservableList<String> columnList = FXCollections.observableArrayList();
        columnList.addAll("Question Number","ID","Subject","Course Name", "Question Text", "Lecturer");
        TableManager.createTable(manageQuestionsTableView,columnList);
        TableManager.importData(manageQuestionsTableView,questionList);
        TableManager.addDoubleClickFunctionality(manageQuestionsTableView,"ManageQuestionsScreen/UpdateQuestion.fxml",true);

        double[] multipliers = {0.15, 0.1,0.1,0.13,0.35,0.162};
        TableManager.resizeColumns(manageQuestionsTableView, multipliers);
        //filter result as you search
        FilteredList<Question> filteredData = new FilteredList<>(questionList, b -> true);
        TableManager.MakeFilterListForSearch(filteredData, searchField, Question::getQuestion_text);

        SortedList<Question> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(manageQuestionsTableView.comparatorProperty());
        manageQuestionsTableView.setItems(sortedData);

    }


    public void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml");
    }

    public void back(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "MainMenuScreen/MainMenu.fxml");
    }

    public void AddQuestion(ActionEvent event) {
        ScreenManager.goToNewScreen(event, "AddAQuestionScreen/AddAQuestion.fxml");
    }

    public void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
