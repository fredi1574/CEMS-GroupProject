package application.ManageQuestionsScreen;

import application.Client.ClientUI;
import application.Question;
import application.common.MsgHandler;
import application.common.TypeMsg;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import application.ManageQuestionsScreen.*;
public class UpdateQuestionController{
    @FXML
    private TextField idField;

    @FXML
    private TextField subjectField;

    @FXML
    private TextField courseNameField;

    @FXML
    private TextArea questionTextField;

    @FXML
    private TextField questionNumberField;

    @FXML
    private TextField lecturerField;

    @FXML
    private Button saveButton;
    

    private  Question question;
    @FXML
   
    public void initialize() {
        // You can add initialization code here
    }
    Stage manage;
    public  void setManage(Stage manage){
        this.manage=manage;
    }


    @FXML
    void onSaveButtonClicked(ActionEvent e) {
        ArrayList<Question> arr=new ArrayList<>();
        //public Question(String questionNumber,String questionId,String questionText, String questionWrittenBy,String subject,String course_name) {
        Question Update_Questions = new Question(questionNumberField.getText(), question.getId(), questionTextField.getText(),lecturerField.getText(), subjectField.getText(),courseNameField.getText());
        arr.add(Update_Questions);
        MsgHandler editQ = new MsgHandler(TypeMsg.EditQuestion, arr);
        ClientUI.chat.accept((Object)  editQ);
     Stage stage = (Stage) saveButton.getScene().getWindow();
       stage.close();
        try {
            manage.close();
            Parent reFresh_Root = FXMLLoader.load(getClass().getResource("/application/ManageQuestionsScreen/ManageQuestions.fxml"));
            Scene refresh_Root_Scene = new Scene(reFresh_Root);
            Stage refresh_Root_Stage = new Stage();
            refresh_Root_Stage.setTitle("Login Window");
            refresh_Root_Stage.setScene(refresh_Root_Scene);
            refresh_Root_Stage.show();
             stage = (Stage) saveButton.getScene().getWindow();

            // Close the stage
            stage.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }


    public void  setQuestion(Question q){
        question=q;
        subjectField.setText(q.getSubject());
        courseNameField.setText(q.getCourse_name());
        questionTextField.setText(q.getQuestion_text());
        questionNumberField.setText(q.getQuestion_number());
        lecturerField.setText(q.getLecturer());
        idField.setText(q.getId());
    }
}
