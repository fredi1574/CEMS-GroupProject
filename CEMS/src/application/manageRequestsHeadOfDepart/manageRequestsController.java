package application.manageRequestsHeadOfDepart;

import client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.ExitButton;
import util.MinimizeButton;
import util.PathConstants;
import util.ScreenManager;

public class manageRequestsController {

    @FXML
    private Text usernameText;

    @FXML
    private AnchorPane header;

    @FXML
    private TableView<?> questionDBTableView;

    public void initialize() {
        ScreenManager.dragAndDrop(header);
        usernameText.setText(Client.user.getFullName());
    }

    @FXML
    void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.loginPath);
    }


    @FXML
    void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);

    }

    @FXML
    public void goBackToPreviousScreen(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuHeadOfDepartPath);
    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

}
