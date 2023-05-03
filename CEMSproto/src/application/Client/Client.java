package application.Client;

import application.ScreenManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Client extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    public void start(Stage primaryStage) {

        try {
            FXMLLoader loader = new FXMLLoader(ScreenManager.class.getResource("Client/ClientGui.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            primaryStage.getIcons().add(new Image("application/images/icon.png"));
            primaryStage.initStyle(StageStyle.TRANSPARENT);

            primaryStage.setScene(scene);
            primaryStage.show();

            // Get reference to the LoginBtn in LoginWindow.fxml

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}