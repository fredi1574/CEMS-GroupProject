package application.Client;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ClientUI extends Application {
	private static Stage stage;
	public static ClientControl chat; //only one instance

	public static void main( String args[] ) throws Exception
	   { 
		    launch(args);  
	   } 
	 
	@Override
	public void start(Stage stage) throws Exception {
		setStage(stage);
		ClientGuiController aFrame = new ClientGuiController();
		aFrame.start();
	}
	
	public static Stage getStage() {
		return stage;
	}

	public static void setChat(String gettxtfield, int i) {
		try {
			chat = new ClientControl(gettxtfield,i);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings("static-access")
	private void setStage(Stage stage) {
		this.stage = stage;
	}
    
}