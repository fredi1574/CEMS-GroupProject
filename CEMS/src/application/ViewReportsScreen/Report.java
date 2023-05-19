package application.ViewReportsScreen;

public class Report {
    private int year;
    private String Semester;
    private String subject;
    private String course;
    private int test_id;

    public Report(int year, String semester, String subject, String course, int test_id) {
        this.year = year;
        this.Semester = semester;
        this.subject = subject;
        this.course = course;
        this.test_id = test_id;
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

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }
}
