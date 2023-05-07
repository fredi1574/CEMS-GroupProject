package application;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Question {

    private BooleanProperty selected = new SimpleBooleanProperty(false);
    private int number;
    private String questionId;
    private String questionText;
    private String author;

    //    public Question(BooleanProperty selected, int number, String questionId, String questionText, String author) {
//        this.selected = selected;
//        this.number = number;
//        this.questionId = questionId;
//        this.questionText = questionText;
//        this.author = author;
//    }
    public Question(int number, String questionId, String questionText, String author) {
        this.number = number;
        this.questionId = questionId;
        this.questionText = questionText;
        this.author = author;
    }

    public BooleanProperty getSelected() {
        return selected;
    }

    public void setSelected(BooleanProperty selected) {
        this.selected = selected;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}