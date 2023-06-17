package entity;

import java.io.Serializable;

/**
 * Represents a Student Test.
 */
public class StudentTest implements Serializable {
    private String studentID;
    private String testID;
    private String subjectID;
    private String course;
    private String grade;
    private String fullName;
    private String year;
    private String semester;
    private String session;
    private final String testDuration;

    private ApprovalStatus approved;
    private CheatingSuspicion suspicionOfCheating;
    private String correctAnswers;
    private String totalQuestions;
    private String lecturerComments;

    private TestTypeEnum testType;

    /**
     *
     * @param studentID           the ID of the student
     * @param testID              the ID of the test
     * @param subjectID           the ID of the subject
     * @param course              the course name
     * @param grade               the grade obtained by the student
     * @param fullName            the full name of the student
     * @param year                the year of the test
     * @param semester            the semester of the test
     * @param session             the session of the test
     * @param suspicionOfCheating the suspicion of cheating for the test
     * @param correctAnswers      the number of correct answers
     * @param totalQuestions      the total number of questions
     * @param lecturerComments    the comments from the lecturer
     * @param approved            the approval status of the test
     * @param testType            the type of the test
     * @param testDuration        the duration of the test
     */
    public StudentTest(String studentID, String testID, String subjectID, String course, String grade,
                       String fullName, String year, String semester, String session,
                       CheatingSuspicion suspicionOfCheating, String correctAnswers, String totalQuestions,
                       String lecturerComments, ApprovalStatus approved, TestTypeEnum testType, String testDuration) {
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
        this.testDuration = testDuration;
    }

    /**
     * Gets the test type.
     *
     * @return the test type
     */
    public TestTypeEnum getTestType() {
        return testType;
    }

    /**
     * Sets the test type.
     *
     * @param testType the test type to set
     */
    public void setTestType(TestTypeEnum testType) {
        this.testType = testType;
    }

    /**
     * Gets the test duration.
     *
     * @return the test duration
     */
    public String getTestDuration() {
        return testDuration;
    }

    /**
     * Gets the approval status of the test.
     *
     * @return the approval status
     */
    public ApprovalStatus getApproved() {
        return approved;
    }

    /**
     * Sets the approval status of the test.
     *
     * @param approved the approval status to set
     */
    public void setApproved(ApprovalStatus approved) {
        this.approved = approved;
    }

    /**
     * Gets the ID of the student.
     *
     * @return the student ID
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Sets the ID of the student.
     *
     * @param studentID the student ID to set
     */
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    /**
     * Gets the ID of the test.
     *
     * @return the test ID
     */
    public String getTestID() {
        return testID;
    }

    /**
     * Sets the ID of the test.
     *
     * @param testID the test ID to set
     */
    public void setTestID(String testID) {
        this.testID = testID;
    }

    /**
     * Gets the ID of the subject.
     *
     * @return the subject ID
     */
    public String getSubjectID() {
        return subjectID;
    }

    /**
     * Sets the ID of the subject.
     *
     * @param subjectID the subject ID to set
     */
    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    /**
     * Gets the course name.
     *
     * @return the course name
     */
    public String getCourse() {
        return course;
    }

    /**
     * Sets the course name.
     *
     * @param course the course name to set
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * Gets the grade obtained by the student.
     *
     * @return the grade
     */
    public String getScore() {
        return grade;
    }

    /**
     * Sets the grade obtained by the student.
     *
     * @param grade the grade to set
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * Gets the full name of the student.
     *
     * @return the full name
     */
    public String getFullname() {
        return fullName;
    }

    /**
     * Sets the full name of the student.
     *
     * @param fullName the full name to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Gets the year of the test.
     *
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * Sets the year of the test.
     *
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * Gets the semester of the test.
     *
     * @return the semester
     */
    public String getSemester() {
        return semester;
    }

    /**
     * Sets the semester of the test.
     *
     * @param semester the semester to set
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }

    /**
     * Gets the session of the test.
     *
     * @return the session
     */
    public String getSession() {
        return session;
    }

    /**
     * Sets the session of the test.
     *
     * @param session the session to set
     */
    public void setSession(String session) {
        this.session = session;
    }

    /**
     * Gets the suspicion of cheating for the test.
     *
     * @return the suspicion of cheating
     */
    public CheatingSuspicion getSuspicionOfCheating() {
        return suspicionOfCheating;
    }

    /**
     * Sets the suspicion of cheating for the test.
     *
     * @param suspicionOfCheating the suspicion of cheating to set
     */
    public void setSuspicionOfCheating(CheatingSuspicion suspicionOfCheating) {
        this.suspicionOfCheating = suspicionOfCheating;
    }

    /**
     * Gets the number of correct answers.
     *
     * @return the number of correct answers
     */
    public String getCorrectAnswers() {
        return correctAnswers;
    }

    /**
     * Sets the number of correct answers.
     *
     * @param correctAnswers the number of correct answers to set
     */
    public void setCorrectAnswers(String correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    /**
     * Gets the total number of questions.
     *
     * @return the total number of questions
     */
    public String getTotalQuestions() {
        return totalQuestions;
    }

    /**
     * Sets the total number of questions.
     *
     * @param totalQuestions the total number of questions to set
     */
    public void setTotalQuestions(String totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    /**
     * Gets the comments from the lecturer.
     *
     * @return the lecturer comments
     */
    public String getLecturerComments() {
        return lecturerComments;
    }

    /**
     * Sets the comments from the lecturer.
     *
     * @param lecturerComments the lecturer comments to set
     */
    public void setLecturerComments(String lecturerComments) {
        this.lecturerComments = lecturerComments;
    }

    @Override
    public String toString() {
        return "StudentTest{" +
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
