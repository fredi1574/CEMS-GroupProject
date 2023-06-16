package entity;

import java.io.Serializable;

public class AnswerOfStudent implements Serializable {
    private final String studentID;
    private final String testID;
    private final String questionID;
    private final int studentsAnswer;

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
