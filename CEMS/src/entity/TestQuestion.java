package entity;

public class TestQuestion {

    private Question question;
    private int points;
    private String questionID;
    private String questionText;
    private String testID;
    private String questionNumber;
    private String subject;
    private String courseName;
    private String lecturer;
    protected String content;

    public TestQuestion(){
    }
    // Getters and Setters
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
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

    public String getTestID() {
        return testID;
    }

    public void setTestID(String testID) {
        this.testID = testID;
    }
    public String getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(String questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }


    @Override
    public String toString() {
        return "Question ID: " + this.questionID + "\nPoints: " + this.points + "\nTeacher Comment: "
                + "\nStudent Comment: " + "\nTest ID: " + this.testID;
    }
}