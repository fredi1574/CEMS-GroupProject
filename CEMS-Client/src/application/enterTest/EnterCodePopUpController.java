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

    /**
     * Initializes the controller.
     * Enables dragging and dropping of the application window using the header pane.
     * Sets the full name of the user.
     */
    public void initialize() {
        ScreenManager.dragAndDrop(header);
        fullNameText.setText(Client.user.getName());
    }

    /**
     * Handles the action of entering the test code.
     * Validates the code and checks if the test exists and if the student is allowed to take the test.
     * If Student has already taken the test, an error appears
     * If the test's code is invalid, an error appears
     * Based on the test type,Manual or Computerized - navigates to the appropriate screen.
     *
     * @param event The action event triggered by the Enter Code button.
     */
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
                    break;
            }
        } else {
            showError.showErrorPopup("Test Not found !");
        }
    }

    /**
     * Closes the client application and navigates back to the student's main menu.
     *
     * @param event The action event triggered by the Close button.
     */
    @FXML
    private void closeClient(ActionEvent event) {
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuStudentPath);
    }

    /**
     * Minimizes the application window.
     *
     * @param event The action event triggered by the Minimize button.
     */
    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}
