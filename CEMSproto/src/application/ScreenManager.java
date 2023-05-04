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

    private static final double SCREEN_OFFSET_X = 100;
    private static final double SCREEN_OFFSET_Y = -100;

    public static void goToNewScreen(ActionEvent event, String screenRelativePath, boolean popUp) {
        try {
            FXMLLoader loader = new FXMLLoader(ScreenManager.class.getResource(screenRelativePath));
            Parent root = loader.load();

            // Get the Stage object that contains the source node
            Node source = (Node) event.getSource();
            Stage previousWindow = (Stage) source.getScene().getWindow();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.getIcons().add(new Image("application/images/icon.png"));

            stage.setScene(scene);
            stage.show();

            if (popUp) {
                stage.setX(previousWindow.getX() + SCREEN_OFFSET_X);
                stage.setY(previousWindow.getY() + SCREEN_OFFSET_Y);
            }
            if (!popUp) {
                // Close the previous Window
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