package entity;

import java.io.Serializable;

/**
 * Represents a question entity.
 */
public class Question implements Serializable {
    private final String id;
    private final String questionNumber;
    private final String subject;
    private final String courseName;
    private final String questionText;
    private final String author;
    private final String answer1;
    private final String answer2;
    private final String answer3;
    private final String answer4;
    private final String correctAnswer;

    /**
     * Constructs a Question object with the provided parameters.
     *
     * @param questionNumber The question number.
     * @param questionId     The question ID.
     * @param questionText   The question text.
     * @param questionWrittenBy The author of the question.
     * @param subject        The subject of the question.
     * @param course_name    The course name.
     * @param answer1        The first answer option.
     * @param answer2        The second answer option.
     * @param answer3        The third answer option.
     * @param answer4        The fourth answer option.
     * @param correctAnswer  The correct answer.
     */
    public Question(String questionNumber, String questionId, String questionText, String questionWrittenBy,
                    String subject, String course_name, String answer1, String answer2, String answer3,
                    String answer4, String correctAnswer) {
        this.questionNumber = questionNumber;
        this.id = questionId;
        this.questionText = questionText;
        this.author = questionWrittenBy;
        this.courseName = course_name;
        this.subject = subject;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correctAnswer = correctAnswer;
    }

    /**
     * Returns the first answer option.
     *
     * @return The first answer option.
     */
    public String getAnswer1() {
        return answer1;
    }

    /**
     * Returns the second answer option.
     *
     * @return The second answer option.
     */
    public String getAnswer2() {
        return answer2;
    }

    /**
     * Returns the third answer option.
     *
     * @return The third answer option.
     */
    public String getAnswer3() {
        return answer3;
    }

    /**
     * Returns the fourth answer option.
     *
     * @return The fourth answer option.
     */
    public String getAnswer4() {
        return answer4;
    }

    /**
     * Returns the correct answer.
     *
     * @return The correct answer.
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Returns the ID of the question.
     *
     * @return The ID of the question.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the question number.
     *
     * @return The question number.
     */
    public String getQuestionNumber() {
        return questionNumber;
    }

    /**
     * Returns the question text.
     *
     * @return The question text.
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Returns the author of the question.
     *
     * @return The author of the question.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the course name.
     *
     * @return The course name.
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Returns the subject of the question.
     *
     * @return The subject of the question.
     */
    public String getSubject() {
        return subject;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", questionNumber='" + questionNumber + '\'' +
                ", subject='" + subject + '\'' +
                ", courseName='" + courseName + '\'' +
                ", questionText='" + questionText + '\'' +
                ", author='" + author + '\'' +
                ", wrongAnswer1='" + answer1 + '\'' +
                ", wrongAnswer2='" + answer2 + '\'' +
                ", wrongAnswer3='" + answer3 + '\'' +
                ", wrongAnswer4='" + answer4 + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }
}
