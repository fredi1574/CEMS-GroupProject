package application.manageRequestsHeadOfDepart;

import Client.Client;
import entity.TestRequestForApproval;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.*;
import Client.ClientUI;
import Client.ExitButton;
import Client.LogOut;

import java.util.ArrayList;
import java.util.List;
/**
 * The controller class for the manage requests screen for the Head of Department.
 * It retrieves test requests from different lecturers in his department
 * The class allows the Head of Department to approve or decline individual requests in various tests.
 */
public class ManageRequestsController {

    @FXML
    private Text usernameText;

    @FXML
    private AnchorPane header;

    @FXML
    private TableView<TestRequestForApproval> RequestsDBTableView;
    public static ManageRequestsController ManageQuestionsControl;
    @FXML
    private Button approveBtn;

    @FXML
    private Button declineBtn;

    /**
     * Initializes the application by performing the following actions:
     * - Drags and drops the header on the screen.
     * - Sets the username text to the name of the current user.
     * - Retrieves test requests by subject using a message handler and displays them in a table.
     * - Configures table columns and their respective data.
     * - Adds double-click functionality to the table rows.
     * - Resizes the columns of the table based on specified multipliers.
     *
     * Note: This method assumes that the necessary UI elements and dependencies have been properly initialized.
     */
    public void initialize() {
        ScreenManager.dragAndDrop(header);
        usernameText.setText(Client.user.getName());
        MsgHandler getTable = new MsgHandler(TypeMsg.GetRequestsBySubject, Client.user.getUserName());
        ClientUI.chat.accept(getTable);

       ObservableList<TestRequestForApproval> requests = FXCollections.observableArrayList((List) ClientUI.chat.getAllRequests());
        ObservableList<String> columns = FXCollections.observableArrayList();
        TableManager.importData(RequestsDBTableView, requests);
        TableManager.addDoubleClickFunctionality(RequestsDBTableView, PathConstants.EnterViewRequestsHeadOfDepart, this::setFunctions);
        columns.addAll( "ID", "Subject", "Course ", "Explanation", "Author");
        TableManager.createTable(RequestsDBTableView, columns);
        double[] multipliers = {0.15, 0.15, 0.15, 0.4, 0.15};
        TableManager.resizeColumns(RequestsDBTableView, multipliers);
    }
    /**
     * Sets up the necessary functions for handling a specific action or event related to a given relative path.
     *
     * @param relativePath The relative path to the target screen or view.
     */
    public void setFunctions(String relativePath) {
        ScreenElements<Stage, FXMLLoader> screenElements = ScreenManager.popUpScreen(relativePath);
        FXMLLoader loader = screenElements.getFXMLLoader();
        ViewRequestController controller = loader.getController();
        TestRequestForApproval rowData = RequestsDBTableView.getSelectionModel().getSelectedItem();
        controller.setRequest(rowData);
        controller.setManage((Stage) header.getScene().getWindow());

    }
    /**
     * Deletes a request by sending a deletion message to the server.
     *
     * @param request The request to be deleted.
     */
    public void deleteRequest(String request){
        MsgHandler delete = new MsgHandler(TypeMsg.DeleteRequest,request);
        ClientUI.chat.accept(delete);

    }
    /**
     * Approves a selected request by performing the following actions:
     * - Retrieves the index of the selected request from the table view.
     * - If a request is selected:
     *     - Retrieves necessary information from the selected request, such as the request ID, added time, and author.
     *     - Creates an ArrayList to store the necessary information for changing the test duration.
     *     - Displays a confirmation popup to verify the approval action.
     *     - If the approval is confirmed:
     *         - Sends a message to change the test duration to the server.
     *         - Sends a message to approve the request to the server.
     *         - Deletes the approved request.
     *         - Reloads the page or performs any necessary actions after the approval.
     */
    @FXML
    public void approveRequest() {
        int selectedRequestIndex = RequestsDBTableView.getSelectionModel().getFocusedIndex();
        if (selectedRequestIndex != -1) {
            String requestToApprove = RequestsDBTableView.getItems().get(selectedRequestIndex).getId();
            String AddedTime = RequestsDBTableView.getItems().get(selectedRequestIndex).getNewDuration();
            String requestAuthor = RequestsDBTableView.getItems().get(selectedRequestIndex).getAuthor();
            ArrayList<String> ChangeTestDurationArr = new ArrayList<>();
            ChangeTestDurationArr.add(requestToApprove);
            ChangeTestDurationArr.add(AddedTime);
            ChangeTestDurationArr.add(requestAuthor);
            if (ShowMessage.showConfirmationPopup("Are you sure you want to approve this request?")) {
                MsgHandler changeDuration = new MsgHandler(TypeMsg.changeTestDuration,ChangeTestDurationArr);
                ClientUI.chat.accept(changeDuration);
                MsgHandler approveRequest = new MsgHandler(TypeMsg.ApproveRequestByHeadOfDepartment,requestAuthor);
                ClientUI.chat.accept(approveRequest);
                deleteRequest(requestToApprove);
                reloadPage(approveBtn);
            }
        }

    }
    /**
     * Declines a selected request by performing the following actions:
     * - Retrieves the index of the selected request from the table view.
     * - If a request is selected:
     *     - Retrieves the request ID of the selected request.
     *     - Displays a confirmation popup to verify the decline action.
     *     - If the decline is confirmed:
     *         - Sends a message to decline the request to the server.
     *         - Deletes the declined request.
     *         - Reloads the page or performs any necessary actions after the decline.
     */
    @FXML
    public void declineRequest() {
        int selectedRequestIndex = RequestsDBTableView.getSelectionModel().getFocusedIndex();
        if (selectedRequestIndex != -1) {
            String requestToDecline = RequestsDBTableView.getItems().get(selectedRequestIndex).getId();
            if (ShowMessage.showConfirmationPopup("Are you sure you want to decline this request?")) {
                MsgHandler declineRequest = new MsgHandler(TypeMsg.DeclineRequestByHeadOfDepartment,RequestsDBTableView.getItems().get(selectedRequestIndex).getAuthor());
                ClientUI.chat.accept(declineRequest);
                deleteRequest(requestToDecline);
                reloadPage(declineBtn);
            }
        }
    }
    /**
     * Logs out the user and navigates to the login screen.
     *
     * @param event The action event triggered by the "Log Out" button.
     */
    @FXML
    void LogOut(ActionEvent event) {
        LogOut.logOutToLoginScreen(event);
    }

    /**
     * Closes the client application.
     */
    @FXML
    void closeClient() {
        ExitButton.closeClient();
    }

    /**
     * Navigates back to the previous screen (main menu for the Head of Department).
     *
     * @param event The action event triggered by the "Go Back" button.
     */
    @FXML
    public void goBackToPreviousScreen(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuHeadOfDepartPath);
    }

    /**
     * Reloads the current page by closing the current stage and opening a new stage for the requests management screen.
     *
     * @param button The button that triggers the page reload.
     */
    private void reloadPage(Button button) {
        Stage currentStage = (Stage) button.getScene().getWindow();
        currentStage.close();
        ScreenManager.showStage(PathConstants.EnterManageRequestsHeadOfDepart, PathConstants.iconPath);
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
}
