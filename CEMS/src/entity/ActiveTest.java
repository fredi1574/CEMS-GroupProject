package entity;
/**
 * Represents an active test entity.
 */
public class ActiveTest {
    private String testID;
    private String testDuration;
    private String testCode;
    private String testType;

    /**
     * Constructs a new ActiveTest object.
     *
     * @param testID The ID of the test.
     * @param testDuration The duration of the test.
     * @param testCode The code associated with the test.
     * @param testType The type of the test.
     */
    public ActiveTest(String testID, String testDuration, String testCode, String testType) {
        this.testID = testID;
        this.testDuration = testDuration;
        this.testCode = testCode;
        this.testType = testType;
    }

    /**
     * Retrieves the ID of the test.
     *
     * @return The test ID.
     */
    public String getTestID() {
        return testID;
    }

    /**
     * Retrieves the duration of the test.
     *
     * @return The test duration.
     */
    public String getTestDuration() {
        return testDuration;
    }

    /**
     * Retrieves the code associated with the test.
     *
     * @return The test code.
     */
    public String getTestCode() {
        return testCode;
    }

    /**
     * Retrieves the type of the test.
     *
     * @return The test type.
     */
    public String getTestType() {
        return testType;
    }
}
