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
/**
 * The controller class for the view request controller.
 * In this screen the head of department can see the full details about the request and its reason
 * He can approve or decline the request from this screen. The response will be sent to the lecturer who made the request
 */
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
    private TextField TimeExtensionDurationText;

    @FXML
    private AnchorPane header;

    @FXML
    private TextField testIDText;

    private TestRequestForApproval request;
    private Stage manageRequests;


    /**
     * Closes the pop-up window.
     *
     * @param event The action event triggered by the "Close" button.
     */
    @FXML
    void closeClient(ActionEvent event) {
        ExitButton.closePopUp(event);
    }

    /**
     * Initializes the controller by enabling drag and drop functionality for the header.
     */
    public void initialize() {
        ScreenManager.dragAndDrop(header);
    }

    /**
     * Sets the reference to the manage requests stage.
     *
     * @param manageRequests The stage representing the manage requests screen.
     */
    public void setManage(Stage manageRequests) {
        this.manageRequests = manageRequests;
    }

    /**
     * Minimizes the application window.
     *
     * @param event The action event triggered by the "Minimize" button.
     */
    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

    /**
     * Sets the request data to be displayed in the UI.
     *
     * @param request The test request to be managed.
     */
    public void setRequest(TestRequestForApproval request) {
        this.request = request;
        CourseText.setText(request.getCourse());
        LecturerText.setText(request.getAuthor());
        RequestText.setText(request.getExplanation());
        TimeExtensionDurationText.setText(request.getNewDuration());
        testIDText.setText(request.getId());
    }

    /**
     * Deletes a request by sending a deletion message to the server.
     *
     * @param request The request to be deleted.
     */
    public void deleteRequest(String request) {
        MsgHandler delete = new MsgHandler(TypeMsg.DeleteRequest, request);
        ClientUI.chat.accept(delete);
    }

    /**
     * Approves the selected request by performing the following actions:
     * - Retrieves the added time from the UI.
     * - Creates an ArrayList to store the necessary information for changing the test duration.
     * - Displays a confirmation popup to verify the approval action.
     * - If the approval is confirmed:
     *     - Sends a message to change the test duration to the server.
     *     - Sends a message to approve the request to the server.
     *     - Deletes the approved request.
     *     - Reloads the page or performs any necessary actions after the approval.
     */
    @FXML
    public void approveRequest() {
        String AddedTime = TimeExtensionDurationText.getText();

        ArrayList<String> ChangeTestDurationArr = new ArrayList<>();

        ChangeTestDurationArr.add(testIDText.getText());
        ChangeTestDurationArr.add(AddedTime);

        if (ShowMessage.showConfirmationPopup("Are you sure you want to approve this request?")) {
            MsgHandler changeDuration = new MsgHandler(TypeMsg.changeTestDuration, ChangeTestDurationArr);
            ClientUI.chat.accept(changeDuration);

            MsgHandler approveRequest = new MsgHandler(TypeMsg.ApproveRequestByHeadOfDepartment, request.getAuthor());
            ClientUI.chat.accept(approveRequest);

            deleteRequest(testIDText.getText());
            reloadPage(approveBtn);
        }
    }

    /**
     * Declines the selected request by performing the following actions:
     * - Displays a confirmation popup to verify the decline action.
     * - If the decline is confirmed:
     *     - Sends a message to decline the request to the server.
     *     - Deletes the declined request.
     *     - Reloads the page or performs any necessary actions after the decline.
     */
    @FXML
    public void declineRequest() {
        if (ShowMessage.showConfirmationPopup("Are you sure you want to decline this request?")) {
            MsgHandler declineRequest = new MsgHandler(TypeMsg.DeclineRequestByHeadOfDepartment, request.getAuthor());
            ClientUI.chat.accept(declineRequest);

            deleteRequest(testIDText.getText());
            reloadPage(declineBtn);
        }
    }

    /**
     * Reloads the current page by closing the current stage and opening a new stage for the manage requests screen.
     *
     * @param button The button that triggers the page reload.
     */
    private void reloadPage(Button button) {
        Stage currentStage = (Stage) button.getScene().getWindow();
        currentStage.close();
        manageRequests.close();
        ScreenManager.showStage(PathConstants.EnterManageRequestsHeadOfDepart, PathConstants.iconPath);
    }
}