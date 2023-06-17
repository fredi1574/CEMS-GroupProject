package entity;

import java.io.Serializable;
/**
 * Represents a request in a test .
 */
public class TestRequestForApproval implements Serializable {

	private String newDuration;
	private String explanation;
	private String id;
	private String subject;
	private String course;
	private final String author;

	/**
	 * Constructs a TestRequestForApproval object with the specified parameters.
	 *
	 * @param id          The ID of the test.
	 * @param subject     The subject of the test.
	 * @param course      The course of the test.
	 * @param newDuration The new duration of the test.
	 * @param explanation The explanation for the duration change.
	 * @param author      The author of the test.
	 */
	public TestRequestForApproval(String id, String subject, String course, String newDuration, String explanation, String author) {
		this.newDuration = newDuration;
		this.explanation = explanation;
		this.id = id;
		this.subject = subject;
		this.course = course;
		this.author = author;
	}

	/**
	 * Retrieves the new duration of the test.
	 *
	 * @return The new duration of the test.
	 */
	public String getNewDuration() {
		return newDuration;
	}

	/**
	 * Retrieves the author of the test.
	 *
	 * @return The author of the test.
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Sets the new duration of the test.
	 *
	 * @param newDuration The new duration of the test.
	 */
	public void setNewDuration(String newDuration) {
		this.newDuration = newDuration;
	}

	/**
	 * Retrieves the explanation for the duration change.
	 *
	 * @return The explanation for the duration change.
	 */
	public String getExplanation() {
		return explanation;
	}

	/**
	 * Sets the explanation for the duration change.
	 *
	 * @param explanation The explanation for the duration change.
	 */
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	/**
	 * Retrieves the ID of the test.
	 *
	 * @return The ID of the test.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the ID of the test.
	 *
	 * @param id The ID of the test.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retrieves the subject of the test.
	 *
	 * @return The subject of the test.
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Sets the subject of the test.
	 *
	 * @param subject The subject of the test.
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Retrieves the course of the test.
	 *
	 * @return The course of the test.
	 */
	public String getCourse() {
		return course;
	}

	/**
	 * Sets the course of the test.
	 *
	 * @param course The course of the test.
	 */
	public void setCourse(String course) {
		this.course = course;
	}

	/**
	 * Returns a string representation of the TestRequestForApproval object.
	 *
	 * @return A string representation of the object.
	 */
	@Override
	public String toString() {
		return "TestRequestForApproval [newDuration=" + newDuration + ", explanation=" + explanation + ", id=" + id
				+ ", subject=" + subject + ", course=" + course + "]";
	}
}
