package entity;

public class ExamForStudent {

	private String studentID;
	private String examID;
	private String examType;
	private String grade;
	private String approved;
	private String noteForStudent;
	private String reasonForGradeChange;
	private String[] studentAnswers;

	/**
	 * Constructs a StudentExam object with the specified parameters.
	 * 
	 * @param studentID             the ID of the student
	 * @param examID                the ID of the exam
	 * @param examType              the type of the exam
	 * @param grade                 the grade of the student
	 * @param approved              the approval status of the grade
	 * @param noteForStudent        the note for the student
	 * @param reasonForGradeChange  the reason for grade change
	 * @param studentAnswers        the student's answers
	 */
	public ExamForStudent(String studentID, String examID, String examType, String grade, String approved,
			String noteForStudent, String reasonForGradeChange, String[] studentAnswers) {
		this.studentID = studentID;
		this.examID = examID;
		this.examType = examType;
		this.grade = grade;
		this.approved = approved;
		this.noteForStudent = noteForStudent;
		this.reasonForGradeChange = reasonForGradeChange;
		this.studentAnswers = studentAnswers;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getExamID() {
		return examID;
	}

	public void setExamID(String examID) {
		this.examID = examID;
	}

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public String getNoteForStudent() {
		return noteForStudent;
	}

	public void setNoteForStudent(String noteForStudent) {
		this.noteForStudent = noteForStudent;
	}

	public String getReasonForGradeChange() {
		return reasonForGradeChange;
	}

	public void setReasonForGradeChange(String reasonForGradeChange) {
		this.reasonForGradeChange = reasonForGradeChange;
	}

	public String[] getStudentAnswers() {
		return studentAnswers;
	}

	public void setStudentAnswers(String[] studentAnswers) {
		this.studentAnswers = studentAnswers;
	}
}
