package entity;

import java.io.Serializable;
import java.util.Observable;

/**
 * Represents a student's test entity.
 */
public class StudentTest extends Observable implements Serializable {
    private String studentID;
    private String testID;
    private String subjectID;
    private String course;
    private TestTypeEnum testType;
    private String score;
    private String fullname;
    private String year;
    private String semester;
    private String session;

    /**
     * Constructs a new StudentTest object.
     *
     * @param studentID The ID of the student.
     * @param testID The ID of the test.
     * @param subjectID The ID of the subject.
     * @param course The course name.
     * @param testType The type of the test.
     * @param score The test score.
     * @param fullname fullname of student
     * @param year
     * @param semester
     * @param session
     *
     */
    public StudentTest(String studentID, String testID, String subjectID, String course, TestTypeEnum testType, String score,String fullname,String semester,String session,String year) {
        this.studentID = studentID;
        this.testID = testID;
        this.subjectID = subjectID;
        this.course = course;
        this.testType = testType;
        this.score = score;
        this.fullname = fullname;
        this.semester = semester;
        this. year = year;
        this.session = session;
    }

    public String getYear() {
        return year;
    }

    public String getSemester() {
        return semester;
    }

    public String getSession() {
        return session;
    }

    /**
     * Retrieves the ID of the student.
     *
     * @return The student ID.
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Retrieves the ID of the test.
     *
     * @return The test ID.
     */
    public String getTestID() {
        return testID;
    }
    public String getFullname() {
        return fullname;
    }

    /**
     * Retrieves the ID of the subject.
     *
     * @return The subject ID.
     */
    public String getSubjectID() {
        return subjectID;
    }

    /**
     * Retrieves the course name.
     *
     * @return The course name.
     */
    public String getCourse() {
        return course;
    }

    /**
     * Retrieves the type of the test.
     *
     * @return The test type.
     */
    public TestTypeEnum getTestType() {
        return testType;
    }

    /**
     * Retrieves the test score.
     *
     * @return The test score.
     */
    public String getScore() {
        return score;
    }
}
