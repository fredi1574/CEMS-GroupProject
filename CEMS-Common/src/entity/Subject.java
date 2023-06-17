package entity;

import java.io.Serializable;

/**
 * The Subject class represents a subject in an educational system.
 * It contains information about the subject's ID and name.
 */
public class Subject implements Serializable {
    private final String subjectID;
    private final String subjectName;

    /**
     * Constructs a Subject object with the specified subject ID and name.
     *
     * @param subjectID   The ID of the subject.
     * @param subjectName The name of the subject.
     */
    public Subject(String subjectID, String subjectName) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
    }

    /**
     * Returns the ID of the subject.
     *
     * @return The ID of the subject.
     */
    public String getSubjectID() {
        return subjectID;
    }

    /**
     * Returns the name of the subject.
     *
     * @return The name of the subject.
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * Returns a string representation of the Subject object.
     *
     * @return A string representation of the Subject object.
     */
    @Override
    public String toString() {
        return "Subject{" +
                "subjectID='" + subjectID + '\'' +
                ", subjectName='" + subjectName + '\'' +
                '}';
    }
}
