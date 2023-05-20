package server;

import javafx.application.Application;
import javafx.stage.Stage;
import util.ScreenManager;

import java.io.IOException;

public class ServerGUI extends Application {
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
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Server has stopped listening for connections.");
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
        ScreenManager.showStage("/server/ServerGUI.fxml", "images/server.png");
    }
}