package entity;

import java.io.Serializable;

/**
 * Represents an active test entity.
 */
public class ActiveTest implements Serializable {
    private final String id;
    private final int numOfQuestions;
    private final String testDate;
    private final String startingTime;
    private final String testCode;

    /**
     * Constructs an instance of the ActiveTest class.
     *
     * @param id            The ID of the active test.
     * @param numOfQuestions The number of questions in the active test.
     * @param testDate      The date of the active test.
     * @param startingTime  The starting time of the active test.
     * @param testCode      The code of the active test.
     */
    public ActiveTest(String id, int numOfQuestions, String testDate, String startingTime, String testCode) {
        this.id = id;
        this.numOfQuestions = numOfQuestions;
        this.testDate = testDate;
        this.startingTime = startingTime;
        this.testCode = testCode;
    }

    /**
     * Retrieves the ID of the active test.
     *
     * @return The ID of the active test.
     */
    public String getId() {
        return id;
    }

    /**
     * Retrieves the code of the active test.
     *
     * @return The code of the active test.
     */
    public String getTestCode() {
        return testCode;
    }

    /**
     * Retrieves the number of questions in the active test.
     *
     * @return The number of questions in the active test.
     */
    public int getNumOfQuestions() {
        return numOfQuestions;
    }

    /**
     * Retrieves the date of the active test.
     *
     * @return The date of the active test.
     */
    public String getTestDate() {
        return testDate;
    }

    /**
     * Retrieves the starting time of the active test.
     *
     * @return The starting time of the active test.
     */
    public String getStartingTime() {
        return startingTime;
    }
}
