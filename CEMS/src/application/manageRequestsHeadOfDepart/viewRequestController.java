package application.manageRequestsHeadOfDepart;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import util.ExitButton;
import util.MinimizeButton;
import util.PathConstants;
import util.ScreenManager;

public class viewRequestController {


    @FXML
    private AnchorPane header;


    @FXML
    void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);

    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

}
