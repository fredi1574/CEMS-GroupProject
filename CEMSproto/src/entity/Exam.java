package entity;

import java.util.ArrayList;

public class Exam {

    String id;
    String dateOfExam;
    String timeInMinutes;
    String author;
    String subject;
    String course;
    String startingTime;
    String teacherComment = " ";
    ArrayList<ExamQuestion> questions = new ArrayList<ExamQuestion>();

    /**
     * Constructs an Exam object with the provided details.
     *
     * @param id           The ID of the exam.
     * @param dateOfExam   The date of the exam.
     * @param timeInMinutes The duration of the exam in minutes.
     * @param author       The author of the exam.
     * @param subject      The subject of the exam.
     * @param course       The course of the exam.
     * @param questions    The list of questions in the exam.
     * @param startingTime The starting time of the exam.
     */
    public Exam(String id, String dateOfExam, String timeInMinutes, String author, String subject, String course, ArrayList<ExamQuestion> questions, String startingTime) {
        this.id = id;
        this.dateOfExam = dateOfExam;
        this.timeInMinutes = timeInMinutes;
        this.author = author;
        this.subject = subject;
        this.course = course;
        this.startingTime = startingTime;
        this.questions.addAll(questions);
        if (questions.size() != 0)
            this.teacherComment = questions.get(0).getTeacherComment();
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateOfExam() {
        return dateOfExam;
    }

    public void setDateOfExam(String dateOfExam) {
        this.dateOfExam = dateOfExam;
    }

    public String getTimeInMinutes() {
        return timeInMinutes;
    }

    public void setTimeInMinutes(String timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public ArrayList<ExamQuestion> getQuestions() {
        return this.questions;
    }

    public void setQuestions(ArrayList<ExamQuestion> questions) {
        this.questions = questions;
    }

    /**
     * Returns a string representation of the Exam object.
     *
     * @return The string representation of the Exam object.
     */

	public String toString() {
		return "examID: " + this.id + "dateOfExam: " + this.dateOfExam + "examDuration: " + this.timeInMinutes + "examComposer: " + this.author + "examSubject: " + this.subject + "examCourse: " + this.course + "startingTime: " + this.startingTime;
	}

	public String getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(String startingTime) {
		this.startingTime = startingTime;
	}

	public String getTeacherComment() {
		return teacherComment;
	}
}
