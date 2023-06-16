package application.enterTest;

import Client.Client;
import Client.ClientUI;
import entity.ActiveTest;
import entity.StudentTest;
import entity.Test;
import entity.TestTypeEnum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.*;

import java.util.List;

public class EnterCodePopUpController {
    public static String testID;
    StateManagement stateManagement = StateManagement.getInstance();
    @FXML
    private AnchorPane header;
    @FXML
    private Text fullNameText;
    @FXML
    private TextField CodeText;
    private TestTypeEnum testType;
    private boolean NotAllowed;

    public void initialize() {
        // Enables dragging and dropping of the application window using the header pane
        ScreenManager.dragAndDrop(header);
        fullNameText.setText(Client.user.getName());
    }

    @FXML
    public void EnterCode(ActionEvent event) {
        String codet = CodeText.getText();
        if (codet.isEmpty()) {
            showError.showErrorPopup("Please enter code.");
            return;
        }
        if (codet.length() != 4) {
            showError.showErrorPopup("Code should contain 4 numbers.");
            return;
        }

        // Code is valid
        boolean testExists = false;
        MsgHandler<String> getActiveTestTable = new MsgHandler<>(TypeMsg.GetActiveTests, null);
        ClientUI.chat.accept(getActiveTestTable);
        ObservableList<ActiveTest> activeTests = FXCollections.observableArrayList((List) ClientUI.chat.getActiveTests());

        MsgHandler<String> getAllTestTable = new MsgHandler<>(TypeMsg.GetAllTestsTable, null);
        ClientUI.chat.accept(getAllTestTable);
        ObservableList<Test> allTests = FXCollections.observableArrayList((List) ClientUI.chat.getTests());

        for (ActiveTest activeTest : activeTests) {
            if (activeTest.getTestCode().equals(codet)) {
                testExists = true;
                testID = activeTest.getId();
                for (Test test : allTests) {
                    if (testID.equals(test.getId())) {
                        testType = test.getTestType();
                        break;
                    }
                }
            }
        }
        MsgHandler<String> getTable = new MsgHandler<>(TypeMsg.GetStudentsTests, Client.user.getId());
        ClientUI.chat.accept(getTable);

        ObservableList<StudentTest> studentTest = FXCollections.observableArrayList((List) ClientUI.chat.getStudentTests());
        for (StudentTest specificTest : studentTest) {
            if (specificTest.getTestID().equals(testID)) {
                NotAllowed = true;
                break;
            }
        }
        if (NotAllowed) {
            showError.showErrorPopup("Student has already attended the test!");
            ScreenManager.goToNewScreen(event, PathConstants.mainMenuStudentPath);
        } else if (testExists) {
            switch (testType) {
                case C:
                    ScreenManager.goToNewScreen(event, PathConstants.EnterComputerizedTestPath);
                    break;
                case M:
                    ScreenManager.goToNewScreen(event, PathConstants.StartManualTestPath);
            }

        } else {
            // Test with the given code does not exist
            // Handle the case accordingly
            showError.showErrorPopup("Test Not found !");
        }
    }

    @FXML
    private void closeClient(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuStudentPath);
    }


    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}

