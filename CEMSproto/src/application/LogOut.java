package application;

import application.Client.ClientUI;
import application.common.MsgHandler;
import application.common.TypeMsg;
import javafx.event.ActionEvent;

public class LogOut {

    public static void logOutToLoginScreen(ActionEvent event) {
//        logOut();
        //todo: add the function of logging out
        ScreenManager.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml");
    }

    public static void logOut() {
        MsgHandler disconnectToServer = new MsgHandler(TypeMsg.Disconnected, null);
        ClientUI.chat.accept((Object) disconnectToServer);
    }
}
