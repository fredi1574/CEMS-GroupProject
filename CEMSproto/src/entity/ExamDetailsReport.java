package entity;

public class ExamDetailsReport {
	
	private String examID;
	private String examSubject;
	private String examCourse;
	private String avg;
	private String median;


	/**
	 * Constructs a ReportExamDetails object with the specified parameters.
	 * 
	 * @param examID       the ID of the exam
	 * @param examSubject  the subject of the exam
	 * @param examCourse   the course of the exam
	 * @param median       the median value of the exam
	 * @param avg          the average value of the exam
	 */
	public ExamDetailsReport(String examID, String examSubject, String examCourse, String median, String avg) {
		super();
		this.examID = examID;
		this.examSubject = examSubject;
		this.examCourse = examCourse;
		this.avg = avg;
		this.median = median;

	}

	/**
	 * Returns the ID of the exam.
	 * 
	 * @return the ID of the exam
	 */
	public String getExamID() {
		return examID;
	}

	/**
	 * Sets the ID of the exam.
	 * 
	 * @param examID the ID of the exam to set
	 */
	public void setExamID(String examID) {
		this.examID = examID;
	}

	/**
	 * Returns the subject of the exam.
	 * 
	 * @return the subject of the exam
	 */
	public String getExamSubject() {
		return examSubject;
	}

	/**
	 * Sets the subject of the exam.
	 * 
	 * @param examSubject the subject of the exam to set
	 */
	public void setExamSubject(String examSubject) {
		this.examSubject = examSubject;
	}

	/**
	 * Returns the course of the exam.
	 * 
	 * @return the course of the exam
	 */
	public String getExamCourse() {
		return examCourse;
	}

	/**
	 * Sets the course of the exam.
	 * 
	 * @param examCourse the course of the exam to set
	 */
	public void setExamCourse(String examCourse) {
		this.examCourse = examCourse;
	}

	/**
	 * Returns the average value of the exam.
	 * 
	 * @return the average value of the exam
	 */
	public String getAvg() {
		return avg;
	}

	/**
	 * Sets the average value of the exam.
	 * 
	 * @param avg the average value of the exam to set
	 */
	public void setAvg(String avg) {
		this.avg = avg;
	}

	/**
	 * Returns the median value of the exam.
	 * 
	 * @return the median value of the exam
	 */
	public String getMedian() {
		return median;
	}

	/**
	 * Sets the median value of the exam.
	 * 
	 * @param median the median value of the exam to set
	 */
	public void setMedian(String median) {
		this.median = median;
	}
}
