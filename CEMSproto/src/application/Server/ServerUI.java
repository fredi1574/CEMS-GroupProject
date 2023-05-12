package application.Server;

import application.ScreenManager;
import javafx.application.Application;
import javafx.stage.Stage;

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
        ScreenManager.showStage("Server/ServerGui.fxml", "application/images/server.png");
    }
    //public static void runServer(String IP, String port, String username, String password)
}