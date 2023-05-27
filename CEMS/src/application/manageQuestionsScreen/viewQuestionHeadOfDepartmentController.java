package application.manageQuestionsScreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.ExitButton;
import util.MinimizeButton;
import util.PathConstants;
import util.ScreenManager;

public class viewQuestionHeadOfDepartmentController {

    @FXML
    private AnchorPane header;

    @FXML
    private TableView<?> manageQuestionsTableView;

    @FXML
    private TextField searchField;

    @FXML
    private Text usernameText;

    @FXML
    public void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.loginPath);
    }

    @FXML
    public void goBackToPreviousScreen(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuHeadOfDepartPath);
    }

    @FXML
    public void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

}
