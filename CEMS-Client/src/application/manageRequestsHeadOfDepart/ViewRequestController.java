package application.manageRequestsHeadOfDepart;

import Client.ClientUI;
import Client.ExitButton;
import entity.TestRequestForApproval;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.*;

import java.util.ArrayList;

public class ViewRequestController {

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
        ExitButton.closePopUp(event);

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
        TimeExtentionDurationText.setText(request.getNewDuration());
        testIDText.setText(request.getId());
    }

    public void deleteRequest(String request) {
        MsgHandler delete = new MsgHandler(TypeMsg.DeleteRequest, request);
        ClientUI.chat.accept(delete);

    }

    @FXML
    public void approveRequest() {
        String AddedTime = TimeExtentionDurationText.getText();

        ArrayList<String> ChangeTestDurationArr = new ArrayList<>();

        ChangeTestDurationArr.add(testIDText.getText());
        ChangeTestDurationArr.add(AddedTime);

        if (showError.showConfirmationPopup("Are you sure you want to approve this request?")) {
            MsgHandler changeDuration = new MsgHandler(TypeMsg.changeTestDuration, ChangeTestDurationArr);
            ClientUI.chat.accept(changeDuration);

            MsgHandler approveRequest = new MsgHandler(TypeMsg.ApproveRequestByHeadOfDepartment, request.getAuthor());
            ClientUI.chat.accept(approveRequest);

            deleteRequest(testIDText.getText());
            reloadPage(approveBtn);
        }
    }

    @FXML
    public void declineRequest() {
        if (showError.showConfirmationPopup("Are you sure you want to decline this request?")) {
            MsgHandler declineRequest = new MsgHandler(TypeMsg.DeclineRequestByHeadOfDepartment, request.getAuthor());
            ClientUI.chat.accept(declineRequest);

            deleteRequest(testIDText.getText());
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