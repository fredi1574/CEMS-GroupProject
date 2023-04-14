package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CemsMainMenuController   {
	
	
	public void backTo(ActionEvent e) {
		 try {
            Parent log_Root = FXMLLoader.load(getClass().getResource("CEMS.fxml"));
            Scene log_Root_Scene = new Scene(log_Root);
            Stage  log_Root_Stage = new Stage();
            log_Root_Stage .setTitle("Login Window");
            log_Root_Stage.setScene(log_Root_Scene);
            log_Root_Stage.show();

            Node source = (Node) e.getSource();
            
            // Get the Stage object that contains the source node
            Stage stage = (Stage) source.getScene().getWindow();
            
            // Close the stage
            stage.close();
           
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
    public void showManage(ActionEvent e) {
    
            // Set the action to move to QuestionManagement.fxml when ManageQuestionBtn is clicked
           
                try {
                    Parent mainMenuRoot = FXMLLoader.load(getClass().getResource("QuestionManagement.fxml"));
                    Scene mainMenuScene = new Scene(mainMenuRoot);
                    Stage mainMenuStage = new Stage();
                    mainMenuStage.setTitle("Question Management");
                    mainMenuStage.setScene(mainMenuScene);
                    mainMenuStage.show();
                  
                    Node source = (Node) e.getSource();
                    
                    // Get the Stage object that contains the source node
                    Stage stage = (Stage) source.getScene().getWindow();
                    
                    // Close the stage
                    stage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
           
        
    }
    public void ViewStatistics(ActionEvent e) {
		 

        try {
            Parent StatRoot = FXMLLoader.load(getClass().getResource("Statistics.fxml"));
            Scene statScene = new Scene(StatRoot);
            Stage statStage = new Stage();
            statStage.setTitle("View Statistics");
            statStage.setScene(statScene);
            statStage.show();
          
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