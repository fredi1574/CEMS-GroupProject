package application.manageRequestsHeadOfDepart;

import application.manageQuestionsScreen.ManageQuestionsController;
import application.manageQuestionsScreen.UpdateQuestionController;
import client.Client;
import client.ClientUI;
import common.MsgHandler;
import common.TypeMsg;
import entity.Question;
import entity.TestRequestForApproval;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.*;

import java.util.ArrayList;
import java.util.List;

public class manageRequestsController {

    @FXML
    private Text usernameText;

    @FXML
    private AnchorPane header;

    @FXML
    private TableView<TestRequestForApproval> RequestsDBTableView;
    public static manageRequestsController ManageQuestionsControl;
    @FXML
    private Button approveBtn;

    @FXML
    private Button declineBtn;


    public void initialize() {
        ScreenManager.dragAndDrop(header);
        usernameText.setText(Client.user.getFullName());
        MsgHandler getTable = new MsgHandler(TypeMsg.GetRequestsBySubject, Client.user.getUserName());
        ClientUI.chat.accept(getTable);

       ObservableList<TestRequestForApproval> requests = FXCollections.observableArrayList((List) ClientUI.chat.gelAllRequests());
        ObservableList<String> columns = FXCollections.observableArrayList();
        TableManager.importData(RequestsDBTableView, requests);
        TableManager.addDoubleClickFunctionality(RequestsDBTableView, PathConstants.EnterViewRequestsHeadOfDepart, this::setFunctions);
        columns.addAll( "ID", "Subject", "Course ", "Explanation", "Author");
        TableManager.createTable(RequestsDBTableView, columns);
        double[] multipliers = {0.15, 0.15, 0.15, 0.4, 0.15};
        TableManager.resizeColumns(RequestsDBTableView, multipliers);
    }
    public void setFunctions(String relativePath) {
        ScreenElements<Stage, FXMLLoader> screenElements = ScreenManager.popUpScreen(relativePath);
        FXMLLoader loader = screenElements.getFXMLLoader();
        viewRequestController controller = loader.getController();
        TestRequestForApproval rowData = RequestsDBTableView.getSelectionModel().getSelectedItem();
        controller.setRequest(rowData);
        controller.setManage((Stage) header.getScene().getWindow());

    }
    @FXML
    public void approveRequest(ActionEvent event) {
        int selectedRequestIndex = RequestsDBTableView.getSelectionModel().getFocusedIndex();
        if (selectedRequestIndex != -1) {
            String requestToApprove = RequestsDBTableView.getItems().get(selectedRequestIndex).getId();
            String AddedTime = RequestsDBTableView.getItems().get(selectedRequestIndex).getNewDuration();
            ArrayList<String> ChangeTestDurationArr = new ArrayList<>();
            ChangeTestDurationArr.add(requestToApprove);
            ChangeTestDurationArr.add(AddedTime);
            if (showError.showConfirmationPopup("Are you sure you want to approve this request?")) {
                MsgHandler changeDuration = new MsgHandler(TypeMsg.changeTestDuration,ChangeTestDurationArr);
                ClientUI.chat.accept(changeDuration);
                MsgHandler approveRequest = new MsgHandler(TypeMsg.ApproveRequestByHeadOfDepartment,requestToApprove);
                ClientUI.chat.accept(approveRequest);

                reloadPage(approveBtn);
                //TODO: return to the lecturer with the approval
            }
        }

    }
    @FXML
    public void declineRequest(ActionEvent event) {
        int selectedRequestIndex = RequestsDBTableView.getSelectionModel().getFocusedIndex();
        if (selectedRequestIndex != -1) {
            String requestToDecline = RequestsDBTableView.getItems().get(selectedRequestIndex).getId();
            if (showError.showConfirmationPopup("Are you sure you want to decline this request?")) {
                MsgHandler declineRequest = new MsgHandler(TypeMsg.DeclineRequestByHeadOfDepartment,requestToDecline);
                ClientUI.chat.accept(declineRequest);
                reloadPage(declineBtn);
                //TODO: return to the lecturer with the approval
            }
        }
    }
    @FXML
    void LogOut(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.loginPath);
    }


    @FXML
    void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);

    }

    @FXML
    public void goBackToPreviousScreen(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuHeadOfDepartPath);
    }
    private void reloadPage(Button button) {
        Stage currentStage = (Stage) button.getScene().getWindow();
        currentStage.close();
        ScreenManager.showStage(PathConstants.EnterManageRequestsHeadOfDepart, PathConstants.iconPath);
    }
    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

}
