package entity;

import javafx.collections.ObservableList;

import java.io.Serializable;
/**
 * Represents a test.
 */
public class Test implements Serializable {
    private String id;
    private String testNumber;
    private String author;
    private String testDuration;
    private String subject;
    private String courseName;
    private String teacherComments;
    private String studentComments;
    private TestTypeEnum testType;
    private String year;
    private String semester;
    private String session;
    private String subjectID;

    private ObservableList<TestQuestion> questions;


    /**
     * Constructor for creating a fully initialized Test object.
     *
     * @param testNumber       the test number
     * @param id               the ID of the test
     * @param author           the author of the test
     * @param testDuration     the duration of the test
     * @param courseName       the name of the course
     * @param teacherComments  the teacher comments on the test
     * @param TestType         the type of the test
     * @param studentComments  the student comments on the test
     * @param subject          the subject of the test
     * @param year             the year of the test
     * @param session          the session of the test
     * @param semester         the semester of the test
     * @param subjectID        the ID of the subject
     */
    public Test(String testNumber, String id, String author, String testDuration, String courseName,
                String teacherComments, TestTypeEnum TestType, String studentComments, String subject,
                String year, String session, String semester, String subjectID) {
        this.id = id;
        this.testNumber = testNumber;
        this.author = author;
        this.testDuration = testDuration;
        this.courseName = courseName;
        this.teacherComments = teacherComments;
        this.testType = TestType;
        this.studentComments = studentComments;
        this.subject = subject;
        this.year = year;
        this.session = session;
        this.semester = semester;
        this.subjectID = subjectID;

    }
    /**
     * Returns the year of the test.
     *
     * @return the year of the test
     */
    public String getYear() {
        return year;
    }
    /**
     * Sets the year of the test.
     *
     * @param year the year of the test
     */

    public void setYear(String year) {
        this.year = year;
    }
    /**
     * Returns the subject ID of the test.
     *
     * @return the subject ID of the test
     */
    public String getSubjectID() {
        return subjectID;
    }
    /**
     * Sets the subject ID of the test.
     *
     * @param subjectID the subject ID of the test
     */
    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    /**
     * Returns the semester of the test.
     *
     * @return the semester of the test
     */
    public String getSemester() {
        return semester;
    }
    /**
     * Sets the semester of the test.
     *
     * @param semester the semester of the test
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }

    /**
     * Returns the session of the test.
     *
     * @return the session of the test
     */
    public String getSession() {
        return session;
    }
    /**
     * Sets the session of the test.
     *
     * @param session the session of the test
     */
    public void setSession(String session) {
        this.session = session;
    }
    /**
     * Returns the list of questions in the test.
     *
     * @return the list of questions in the test
     */
    public ObservableList<TestQuestion> getQuestions() {
        return questions;
    }
    /**
     * Sets the list of questions in the test.
     *
     * @param questions the list of questions in the test
     */

    public void setQuestions(ObservableList<TestQuestion> questions) {
        this.questions = questions;
    }
    /**
     * Returns the ID of the test.
     *
     * @return the ID of the test
     */

    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the test.
     *
     * @param id the ID of the test
     */

    public void setId(String id) {
        this.id = id;
    }
    /**
     * Returns the test number.
     *
     * @return the test number
     */
    public String getTestNumber() {
        return testNumber;
    }
    /**
     * Sets the test number.
     *
     * @param testNumber the test number
     */

    public void setTestNumber(String testNumber) {
        this.testNumber = testNumber;
    }
    /**
     * Returns the author of the test.
     *
     * @return the author of the test
     */
    public String getAuthor() {
        return author;
    }
    /**
     * Sets the author of the test.
     *
     * @param author the author of the test
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    /**
     * Returns the duration of the test.
     *
     * @return the duration of the test
     */
    public String getTestDuration() {
        return testDuration;
    }
    /**
     * Sets the duration of the test.
     *
     * @param testDuration the duration of the test
     */
    public void setTestDuration(String testDuration) {
        this.testDuration = testDuration;
    }

    /**
     * Returns the name of the course.
     *
     * @return the name of the course
     */
    public String getCourseName() {
        return courseName;
    }
    /**
     * Sets the name of the course.
     *
     * @param courseName the name of the course
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Returns the teacher comments on the test.
     *
     * @return the teacher comments on the test
     */
    public String getTeacherComments() {
        return teacherComments;
    }
    /**
     * Sets the teacher comments on the test.
     *
     * @param teacherComments the teacher comments on the test
     */

    public void setTeacherComments(String teacherComments) {
        this.teacherComments = teacherComments;
    }
    /**
     * Returns the type of the test.
     *
     * @return the type of the test
     */
    public TestTypeEnum getTestType() {
        return testType;
    }
    /**
     * Sets the type of the test.
     *
     * @param testType the type of the test
     */

    public void setTestType(TestTypeEnum testType) {
        this.testType = testType;
    }
    /**
     * Returns the student comments on the test.
     *
     * @return the student comments on the test
     */

    public String getStudentComments() {
        return studentComments;
    }
    /**
     * Sets the student comments on the test.
     *
     * @param studentComments the student comments on the test
     */
    public void setStudentComments(String studentComments) {
        this.studentComments = studentComments;
    }
    /**
     * Sets the student comment on the test.
     *
     * @param studentComment the student comment on the test
     */
    public void setStudentComment(String studentComment) {
        this.studentComments = studentComment;
    }

    /**
     * Returns the subject of the test.
     *
     * @return the subject of the test
     */

    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject of the test.
     *
     * @param subject the subject of the test
     */

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
