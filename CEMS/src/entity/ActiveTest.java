package entity;

import java.io.Serializable;
import java.util.Observable;

/**
 * Represents an active test entity.
 */
public class ActiveTest extends Observable implements Serializable {
    private String id;
    private String numOfQuestions;
    private String testDate;
    private String startingTime;
    private String testCode;

    public ActiveTest(String id, String numOfQuestions, String testDate, String startingTime, String testCode) {
        this.id = id;
        this.numOfQuestions = numOfQuestions;
        this.testDate = testDate;
        this.startingTime = startingTime;
        this.testCode = testCode;
    }

    public String getId() {
        return id;
    }
    public String getTestCode(){return testCode; }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumOfQuestions() {
        return numOfQuestions;
    }

    public void setNumOfQuestions(String numOfQuestions) {
        this.numOfQuestions = numOfQuestions;
    }
    public void setTestCode(String numOfQuestions) {
        this.testCode = testCode;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

}

