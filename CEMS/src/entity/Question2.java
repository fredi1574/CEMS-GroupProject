package entity;

public class Question2 {

	protected String id;
	protected String content;
	protected String correctAnswer;
	protected String wrongAnswer1;
	protected String wrongAnswer2;
	protected String wrongAnswer3;
	private String subject;
	private String composer;
	private String courses;
	/**
	 * Constructs a Question2 object with the specified parameters.
	 * 
	 * @param composer       The composer of the question.
	 * @param subject        The subject of the question.
	 * @param content        The content of the question.
	 * @param answer0        The first wrong answer choice.
	 * @param answer1        The second wrong answer choice.
	 * @param answer2        The third wrong answer choice.
	 * @param correctAnswer  The correct answer choice.
	 * @param courses        The courses associated with the question.
	 * @param id             The ID of the question.
	 */
	public Question2(String composer, String subject, String content, String answer0, String answer1, String answer2,
			String correctAnswer, String courses, String id) {
		this.id = id;
		this.content = content;
		this.subject = subject;
		this.courses = courses;
		this.correctAnswer = correctAnswer;
		this.wrongAnswer1 = answer0;
		this.wrongAnswer2 = answer1;
		this.wrongAnswer3 = answer2;
		this.composer = composer;
	}

	// Getters and Setters

	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public String getSubject() {
		return subject;
	}

	public String getCourses() {
		return courses;
	}

	public String getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWrongAnswer1() {
		return wrongAnswer1;
	}

	public String getWrongAnswer2() {
		return wrongAnswer2;
	}

	public String getWrongAnswer3() {
		return wrongAnswer3;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}
}
/* idea
	public void editQuestion(Question2 updatedQuestion) {
		this.subject = updatedQuestion.getSubject();
		this.courses = updatedQuestion.getCourses();
		this.content = updatedQuestion.getContent();
		this.wrongAnswer1 = updatedQuestion.getWrongAnswer1();
		this.wrongAnswer2 = updatedQuestion.getWrongAnswer2();
		this.wrongAnswer3 = updatedQuestion.getWrongAnswer3();
	}

	@Override
	public String toString() {
		return "Composer: " + this.getComposer() + "\nSubject: " + this.getSubject() + "\nContent: " + this.getContent()
				+ "\nWrong Answer 1: " + this.getWrongAnswer1() + "\nWrong Answer 2: " + this.getWrongAnswer2()
				+ "\nWrong Answer 3: " + this.getWrongAnswer3() + "\nCorrect Answer: " + this.getCorrectAnswer()
				+ "\nCourses: " + this.getCourses() + "\nQuestion ID: " + this.getId();
	}
	*/




