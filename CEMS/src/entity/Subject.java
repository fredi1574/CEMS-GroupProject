package entity;

import java.io.Serializable;
import java.util.Observable;

public class Subject extends Observable implements Serializable {
    private String subjectID;
    private String subjectName;

    public Subject(String subjectID, String subjectName) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
    }
    @Override
    public String toString() {
        return "Subject{" +
                "subjectID='" + subjectID + '\'' +
                ", subjectName='" + subjectName + '\'' +
                '}';
    }
    public String getSubjectID() {
        return subjectID;
    }


    public String getSubjectName() {
        return subjectName;
    }

}

