package application.ViewReportsScreen;

public class Report {
    private int year;
    private String Semester;
    private String subject;
    private String course;
    private int testID;

    public Report(int year, String semester, String subject, String course, int testID) {
        this.year = year;
        this.Semester = semester;
        this.subject = subject;
        this.course = course;
        this.testID = testID;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSemester() {
        return Semester;
    }

    public void setSemester(String semester) {
        Semester = semester;
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

    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }
}
