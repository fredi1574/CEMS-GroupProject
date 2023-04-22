package application.MainMenuScreen;

import application.MenuController;
import javafx.event.ActionEvent;

public class MainMenuController {

    MenuController menu = new MenuController();

    //    public void goToNewScreen(ActionEvent event, String screenRelativePath, String title) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource(screenRelativePath));
//            Parent root = loader.load();
//
//            Scene scene = new Scene(root);
//            Stage stage = new Stage();
//            stage.setTitle(title);
//            stage.getIcons().add(new Image("application/images/Title_icon.png"));
//
//            stage.setScene(scene);
//            stage.show();
//
//            // Get the Stage object that contains the source node
//            Node source = (Node) event.getSource();
//
//            Stage stage1 = (Stage) source.getScene().getWindow();
//
//            // Close the stage
//            stage1.close();
//
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//    }
    public void backTo(ActionEvent event) {
        menu.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml", "C.E.M.S Test Manager - Login");
//        try {
//            Parent backTo_Root = FXMLLoader.load(getClass().getResource("../LoginWindowScreen/LoginWindow.fxml"));
//            Scene backTo_Scene = new Scene(backTo_Root);
//            Stage backTo_Stage = new Stage();
//            backTo_Stage.setTitle("C.E.M.S Test Manager");
//            backTo_Stage.setScene(backTo_Scene);
//            backTo_Stage.show();
//
//
//            Node source = (Node) e.getSource();
//
//            // Get the Stage object that contains the source node
//            Stage stage = (Stage) source.getScene().getWindow();
//
//            // Close the stage
//            stage.close();
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
    }

    public void manageQuestions(ActionEvent event) {
        menu.goToNewScreen(event, "ManageQuestionsScreen/ManageQuestions.fxml", "C.E.M.S Test Manager - Manage Questions");
        // Set the action to move to ManageQuestions.fxml when ManageQuestionBtn is clicked
//        try {
//            Parent manageQuestions_Root = FXMLLoader.load(getClass().getResource("../ManageQuestionsScreen/ManageQuestions.fxml"));
//            Scene manageQuestions_Scene = new Scene(manageQuestions_Root);
//            Stage manageQuestions_Stage = new Stage();
//            manageQuestions_Stage.getIcons().add(new Image("application/images/Title_icon.png"));
//            manageQuestions_Stage.setTitle("C.E.M.S Test Manager");
//            manageQuestions_Stage.setScene(manageQuestions_Scene);
//            manageQuestions_Stage.show();
//
//            Node source = (Node) e.getSource();
//
//            // Get the Stage object that contains the source node
//            Stage stage = (Stage) source.getScene().getWindow();
//
//            // Close the stage
//            stage.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
    }

    public void ViewStatistics(ActionEvent event) {
        menu.goToNewScreen(event, "ViewStatisticsScreen/ReportScreen/Reports.fxml", "C.E.M.S Test Manager - Reports");
//        try {
//            Parent viewStatistics_Root = FXMLLoader.load(getClass().getResource("../ViewStatisticsScreen/ReportScreen/Reports.fxml"));
//            Scene viewStatistics_Scene = new Scene(viewStatistics_Root);
//            Stage viewStatistics_Stage = new Stage();
//            viewStatistics_Stage.setTitle("C.E.M.S Test Manager");
//            viewStatistics_Stage.setScene(viewStatistics_Scene);
//            viewStatistics_Stage.show();
//
//            Node source = (Node) e.getSource();
//
//            // Get the Stage object that contains the source node
//            Stage stage = (Stage) source.getScene().getWindow();
//
//            // Close the stage
//            stage.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
    }

    public void CreateNewTest(ActionEvent event) {
        menu.goToNewScreen(event, "CreateNewTestScreen/CreateNewTest.fxml", "C.E.M.S Test Manager - Create a new test");
//        try {
//            Parent createNewTest_Root = FXMLLoader.load(getClass().getResource("../CreateNewTestScreen/CreateNewTest.fxml"));
//            Scene createNewTest_Scene = new Scene(createNewTest_Root);
//            Stage createNewTest_Stage = new Stage();
//            createNewTest_Stage.setTitle("C.E.M.S Test Manager");
//            createNewTest_Stage.setScene(createNewTest_Scene);
//            createNewTest_Stage.show();
//
//            Node source = (Node) e.getSource();
//
//            // Get the Stage object that contains the source node
//            Stage stage = (Stage) source.getScene().getWindow();
//
//            // Close the stage
//            stage.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
    }

    public void addQuestion(ActionEvent event) {
        menu.goToNewScreen(event, "AddAQuestionScreen/AddAQuestion.fxml", "C.E.M.S Test Manager - Add a new question");
//        try {
//            Parent addQuestionRoot = FXMLLoader.load(getClass().getResource("../AddAQuestionScreen/AddAQuestion.fxml"));
//            Scene addQuestionScene = new Scene(addQuestionRoot);
//            Stage addQuestionStage = new Stage();
//            addQuestionStage.setTitle("C.E.M.S Test Manager");
//            addQuestionStage.setScene(addQuestionScene);
//            addQuestionStage.show();
//
//            Node source = (Node) e.getSource();
//
//            // Get the Stage object that contains the source node
//            Stage stage = (Stage) source.getScene().getWindow();
//
//            // Close the stage
//            stage.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
    }
}