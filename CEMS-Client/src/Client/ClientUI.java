package Client;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientUI extends Application {
	private static Stage stage;
	public static ClientControl chat; //only one instance

	public static void main(String[] args) {
		    launch(args);  
	   } 
	 
	@Override
	public void start(Stage stage) {
		setStage(stage);
		ClientGuiController aFrame = new ClientGuiController();
		aFrame.start();
	}
	
	public static Stage getStage() {
		return stage;
	}

	public static void setChat(String gettxtfield, int i) {
        chat = new ClientControl(gettxtfield,i);

    }
	@SuppressWarnings("static-access")
	private void setStage(Stage stage) {
		this.stage = stage;
	}
    
}