package entity;

import java.io.Serializable;
import java.util.Observable;

public class TestQuestion implements Serializable {

	private Question question;
	private int points;
	private String questionID;
	private String questionText;
	private String testID;
	private String questionNumber;
	private String subject;
	private String courseName;
	private String author;

	public TestQuestion(){
	}

	public TestQuestion(String questionID, String questionNumber, int points, String questionText, String testID,
						String subject,  String courseName, String author) {
		this.questionID = questionID;
		this.questionNumber = questionNumber;
		this.points = points;
		this.questionText = questionText;
		this.testID = testID;
		this.subject = subject;
		this.courseName = courseName;
		this.author = author;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "TestQuestion{" +
				"points=" + points +
				", questionID='" + questionID + '\'' +
				", questionText='" + questionText + '\'' +
				", testID='" + testID + '\'' +
				", questionNumber='" + questionNumber + '\'' +
				", subject='" + subject + '\'' +
				", courseName='" + courseName + '\'' +
				", author='" + author + '\'' +
				'}';
	}
}
