package entity;

import java.io.Serializable;
import java.util.Observable;

/**
 * Represents an active test entity.
 */
public class ActiveTest extends Observable implements Serializable {
    private String id;
    private int numOfQuestions;
    private String testDate;
    private String startingTime;
    private String testCode;

    public ActiveTest(String id, int numOfQuestions, String testDate, String startingTime, String testCode) {
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

    public int getNumOfQuestions() {
        return numOfQuestions;
    }

    public String getTestDate() {
        return testDate;
    }

    public String getStartingTime() {
        return startingTime;
    }

}

