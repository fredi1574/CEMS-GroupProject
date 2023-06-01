package application.viewTestsStudent;

/*public class viewTestStudentController {
}*/
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.ExitButton;
import util.MinimizeButton;
import util.PathConstants;
import util.ScreenManager;

public class viewTestStudentController {

    @FXML
    private AnchorPane header;

    @FXML
    private Text usernameText;

    @FXML
    private TableView<?> reportsTableView;

    @FXML
    private ComboBox<?> subjectComboBox;

    @FXML
    private ComboBox<?> yearComboBox;

    @FXML
    private ComboBox<?> semesterComboBox;

    @FXML
    public void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.loginPath);
    }

    @FXML
    void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    public void goBackToPreviousScreen(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuPath);
    }

    @FXML
    void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

    @FXML
    void showReports(ActionEvent event) {

    }

}

