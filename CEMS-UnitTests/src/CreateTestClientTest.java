import entity.Course;
import entity.TestTypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import util.StateManagement;

import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CreateTestClientTest {
    public StateManagement stateManagement;

    @Mock
    private Connection mockConnection;
    @Mock
    private Statement mockStatement;
    @Mock
    private ResultSet mockResultSet;

    @BeforeEach
    void setUp() {

        mockConnection = mock(Connection.class);
        mockStatement = mock(Statement.class);
        mockResultSet = mock(ResultSet.class);

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


    }
}
