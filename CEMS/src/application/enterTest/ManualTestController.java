package application.enterTest;

import client.Client;
import client.ClientUI;
import common.MsgHandler;
import common.TypeMsg;
import entity.ActiveTest;
import entity.StateManagement;
import entity.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import util.*;
import javafx.scene.control.TextField;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ManualTestController {
    StateManagement stateManagement =StateManagement.getInstance();
    Test test;

    ActiveTest testActive;
    @FXML
    private AnchorPane header;
    private Thread timerThread;
    @FXML
    private Text addtionalTimeForSubmitTEXT;
    private boolean isSubmit = true;
    private boolean forText = true;
    private boolean notUpload = true;
    private boolean isTimerRunning;
    private int totalSecondsRemaining;
    File selectedFile;
    @FXML
    private TextField EndTimeText;

    @FXML
    private TextField FileSubmissionsText;

    @FXML
    private TextField StartTimeText;

    @FXML
    private TextField SubmissionStatusText;

    @FXML
    private TextField durationTime;
    @FXML
    private Text fullNameText;


    public void initialize() {

        // Enables dragging and dropping of the application window using the header pane
        ScreenManager.dragAndDrop(header);
        fullNameText.setText(Client.user.getFullName());
        MsgHandler getActiveTestTable = new MsgHandler(TypeMsg.GetActiveTests, null);
        ClientUI.chat.accept(getActiveTestTable);
        ObservableList<ActiveTest> activeTests = FXCollections.observableArrayList((List) ClientUI.chat.getActiveTests());

        MsgHandler getAllTestTable = new MsgHandler(TypeMsg.GetAllTestsTable, null);
        ClientUI.chat.accept(getAllTestTable);
        ObservableList<Test> allTests = FXCollections.observableArrayList((List) ClientUI.chat.getTests());
        for(int i=0;i<allTests.size();i++){
            if(stateManagement.getTestCode().equals(allTests.get(i).getTestCode())){
                test = allTests.get(i);
                break;
            }
        }
        for(int i=0; i<activeTests.size(); i++){
            if(test.getTestCode().equals(activeTests.get(i).getTestCode())){
                testActive = activeTests.get(i);
                StartTimeText.setText(testActive.getStartingTime());
                FinishedTime(test);
                break;
            }

        }
        if(test == null){
            showError.showErrorPopup("No Test Now");
            return;
        }


    }
    private String formatTime(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }


    private void startTimer(int minutesRemaining) {
        totalSecondsRemaining = minutesRemaining * 60;
        durationTime.setText(formatTime(totalSecondsRemaining));
        durationTime.setDisable(true);

        if (timerThread == null || !isTimerRunning) {
            timerThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    isTimerRunning = true;
                    while (totalSecondsRemaining > 0) {
                        try {
                            Thread.sleep(1000); // Wait for 1 second
                            decreaseTimeByOneSecond();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    isTimerRunning = false;
                }
            });
            timerThread.start();
        }
    }

    private void decreaseTimeByOneSecond() {
        totalSecondsRemaining--;
        durationTime.setText(formatTime(totalSecondsRemaining));
        if (totalSecondsRemaining <= 0) {
            if(isSubmit){
                totalSecondsRemaining = 60;
                addtionalTimeForSubmitTEXT.setText("*You have only one minutes left to submitedd test*");
                isSubmit = false;
            }else
                stopTimer();
        }
    }
    private void stopTimer() {
        if (timerThread != null) {
            isTimerRunning = false;
            notUpload = false;
            forText = false;
            try {
                timerThread.join(); // Wait for the thread to finish
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timerThread = null;
        }
    }
    void FinishedTime(Test test){
        int hour = Integer.parseInt(StartTimeText.getText().substring(0,2));
        int min = Integer.parseInt(StartTimeText.getText().substring(3,5));
        int sec = Integer.parseInt(StartTimeText.getText().substring(6,8));
        int durationTime = Integer.parseInt(test.getTestDuration());
        if(durationTime + min >= 60){
            hour += (durationTime + min) / 60;
            min  +=  (durationTime + min) % 60;
        }else
            min  +=  durationTime + min % 60;

        EndTimeText.setText(String.format("%02d:%02d:%02d", hour, min, sec));
    }

    @FXML
    public void uploadFileBTN(ActionEvent event) {
        if(notUpload) {
            FileChooser fc = new FileChooser();
            selectedFile = fc.showOpenDialog(null);
            if (selectedFile != null) {
                FileSubmissionsText.setText(selectedFile.getName());
                SubmissionStatusText.setText("The solve is submitedd file : " + selectedFile.getName());
            }
        }else {

            showError.showErrorPopup("Can not do upload now the time is finished");
        }
    }
    @FXML
    public void DownloadFileBTN(ActionEvent event) {
        try {
            FileChooser fc = new FileChooser();
            fc.setTitle("Download File");
            fc.setInitialFileName(test.getTestCode());
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Word Files", "*.docx"));
            File downloadedFile = fc.showSaveDialog(null);// UserController.currentStage
            System.out.println("Downloaded");
            startTimer(Integer.parseInt(test.getTestDuration()));
            if (downloadedFile == null)
                return;
            File ManualTest = new File(downloadedFile.getAbsolutePath());
            FileOutputStream fos = new FileOutputStream(ManualTest);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            bos.flush();
            fos.flush();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    public void LogOut(ActionEvent event) {
        LogOut.logOutToLoginScreen(event);
    }
    @FXML
    private void closeClient(ActionEvent event) {
        ExitButton.closeClient(event);
    }

    @FXML
    void onSaveButton(ActionEvent event) {
        if(forText) {
            if (FileSubmissionsText.getText().isEmpty()) {
                if (showError.showConfirmationPopup("Are you sure want to save not submit yet")) {
                    StateManagement.resetInstance();
                    ScreenManager.goToNewScreen(event, PathConstants.mainMenuStudentPath);
                }
            }
            else {
                if (showError.showConfirmationPopup("Are you sure want to save your test")) {
                    StateManagement.resetInstance();
                    ScreenManager.goToNewScreen(event, PathConstants.mainMenuStudentPath);
                } else
                    return;
            }

        }else
            showError.showInfoPopup("Sorry for you dont have time to submitedd");
        ScreenManager.goToNewScreen(event, PathConstants.mainMenuStudentPath);
    }
    @FXML
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }

}


