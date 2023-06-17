package entity;

import java.io.Serializable;
/**
 * Represents a test waiting for approval from lecturer.
 */
public class TestForApproval implements Serializable {
    private String studentID;
    private String testID;
    private String subjectID;
    private String course;
    private String grade;
    private String fullName;
    private String year;
    private String semester;
    private String session;

    private ApprovalStatus approved;
    private CheatingSuspicion suspicionOfCheating;
    private String correctAnswers;
    private String totalQuestions;
    private String lecturerComments;

    private TestTypeEnum testType;

    /**
     * Constructs a TestForApproval object with the provided information.
     *
     * @param studentID           The ID of the student associated with the test.
     * @param testID              The ID of the test.
     * @param subjectID           The ID of the subject.
     * @param course              The course name.
     * @param grade               The grade of the test.
     * @param fullName            The full name of the student.
     * @param year                The year of the test.
     * @param semester            The semester of the test.
     * @param session             The session of the test.
     * @param suspicionOfCheating The suspicion of cheating for the test.
     * @param correctAnswers      The number of correct answers in the test.
     * @param totalQuestions      The total number of questions in the test.
     * @param lecturerComments    The comments provided by the lecturer.
     * @param approved            The approval status of the test.
     * @param testType            The type of the test.
     */
    public TestForApproval(String studentID, String testID, String subjectID, String course, String grade,
                           String fullName, String year, String semester, String session,
                           CheatingSuspicion suspicionOfCheating, String correctAnswers, String totalQuestions,
                           String lecturerComments, ApprovalStatus approved, TestTypeEnum testType) {
        this.studentID = studentID;
        this.testID = testID;
        this.subjectID = subjectID;
        this.course = course;
        this.grade = grade;
        this.fullName = fullName;
        this.year = year;
        this.semester = semester;
        this.session = session;
        this.suspicionOfCheating = suspicionOfCheating;
        this.correctAnswers = correctAnswers;
        this.totalQuestions = totalQuestions;
        this.lecturerComments = lecturerComments;
        this.approved = approved;
        this.testType = testType;
    }

    /**
     * Retrieves the type of the test.
     *
     * @return The type of the test.
     */
    public TestTypeEnum getTestType() {
        return testType;
    }

    /**
     * Sets the type of the test.
     *
     * @param testType The type of the test.
     */
    public void setTestType(TestTypeEnum testType) {
        this.testType = testType;
    }

    /**
     * Retrieves the approval status of the test.
     *
     * @return The approval status of the test.
     */
    public ApprovalStatus getApproved() {
        return approved;
    }

    /**
     * Sets the approval status of the test.
     *
     * @param approved The approval status of the test.
     */
    public void setApproved(ApprovalStatus approved) {
        this.approved = approved;
    }

    /**
     * Retrieves the ID of the student associated with the test.
     *
     * @return The ID of the student.
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Sets the ID of the student associated with the test.
     *
     * @param studentID The ID of the student.
     */
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    /**
     * Retrieves the ID of the test.
     *
     * @return The ID of the test.
     */
    public String getTestID() {
        return testID;
    }

    /**
     * Sets the ID of the test.
     *
     * @param testID The ID of the test.
     */
    public void setTestID(String testID) {
        this.testID = testID;
    }

    /**
     * Retrieves the ID of the subject.
     *
     * @return The ID of the subject.
     */
    public String getSubjectID() {
        return subjectID;
    }

    /**
     * Sets the ID of the subject.
     *
     * @param subjectID The ID of the subject.
     */
    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
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
     * Sets the course name.
     *
     * @param course The course name.
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * Retrieves the grade of the test.
     *
     * @return The grade of the test.
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Sets the grade of the test.
     *
     * @param grade The grade of the test.
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * Retrieves the full name of the student.
     *
     * @return The full name of the student.
     */
    public String getFullname() {
        return fullName;
    }

    /**
     * Sets the full name of the student.
     *
     * @param fullName The full name of the student.
     */
    public void setFullname(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Retrieves the year of the test.
     *
     * @return The year of the test.
     */
    public String getYear() {
        return year;
    }

    /**
     * Sets the year of the test.
     *
     * @param year The year of the test.
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * Retrieves the semester of the test.
     *
     * @return The semester of the test.
     */
    public String getSemester() {
        return semester;
    }

    /**
     * Sets the semester of the test.
     *
     * @param semester The semester of the test.
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }

    /**
     * Retrieves the session of the test.
     *
     * @return The session of the test.
     */
    public String getSession() {
        return session;
    }

    /**
     * Sets the session of the test.
     *
     * @param session The session of the test.
     */
    public void setSession(String session) {
        this.session = session;
    }

    /**
     * Retrieves the suspicion of cheating for the test.
     *
     * @return The suspicion of cheating.
     */
    public CheatingSuspicion getSuspicionOfCheating() {
        return suspicionOfCheating;
    }

    /**
     * Sets the suspicion of cheating for the test.
     *
     * @param suspicionOfCheating The suspicion of cheating.
     */
    public void setSuspicionOfCheating(CheatingSuspicion suspicionOfCheating) {
        this.suspicionOfCheating = suspicionOfCheating;
    }

    /**
     * Retrieves the number of correct answers in the test.
     *
     * @return The number of correct answers.
     */
    public String getCorrectAnswers() {
        return correctAnswers;
    }

    /**
     * Sets the number of correct answers in the test.
     *
     * @param correctAnswers The number of correct answers.
     */
    public void setCorrectAnswers(String correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    /**
     * Retrieves the total number of questions in the test.
     *
     * @return The total number of questions.
     */
    public String getTotalQuestions() {
        return totalQuestions;
    }

    /**
     * Sets the total number of questions in the test.
     *
     * @param totalQuestions The total number of questions.
     */
    public void setTotalQuestions(String totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    /**
     * Retrieves the comments provided by the lecturer.
     *
     * @return The comments provided by the lecturer.
     */
    public String getLecturerComments() {
        return lecturerComments;
    }

    /**
     * Sets the comments provided by the lecturer.
     *
     * @param lecturerComments The comments provided by the lecturer.
     */
    public void setLecturerComments(String lecturerComments) {
        this.lecturerComments = lecturerComments;
    }

    /**
     * Returns a string representation of the TestForApproval object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "TestForApproval{" +
                "studentID='" + studentID + '\'' +
                ", testID='" + testID + '\'' +
                ", subjectID='" + subjectID + '\'' +
                ", course='" + course + '\'' +
                ", grade='" + grade + '\'' +
                ", fullName='" + fullName + '\'' +
                ", year='" + year + '\'' +
                ", semester='" + semester + '\'' +
                ", session='" + session + '\'' +
                ", approved=" + approved +
                ", suspicionOfCheating=" + suspicionOfCheating +
                ", correctAnswers='" + correctAnswers + '\'' +
                ", totalQuestions='" + totalQuestions + '\'' +
                ", lecturerComments='" + lecturerComments + '\'' +
                '}';
    }
}
