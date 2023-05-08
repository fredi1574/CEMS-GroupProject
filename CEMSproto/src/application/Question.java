package application;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Question {

    private BooleanProperty selected = new SimpleBooleanProperty(false);
    private int number;
    private String questionID;
    private String questionText;
    private String author;

    public Question(int number, String questionID, String questionText, String author) {
        this.number = number;
        this.questionID = questionID;
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

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
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