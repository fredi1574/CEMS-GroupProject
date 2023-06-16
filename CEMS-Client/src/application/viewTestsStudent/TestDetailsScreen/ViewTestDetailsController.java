package application.viewTestsStudent.TestDetailsScreen;

import Client.Client;
import Client.ClientUI;
import entity.StudentTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.*;
import Client.ExitButton;

public class ViewTestDetailsController {

    @FXML
    private AnchorPane header;
    @FXML
    private Text usernameText;

    @FXML
    private TextField year;
    @FXML
    private TextField semester;
    @FXML
    private TextField session;
    @FXML
    private TextField grade;
    @FXML
    private TextField averageField;
    @FXML
    private Label courseTest;
    @FXML
    private TextArea commentsField;

    public void initialize() {
        ScreenManager.dragAndDrop(header);
        usernameText.setText(Client.user.getName());
    }

    public void setTest(StudentTest test) {

        semester.setText(test.getSemester());
        year.setText(test.getYear());
        session.setText(test.getSession());
        grade.setText(test.getScore());
        commentsField.setText(test.getLecturerComments());

        MsgHandler getAverage = new MsgHandler(TypeMsg.GetTestAverage, test.getTestID());
        ClientUI.chat.accept(getAverage);
        String average = ((String) ClientUI.chat.getSingleTest());

        averageField.setText(average);
        courseTest.setText(test.getCourse());
    }

    /**
     * Closes the pop-up window.
     *
     * @param event The event triggered by clicking the close button.
     */
    public void closePopUp(ActionEvent event) {
        ExitButton.closePopUp(event);
    }

    /**
     * Minimizes the pop-up window.
     *
     * @param event The event triggered by clicking the minimize button.
     */
    public void minimizeWindow(ActionEvent event) {
        MinimizeButton.minimizeWindow(event);
    }
}