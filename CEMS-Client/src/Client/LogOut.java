package Client;

import javafx.event.ActionEvent;
import util.MsgHandler;
import util.PathConstants;
import util.ScreenManager;
import util.TypeMsg;

public class LogOut {

    /**
     * goes to the login screen and logs the user out
     *
     * @param event the source event that triggered the method
     */
    public static void logOutToLoginScreen(ActionEvent event) {
        String[] LoginValues = {Client.user.getId(), "0"};
        MsgHandler<String[]> changeLoggedInValue = new MsgHandler<>(TypeMsg.ChangeIsLoggedValue, LoginValues);
        ClientUI.chat.accept(changeLoggedInValue);
        ScreenManager.goToNewScreen(event, PathConstants.loginPath);
    }

    /**
     * disconnects the client from the server
     */
    public static void logOut() {
        MsgHandler<String> disconnectToServer = new MsgHandler<>(TypeMsg.Disconnected, null);
        ClientUI.chat.accept(disconnectToServer);
    }
}
