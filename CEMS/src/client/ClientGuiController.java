package client;

import common.MsgHandler;
import common.TypeMsg;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import util.ExitButton;
import util.MinimizeButton;
import util.PathConstants;
import util.ScreenManager;

public class ClientGuiController {
    static ClientGuiController mainClient;
    @FXML
    private TextField IPtxt;
    @FXML
    private AnchorPane header;

    public void Close(ActionEvent e) {
        System.exit(0);
    }

    public void logIn(ActionEvent event) {//login button
        String host = IPtxt.getText();
//		 	try {
//				ClientControl client = new ClientControl(host,5555);
//				client.client.openConnection();
//			} catch (IOException e2) {
//				e2.printStackTrace();
//		}
        MsgHandler connectToServer = new MsgHandler(TypeMsg.Connected, null);
        ClientUI.setChat(host, 5555);
        ClientUI.chat.accept((Object) connectToServer);

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
