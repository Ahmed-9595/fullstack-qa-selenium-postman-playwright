package tests.listener;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.testBase.BaseTest;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class TestListener implements ITestListener {


    @Override
    public void onTestFailure(ITestResult result){
        // فقط لو الـ Screenshot لم يُلتقط بالفعل من SoftAssert
        Object currentClass = result.getInstance();
        WebDriver driver = ((BaseTest) currentClass).getDriver();
            takeScreenShot(driver ,result.getName());
    }
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getName());
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println(" Test Skipped: " + result.getName());
    }


    public static void takeScreenShot(WebDriver driver , String testName) {

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String unique = UUID.randomUUID().toString();
        String path = "target/screenshots/" + testName+unique + ".png";

        try {
            FileUtils.copyFile(screenshot, new File(path));
            System.out.println(" Screenshot saved: " + path);
        } catch (IOException e) {
            System.err.println(" Error capturing screenshot: " + e.getMessage());
        }
    }

    public static  void cleanScreenShotFolder(){
        File file = new File("target/screenshots/");
        if (!file.exists()){
          System.out.println("Screenshot folder does not exist");
        }

        try {
            FileUtils.deleteDirectory(file);
            System.out.println(" Screenshot folder cleaned: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println(" Failed to clean screenshot folder: " + e.getMessage());
        }


    }

}