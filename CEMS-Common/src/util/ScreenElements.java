package util;

/**
 * a class that saves elements of the javafx window
 * for use in ScreenManager and TableManager related functions
 *
 * @param <Stage>      the window's Stage object (the top-level JAVAFX container)
 * @param <FXMLLoader> the window's FXMLLoader object (a class that loads an object hierarchy from an XML document)
 */
public class ScreenElements<Stage, FXMLLoader> {
    private Stage stage;
    private FXMLLoader fxmlLoader;

    public ScreenElements(Stage stage, FXMLLoader fxmlLoader) {
        this.stage = stage;
        this.fxmlLoader = fxmlLoader;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setfxmlLoader(FXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }

    public FXMLLoader getFXMLLoader() {
        return fxmlLoader;
    }
}