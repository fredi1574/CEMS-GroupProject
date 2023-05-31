package application.manageRequestsHeadOfDepart;

import client.Client;
import client.ClientUI;
import common.MsgHandler;
import common.TypeMsg;
import entity.Question;
import entity.TestRequestForApproval;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.*;

public class viewRequestController {

    @FXML
    private Button approveBtn;
    @FXML
    private Button declineBtn;
    @FXML
    private TextField CourseText;

    @FXML
    private TextField LecturerText;

    @FXML
    private TextArea RequestText;

    @FXML
    private TextField TimeExtentionDurationText;

    @FXML
    private AnchorPane header;

    @FXML
    private TextField testIDText;

    private TestRequestForApproval request;
    private Stage manageRequests;


    @FXML
    void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);

    }
    public void initialize() {
        ScreenManager.dragAndDrop(header);
    }
    public void setManage(Stage manageRequests) {
        this.manageRequests = manageRequests;
    }
    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
    public void setRequest(TestRequestForApproval request) {
        this.request = request;
        CourseText.setText(request.getCourse());
        LecturerText.setText(request.getAuthor());
        RequestText.setText(request.getExplanation());
        TimeExtentionDurationText.setText(request.getExplanation());
       testIDText.setText(request.getId());
    }
    @FXML
    public void approveRequest(ActionEvent event) {
        if (showError.showConfirmationPopup("Are you sure you want to approve this request?")) {
            MsgHandler approveRequest = new MsgHandler(TypeMsg.ApproveRequestByHeadOfDepartment, (String) testIDText.getText());
            ClientUI.chat.accept(approveRequest);
            reloadPage(approveBtn);
            //TODO: return to the lecturer with the approval
        }


    }
    @FXML
    public void declineRequest(ActionEvent event) {
        if (showError.showConfirmationPopup("Are you sure you want to decline this request?")) {
            MsgHandler declineRequest = new MsgHandler(TypeMsg.ApproveRequestByHeadOfDepartment, (String) testIDText.getText());
            ClientUI.chat.accept(declineRequest);
            //TODO: return to the lecturer with the decline
            reloadPage(declineBtn);
        }

    }
    private void reloadPage(Button button) {
        Stage currentStage = (Stage) button.getScene().getWindow();
        currentStage.close();
        manageRequests.close();
        ScreenManager.showStage(PathConstants.EnterManageRequestsHeadOfDepart, PathConstants.iconPath);
    }
}
