package utils;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {
    public static int successnumber=0;
    public static int failurenumber=0;
    public static int totalnumber=0;
    public static int skippednumber=0;


    @Override
    public void onTestFailure(ITestResult arg0) {
        failurenumber+=1;

    }

    @Override
    public void onTestSkipped(ITestResult arg0) {
        skippednumber+=1;

    }

    @Override
    public void onTestStart(ITestResult arg0) {
        totalnumber+=1;

    }

    @Override
    public void onTestSuccess(ITestResult arg0) {
        successnumber+=1;

    }


}
