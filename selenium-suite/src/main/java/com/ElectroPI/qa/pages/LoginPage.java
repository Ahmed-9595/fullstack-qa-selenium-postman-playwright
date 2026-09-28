package com.ElectroPI.qa.pages;

import com.ElectroPI.qa.utils.ConfigUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Dynamic locator method
    private By getInputField(String fieldName) {
        return By.xpath("//input[@name='" + fieldName + "' or @id='" + fieldName + "']");
    }
    private By submitButton = By.xpath("//button[@type='submit' or contains(text(),'Log')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToLogin() {
        driver.get(ConfigUtils.getInstance().getProperty("base.url") + "/login");
    }

    public void loginWithDefaultAdmin() {
        String user = ConfigUtils.getInstance().getProperty("admin.username");
        String pass = ConfigUtils.getInstance().getProperty("admin.password");

        wait.until(ExpectedConditions.visibilityOfElementLocated(getInputField("username"))).sendKeys(user);
        driver.findElement(getInputField("password")).sendKeys(pass);
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }
}
