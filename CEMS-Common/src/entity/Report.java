package entity;

/**
 * Represents a report entity.
 */
public class Report {
    private int year;
    private String semester;
    private String subject;
    private String course;
    private int testID;

    /**
     * Constructs a Report object with the provided parameters.
     *
     * @param year     The year of the report.
     * @param semester The semester of the report.
     * @param subject  The subject of the report.
     * @param course   The course of the report.
     * @param testID   The test ID associated with the report.
     */
    public Report(int year, String semester, String subject, String course, int testID) {
        this.year = year;
        this.semester = semester;
        this.subject = subject;
        this.course = course;
        this.testID = testID;
    }

    /**
     * Returns the year of the report.
     *
     * @return The year of the report.
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the year of the report.
     *
     * @param year The year of the report.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Returns the semester of the report.
     *
     * @return The semester of the report.
     */
    public String getSemester() {
        return semester;
    }

    /**
     * Sets the semester of the report.
     *
     * @param semester The semester of the report.
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }

    /**
     * Returns the subject of the report.
     *
     * @return The subject of the report.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject of the report.
     *
     * @param subject The subject of the report.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Returns the course of the report.
     *
     * @return The course of the report.
     */
    public String getCourse() {
        return course;
    }

    /**
     * Sets the course of the report.
     *
     * @param course The course of the report.
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * Returns the test ID associated with the report.
     *
     * @return The test ID associated with the report.
     */
    public int getTestID() {
        return testID;
    }

    /**
     * Sets the test ID associated with the report.
     *
     * @param testID The test ID associated with the report.
     */
    public void setTestID(int testID) {
        this.testID = testID;
    }
}
