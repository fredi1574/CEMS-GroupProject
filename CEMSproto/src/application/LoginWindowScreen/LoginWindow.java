package application.LoginWindowScreen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginWindow extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("LoginWindow.fxml"));
            Scene scene = new Scene(root);
            primaryStage.getIcons().add(new Image("application/images/icons8-title-icon.png"));
            primaryStage.setTitle("C.E.M.S - Tests Manager");
            primaryStage.setScene(scene);
            primaryStage.show();

            // Get reference to the LoginBtn in LoginWindow.fxml

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
