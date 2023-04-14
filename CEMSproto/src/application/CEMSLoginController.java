package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class CEMSLoginController {
    public void logIN(ActionEvent e) {

        try {
            Parent mainMenuRoot = FXMLLoader.load(getClass().getResource("CemsMainMenu.fxml"));
            Scene mainMenuScene = new Scene(mainMenuRoot);
            Stage mainMenuStage = new Stage();
            // mainMenuStage.setTitle("CEMS Main Menu");
            mainMenuStage.setScene(mainMenuScene);
            mainMenuStage.show();

            Node source = (Node) e.getSource();

            // Get the Stage object that contains the source node
            Stage stage = (Stage) source.getScene().getWindow();

            // Close the stage
            stage.close();

            // Close the LoginWindow

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
