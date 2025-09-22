package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {
    public void onStart(ITestContext context) {
        System.out.println(context.getClass());
    }
    public void onTestSuccess(ITestResult result) {

    }
    public void onTestFailure(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + "is failed");

    }
    public void onFinish(ITestContext context) {

    }
}
