package entity;

import java.io.Serializable;
import java.util.Observable;

/**
 * Represents an active test entity.
 */
public class ActiveTest extends Observable implements Serializable {
    private String id;
    private String testDuration;
    private String testCode;
    private TestTypeEnum testType;

    /**
     * Constructs a new ActiveTest object.
     *
     * @param id The ID of the test.
     * @param testDuration The duration of the test.
     * @param testCode The code associated with the test.
     * @param testType The type of the test.
     */
    public ActiveTest(String id, String testDuration, String testCode, TestTypeEnum testType) {
        this.id = id;
        this.testDuration = testDuration;
        this.testCode = testCode;
        this.testType = testType;
    }

    /**
     * Retrieves the ID of the test.
     *
     * @return The test ID.
     */
    public String getId() {
        return id;
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
    public TestTypeEnum getTestType() {
        return testType;
    }

}
