package application.createNewTestScreen.pickQuestionsScreen.questionPreviewPopup;
import Client.ExitButton;
import Client.LogOut;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import util.*;

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
       LogOut.logOutToLoginScreen(event);
    }

    public void closePopUp(ActionEvent event) {
        ExitButton.closePopUp(event);
    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

}
