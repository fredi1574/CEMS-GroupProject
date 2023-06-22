import entity.Course;
import entity.TestTypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import util.StateManagement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.mockito.Mockito.mock;

public class CreateTestServerTest {

    public StateManagement stateManagement;

    @Mock
    private Connection mockConnection;
    @Mock
    private Statement mockStatement;
    @Mock
    private ResultSet mockResultSet;
    @Mock
    private MysqlConnection mysqlConnectionMock = mock(MysqlConnection.class);

    @BeforeEach
    void setUp() {
        mockConnection = mock(Connection.class);
        mockStatement = mock(Statement.class);
        mockResultSet = mock(ResultSet.class);

    }

    public void createTestSuccess() {

        //when(mysqlConnectionMock.)

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


}