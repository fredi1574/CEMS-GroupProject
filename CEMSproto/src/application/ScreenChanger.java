package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ScreenChanger {

    public static void goToNewScreen(ActionEvent event, String screenRelativePath) {
        try {
            FXMLLoader loader = new FXMLLoader(ScreenChanger.class.getResource(screenRelativePath));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.initStyle(StageStyle.TRANSPARENT);
            stage.getIcons().add(new Image("application/images/icon.png"));

            stage.setScene(scene);
            stage.show();

            // Get the Stage object that contains the source node
            Node source = (Node) event.getSource();

            Stage previousWindow = (Stage) source.getScene().getWindow();

            // Close the stage
            previousWindow.close();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
