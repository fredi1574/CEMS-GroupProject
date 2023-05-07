package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;

public class ScreenManager {

    private static final double SCREEN_OFFSET_X = 100;
    private static final double SCREEN_OFFSET_Y = -50;

    public static void goToNewScreen(ActionEvent event, String screenRelativePath) {
        showStage(screenRelativePath);

        // Get the Stage object that contains the source node
        Node source = (Node) event.getSource();
        Stage previousWindow = (Stage) source.getScene().getWindow();

        previousWindow.close();
    }

    public static void popUpScreen(String screenRelativePath) {
        Stage stage = showStage(screenRelativePath);
        moveStage(stage);
    }

    public static Stage showStage(String screenRelativePath) {
        FXMLLoader loader = new FXMLLoader(ScreenManager.class.getResource(screenRelativePath));
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.getIcons().add(new Image("application/images/icon.png"));

        stage.setScene(scene);
        stage.show();
        return stage;
    }

    public static void moveStage(Stage stage) {
        // Get the current window
        Window currentWindow = stage.getScene().getWindow();
        currentWindow.setX(currentWindow.getX() + SCREEN_OFFSET_X);
        currentWindow.setY(currentWindow.getY() + SCREEN_OFFSET_Y);
    }

    public static void dragAndDrop(Node node) {
        double[] offset ={0,0}; //offset[0] - X axis, offset[1] - Y axis

        // Set the mouse pressed event for the node to enable drag and drop
        node.setOnMousePressed(event -> {
            offset[0] = event.getSceneX();
            offset[1] = event.getSceneY();
        });

        // Set the mouse dragged event for the node to enable drag and drop
        node.setOnMouseDragged(event -> {
            node.getScene().getWindow().setX(event.getScreenX() - offset[0]);
            node.getScene().getWindow().setY(event.getScreenY() - offset[1]);
        });
    }
}