package org.rahulshettyacademy.TestUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.AppiumUtils;

import java.io.IOException;

public class Listeners extends AppiumUtils implements ITestListener {
   ExtentReports extentReports =ExtentReporterNG.getReporterObject();
    ExtentTest test;
    //AppiumDriver driver;
   public Listeners(AppiumDriver driver){
        super(driver);
       // this.driver =driver;
    }
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getName());
         test = extentReports.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getName());
        test.log(Status.PASS,"Test Passed");
    }


        @Override
        public void onTestFailure(ITestResult result) {
            System.out.println("Test Failed: " + result.getName());

            test.fail(result.getThrowable());

            try {
                AppiumDriver driver = (AppiumDriver) result.getTestClass()
                        .getRealClass()
                        .getField("driver")
                        .get(result.getInstance());

                String screenshotPath = getScreenshotPath(
                        result.getMethod().getMethodName(),
                        driver
                );

                test.addScreenCaptureFromPath(screenshotPath);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}