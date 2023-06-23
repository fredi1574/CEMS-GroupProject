import application.createNewTestScreen.notesScreen.InotesController;
import application.createNewTestScreen.notesScreen.NotesController;
import entity.Course;
import entity.TestQuestion;
import entity.TestTypeEnum;
import javafx.event.ActionEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.*;
import Client.ClientControl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
//import static org.mockito.Matchers.anyString;
//import static org.mockito.Mockito.*;

public class CreateTestClientTest {


    public static StateManagement stateManagement;

    private static NotesController notesController;
    private ShowMessage mockedShowMessage;
    private entity.Test test;
    private class ClientControlStub implements ChatIF{

        @Override
        public void accept(Object str) {
            notesController.setTest(test);

        }

        @Override
        public void display(String message) {

        }
    }
    public static ClientControl clientControl;

    @BeforeEach
    void setUp() {

        clientControl = new ClientControl(new ClientControlStub());
        notesController = new NotesController(new NotesControllerStub());
        //mockedShowMessage = mock(ShowMessage.class);

    }



    @Test
    //functionality: createTest() with missing test data
    //input: Test testWithMissingData
    //expected result: Error thrown due to missing test data
    public void CreateTest_IncompleteData_ReturnsMissingDataError() {
        stateManagement = StateManagement.getInstance();
        stateManagement.semester = "";
        stateManagement.year = "";
        stateManagement.session = "";
        stateManagement.testDuration = "";
        stateManagement.testType = null;

        entity.Test testWithMissingData = new entity.Test(
                "20",
                "010101",
                "Roman Gury",
                "",
                "Algebra",
                "abc",
                null,
                "def",
                "Math",
                "",
                "",
                "",
                "01"
        );

        String subjectID = testWithMissingData.getId().substring(0, 2);
        String courseID = testWithMissingData.getId().substring(2, 4);
        Course course = new Course(subjectID, courseID, testWithMissingData.getSubject(), testWithMissingData.getCourseName());

        stateManagement.setTestNum(testWithMissingData.getTestNumber());
        stateManagement.setTestID(testWithMissingData.getId());
        stateManagement.setTestDuration(testWithMissingData.getTestDuration());
        stateManagement.setCourse(course);
        stateManagement.setTestType(testWithMissingData.getTestType());
        stateManagement.setYear(testWithMissingData.getYear());
        stateManagement.setSession(testWithMissingData.getSession());
        stateManagement.setSemester(testWithMissingData.getSemester());
        stateManagement.setSubjectID(subjectID);
        stateManagement.setAuthor(testWithMissingData.getAuthor());

        String actualResult = notesController.createTest(new ActionEvent());

        assertEquals("Go to page 1 and complete the data of test", actualResult);
    }
    @Test
    //functionality: createTest() with no questions selected for the test
    //input: Test testWithNoQuestions
    //expected result: Error thrown due to missing test questions
    public void CreateTest_NoQuestions_ReturnsMissingQuestionsError() {
        stateManagement = StateManagement.getInstance();

        entity.Test testWithNoQuestions = new entity.Test(
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
                "b",
                "A",
                "01"
        );

        String subjectID = testWithNoQuestions.getId().substring(0, 2);
        String courseID = testWithNoQuestions.getId().substring(2, 4);
        Course course = new Course(subjectID, courseID, testWithNoQuestions.getSubject(), testWithNoQuestions.getCourseName());

        stateManagement.setTestNum(testWithNoQuestions.getTestNumber());
        stateManagement.setTestID(testWithNoQuestions.getId());
        stateManagement.setTestDuration(testWithNoQuestions.getTestDuration());
        stateManagement.setCourse(course);
        stateManagement.setTestType(testWithNoQuestions.getTestType());
        stateManagement.setYear(testWithNoQuestions.getYear());
        stateManagement.setSession(testWithNoQuestions.getSession());
        stateManagement.setSemester(testWithNoQuestions.getSemester());
        stateManagement.setSubjectID(subjectID);
        stateManagement.setAuthor(testWithNoQuestions.getAuthor());

        String actualResult = notesController.createTest(new ActionEvent());

        assertEquals("Select Questions for test from page 2", actualResult);
    }
    @Test
    //functionality: createTest() with less than 100 points allocated between the test's questions
    //input: Test testWithNotEnoughPoints
    //expected result: Error thrown due to insufficient points
    public void CreateTest_NotEnoughPoints_ReturnsNotEnoughPointsError() {
        stateManagement = StateManagement.getInstance();

        entity.Test testWithNotEnoughPoints = new entity.Test(
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
                "b",
                "A",
                "01"
        );

        String subjectID = testWithNotEnoughPoints.getId().substring(0, 2);
        String courseID = testWithNotEnoughPoints.getId().substring(2, 4);
        Course course = new Course(subjectID, courseID, testWithNotEnoughPoints.getSubject(), testWithNotEnoughPoints.getCourseName());

        stateManagement.setTestNum(testWithNotEnoughPoints.getTestNumber());
        stateManagement.setTestID(testWithNotEnoughPoints.getId());
        stateManagement.setTestDuration(testWithNotEnoughPoints.getTestDuration());
        stateManagement.setCourse(course);
        stateManagement.setTestType(testWithNotEnoughPoints.getTestType());
        stateManagement.setYear(testWithNotEnoughPoints.getYear());
        stateManagement.setSession(testWithNotEnoughPoints.getSession());
        stateManagement.setSemester(testWithNotEnoughPoints.getSemester());
        stateManagement.setSubjectID(subjectID);
        stateManagement.setAuthor(testWithNotEnoughPoints.getAuthor());
        TestQuestion question = new TestQuestion("1","1",50,"How are you?","1","Math","Algebra","May Caspi");
        stateManagement.setTestQuestions(question);
        stateManagement.subtractTotalRemainingPoints(50);

        String actualResult = notesController.createTest(new ActionEvent());
        assertEquals("Points for the questions do not add up to 100 in page 2", actualResult);
    }

    @Test
    //functionality: createTest() with all the required test details filled
    //input: Test regularTest
    //expected result: Test object with all the correct details is created
    public void createTestSuccess() {
        stateManagement = StateManagement.getInstance();

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
        stateManagement.setAuthor(regularTest.getAuthor());
        TestQuestion question = new TestQuestion("1","1",100,"How are you?","1","Math","Algebra","May Caspi");
        stateManagement.setTestQuestions(question);
        stateManagement.subtractTotalRemainingPoints(100);
        String actualResult = notesController.createTest(new ActionEvent());
        assertEquals("Test added successfully", actualResult);
        assertEquals(regularTest.getId(),notesController.getTest().getId());
        assertEquals(regularTest.getAuthor(),notesController.getTest().getAuthor());

    }

    @Test
    //functionality: createTest() is used twice with duplicate IDs
    //input: Test regularTest, Test duplicateTest
    //expected result: duplicateTest is created and overrides regularTest
    public void createTest_DuplicateTest_OverridesExistingTest() {
        stateManagement = StateManagement.getInstance();

        //creating first test
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
        stateManagement.setAuthor(regularTest.getAuthor());
        TestQuestion question = new TestQuestion("1","1",100,"How are you?","1","Math","Algebra","May Caspi");
        stateManagement.setTestQuestions(question);
        stateManagement.subtractTotalRemainingPoints(100);

        notesController.createTest(new ActionEvent());

        //creating duplicate test
        entity.Test duplicateTest = new entity.Test(
                "20",
                "010101",
                "May Caspi",
                "10",
                "Algebra",
                "abcd",
                TestTypeEnum.C,
                "defg",
                "Math",
                "2023",
                "A",
                "A",
                "01"
        );

        subjectID = duplicateTest.getId().substring(0, 2);
        courseID = duplicateTest.getId().substring(2, 4);
        Course duplicateTestCourse = new Course(subjectID, courseID, duplicateTest.getSubject(), duplicateTest.getCourseName());

        stateManagement.setTestNum(duplicateTest.getTestNumber());
        stateManagement.setTestID(duplicateTest.getId());
        stateManagement.setTestDuration(duplicateTest.getTestDuration());
        stateManagement.setCourse(duplicateTestCourse);
        stateManagement.setTestType(duplicateTest.getTestType());
        stateManagement.setYear(duplicateTest.getYear());
        stateManagement.setSession(duplicateTest.getSession());
        stateManagement.setSemester(duplicateTest.getSemester());
        stateManagement.setSubjectID(subjectID);
        stateManagement.setAuthor(duplicateTest.getAuthor());
        question = new TestQuestion("1","1",100,"How are you?","1","Math","Algebra","May Caspi");
        stateManagement.setTestQuestions(question);
        stateManagement.subtractTotalRemainingPoints(100);

        String actualResult = notesController.createTest(new ActionEvent());
        assertEquals("Test added successfully", actualResult);
        assertEquals(regularTest.getId(),notesController.getTest().getId());
        assertNotEquals(regularTest.getAuthor(),notesController.getTest().getAuthor());

    }

    public static class NotesControllerStub implements InotesController {

        public NotesControllerStub() {
        }

        @Override
        public void deleteTestIfAlreadyExists() {
            if (notesController.getTest()!=null) {
                notesController.setTest(null);
            }
        }

        @Override
        public void addTestToDB() {

            entity.Test newTest = new entity.Test(
                    stateManagement.getTestNum(),
                    stateManagement.getTestID(),
                    stateManagement.getAuthor(),
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

            //ClientUI.chat.accept(newTest);
            notesController.setTest(newTest);

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
