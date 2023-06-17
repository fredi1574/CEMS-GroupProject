package entity;

import java.io.Serializable;
/**
 * Represents a question in a test.
 */
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

	public TestQuestion() {
	}

	/**
	 * Constructs a TestQuestion object with the provided information.
	 *
	 * @param questionID     The ID of the question.
	 * @param questionNumber The number of the question.
	 * @param points         The points assigned to the question.
	 * @param questionText   The text of the question.
	 * @param testID         The ID of the test associated with the question.
	 * @param subject        The subject of the question.
	 * @param courseName     The name of the course associated with the question.
	 * @param author         The author of the question.
	 */
	public TestQuestion(String questionID, String questionNumber, int points, String questionText, String testID,
						String subject, String courseName, String author) {
		this.questionID = questionID;
		this.questionNumber = questionNumber;
		this.points = points;
		this.questionText = questionText;
		this.testID = testID;
		this.subject = subject;
		this.courseName = courseName;
		this.author = author;
	}

	/**
	 * Retrieves the Question object associated with the test question.
	 *
	 * @return The Question object.
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * Sets the Question object associated with the test question.
	 *
	 * @param question The Question object.
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

	/**
	 * Retrieves the points assigned to the test question.
	 *
	 * @return The points assigned to the test question.
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Sets the points assigned to the test question.
	 *
	 * @param points The points assigned to the test question.
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * Retrieves the ID of the test question.
	 *
	 * @return The ID of the test question.
	 */
	public String getQuestionID() {
		return questionID;
	}

	/**
	 * Sets the ID of the test question.
	 *
	 * @param questionID The ID of the test question.
	 */
	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}

	/**
	 * Retrieves the text of the test question.
	 *
	 * @return The text of the test question.
	 */
	public String getQuestionText() {
		return questionText;
	}

	/**
	 * Sets the text of the test question.
	 *
	 * @param questionText The text of the test question.
	 */
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	/**
	 * Retrieves the ID of the test associated with the question.
	 *
	 * @return The ID of the test.
	 */
	public String getTestID() {
		return testID;
	}

	/**
	 * Sets the ID of the test associated with the question.
	 *
	 * @param testID The ID of the test.
	 */
	public void setTestID(String testID) {
		this.testID = testID;
	}

	/**
	 * Retrieves the number of the test question.
	 *
	 * @return The number of the test question.
	 */
	public String getQuestionNumber() {
		return questionNumber;
	}

	/**
	 * Sets the number of the test question.
	 *
	 * @param questionNumber The number of the test question.
	 */
	public void setQuestionNumber(String questionNumber) {
		this.questionNumber = questionNumber;
	}

	/**
	 * Retrieves the subject of the test question.
	 *
	 * @return The subject of the test question.
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Sets the subject of the test question.
	 *
	 * @param subject The subject of the test question.
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Retrieves the name of the course associated with the test question.
	 *
	 * @return The name of the course.
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * Sets the name of the course associated with the test question.
	 *
	 * @param courseName The name of the course.
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * Retrieves the author of the test question.
	 *
	 * @return The author of the test question.
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Sets the author of the test question.
	 *
	 * @param author The author of the test question.
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Returns a string representation of the TestQuestion object.
	 *
	 * @return A string representation of the object.
	 */
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
