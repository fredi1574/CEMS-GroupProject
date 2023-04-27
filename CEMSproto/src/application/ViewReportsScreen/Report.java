package application.ViewReportsScreen;

public class Report {
    private int year;
    private char Semester;
    private String subject;
    private String course;
    private int testId;

    public Report(int year, char semester, String subject, String course, int testId) {
        this.year = year;
        Semester = semester;
        this.subject = subject;
        this.course = course;
        this.testId = testId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public char getSemester() {
        return Semester;
    }

    public void setSemester(char semester) {
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

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }
}
