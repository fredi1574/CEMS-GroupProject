package entity;

public class StudentGradeDetails extends Student {

	private String dateOfExam;
	private String examID;
	private String grade;
	private String course;
	private String approve;
	private String notes;
	private String changeReason;

	/**
	 * Constructs a StudentGradeDetails object with the specified parameters.
	 * 
	 * @param id              the ID of the student
	 * @param firstName       the first name of the student
	 * @param lastName        the last name of the student
	 * @param userName        the username of the student
	 * @param password        the password of the student
	 * @param email           the email address of the student
	 * @param role            the role of the student
	 * @param dateOfExam      the date of the exam
	 * @param examID          the ID of the exam
	 * @param grade           the grade of the student
	 * @param course          the course of the student
	 * @param approve         the approval status of the grade
	 * @param notes           the notes for the student
	 * @param changeReason    the reason for grade change
	 */
	public StudentGradeDetails(String id, String firstName, String lastName, String userName, String password,
			String email, String role, String dateOfExam, String examID, String grade, String course, String approve, String notes, String changeReason) {
		super(id, firstName, lastName, userName, password, email, role);
		this.dateOfExam = dateOfExam;
		this.examID = examID;
		this.grade = grade;
		this.course = course;
		this.approve = approve;
		this.notes = notes;
		this.changeReason = changeReason;
	}

	public String getDateOfExam() {
		return dateOfExam;
	}

	public void setDateOfExam(String dateOfExam) {
		this.dateOfExam = dateOfExam;
	}

	public String getExamID() {
		return examID;
	}

	public void setExamID(String examID) {
		this.examID = examID;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getApprove() {
		return approve;
	}

	public void setApprove(String approve) {
		this.approve = approve;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}
}
