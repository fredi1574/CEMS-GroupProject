package application.Client;

import application.ScreenChanger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Client extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    public void start(Stage primaryStage) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("ClientGui.fxml"));
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