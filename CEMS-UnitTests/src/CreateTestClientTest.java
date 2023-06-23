import Client.Client;
import application.createNewTestScreen.notesScreen.InotesController;
import application.createNewTestScreen.notesScreen.NotesController;
import entity.Course;
import entity.TestQuestion;
import entity.TestTypeEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.*;
import Client.ClientControl;

import javax.swing.plaf.nimbus.State;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class CreateTestClientTest {


    public StateManagement stateManagement;

    private NotesController notesController;
    private ShowMessage mockedShowMessage;
    private entity.Test test;
    private class ClientConrolStub implements ChatIF{

        @Override
        public void accept(Object str) {
            notesController.setTest(test);

        }

        @Override
        public void display(String message) {

        }
    }
    private ClientControl clientControl;

    @BeforeEach
    void setUp() {

        clientControl = new ClientControl(new ClientConrolStub());
        notesController = new NotesController(new NotesControllerStub());
        //mockedShowMessage = mock(ShowMessage.class);

    }



    @Test
    public void test_IncompleteData_should_return_false() {
        stateManagement = StateManagement.getInstance();
        stateManagement.semester = "";
        stateManagement.year = "";
        stateManagement.session = "";
        stateManagement.testDuration = "";
        stateManagement.testType = null;

        String actualResult = notesController.createTest(new ActionEvent());

        assertEquals("Go to page 1 and complete the data of test", actualResult);
    }
    @Test
    public void test_NoQuestions_should_return_false() {
        stateManagement = StateManagement.getInstance();
        stateManagement.semester = "A";
        stateManagement.year = "2020";
        stateManagement.session = "b";
        stateManagement.testDuration = "10";
        stateManagement.testType = TestTypeEnum.C;
        String actualResult = notesController.createTest(new ActionEvent());

        assertEquals("Select Questions for test from page 2", actualResult);
    }
    @Test
    public void CreateTest_NotEnoughPoints_should_return_false() {
        stateManagement = StateManagement.getInstance();
        stateManagement.semester = "A";
        stateManagement.year = "2020";
        stateManagement.session = "b";
        stateManagement.testDuration = "10";
        stateManagement.testType = TestTypeEnum.C;
        TestQuestion question = new TestQuestion("1","1",50,"How are you?","1","Math","Algebra","May Caspi");
        stateManagement.setTestQuestions(question);
        stateManagement.addTotalRemainingPoints(50);
        String actualResult = notesController.createTest(new ActionEvent());
        assertEquals("Points for the questions do not add up to 100 in page 2", actualResult);
    }

    @Test
    //functionality: createTest() with all the required test details filled
    //input: Test regularTest
    //expected result: Test object with all the correct details
    public void createTestSuccess() {
        entity.Test regularTest = new entity.Test(
                "20",
                "010101",
                "Roman Gury",
                "10",
                "Algebra",
                "abc",
                TestTypeEnum.C,
                "def",
                "Math",
                "2023",
                "A",
                "A",
                "01"
        );

        stateManagement = StateManagement.getInstance();

        String subjectID = regularTest.getId().substring(0, 2);
        String courseID = regularTest.getId().substring(2, 4);
        Course regularTestCourse = new Course(subjectID, courseID, regularTest.getSubject(), regularTest.getCourseName());

        stateManagement.setTestNum(regularTest.getTestNumber());
        stateManagement.setTestID(regularTest.getId());
        stateManagement.setTestDuration(regularTest.getTestDuration());
        stateManagement.setCourse(regularTestCourse);
        stateManagement.setTestType(regularTest.getTestType());
        stateManagement.setYear(regularTest.getYear());
        stateManagement.setSession(regularTest.getSession());
        stateManagement.setSemester(regularTest.getSemester());
        stateManagement.setSubjectID(subjectID);
        TestQuestion question = new TestQuestion("1","1",100,"How are you?","1","Math","Algebra","May Caspi");
        stateManagement.setTestQuestions(question);
        stateManagement.subtractTotalRemainingPoints(100);
        String actualResult = notesController.createTest(new ActionEvent());
        assertEquals("Test added successfully", actualResult);

    }

    public static class NotesControllerStub implements InotesController {
        public MysqlConnection mysqlConnection;
        public StateManagement stateManagement;

        public NotesControllerStub() {
        }

        @Override
        public void deleteTestIfAlreadyExists() {
        }

        @Override
        public void addTestToDB() {
            mysqlConnection = MysqlConnection.getInstance();
            mysqlConnection.connectToDb("Aa123456");
            entity.Test newTest = new entity.Test(
                    stateManagement.getTestNum(),
                    stateManagement.getTestID(),
                    Client.user.getFullName(),
                    stateManagement.getTestDuration(),
                    stateManagement.getCourse().getCourseName(),
                    stateManagement.getTeacherComment(),
                    stateManagement.getTestType(),
                    stateManagement.getStudentComment(),
                    stateManagement.getCourse().getSubjectName(),
                    stateManagement.getYear(),
                    stateManagement.getSession(),
                    stateManagement.getSemester(),
                    stateManagement.getSubjectID()
            );

        }

        @Override
        public void addAllTestQuestionsToDB() {
        }

        @Override
        public void replaceScreen(ActionEvent event) {
        }

        @Override
        public void checkNotes() {

        }
    }
}
