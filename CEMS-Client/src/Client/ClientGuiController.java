package Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import util.*;

public class ClientGuiController {
    @FXML
    private TextField IPText;
    @FXML
    private AnchorPane header;

    /**
     * Closes the client application.
     */
    public void Close() {
        System.exit(0);
    }

    /**
     * Handles the login button action.
     * Connects to the server using the provided IP address,
     * and navigates to the login screen.
     *
     * @param event The ActionEvent triggered by the login button.
     */
    public void logIn(ActionEvent event) {
        String host = IPText.getText();

        @SuppressWarnings("unchecked")
        MsgHandler connectToServer = new MsgHandler(TypeMsg.Connected, null);
        ClientUI.setChat(host, 5555);
        ClientUI.chat.accept(connectToServer);

        ScreenManager.goToNewScreen(event, PathConstants.loginPath);
    }

    /**
     * Initializes the client GUI.
     * Displays the client window and sets up the drag-and-drop functionality.
     */
    public void start() {
        ScreenManager.showStage(PathConstants.clientPath, PathConstants.iconPath);
    }

    /**
     * Initializes the controller.
     * Sets up the drag-and-drop functionality for the header pane.
     */
    public void initialize() {
        ScreenManager.dragAndDrop(header);
    }

    /**
     * Closes the client application by displaying a confirmation popup.
     *
     * @param event The ActionEvent triggered by the close client button.
     */
    public void closeClient(ActionEvent event) {
        ExitButton.closePopUp(event);
    }

    /**
     * Minimizes the client window to the system tray.
     *
     * @param event The ActionEvent triggered by the minimize window button.
     */
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
