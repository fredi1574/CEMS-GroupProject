package entity;

import java.io.Serializable;
import java.util.Observable;

public class Question implements Serializable {
    //TODO: fix selected property support (used in checkboxes)
    //private BooleanProperty selected = new SimpleBooleanProperty(false);
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

    public Question(String questionNumber,
                    String questionId,String questionText, String questionWrittenBy,String subject,String course_name,
                    String answer1,String answer2,String answer3,String answer4,String correctAnswer) {
        this.questionNumber = questionNumber;
        this.id = questionId;
        this.questionText = questionText;
        this.author = questionWrittenBy;
        this.courseName = course_name;
        this.subject=subject;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correctAnswer = correctAnswer;
    }
    public String getAnswer1() {
        return answer1;
    }
    public String getAnswer2() {
        return answer2;
    }
    public String getAnswer3() {
        return answer3;
    }
    public String getAnswer4() {
        return answer4;
    }
    public String getCorrectAnswer() {
        return correctAnswer;
    }
    public String getId() {
        return id;
    }
    public String getQuestionNumber() {
        return questionNumber;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getAuthor() {
        return author;
    }

    public String getCourseName() {
        return courseName;
    }

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
                ", wrongAnswer3='" + answer4 + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }


}
