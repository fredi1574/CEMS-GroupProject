package entity;

import java.io.Serializable;
import java.util.Observable;

public class AnswerOfStudent  extends Observable implements Serializable {
    private String studentID;
    private String testID;
    private String questionID;
    private int studentsAnswer;

    public AnswerOfStudent (String studentID, String testID, String questionID, int studentsAnswer) {
        this.studentID = studentID;
        this.testID = testID;
        this.questionID = questionID;
        this.studentsAnswer = studentsAnswer;
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

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public int getStudentsAnswer() {
        return studentsAnswer;
    }

    public void setStudentsAnswer(int studentsAnswer) {
        this.studentsAnswer = studentsAnswer;
    }
}
