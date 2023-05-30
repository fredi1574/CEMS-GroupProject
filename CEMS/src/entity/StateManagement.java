package entity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StateManagement {
    public Course course;
    public String testNum;
    public String year;
    public String session;
    public String semester;
    public String testID;
    public String studentComment;
    public String teacherComment;
    public String durationTimeOfTest;
    ObservableList<TestQuestion> testQuestions = FXCollections.observableArrayList();
    public Test newTest;
    public int totalRemainingPoints;
    public TestTypeEnum testType = TestTypeEnum.C;

    private  static StateManagement instance = null;

    public static StateManagement getInstance(){
        if(instance ==  null){
            instance = new StateManagement();
        }
        return instance;
    }

    private StateManagement(){

       setTestID("-1"); //sets default empty value for testID on StateManagement initialization
       totalRemainingPoints = 100;
    }
    public void setDataOfTest(Course course, String testNum, String testID, String durationTimeOfTest,
                              String year, String session, String semester) {
        this.durationTimeOfTest = durationTimeOfTest;
        this.course = course;
        this.testID = testID;
        this.testNum = testNum;
        this.year = year;
        this.session = session;
        this.semester = semester;


    }
    public void SaveTest(){
        newTest = new Test(testNum,getTestID(),null,
                durationTimeOfTest,course.getCourseName(),getTeacherComment(),testType,getStudentComment(),
                    course.getSubjectName(),year,session,semester);
    }

    public void SaveTestQuestions() {

    }
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getStudentComment() {
        return studentComment;
    }

    public void setStudentComment(String studentComment) {
        this.studentComment = studentComment;
    }

    public String getTeacherComment() {
        return teacherComment;
    }

    public void setTeacherComment(String teacherComment) {
        this.teacherComment = teacherComment;
    }

    public String getTestNum() {
        return testNum;
    }
    public int getTotalRemainingPoints() {
        return totalRemainingPoints;
    }

    //used when removing a question from the selected questions table
    public void addTotalRemainingPoints(int totalRemainingPoints) {
        this.totalRemainingPoints += totalRemainingPoints;
    }

    //used when picking a question for the selected questions table
    public void subtractTotalRemainingPoints(int totalRemainingPoints) {
        this.totalRemainingPoints -= totalRemainingPoints;
    }
    public void setTestNum(String testNum) {
        this.testNum = testNum;
    }

    public String getTestID() {
        return testID;
    }

    public void setTestID(String testID) {
        this.testID = testID;
    }

    public String getDurationTimeOfTest() {
        return durationTimeOfTest;
    }

    public void setDurationTimeOfTest(String durationTimeOfTest) {
        this.durationTimeOfTest = durationTimeOfTest;
    }

    public ObservableList<TestQuestion> getTestQuestions() {
        return testQuestions;
    }

    //adds a single test question to the testQuestions observable list
    public void setTestQuestions(TestQuestion question) {
        if(!testQuestions.contains(question))
            this.testQuestions.add(question);
    }

    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * resets all test-state related data
     * used when a new test has been created
     */
    public void clearTestState() { //TODO: alter the function if state management is utilized in more screens
        instance = null;
//        year = null;
//        session = null;
//        semester = null;
//        testQuestions.clear();
//        course.setSubject(null);
//        course.setCourse(null);
//        course.setCourseID(null);
//        course.setSubjectID(null);
//        testNum = null;
//        teacherComment = null;
//        studentComment = null;
//        durationTimeOfTest = null;
//        totalRemainingPoints = 100;

    }
    @Override
    public String toString() {
        return "StateManagement{" +
                "course=" + course +
                ", testNum='" + testNum + '\'' +
                ", testID='" + testID + '\'' +
                ", studentComment='" + studentComment + '\'' +
                ", teacherComment='" + teacherComment + '\'' +
                ", durationTimeOfTest='" + durationTimeOfTest + '\'' +
                ", testQuestions=" + testQuestions +
                '}';
    }
}