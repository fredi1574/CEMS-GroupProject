package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class TestPreviewPopUpController {
	
	public void LogOut(ActionEvent e) {
		try {
			
            Parent CMEMS_Root = FXMLLoader.load(getClass().getResource("CEMS.fxml"));
            Scene CMEMS_Scene = new Scene(CMEMS_Root);
            Stage  CMEMS_Stage = new Stage();
            CMEMS_Stage.setTitle("Login Window");
            CMEMS_Stage.setScene(CMEMS_Scene);
            CMEMS_Stage.show();

            Node source = (Node) e.getSource();
            
            // Get the Stage object that contains the source node
            Stage stage = (Stage) source.getScene().getWindow();
            
            // Close the stage
            stage.close();
           
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
}
