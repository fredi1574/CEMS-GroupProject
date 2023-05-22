package entity;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.io.Serializable;
import java.util.Observable;

public class Question extends Observable implements Serializable {
    //TODO: fix selected property support (used in checkboxes)
    //private BooleanProperty selected = new SimpleBooleanProperty(false);
    private String id;
    private String question_number;
    private String subject;
    private String course_name;
    private String question_text;
    private String lecturer;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String correctAnswer;

    public Question(String questionNumber,
                    String questionId,String questionText, String questionWrittenBy,String subject,String course_name,
                    String answer1,String answer2,String answer3,String answer4,String correctAnswer) {
        this.question_number = questionNumber;
        this.id = questionId;
        this.question_text = questionText;
        this.lecturer = questionWrittenBy;
        this.course_name=course_name;
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
    public String getQuestion_number() {
        return question_number;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public String getLecturer() {
        return lecturer;
    }

    public String getCourse_name() {
        return course_name;
    }

    public String getSubject() {
        return subject;
    }


    @Override
    public String toString() {
        return "MyClass{" +
                "id='" + id + '\'' +
                ", question_number='" + question_number + '\'' +
                ", subject='" + subject + '\'' +
                ", course_name='" + course_name + '\'' +
                ", question_text='" + question_text + '\'' +
                ", lecturer='" + lecturer + '\'' +
                ", wrongAnswer1='" + answer1 + '\'' +
                ", wrongAnswer2='" + answer2 + '\'' +
                ", wrongAnswer3='" + answer3 + '\'' +
                ", wrongAnswer3='" + answer4 + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }


}
