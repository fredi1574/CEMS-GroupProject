package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StatisticsController {
	public void backStat(ActionEvent e) {
		 try {
            Parent static_Root = FXMLLoader.load(getClass().getResource("CemsMainMenu.fxml"));
            Scene static_Scene = new Scene(static_Root);
            Stage static_Root_Stage = new Stage();
            static_Root_Stage .setTitle("CMES Main Menu");
            static_Root_Stage.setScene(static_Scene);
            static_Root_Stage.show();

            Node source = (Node) e.getSource();
            
            // Get the Stage object that contains the source node
            Stage stage = (Stage) source.getScene().getWindow();
            
            // Close the stage
            stage.close();
           
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
    public void LogOut(ActionEvent e) {
        try {

            Parent logOut_Root = FXMLLoader.load(getClass().getResource("LoginWindow.fxml"));
            Scene logOut_Scene = new Scene(logOut_Root);
            Stage logOut_Stage = new Stage();
            logOut_Stage.setTitle("Login Window");
            logOut_Stage.setScene(logOut_Scene);
            logOut_Stage.show();

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
