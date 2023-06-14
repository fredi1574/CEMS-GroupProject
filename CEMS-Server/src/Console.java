import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.OutputStream;

/**
 * This class replaces the output stream into a TextArea within the GUI in the FXML file
 */
public class Console extends OutputStream {
    private final TextArea console;

    //We pass the TextArea variable from the F
    public Console(TextArea console) {
        this.console = console;
    }

    public void appendText(String valueOf) {
        Platform.runLater(() -> console.appendText(valueOf));
    }

    @Override
    public void write(int b) {
        appendText(String.valueOf((char) b));
    }
}
