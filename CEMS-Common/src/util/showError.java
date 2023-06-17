package util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
/**
 * This utility class provides methods for displaying various types of pop-up messages using JavaFX Alert dialogs.
 */
public class showError {

    /**
     * Displays an error pop-up dialog with the specified message.
     *
     * @param message the error message to display
     */
    public static void showErrorPopup(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    /**
     * Displays an information pop-up dialog with the specified message.
     *
     * @param message the information message to display
     */

    public static void showInfoPopup(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Displays a confirmation pop-up dialog with the specified message.
     * The dialog includes "Yes" and "No" buttons, and the method returns true if the "Yes" button is clicked.
     *
     * @param message the confirmation message to display
     * @return true if the "Yes" button is clicked, false otherwise
     */

    public static boolean showConfirmationPopup(String message) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(message);

        ButtonType buttonYes = new ButtonType("Yes");
        ButtonType buttonNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        return alert.showAndWait().orElse(ButtonType.NO) == buttonYes;
    }

}
