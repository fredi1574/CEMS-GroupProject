package application.viewTestsStudent.TestDetailsScreen;

import client.Client;
import entity.StudentTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.ExitButton;
import util.MinimizeButton;
import util.ScreenManager;

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
    private TextField average;
    @FXML
    private Label courseTest;

    private StudentTest test;

    private Stage viewTestStudent;

    public void initialize() {
        ScreenManager.dragAndDrop(header);
        usernameText.setText(Client.user.getFullName());

    }

    public void setController(Stage viewTestStudent) {
        this.viewTestStudent = viewTestStudent;
    }

    public void setTest(StudentTest test) {
        this.test = test;

        semester.setText(test.getSemester());
        year.setText(test.getYear());
        session.setText(test.getSession());
        grade.setText(test.getScore());
//        average.setText(test.getAverage());
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