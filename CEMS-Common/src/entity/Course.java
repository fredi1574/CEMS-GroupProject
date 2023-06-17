package entity;

import java.io.Serializable;

/**
 * Represents a course entity.
 */
public class Course implements Serializable {

    private final String subjectID;
    private final String courseID;
    private final String subjectName;
    private final String courseName;

    /**
     * Constructs a Course object with the specified parameters.
     *
     * @param subjectID   The ID of the subject.
     * @param courseID    The ID of the course.
     * @param subjectName The name of the subject.
     * @param courseName  The name of the course.
     */
    public Course(String subjectID, String courseID, String subjectName, String courseName) {
        this.subjectID = subjectID;
        this.courseID = courseID;
        this.subjectName = subjectName;
        this.courseName = courseName;
    }

    /**
     * Returns the subject ID of the course.
     *
     * @return The subject ID of the course.
     */
    public String getSubjectID() {
        return subjectID;
    }

    /**
     * Returns the course ID.
     *
     * @return The course ID.
     */
    public String getCourseID() {
        return courseID;
    }

    /**
     * Returns the name of the subject.
     *
     * @return The name of the subject.
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * Returns the name of the course.
     *
     * @return The name of the course.
     */
    public String getCourseName() {
        return courseName;
    }

    @Override
    public String toString() {
        return "Course{" +
                "subjectID='" + subjectID + '\'' +
                ", courseID='" + courseID + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
