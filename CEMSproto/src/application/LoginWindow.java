package application;
 
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
 //check
public class LoginWindow extends Application {
 
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CEMS.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Login Window");
            primaryStage.setScene(scene);
            primaryStage.show();
 
            // Get reference to the LoginBtn in CEMS.fxml
            Button loginBtn = (Button) root.lookup("#LoginBtn");
            // Set the action to move to CemsMainMenu.fxml when LoginBtn is clicked
            loginBtn.setOnAction(event -> {
                try {
                    Parent mainMenuRoot = FXMLLoader.load(getClass().getResource("CemsMainMenu.fxml"));
                    Scene mainMenuScene = new Scene(mainMenuRoot);
                    Stage mainMenuStage = new Stage();
                    mainMenuStage.setTitle("CEMS Main Menu");
                    mainMenuStage.setScene(mainMenuScene);
                    mainMenuStage.show();
 
                    // Close the LoginWindow
                    primaryStage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}
