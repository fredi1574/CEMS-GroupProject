import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.OutputStream;

/**
 * This class replaces the output stream into a TextArea within the GUI in the FXML file.
 * It allows redirecting the output to the TextArea for displaying messages.
 */
public class Console extends OutputStream {
    private final TextArea console;

    /**
     * Constructs a Console object.
     *
     * @param console the TextArea to which the output will be redirected.
     */
    public Console(TextArea console) {
        this.console = console;
    }

    /**
     * Appends the specified text to the TextArea in a thread-safe manner.
     *
     * @param text the text to be appended.
     */
    public void appendText(String text) {
        Platform.runLater(() -> console.appendText(text));
    }

    /**
     * Writes a byte to the output stream. This method is called by the system.
     *
     * @param b the byte to be written.
     */
    @Override
    public void write(int b) {
        appendText(String.valueOf((char) b));
    }
}
