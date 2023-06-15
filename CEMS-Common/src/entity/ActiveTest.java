package entity;

import java.io.Serializable;
import java.util.Observable;

/**
 * Represents an active test entity.
 */
public class ActiveTest implements Serializable {
    private final String id;
    private final int numOfQuestions;
    private final String testDate;
    private final String startingTime;
    private final String testCode;

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

