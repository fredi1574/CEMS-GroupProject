package entity;

import java.io.Serializable;
import java.util.Observable;

public class StudentTest extends Test implements Serializable {
    private String studentID;
    private String testID;
    private String subjectID;
    private String course;
    private String grade;
    private String fullname;
    private String year;
    private String semester;
    private String session;
    private String testDuration;

    private ApprovalStatus approved;
    private CheatingSuspicion suspicionOfCheating;
    private String correctAnswers;
    private String totalQuestions;
    private String lecturerComments;


    private TestTypeEnum testType;

    public StudentTest(String studentID, String testID,
                       String subjectID, String course, String grade, String fullname, String year, String semester,
                       String session, CheatingSuspicion suspicionOfCheating, String correctAnswers, String totalQuestions,
                       String lecturerComments, ApprovalStatus approved, TestTypeEnum testType, String testDuration) {
        // Assign the parameters to the corresponding fields
        this.studentID = studentID;
        this.testID = testID;
        this.subjectID = subjectID;
        this.course = course;
        this.grade = grade;
        this.fullname = fullname;
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

    public TestTypeEnum getTestType() {
        return testType;
    }

    public void setTestType(TestTypeEnum testType) {
        this.testType = testType;
    }

    public String getTestDuration() {
        return testDuration;
    }

    public ApprovalStatus getApproved() {
        return approved;
    }

    public void setApproved(ApprovalStatus approved) {
        this.approved = approved;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getTestID() {
        return testID;
    }

    public void setTestID(String testID) {
        this.testID = testID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getScore() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public CheatingSuspicion getSuspicionOfCheating() {
        return suspicionOfCheating;
    }

    public void setSuspicionOfCheating(CheatingSuspicion suspicionOfCheating) {
        this.suspicionOfCheating = suspicionOfCheating;
    }

    public String getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(String correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public String getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(String totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public String getLecturerComments() {
        return lecturerComments;
    }

    public void setLecturerComments(String lecturerComments) {
        this.lecturerComments = lecturerComments;
    }

    @Override
    public String toString() {
        return "TestForApproval{" +
                "StudentID='" + studentID + '\'' +
                ", testID='" + testID + '\'' +
                ", subjectID='" + subjectID + '\'' +
                ", course='" + course + '\'' +
                ", grade='" + grade + '\'' +
                ", fullname='" + fullname + '\'' +
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