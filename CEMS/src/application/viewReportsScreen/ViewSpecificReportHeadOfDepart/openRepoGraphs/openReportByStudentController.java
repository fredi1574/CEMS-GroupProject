package application.viewReportsScreen.ViewSpecificReportHeadOfDepart.openRepoGraphs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.AnchorPane;
import util.ExitButton;
import util.MinimizeButton;
import util.ScreenManager;

public class openReportByStudentController {

    @FXML
    private AnchorPane header;

    @FXML
    private BarChart<?, ?> reportGraph;

    public void initialize() {
        ScreenManager.dragAndDrop(header);
    }
    @FXML
    void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

}
