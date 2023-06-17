package entity;

import java.io.Serializable;

/**
 * Represents a file creator entity.
 */
public class FileCreator implements Serializable {

    private static final long serialVersionUID = 1843L;
    private String studentID;
    private String examID;
    private String fileName = null;
    private String examType;
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

    /**
     * Returns the file name.
     *
     * @return The file name.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the file name.
     *
     * @param fileName The file name to set.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Returns the size of the file.
     *
     * @return The size of the file.
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the size of the file.
     *
     * @param size The size of the file to set.
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Returns the byte array of the file.
     *
     * @return The byte array of the file.
     */
    public byte[] getMybytearray() {
        return mybytearray;
    }

    /**
     * Returns the byte at the specified index of the file's byte array.
     *
     * @param i The index of the byte to retrieve.
     * @return The byte at the specified index.
     */
    public byte getMybytearray(int i) {
        return mybytearray[i];
    }

    /**
     * Sets the byte array of the file.
     *
     * @param mybytearray The byte array to set.
     */
    public void setMybytearray(byte[] mybytearray) {
        System.arraycopy(mybytearray, 0, this.mybytearray, 0, mybytearray.length);
    }

    /**
     * Returns the student ID.
     *
     * @return The student ID.
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Sets the student ID.
     *
     * @param studentID The student ID to set.
     */
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    /**
     * Returns the exam ID.
     *
     * @return The exam ID.
     */
    public String getExamID() {
        return examID;
    }

    /**
     * Sets the exam ID.
     *
     * @param examID The exam ID to set.
     */
    public void setExamID(String examID) {
        this.examID = examID;
    }

    /**
     * Returns the exam type.
     *
     * @return The exam type.
     */
    public String getExamType() {
        return examType;
    }

    /**
     * Sets the exam type.
     *
     * @param examType The exam type to set.
     */
    public void setExamType(String examType) {
        this.examType = examType;
    }

    /**
     * Returns the exam subject.
     *
     * @return The exam subject.
     */
    public String getExamSubject() {
        return examSubject;
    }

    /**
     * Sets the exam subject.
     *
     * @param examSubject The exam subject to set.
     */
    public void setExamSubject(String examSubject) {
        this.examSubject = examSubject;
    }

    /**
     * Returns the exam course.
     *
     * @return The exam course.
     */
    public String getExamCourse() {
        return examCourse;
    }

    /**
     * Sets the exam course.
     *
     * @param examCourse The exam course to set.
     */
    public void setExamCourse(String examCourse) {
        this.examCourse = examCourse;
    }

    /**
     * Returns the exam date.
     *
     * @return The exam date.
     */
    public String getExamDate() {
        return examDate;
    }

    /**
     * Sets the exam date.
     *
     * @param examDate The exam date to set.
     */
    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    /**
     * Returns the user type.
     *
     * @return The user type.
     */
    public String getUserType() {
        return userType;
    }
}
