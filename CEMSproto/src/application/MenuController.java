package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MenuController {

    public void goToNewScreen(ActionEvent event, String screenRelativePath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(screenRelativePath));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.getIcons().add(new Image("application/images/Title_icon.png"));

            stage.setScene(scene);
            stage.show();

            // Get the Stage object that contains the source node
            Node source = (Node) event.getSource();

            Stage stage1 = (Stage) source.getScene().getWindow();

            // Close the stage
            stage1.close();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
