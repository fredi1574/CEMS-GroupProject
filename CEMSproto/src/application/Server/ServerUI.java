package application.Server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ServerUI extends Application {
    static CemsServer Cems;
    static ServerGuiController controller;

    public static void main(String[] args) {
        launch(args);
    }

    public static void disconnect() {
        if (Cems == null)
            System.out.println("Server Already Stopped");
        else {
            try {
                Cems.serverStopped();
                System.out.println("Server Disconnected");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Server Disconnected");
        }
    }

    public static void runServer(String port) {
        int Port; // Port to listen on

        try {
            Port = Integer.parseInt(port); // Set port to 5555

        } catch (Throwable t) {
            System.out.println("ERROR - Could not connect!");
            return;
        }
        System.out.println("Server Started");
        Cems = new CemsServer(Port);
        try {
            Cems.listen(); // Start listening for connections
        } catch (IOException e) {
            System.out.println("IO exception");
        }
    }

    @Override
    public void start(Stage primaryStage) {
        AnchorPane pane;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ServerGui.fxml"));
            pane = loader.load();
            controller = loader.getController();

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(pane);
        primaryStage.getIcons().add(new Image("application/images/icons8-server-96.png"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    //public static void runServer(String IP, String port, String username, String password)
}