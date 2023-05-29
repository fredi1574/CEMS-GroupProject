package entity;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.io.Serializable;
import java.util.Observable;

public class Question extends Observable implements Serializable {
    //TODO: fix selected property support (used in checkboxes)
    //private BooleanProperty selected = new SimpleBooleanProperty(false);
    private String id;
    private String questionNumber;
    private String subject;
    private String courseName;
    private String questionText;
    private String lecturer;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String correctAnswer;

    public Question(String questionNumber,
                    String questionId,String questionText, String questionWrittenBy,String subject,String course_name,
                    String answer1,String answer2,String answer3,String answer4,String correctAnswer) {
        this.questionNumber = questionNumber;
        this.id = questionId;
        this.questionText = questionText;
        this.lecturer = questionWrittenBy;
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

    public String getLecturer() {
        return lecturer;
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
                ", lecturer='" + lecturer + '\'' +
                ", wrongAnswer1='" + answer1 + '\'' +
                ", wrongAnswer2='" + answer2 + '\'' +
                ", wrongAnswer3='" + answer3 + '\'' +
                ", wrongAnswer3='" + answer4 + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }


}
