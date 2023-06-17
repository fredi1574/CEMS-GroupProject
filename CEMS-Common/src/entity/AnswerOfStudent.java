package entity;

import java.io.Serializable;

public class AnswerOfStudent implements Serializable {
    private final String studentID;
    private final String testID;
    private final String questionID;
    private final int studentsAnswer;

    /**
     * Constructs an instance of the AnswerOfStudent class.
     *
     * @param studentID      The ID of the student.
     * @param testID         The ID of the test.
     * @param questionID     The ID of the question.
     * @param studentsAnswer The answer provided by the student.
     */
    public AnswerOfStudent(String studentID, String testID, String questionID, int studentsAnswer) {
        this.studentID = studentID;
        this.testID = testID;
        this.questionID = questionID;
        this.studentsAnswer = studentsAnswer;
    }

    /**
     * Retrieves the ID of the student.
     *
     * @return The ID of the student.
     */
    public String getStudentID() {
        return studentID;
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
     * Retrieves the ID of the question.
     *
     * @return The ID of the question.
     */
    public String getQuestionID() {
        return questionID;
    }

    /**
     * Retrieves the answer provided by the student.
     *
     * @return The answer provided by the student.
     */
    public int getStudentsAnswer() {
        return studentsAnswer;
    }
}
