package util;

import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class manages the state and data related to the application's test parameters and other relevant information.
 * It provides methods to access and manipulate the data stored in the class.
 */
public class StateManagement {
    private static StateManagement instance = null;

    // Test parameters
    public Course course;
    public String testNum;
    public String year;
    public String session;
    public String semester;
    public String testID;
    public String studentID;
    public String studentComment;
    public String teacherComment;
    public String testDuration;

    public String subjectID;
    public ActiveTest currentActivetest;
    public int totalRemainingPoints;


    public String author;

    public TestTypeEnum testType;
    public String previousScreenPath = PathConstants.mainMenuPath;
    ObservableList<TestForApproval> testForApproval = FXCollections.observableArrayList();
    final ObservableList<TestQuestion> testQuestions = FXCollections.observableArrayList();

    private StateManagement() {
        setTestID("-1"); // sets default empty value for testID on StateManagement initialization
        totalRemainingPoints = 100;
    }

    /**
     * Retrieves the singleton instance of the StateManagement class.
     *
     * @return the StateManagement instance
     */
    public static StateManagement getInstance() {
        if (instance == null) {
            instance = new StateManagement();
        }
        return instance;
    }

    /**
     * Resets the singleton instance of the StateManagement class.
     */
    public static void resetInstance() {
        instance = null;
    }

    /**
     * Sets the data of the current test.
     *
     * @param course  the course associated with the test
     * @param testNum the test number
     * @param testID  the test ID
     */
    public void setDataOfTest(Course course, String testNum, String testID) {
        this.course = course;
        this.testID = testID;
        this.testNum = testNum;
    }

    /**
     * Retrieves the list of tests awaiting approval.
     *
     * @return the observable list of tests for approval
     */
    public ObservableList<TestForApproval> getTestForApproval() {
        return testForApproval;
    }

    /**
     * Sets the list of tests awaiting approval.
     *
     * @param testForApproval the observable list of tests for approval
     */
    public void setTestForApproval(ObservableList<TestForApproval> testForApproval) {
        this.testForApproval = testForApproval;
    }

    /**
     * Retrieves the year associated with the test.
     *
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * Sets the year associated with the test.
     *
     * @param year the year
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * Retrieves the session associated with the test.
     *
     * @return the session
     */
    public String getSession() {
        return session;
    }

    /**
     * Sets the session associated with the test.
     *
     * @param session the session
     */
    public void setSession(String session) {
        this.session = session;
    }

    /**
     * Retrieves the semester associated with the test.
     *
     * @return the semester
     */
    public String getSemester() {
        return semester;
    }

    /**
     * Sets the semester associated with the test.
     *
     * @param semester the semester
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }

    /**
     * Retrieves the student comment associated with the test.
     *
     * @return the student comment
     */
    public String getStudentComment() {
        return studentComment;
    }

    /**
     * Sets the student comment associated with the test.
     *
     * @param studentComment the student comment
     */
    public void setStudentComment(String studentComment) {
        this.studentComment = studentComment;
    }

    /**
     * Retrieves the teacher comment associated with the test.
     *
     * @return the teacher comment
     */
    public String getTeacherComment() {
        return teacherComment;
    }

    /**
     * Sets the teacher comment associated with the test.
     *
     * @param teacherComment the teacher comment
     */
    public void setTeacherComment(String teacherComment) {
        this.teacherComment = teacherComment;
    }

    /**
     * Retrieves the test number.
     *
     * @return the test number
     */
    public String getTestNum() {
        return testNum;
    }

    /**
     * Sets the test number.
     *
     * @param testNum the test number
     */
    public void setTestNum(String testNum) {
        this.testNum = testNum;
    }

    /**
     * Retrieves the total remaining points for the test.
     *
     * @return the total remaining points
     */
    public int getTotalRemainingPoints() {
        return totalRemainingPoints;
    }

    /**
     * Adds the specified value to the total remaining points.
     * Used when removing a question from the selected questions table.
     *
     * @param totalRemainingPoints the points to add
     */
    public void addTotalRemainingPoints(int totalRemainingPoints) {
        this.totalRemainingPoints += totalRemainingPoints;
    }

    /**
     * Subtracts the specified value from the total remaining points.
     * Used when picking a question for the selected questions table.
     *
     * @param totalRemainingPoints the points to subtract
     */
    public void subtractTotalRemainingPoints(int totalRemainingPoints) {
        this.totalRemainingPoints -= totalRemainingPoints;
    }

    /**
     * Retrieves the test ID.
     *
     * @return the test ID
     */
    public String getTestID() {
        return testID;
    }

    /**
     * Sets the test ID.
     *
     * @param testID the test ID
     */
    public void setTestID(String testID) {
        this.testID = testID;
    }

    /**
     * Retrieves the test duration.
     *
     * @return the test duration
     */
    public String getTestDuration() {
        return testDuration;
    }

    /**
     * Sets the test duration.
     *
     * @param testDuration the test duration
     */
    public void setTestDuration(String testDuration) {
        this.testDuration = testDuration;
    }

    /**
     * Retrieves the test type.
     *
     * @return the test type
     */
    public TestTypeEnum getTestType() {
        return testType;
    }

    /**
     * Sets the test type.
     *
     * @param testType the test type
     */
    public void setTestType(TestTypeEnum testType) {
        this.testType = testType;
    }

    /**
     * Retrieves the list of test questions.
     *
     * @return the observable list of test questions
     */
    public ObservableList<TestQuestion> getTestQuestions() {
        return testQuestions;
    }

    /**
     * Adds a test question to the list of test questions.
     *
     * @param question the test question to add
     */
    public void setTestQuestions(TestQuestion question) {
        this.testQuestions.add(question);
    }

    /**
     * Clears the list of test questions.
     */
    public void clearTestQuestions() {
        this.testQuestions.clear();
    }

    /**
     * Retrieves the course associated with the test.
     *
     * @return the course
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Sets the course associated with the test.
     *
     * @param course the course
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * Retrieves the current active test.
     *
     * @return the current active test
     */
    public ActiveTest getCurrentActivetest() {
        return currentActivetest;
    }

    /**
     * Sets the current active test.
     *
     * @param currentActivetest the current active test
     */
    public void setCurrentActivetest(ActiveTest currentActivetest) {
        this.currentActivetest = currentActivetest;
    }

    /**
     * Retrieves the path of the previous screen.
     *
     * @return the previous screen path
     */
    public String getPreviousScreenPath() {
        return previousScreenPath;
    }

    /**
     * Sets the path of the previous screen.
     *
     * @param previousScreenPath the previous screen path
     */
    public void setPreviousScreenPath(String previousScreenPath) {
        this.previousScreenPath = previousScreenPath;
    }

    /**
     * Retrieves the student ID associated with the test.
     *
     * @return the student ID
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Sets the student ID associated with the test.
     *
     * @param studentID the student ID
     */
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    /**
     * Retrieves the subject ID associated with the test.
     *
     * @return the subject ID
     */
    public String getSubjectID() {
        return subjectID;
    }

    /**
     * Sets the subject ID associated with the test.
     *
     * @param subjectID the subject ID
     */
    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Resets all test-state related data.
     * Used when a new test has been created.
     */
    public void resetTestState() {
        this.course = null;
        this.testNum = null;
        this.testID = "-1";
        this.studentComment = null;
        this.teacherComment = null;
        this.testDuration = null;
        this.testQuestions.clear();
    }

    /**
     * Returns a string representation of the StateManagement object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "StateManagement{" +
                "course=" + course +
                ", testNum='" + testNum + '\'' +
                ", testID='" + testID + '\'' +
                ", studentComment='" + studentComment + '\'' +
                ", teacherComment='" + teacherComment + '\'' +
                ", testDuration='" + testDuration + '\'' +
                ", testQuestions=" + testQuestions +
                '}';
    }
}
