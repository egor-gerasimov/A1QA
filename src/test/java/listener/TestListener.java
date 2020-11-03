package listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import testRail.TestRailManager;
import testRail.utils.ImageUtils;

public class TestListener implements ITestListener {

    private TestRailManager testRailManager;

    @Override
    public void onStart(ITestContext context) {
        testRailManager = new TestRailManager(context.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ImageUtils.saveScreenshot(result.getMethod().getMethodName());
        testRailManager.addResult(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ImageUtils.saveScreenshot(result.getMethod().getMethodName());
        testRailManager.addResult(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ImageUtils.saveScreenshot(result.getMethod().getMethodName());
        testRailManager.addResult(result);
    }

    @Override
    public void onFinish(ITestContext context) {
        testRailManager.deleteAll(context);
    }
}
