package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ScreenManager {


    public static void goToNewScreen(ActionEvent event, String screenRelativePath, boolean popUp) {
        try {
            FXMLLoader loader = new FXMLLoader(ScreenManager.class.getResource(screenRelativePath));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.initStyle(StageStyle.TRANSPARENT);
            stage.getIcons().add(new Image("application/images/icon.png"));

            stage.setScene(scene);
            stage.show();


            // Close the previous Window
            if (!popUp) {
                // Get the Stage object that contains the source node
                Node source = (Node) event.getSource();

                Stage previousWindow = (Stage) source.getScene().getWindow();
                previousWindow.close();
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void dragAndDrop(Node node) {
        //todo: change that method to not use arrays of one element
        final double[] xOffset = {0};
        final double[] yOffset = {0};

        // Set the mouse pressed event for the node to enable drag and drop
        node.setOnMousePressed(event -> {
            xOffset[0] = event.getSceneX();
            yOffset[0] = event.getSceneY();
        });

        // Set the mouse dragged event for the node to enable drag and drop
        node.setOnMouseDragged(event -> {
            node.getScene().getWindow().setX(event.getScreenX() - xOffset[0]);
            node.getScene().getWindow().setY(event.getScreenY() - yOffset[0]);
        });
    }
}