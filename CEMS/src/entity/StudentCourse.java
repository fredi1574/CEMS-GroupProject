package entity;

import java.io.Serializable;

public class StudentCourse  implements Serializable {
    private String studentID;
    private String course;

    // Constructors
    public StudentCourse() {
        // Default constructor
    }

    public StudentCourse(String studentID, String course) {
        this.studentID = studentID;
        this.course = course;
    }

    // Getters and Setters
    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    // Optional: Override toString() method for better representation
    @Override
    public String toString() {
        return "StudentCourse{" +
                "studentID='" + studentID + '\'' +
                ", course='" + course + '\'' +
                '}';
    }
}
