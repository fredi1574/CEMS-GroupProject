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

    public String getTestID() {
        return testID;
    }

    public String getQuestionID() {
        return questionID;
    }

    public int getStudentsAnswer() {
        return studentsAnswer;
    }

}
