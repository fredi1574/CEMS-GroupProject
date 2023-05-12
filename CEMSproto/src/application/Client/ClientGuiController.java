package application.Client;

import application.ExitButton;
import application.MinimizeButton;
import application.ScreenManager;
import application.common.MsgHandler;
import application.common.TypeMsg;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ClientGuiController {
    static ClientGuiController mainClient;
    @FXML
    private TextField IPtxt;
    @FXML
    private AnchorPane header;

    public void Close(ActionEvent e) {
        System.exit(0);
    }

    public void logIN(ActionEvent event) {//login button
        String host = IPtxt.getText();
//		 	try {
//				ClientControl client = new ClientControl(host,5555);
//				client.client.openConnection();
//			} catch (IOException e2) {
//				// TODO Auto-generated catch block
//				e2.printStackTrace();
//		}
        MsgHandler connectToServer = new MsgHandler(TypeMsg.Connected, null);
        ClientUI.setChat(host, 5555);
        ClientUI.chat.accept((Object) connectToServer);

        ScreenManager.goToNewScreen(event, "LoginWindowScreen/LoginWindow.fxml");
    }

    public void start() {
        ScreenManager.showStage("Client/ClientGui.fxml", "application/images/icon.png");
    }

    //        public void start(Stage stage) {
//                AnchorPane pane;
//        try {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("ClientGui.fxml"));
//            pane = loader.load();
//            mainClient = loader.getController();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return;
//        }
//        Scene scene = new Scene(pane);
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.setScene(scene);
//        stage.show();
//}
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
