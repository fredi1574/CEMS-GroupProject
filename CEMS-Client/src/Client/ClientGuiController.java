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

    public void Close() {
        System.exit(0);
    }

    public void logIn(ActionEvent event) {//login button
        String host = IPText.getText();

        @SuppressWarnings("unchecked")
        MsgHandler connectToServer = new MsgHandler(TypeMsg.Connected, null);
        ClientUI.setChat(host, 5555);
        ClientUI.chat.accept(connectToServer);

        ScreenManager.goToNewScreen(event, PathConstants.loginPath);
    }

    public void start() {
        ScreenManager.showStage(PathConstants.clientPath, PathConstants.iconPath);
    }

    public void initialize() {
        ScreenManager.dragAndDrop(header);
    }

    public void closeClient(ActionEvent event) {
        ExitButton.closePopUp(event);
    }

    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
