package testComponenets;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import commons.AbstractComponents;
import commons.ExtentReportNG;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners implements ITestListener {

    ExtentReportNG extentReports = new ExtentReportNG();
    ExtentReports report = extentReports.config();
    ExtentTest test;
    ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    public void onTestStart(ITestResult result) {
        test = report.createTest(result.getMethod().getMethodName());
        extentTestThreadLocal.set(test);
    }

    public void onTestSuccess(ITestResult result) {
        extentTestThreadLocal.get().log(Status.PASS,"TEST PASSED");
    }

    public void onTestFailure(ITestResult result) {
        extentTestThreadLocal.get().fail(result.getThrowable());

        //Take a screenshot
        WebDriver driver;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        AbstractComponents abstractComponents = new AbstractComponents(driver);

        try {
            abstractComponents.getScreenShot(result.getMethod().getMethodName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onTestSkipped(ITestResult result) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    public void onStart(ITestContext context) {
    }

    public void onFinish(ITestContext context) {
        report.flush();
    }

}
