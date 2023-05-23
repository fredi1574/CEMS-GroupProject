package util;

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
import application.loginWindowScreen.LoginWindowController;
/**
 * class containing functions relevant to screen interactions
 * and transitions
 */
public class ScreenManager {

    private static final double SCREEN_OFFSET_X = 100;
    private static final double SCREEN_OFFSET_Y = -50;

    /**
     * closes the screen from which the method was called
     * and shows a new screen using the showStage method
     *
     * @param event      the source event that triggered the method
     * @param screenPath the path of the new screen
     */
    public static void goToNewScreen(ActionEvent event, String screenPath) {
        showStage(screenPath, PathConstants.iconPath);

        // Get the Stage object that contains the source node
        Node source = (Node) event.getSource();
        Stage previousWindow = (Stage) source.getScene().getWindow();

        previousWindow.close();
    }

    /**
     * opens a new window as a popup on top of the current window
     *
     * @param screenPath the path of the new popup
     * @return the Stage and FXMLLoader objects of the current window
     */
    public static ScreenElements<Stage, FXMLLoader> popUpScreen(String screenPath) {
        ScreenElements<Stage, FXMLLoader> screenElement = showStage(screenPath, PathConstants.iconPath);

        moveStage(screenElement.getStage());
        return screenElement;
    }

    /**
     * displays a new screen
     *
     * @param screenPath the path of the new screen
     * @param iconPath   the path of the program icon
     * @return the Stage and FXMLLoader objects of the current window
     */
    public static ScreenElements<Stage, FXMLLoader> showStage(String screenPath, String iconPath) {
        FXMLLoader loader = new FXMLLoader(ScreenManager.class.getResource(screenPath));
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.initStyle(StageStyle.TRANSPARENT);

        stage.getIcons().add(new Image(iconPath));
        stage.setScene(scene);
        stage.show();

        return new ScreenElements<>(stage, loader);
    }

    /**
     * offsets the window using constant horizontal and vertical values SCREEN_OFFSET_X and SCREEN OFFSET_Y
     *
     * @param stage the stage that gets moved
     */
    public static void moveStage(Stage stage) {
        // Get the current window
        Window currentWindow = stage.getScene().getWindow();

        // Set the current window position according to the previous window
        currentWindow.setX(currentWindow.getX() + SCREEN_OFFSET_X);
        currentWindow.setY(currentWindow.getY() + SCREEN_OFFSET_Y);
    }

    /**
     * adds drag and drop functionality to a window using a node belonging to it
     *
     * @param node the window's relevant node (usually the header element)
     */
    public static void dragAndDrop(Node node) {
        double[] offset = {0, 0}; //offset[0] - X axis, offset[1] - Y axis

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

