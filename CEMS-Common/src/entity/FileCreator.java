package entity;

import java.io.Serializable;
import java.lang.reflect.Array;

public class FileCreator implements Serializable {

    private static final long serialVersionUID = 1843L;
    private String studentID = null;
    private String examID = null;
    private String fileName = null;
    private String examType = null;
    private String examSubject;
    private String examCourse;
    private String examDate;
    private int size = 0;
    public byte[] mybytearray;
    private final String userType = null;

    /**
     * Initializes the byte array with the given size.
     *
     * @param size The size of the byte array.
     */
    public void initArray(int size) {
        mybytearray = new byte[size];
    }

    /**
     * Constructs a FileCreator object with the provided student ID, exam ID, and exam type.
     *
     * @param studentID The ID of the student.
     * @param examID    The ID of the exam.
     * @param examType  The type of the exam.
     */
    public FileCreator(String studentID, String examID, String examType) {
        this.studentID = studentID;
        this.examID = examID;
        this.examType = examType;
    }

    // Getters and Setters

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public byte[] getMybytearray() {
        return mybytearray;
    }

    public byte getMybytearray(int i) {
        return mybytearray[i];
    }

    public void setMybytearray(byte[] mybytearray) {
        System.arraycopy(mybytearray, 0, this.mybytearray, 0, mybytearray.length);
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getExamID() {
        return examID;
    }

    public void setExamID(String examID) {
        this.examID = examID;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getExamSubject() {
        return examSubject;
    }

    public void setExamSubject(String examSubject) {
        this.examSubject = examSubject;
    }

    public String getExamCourse() {
        return examCourse;
    }

    public void setExamCourse(String examCourse) {
        this.examCourse = examCourse;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getUserType() {
        return userType;
    }
}
   
