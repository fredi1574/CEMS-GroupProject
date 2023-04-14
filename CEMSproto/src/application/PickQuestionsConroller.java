package application;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class PickQuestionsConroller {
    @FXML
    TableView<Question> tableView = new TableView<>();
    @FXML
    public void initialize() {

        ObservableList<Question> qeustion1 = FXCollections.observableArrayList(new Question("1",
                "023564","<What the value of x in this equation>","Roman"));
        ObservableList<Question> qeustion2 = FXCollections.observableArrayList(new Question("2",
                "025324","<What the value of y in this equation>","Abed"));
        tableView.setItems(qeustion1);
        tableView.setItems(qeustion2);

    }

}
