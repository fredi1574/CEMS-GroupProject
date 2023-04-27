package application.Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Client extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    public void start(Stage primaryStage) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("ClientGui.fxml"));
            Scene scene = new Scene(root);
            primaryStage.getIcons().add(new Image("application/images/icon3.png"));

            primaryStage.setTitle("C.E.M.S - IP Connection");
            primaryStage.setScene(scene);
            primaryStage.show();

            // Get reference to the LoginBtn in LoginWindow.fxml

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}