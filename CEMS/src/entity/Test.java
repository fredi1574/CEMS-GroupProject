package entity;

import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.Observable;

public class Test extends Observable implements Serializable {
    private String id;
    private int testNumber;
    private String author;
    private String testDuration;
    private String dateOfTest;
    private String startingTime;
    private String subject;
    private String courseName;
    private String teacherComments;
    private String studentComments;
    private TestTypeEnum testType;

    private ObservableList<TestQuestion> questions;

    //constructor for step 1 of test creation - adding test details
    public Test(int testNumber, String id, String subject, String courseName, String author) {
        this.testNumber = testNumber;
        this.id = id;
        this.subject = subject;
        this.courseName = courseName;
        this.author = author;
    }

    //constructor for step 2 of test creation - adding test questions
    public Test(int testNumber, String id, String subject, String courseName, String author, ObservableList<TestQuestion> questions, String teacherComments, String studentComments) {
        this.testNumber = testNumber;
        this.id = id;
        this.subject = subject;
        this.courseName = courseName;
        this.author = author;
        this.questions = questions;
        this.teacherComments = teacherComments;
        this.studentComments = studentComments;
    }

    //constructor for fully created test
    public Test(int testNumber, String id, String author, String testDuration, String dateOfTest, String courseName,
                String teacherComments, TestTypeEnum TestType, String studentComments, String startingTime, String subject) {
        this.id = id;
        this.testNumber = testNumber;
        this.author = author;
        this.testDuration = testDuration;
        this.dateOfTest = dateOfTest;
        this.courseName = courseName;
        this.teacherComments = teacherComments;
        this.testType = TestType;
        this.studentComments = studentComments;
        this.startingTime = startingTime;
        this.subject = subject;
    }

    public ObservableList<TestQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(ObservableList<TestQuestion> questions) {
        this.questions = questions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTestNumber() {
        return testNumber;
    }

    public void setTestNumber(int testNumber) {
        this.testNumber = testNumber;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTestDuration() {
        return testDuration;
    }

    public void setTestDuration(String testDuration) {
        this.testDuration = testDuration;
    }

    public String getDateOfTest() {
        return dateOfTest;
    }

    public void setDateOfTest(String dateOfTest) {
        this.dateOfTest = dateOfTest;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherComments() {
        return teacherComments;
    }

    public void setTeacherComments(String teacherComments) {
        this.teacherComments = teacherComments;
    }

    public TestTypeEnum getTestType() {
        return testType;
    }

    public void setTestType(TestTypeEnum testType) {
        this.testType = testType;
    }

    public String getStudentComments() {
        return studentComments;
    }

    public void setStudentComment(String studentComment) {
        this.studentComments = studentComment;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id='" + id + '\'' +
                ", testNum='" + testNumber + '\'' +
                ", author='" + author + '\'' +
                ", testDuration='" + testDuration + '\'' +
                ", dateOfTest='" + dateOfTest + '\'' +
                ", courseName='" + courseName + '\'' +
                ", teacherComment='" + teacherComments + '\'' +
                ", testType='" + testType + '\'' +
                ", studentComment='" + studentComments + '\'' +
                ", startingTime='" + startingTime + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}