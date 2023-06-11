package util;

import client.Client;
import client.ClientUI;
import common.MsgHandler;
import common.TypeMsg;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.List;

public class LogOut {

    /**
     * goes to the login screen and logs the user out
     *
     * @param event the source event that triggered the method
     */
    public static void logOutToLoginScreen(ActionEvent event) {
//        logOut();
        //todo: add the function of logging out
        String[] LoginValues = {Client.user.getId(),"0"};
        MsgHandler changeLoggedInValue = new MsgHandler(TypeMsg.ChangeIsLoggedValue, LoginValues);
        ClientUI.chat.accept(changeLoggedInValue);
        ScreenManager.goToNewScreen(event, PathConstants.loginPath);


    }

    /**
     * disconnects the client from the server
     */
    public static void logOut() {
        common.MsgHandler disconnectToServer = new MsgHandler(TypeMsg.Disconnected, null);
        ClientUI.chat.accept((Object) disconnectToServer);
    }
}
