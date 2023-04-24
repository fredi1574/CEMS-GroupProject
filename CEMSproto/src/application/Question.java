package application;

public class Question {
    private int questionNumber;
    private String questionId;
    private String questionText;
    private String author;


    public Question(int questionNumber,String questionId, String questionText, String author) {
        this.questionNumber = questionNumber;
        this.questionId = questionId;
        this.questionText = questionText;
        this.author = author;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
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
