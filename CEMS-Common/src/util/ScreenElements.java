package util;

/**
 * This class represents the elements of a JavaFX window used in the ScreenManager and TableManager related functions.
 *
 * @param <Stage>      the window's Stage object (the top-level JavaFX container)
 * @param <FXMLLoader> the window's FXMLLoader object (a class that loads an object hierarchy from an XML document)
 */
public class ScreenElements<Stage, FXMLLoader> {
    private final Stage stage;
    private final FXMLLoader fxmlLoader;

    /**
     * Constructs a ScreenElements object with the specified Stage and FXMLLoader.
     *
     * @param stage      the Stage object representing the window
     * @param fxmlLoader the FXMLLoader object used to load the window's object hierarchy
     */
    public ScreenElements(Stage stage, FXMLLoader fxmlLoader) {
        this.stage = stage;
        this.fxmlLoader = fxmlLoader;
    }

    /**
     * Returns the Stage object representing the window.
     *
     * @return the Stage object
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Returns the FXMLLoader object used to load the window's object hierarchy.
     *
     * @return the FXMLLoader object
     */
    public FXMLLoader getFXMLLoader() {
        return fxmlLoader;
    }
}
