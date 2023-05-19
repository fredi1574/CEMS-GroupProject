package util;

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