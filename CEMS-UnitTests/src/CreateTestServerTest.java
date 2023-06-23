import application.createNewTestScreen.notesScreen.InotesController;
import application.createNewTestScreen.notesScreen.NotesController;
import entity.Course;
import entity.TestQuestion;
import entity.TestTypeEnum;
import javafx.event.ActionEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import util.ChatIF;
import util.StateManagement;
import util.TypeMsg;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class CreateTestServerTest {

    static CemsServer Cems;

    public static StateManagement stateManagement;
    private static NotesController notesController;
    private TypeMsg expectedResponseMsg;

    @Mock
    private Connection mockConnection;
    @Mock
    private Statement mockStatement;
    @Mock
    private ResultSet mockResultSet;
    private MysqlConnection mysqlConnection;



    @BeforeEach
    void setUp() {
        notesController = new NotesController(new NotesControllerStub());
        Cems = new CemsServer(5555, "Aa123456");
//        try {
//            Cems.listen(); // Start listening for connections
//
//        } catch (IOException e) {
//            System.out.println("IO exception");
//        }
        mysqlConnection = new MysqlConnection();
        mysqlConnection.connectToDb("Aa123456");

    }

    @Test
    public void createTestSuccess() {

        expectedResponseMsg = TypeMsg.AddNewTestResponse;

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

        TestQuestion question = new TestQuestion("1","1",100,"How are you?","1","Math","Algebra","May Caspi");
        stateManagement.setTestQuestions(question);
        stateManagement.subtractTotalRemainingPoints(100);

        notesController.createTest(new ActionEvent());

        assertEquals(expectedResponseMsg,notesController.getServerResponseMsg());
        //Client.sendToClient(new MsgHandler<>(TypeMsg.AddNewTestResponse, null));

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