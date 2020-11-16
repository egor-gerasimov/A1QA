package testRail;

import org.testng.ITestResult;

public enum TestRailStatus {

    PASSED(1), SKIPPED(4), FAILED(5), UNTESTED(3);

    private final int id;

    TestRailStatus(int id) {
        this.id = id;
    }
    
    static TestRailStatus getTestRailStatus(int statusId) {
        switch (statusId) {
            case ITestResult.SUCCESS:
                return PASSED;
            case ITestResult.FAILURE:
                return FAILED;
            case ITestResult.SKIP:
                return SKIPPED;
            default:
                return UNTESTED;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return this.toString();
    }
}
