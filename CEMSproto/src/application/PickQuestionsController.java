package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

//TODO: change add/delete buttons of questions
public class PickQuestionsController {
    @FXML
    TableView<Question> tableView = new TableView<>();
    @FXML
    TableColumn<Question, String> questionNumber;
    @FXML
    TableColumn<Question, String> questionID;
    @FXML
    TableColumn<Question, String> questionDescription;
    @FXML
    TableColumn<Question, String> author;
    ObservableList<Question> questions = FXCollections.observableArrayList(new Question("1", "023564", "<What is the value of x in this equation>", "Roman"),
            new Question("2", "025324", "<What is the value of y in this equation>", "Abed"));

    public void initialize() {

        questionNumber.setCellValueFactory(new PropertyValueFactory<>("Number"));
        questionID.setCellValueFactory(new PropertyValueFactory<>("Question ID"));
        questionDescription.setCellValueFactory(new PropertyValueFactory<>("Questions"));
        author.setCellValueFactory(new PropertyValueFactory<>("Author"));
        tableView.setItems(questions);
    }

    public void backToCreateTest(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateTest.fxml"));
            Parent create_test_Root = loader.load();

            Scene create_Test_Scene = new Scene(create_test_Root);
            Stage create_test_Stage = new Stage();
            create_test_Stage.setTitle("Create Test");
            create_test_Stage.setScene(create_Test_Scene);
            create_test_Stage.show();

            Node source = (Node) e.getSource();

            // Get the Stage object that contains the source node
            Stage stage = (Stage) source.getScene().getWindow();

            // Close the stage
            stage.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
