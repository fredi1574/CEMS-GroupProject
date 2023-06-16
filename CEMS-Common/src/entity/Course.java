package entity;


import java.io.Serializable;

public class Course implements Serializable {

    private final String subjectID;
    private final String courseID;
    private final String subjectName;
    private final String courseName;

    /**
     * Constructs a Course object with the specified parameters.
     * 
     * @param subjectID   the ID of the subject
     * @param courseID    the ID of the course
     * @param subjectName the name of the subject
     * @param courseName  the name of the course
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
     * @return the subject ID of the course
     */
    public String getSubjectID() {
        return subjectID;
    }

    public String getCourseID() {
        return courseID;
    }


    public String getSubjectName() {
        return subjectName;
    }


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
