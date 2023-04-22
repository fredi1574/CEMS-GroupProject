package application.LoginWindowScreen;

import application.MenuController;
import javafx.event.ActionEvent;

public class LoginController {

    MenuController menu = new MenuController();

    public void logIN(ActionEvent event) {
        menu.goToNewScreen(event, "MainMenuScreen/MainMenu.fxml", "C.E.M.S Test Manager");
//        try {
//            Parent login_Root = FXMLLoader.load(getClass().getResource("MainMenuScreen/MainMenu.fxml"));
//            Scene login_Scene = new Scene(login_Root);
//            Stage login_Stage = new Stage();
//            login_Stage.setTitle("C.E.M.S Test Manager");
//            login_Stage.getIcons().add(new Image("application/images/Title_icon.png"));
//
//            login_Stage.setScene(login_Scene);
//            login_Stage.show();
//
//            Node source = (Node) e.getSource();
//
//            // Get the Stage object that contains the source node
//            Stage stage = (Stage) source.getScene().getWindow();
//
//            // Close the stage
//            stage.close();
//
//            // Close the LoginWindow
//
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }
    }
}
