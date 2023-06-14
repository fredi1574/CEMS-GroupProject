package util;

import entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StateManagement {
    private static StateManagement instance = null;
    //Test parameters
    public Course course;
    public String testNum;
    public String year;
    public String session;
    public String semester;
    public String testID;
    public String studentID;
    public String studentComment;
    public String teacherComment;
    public String testDuration;

    public String subjectID;
    public Test newTest;
    public ActiveTest currentActivetest;
    public int totalRemainingPoints;

    public TestTypeEnum testType = TestTypeEnum.C;
    public String previousScreenPath = PathConstants.mainMenuPath;
    ObservableList<TestForApproval> testForApproval = FXCollections.observableArrayList();
    ObservableList<TestQuestion> testQuestions = FXCollections.observableArrayList();

    private StateManagement() {

        setTestID("-1"); //sets default empty value for testID on StateManagement initialization
        totalRemainingPoints = 100;
    }

    public static StateManagement getInstance() {
        if (instance == null) {
            instance = new StateManagement();
        }
        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }

    public void setDataOfTest(Course course, String testNum, String testID) {
        this.course = course;
        this.testID = testID;
        this.testNum = testNum;
    }

//    public void SaveTest() {
//        newTest = new Test(testNum, getTestID(), Client.user.getFullName(),
//                testDuration, course.getCourseName(), getTeacherComment(), testType, getStudentComment(),
//                course.getSubjectName(), year, session, semester);
//    }

    public ObservableList<TestForApproval> getTestForApproval() {
        return testForApproval;
    }

    public void setTestForApproval(ObservableList<TestForApproval> testForApproval) {
        this.testForApproval = testForApproval;
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

    public void setTestNum(String testNum) {
        this.testNum = testNum;
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

    public String getTestID() {
        return testID;
    }

    public void setTestID(String testID) {
        this.testID = testID;
    }

    public String getTestDuration() {
        return testDuration;
    }

    public void setTestDuration(String testDuration) {
        this.testDuration = testDuration;
    }

    public ObservableList<TestQuestion> getTestQuestions() {
        return testQuestions;
    }

    //adds a single test question to the testQuestions observable list
    public void setTestQuestions(TestQuestion question) {
        this.testQuestions.add(question);
    }

    public void clearTestQuestions() {
        this.testQuestions.clear();
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


    public ActiveTest getCurrentActivetest() {
        return currentActivetest;
    }

    public void setCurrentActivetest(ActiveTest currentActivetest) {
        this.currentActivetest = currentActivetest;
    }

    public String getPreviousScreenPath() {
        return previousScreenPath;
    }

    public void setPreviousScreenPath(String previousScreenPath) {
        this.previousScreenPath = previousScreenPath;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getSubjectID() {
        return subjectID;
    }
    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }
    public TestTypeEnum getTestType() {
        return testType;
    }

    public void setTestType(TestTypeEnum testType) {
        this.testType = testType;
    }

    /**
     * resets all test-state related data
     * used when a new test has been created
     */

    @Override
    public String toString() {
        return "StateManagement{" +
                "course=" + course +
                ", testNum='" + testNum + '\'' +
                ", testID='" + testID + '\'' +
                ", studentComment='" + studentComment + '\'' +
                ", teacherComment='" + teacherComment + '\'' +
                ", testDuration='" + testDuration + '\'' +
                ", testQuestions=" + testQuestions +
                '}';
    }
}
