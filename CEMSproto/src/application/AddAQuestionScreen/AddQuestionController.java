
package application.AddAQuestionScreen;

import application.MenuController;
import javafx.event.ActionEvent;

public class AddQuestionController {

    MenuController menu = new MenuController();

    public void BackToMenu(ActionEvent event) {
        menu.goToNewScreen(event, "MainMenuScreen/MainMenu.fxml", "C.E.M.S Test Manager");
//        try {
//            Parent backToMenu_Root = FXMLLoader.load(getClass().getResource("../MainMenuScreen/MainMenu.fxml"));
//            Scene backToMenu_Scene = new Scene(backToMenu_Root);
//            Stage backToMenu_Stage = new Stage();
//            backToMenu_Stage.setTitle("C.E.M.S Test Manager");
//            backToMenu_Stage.setScene(backToMenu_Scene);
//            backToMenu_Stage.show();
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

    public void pickQuestion(ActionEvent event) {
        menu.goToNewScreen(event, "PickQuestions.fxml", "C.E.M.S Test Manager - Pick Questions");
//        try {
//            Parent pickQuestion_Root = FXMLLoader.load(getClass().getResource("../PickQuestions.fxml"));
//            Scene pickQuestion_Scene = new Scene(pickQuestion_Root);
//            Stage pickQuestion_Stage = new Stage();
//            pickQuestion_Stage.setTitle("Pick Questions");
//            pickQuestion_Stage.setScene(pickQuestion_Scene);
//            pickQuestion_Stage.show();
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

    public void LogOut(ActionEvent event) {
        menu.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml", "C.E.M.S Test Manager");
//        try {
//            Parent logOut_Root = FXMLLoader.load(getClass().getResource("../LoginWindowScreen/LoginWindow.fxml"));
//            Scene logOut_Scene = new Scene(logOut_Root);
//            Stage logOut_Stage = new Stage();
//            logOut_Stage.setTitle("C.E.M.S Test Manager");
//            logOut_Stage.setScene(logOut_Scene);
//            logOut_Stage.show();
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
}
