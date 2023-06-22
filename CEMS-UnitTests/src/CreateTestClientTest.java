import application.createNewTestScreen.notesScreen.InotesController;
import application.createNewTestScreen.notesScreen.NotesController;
import entity.Course;
import entity.TestTypeEnum;
import javafx.event.ActionEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.ShowMessage;
import util.StateManagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateTestClientTest {

    public StateManagement stateManagement;
    private NotesController notesController;
    private ShowMessage mockedShowMessage;

    @BeforeEach
    void setUp() {

        notesController = new NotesController(new NotesControllerStub());
        mockedShowMessage = mock(ShowMessage.class);

    }

    @Test
    public void test_IncompleteData_should_return_false() {
        when(mockedShowMessage.showErrorPopup(anyString())).thenReturn("Go to page 1 and complete the data of test");

        stateManagement = StateManagement.getInstance();
        // Test that an error popup is shown if data is incomplete
        stateManagement.semester = "";
        stateManagement.year = "";
        stateManagement.session = "";
        stateManagement.testDuration = "";
        stateManagement.testType = null;

        String actualResult = notesController.createTest(new ActionEvent());

        assertEquals("Go to page 1 and complete the data of test", actualResult);
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
        stateManagement.setTeacherComment(regularTest.getTeacherComments());
        stateManagement.setTestType(regularTest.getTestType());
        stateManagement.setStudentComment(regularTest.getStudentComments());
        stateManagement.setYear(regularTest.getYear());
        stateManagement.setSession(regularTest.getSession());
        stateManagement.setSemester(regularTest.getSemester());
        stateManagement.setSubjectID(subjectID);

        //Client.sendToClient(new MsgHandler<>(TypeMsg.AddNewTestResponse, null));

    }

    public static class NotesControllerStub implements InotesController {


        public NotesControllerStub() {
        }

        @Override
        public void deleteTestIfAlreadyExists() {
        }

        @Override
        public void addTestToDB() {
        }

        @Override
        public void addAllTestQuestionsToDB() {
        }

        @Override
        public void replaceScreen(ActionEvent event) {
        }
    }
}
//    @Test
//    public void testNoQuestionsSelected() {
//        // Test that an error popup is shown if no questions are selected
//        stateManagement.semester = "Spring";
//        stateManagement.year = "2022";
//        stateManagement.session = "Session 1";
//        stateManagement.testDuration = "2 hours";
//        stateManagement.testType = "Midterm";
//        clickOn("#createTestButton");
//        assertEquals("Select Questions for test from page 2", showError.getErrorMessage());
//    }
//
//    @Test
//    public void testPointsDoNotAddUp() {
//        // Test that an error popup is shown if points do not add up to 100
//        stateManagement.semester = "Fall";
//        stateManagement.year = "2021";
//        stateManagement.session = "Session 2";
//        stateManagement.testDuration = "1 hour";
//        stateManagement.testType = "Final";
//        stateManagement.addTestQuestion(new TestQuestion("Question 1", 50));
//        stateManagement.addTestQuestion(new TestQuestion("Question 2", 30));
//        clickOn("#createTestButton");
//        assertEquals("Points for the questions do not add up to 100 in page 2", showError.getErrorMessage());
//    }
//
//    @Test
//    public void testStudentAndTeacherComments() {
//        // Test that student and teacher comments are added if present
//        stateManagement.semester = "Summer";
//        stateManagement.year = "2022";
//        stateManagement.session = "Session 1";
//        stateManagement.testDuration = "3 hours";
//        stateManagement.testType = "Final";
//        stateManagement.addTestQuestion(new TestQuestion("Question 1", 20));
//        stateManagement.addTestQuestion(new TestQuestion("Question 2", 30));
//        clickOn("#studentNote").write("This is a student comment");
//        clickOn("#teacherNote").write("This is a teacher comment");
//        clickOn("#createTestButton");
//        assertEquals("This is a student comment", stateManagement.getStudentComment());
//        assertEquals("This is a teacher comment", stateManagement.getTeacherComment());
//    }
//}