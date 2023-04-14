package application;

public class Question {
    private String questionNumber;
    private String questionId;
    private String questionText;
    private String questionWrittenBy;


    public Question(String questionNumber,String questionId,String questionText, String questionWrittenBy) {
        this.questionNumber = questionNumber;
        this.questionId = questionId;
        this.questionText = questionText;
        this.questionWrittenBy = questionWrittenBy;
    }

    public String getQuestionNumber (){
        return questionNumber;
    }
    public String getQuestionId (){
        return questionId;
    }

    public String getQuestion() {
       return questionText;
    }

    public String getQuestionWrittenBy() {
        return questionWrittenBy;
    }

}
