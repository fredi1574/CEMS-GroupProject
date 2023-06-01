package entity;
/**
 * Represents a student's test entity.
 */
public class StudentTest {
    private String studentID;
    private String testID;
    private String subjectID;
    private String course;
    private String testType;
    private String score;

    /**
     * Constructs a new StudentTest object.
     *
     * @param studentID The ID of the student.
     * @param testID The ID of the test.
     * @param subjectID The ID of the subject.
     * @param course The course name.
     * @param testType The type of the test.
     * @param score The test score.
     */
    public StudentTest(String studentID, String testID, String subjectID, String course, String testType, String score) {
        this.studentID = studentID;
        this.testID = testID;
        this.subjectID = subjectID;
        this.course = course;
        this.testType = testType;
        this.score = score;
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
    public String getTestType() {
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
