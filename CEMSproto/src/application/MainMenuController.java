package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainMenuController {


    public void backTo(ActionEvent e) {
        try {
            Parent backTo_Root = FXMLLoader.load(getClass().getResource("LoginWindow.fxml"));
            Scene backTo_Scene = new Scene(backTo_Root);
            Stage backTo_Stage = new Stage();
            backTo_Stage.setTitle("C.E.M.S Test Manager");
            backTo_Stage.setScene(backTo_Scene);
            backTo_Stage.show();

            Node source = (Node) e.getSource();

            // Get the Stage object that contains the source node
            Stage stage = (Stage) source.getScene().getWindow();

            // Close the stage
            stage.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void manageQuestions(ActionEvent e) {

        // Set the action to move to QuestionManagement.fxml when ManageQuestionBtn is clicked

        try {
            Parent manageQuestions_Root = FXMLLoader.load(getClass().getResource("QuestionManagement.fxml"));
            Scene manageQuestions_Scene = new Scene(manageQuestions_Root);
            Stage manageQuestions_Stage = new Stage();
            manageQuestions_Stage.getIcons().add(new Image("application/images/icons8-title-icon.png"));
            manageQuestions_Stage.setTitle("C.E.M.S Test Manager");
            manageQuestions_Stage.setScene(manageQuestions_Scene);
            manageQuestions_Stage.show();

            Node source = (Node) e.getSource();

            // Get the Stage object that contains the source node
            Stage stage = (Stage) source.getScene().getWindow();

            // Close the stage
            stage.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    public void ViewStatistics(ActionEvent e) {


        try {
            Parent viewStatistics_Root = FXMLLoader.load(getClass().getResource("Reports.fxml"));
            Scene viewStatistics_Scene = new Scene(viewStatistics_Root);
            Stage viewStatistics_Stage = new Stage();
            viewStatistics_Stage.setTitle("C.E.M.S Test Manager");
            viewStatistics_Stage.setScene(viewStatistics_Scene);
            viewStatistics_Stage.show();

            Node source = (Node) e.getSource();

            // Get the Stage object that contains the source node
            Stage stage = (Stage) source.getScene().getWindow();

            // Close the stage
            stage.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void CreateNewTest(ActionEvent e) {
        try {
            Parent createNewTest_Root = FXMLLoader.load(getClass().getResource("CreateTest.fxml"));
            Scene createNewTest_Scene = new Scene(createNewTest_Root);
            Stage createNewTest_Stage = new Stage();
            createNewTest_Stage.setTitle("C.E.M.S Test Manager");
            createNewTest_Stage.setScene(createNewTest_Scene);
            createNewTest_Stage.show();

            Node source = (Node) e.getSource();

            // Get the Stage object that contains the source node
            Stage stage = (Stage) source.getScene().getWindow();

            // Close the stage
            stage.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void addQuestion(ActionEvent e) {
        try {
            Parent addQuestionRoot = FXMLLoader.load(getClass().getResource("AddQuestion.fxml"));
            Scene addQuestionScene = new Scene(addQuestionRoot);
            Stage addQuestionStage = new Stage();
            addQuestionStage.setTitle("C.E.M.S Test Manager");
            addQuestionStage.setScene(addQuestionScene);
            addQuestionStage.show();

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