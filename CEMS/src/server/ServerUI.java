package server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.ScreenManager;

import java.io.IOException;

public class ServerUI extends Application {
    static CemsServer Cems;
    static ServerController controller;

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

    public static void runServer(String IP, String port, String DBName, String username, String password) {
        int Port = 0; // Port to listen on

        try {
            Port = Integer.parseInt(port); // Set port to 5555

        } catch (Throwable t) {
            System.out.println("ERROR - Could not connect!");
            return;
        }
        Cems = new CemsServer(Port, password);
        try {
            Cems.listen(); // Start listening for connections

        } catch (IOException e) {
            System.out.println("IO exception");
        }
    }

    @Override
    public void start(Stage primaryStage) {
//        ScreenManager.showStage("server/ServerGUI.fxml", "application/images/server.png");

        FXMLLoader loader = new FXMLLoader(ScreenManager.class.getResource("/server/ServerGUI.fxml"));
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.getIcons().add(new Image("application/images/server.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}