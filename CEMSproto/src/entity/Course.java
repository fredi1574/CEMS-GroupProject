package entity;


public class Course {

    private String subjectID;
    private String courseID;
    private String subjectName;
    private String courseName;

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

    /**
     * Sets the subject ID of the course.
     * 
     * @param subjectID the subject ID to set
     */
    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    /**
     * Returns the course ID of the course.
     * 
     * @return the course ID of the course
     */
    public String getCourseID() {
        return courseID;
    }

    /**
     * Sets the course ID of the course.
     * 
     * @param courseID the course ID to set
     */
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    /**
     * Returns the subject name of the course.
     * 
     * @return the subject name of the course
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * Sets the subject name of the course.
     * 
     * @param subjectName the subject name to set
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * Returns the course name.
     * 
     * @return the course name
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Sets the course name.
     * 
     * @param courseName the course name to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

}
