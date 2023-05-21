package util;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class showError {
    public static void showErrorPopup(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
