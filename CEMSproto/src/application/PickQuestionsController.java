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

    //TODO: add elements to questions table
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
            Parent backToCreateTest_Root = loader.load();
            Scene backToCreateTest_Root_Scene = new Scene(backToCreateTest_Root);
            Stage backToCreateTest_Stage = new Stage();
            backToCreateTest_Stage.setTitle("C.E.M.S Test Manager");
            backToCreateTest_Stage.setScene(backToCreateTest_Root_Scene);
            backToCreateTest_Stage.show();

            Node source = (Node) e.getSource();

            // Get the Stage object that contains the source node
            Stage stage = (Stage) source.getScene().getWindow();

            // Close the stage
            stage.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void LogOut(ActionEvent e) {
        try {

            Parent logOut_Root = FXMLLoader.load(getClass().getResource("LoginWindow.fxml"));
            Scene logOut_Scene = new Scene(logOut_Root);
            Stage logOut_Stage = new Stage();
            logOut_Stage.setTitle("C.E.M.S Test Manager");
            logOut_Stage.setScene(logOut_Scene);
            logOut_Stage.show();

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
