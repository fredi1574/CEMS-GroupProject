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

	/**
	 * Sets up the JavaFX stage and initializes the client GUI.
	 *
	 * @param stage The primary stage of the JavaFX application.
	 */
	@Override
	public void start(Stage stage) {
		setStage(stage);
		ClientGuiController aFrame = new ClientGuiController();
		aFrame.start();
	}

	/**
	 * Retrieves the JavaFX stage.
	 *
	 * @return The JavaFX stage.
	 */
	public static Stage getStage() {
		return stage;
	}

	/**
	 * Sets up the client control instance for communication with the server.
	 *
	 * @param gettxtfield The IP address of the server.
	 * @param i           The port number to connect to.
	 */
	public static void setChat(String gettxtfield, int i) throws IOException {
		chat = new ClientControl(gettxtfield, i);
	}

	@SuppressWarnings("static-access")
	private void setStage(Stage stage) {
		this.stage = stage;
	}
}
