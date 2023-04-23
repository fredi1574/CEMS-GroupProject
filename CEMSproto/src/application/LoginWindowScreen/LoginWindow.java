package application.LoginWindowScreen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
//import application.MenuController;

public class LoginWindow extends Application {
//    MenuController menu = new MenuController();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginWindow.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.getIcons().add(new Image("application/images/Title_icon.png"));
            primaryStage.setTitle("C.E.M.S - Tests Manager");

            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
