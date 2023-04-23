package application.Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class ClientGuiController {
	public void Close(ActionEvent e) {
		System.exit(0);
	}
	 public void logIN(ActionEvent e) {

	        try {
	            Parent login_Root = FXMLLoader.load(getClass().getResource("../LoginWindowScreen/LoginWindow.fxml"));
	            Scene login_Scene = new Scene(login_Root);
	            Stage login_Stage = new Stage();
	            login_Stage.setTitle("C.E.M.S Test Manager");

	            login_Stage.setScene(login_Scene);
	            login_Stage.show();

	            Node source = (Node) e.getSource();

	            // Get the Stage object that contains the source node
	            Stage stage = (Stage) source.getScene().getWindow();

	            // Close the stage
	            stage.close();

	            // Close the LoginWindow

	        } catch (Exception e1) {
	            e1.printStackTrace();
	        }
	    }
}
