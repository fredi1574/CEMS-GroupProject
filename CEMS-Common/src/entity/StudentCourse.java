package entity;

import java.io.Serializable;

/**
 * Represents a student's enrolled course.
 */
public class StudentCourse implements Serializable {
    private String studentID;
    private String course;

    /**
     * Constructs a StudentCourse object with the provided student ID and course.
     *
     * @param studentID The ID of the student.
     * @param course    The enrolled course.
     */
    public StudentCourse(String studentID, String course) {
        this.studentID = studentID;
        this.course = course;
    }

    /**
     * Returns the ID of the student.
     *
     * @return The ID of the student.
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Sets the ID of the student.
     *
     * @param studentID The ID of the student.
     */
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    /**
     * Returns the enrolled course.
     *
     * @return The enrolled course.
     */
    public String getCourse() {
        return course;
    }

    /**
     * Sets the enrolled course.
     *
     * @param course The enrolled course.
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * Returns a string representation of the StudentCourse object.
     *
     * @return A string representation of the StudentCourse object.
     */
    @Override
    public String toString() {
        return "StudentCourse{" +
                "studentID='" + studentID + '\'' +
                ", course='" + course + '\'' +
                '}';
    }
}
