package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StatisticsController {
    public void backToReport(ActionEvent e) {
        try {
            Parent backToReport_Root = FXMLLoader.load(getClass().getResource("Reports.fxml"));
            Scene backToReport_Scene = new Scene(backToReport_Root);
            Stage backToReport_Stage = new Stage();
            backToReport_Stage.setTitle("C.E.M.S Test Manager");
            backToReport_Stage.setScene(backToReport_Scene);
            backToReport_Stage.show();

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
            logOut_Stage.setTitle("C.E.M.S Test Manager");
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
