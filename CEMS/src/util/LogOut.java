package util;

import client.ClientUI;
import common.MsgHandler;
import common.TypeMsg;
import javafx.event.ActionEvent;

public class LogOut {

    /**
     * goes to the login screen and logs the user out
     * @param event   the source event that triggered the method
     */
    public static void logOutToLoginScreen(ActionEvent event) {
//        logOut();
        //todo: add the function of logging out
        ScreenManager.goToNewScreen(event, "/application/loginWindowScreen/LoginWindow.fxml");
    }

    /**
     * disconnects the client from the server
     */
    public static void logOut() {
        common.MsgHandler disconnectToServer = new MsgHandler(TypeMsg.Disconnected, null);
        ClientUI.chat.accept((Object) disconnectToServer);
    }
}
