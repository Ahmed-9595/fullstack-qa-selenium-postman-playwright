package tests.testBase;

import com.ElectroPI.qa.factoryPage.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import tests.listener.TestListener;
@Listeners({TestListener.class})

public class BaseTest {
    private WebDriver driver;

    private static boolean isScreenshotFolderCleaned = false;


    @BeforeSuite
    public void beforeSuite() {
        // Sync to avoid race Condition when parallel execution
        synchronized (BaseTest.class) {
            if (!isScreenshotFolderCleaned) {
                System.out.println("clean screenshot folder before run");
                TestListener.cleanScreenShotFolder();
                isScreenshotFolderCleaned = true;
            }
        }
    }

    @BeforeMethod

    public void setup() {
        driver = new DriverFactory().initialization();

    }

    public WebDriver getDriver() {
        return driver;
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}

