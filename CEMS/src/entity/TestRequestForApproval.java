package entity;

public class TestRequestForApproval {
	
	private String newDuration;
	private String explanation;
	private String id;
	private String subject;
	private String course;
	/**
	 * Constructs an ExamRequestForApproval object with the specified parameters.
	 * 
	 * @param id           The ID of the exam.
	 * @param subject      The subject of the exam.
	 * @param course       The course of the exam.
	 * @param newDuration  The new duration of the exam.
	 * @param explanation  The explanation for the duration change.
	 */
	public TestRequestForApproval(String id, String subject, String course, String newDuration, String explanation) {
		this.newDuration = newDuration;
		this.explanation = explanation;
		this.id = id;
		this.subject = subject;
		this.course = course;
	}
	
	// Getters and Setters

	public String getNewDuration() {
		return newDuration;
	}

	public void setNewDuration(String newDuration) {
		this.newDuration = newDuration;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "ExamRequestForApproval [newDuration=" + newDuration + ", explanation=" + explanation + ", id=" + id
				+ ", subject=" + subject + ", course=" + course + "]";
	}
}
