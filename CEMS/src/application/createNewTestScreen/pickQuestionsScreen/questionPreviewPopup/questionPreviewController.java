package application.createNewTestScreen.pickQuestionsScreen.questionPreviewPopup;

import entity.LoggedInUser;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.ExitButton;
import util.MinimizeButton;
import util.PathConstants;
import util.ScreenManager;

public class questionPreviewController {

    @FXML
    private AnchorPane header;
//    @FXML
//    private Text usernameText;
    public void initialize() {
        ScreenManager.dragAndDrop(header);
//        User authenticatedUser = LoggedInUser.getAuthenticatedUser();
//        if (authenticatedUser != null) {
//            // Set the text in the usernameText element
//            usernameText.setText(authenticatedUser.getUserName());
//        }
    }

    public void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.loginPath);
    }

    public void closePopUp(ActionEvent event) {
        ExitButton.closePopUp(event);
    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

}
