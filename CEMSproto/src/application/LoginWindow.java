package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class LoginWindow extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("LoginWindow.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Login Window");
            primaryStage.setScene(scene);
            primaryStage.show();

            // Get reference to the LoginBtn in LoginWindow.fxml

        } catch(Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        launch(args);
    }
}
