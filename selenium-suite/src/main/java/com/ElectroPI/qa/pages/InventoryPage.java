package com.ElectroPI.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InventoryPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Dynamic locators
    private By getNavTab(String tabName) {
        return By.xpath("//nav//*[contains(text(),'" + tabName + "')]");
    }

    private By getInputField(String fieldName) {
        return By.xpath("//input[@name='" + fieldName + "' or @data-testid='" + fieldName + "']");
    }

    private By getButtonByText(String btnText) {
        return By.xpath("//button[contains(text(),'" + btnText + "')]");
    }

    private By getToastMessage(String partialText) {
        return By.xpath("//*[contains(@class,'toast') or contains(@class,'alert') or @role='alert'][contains(text(),'" + partialText + "')]");
    }

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToModule(String moduleName) {
        wait.until(ExpectedConditions.elementToBeClickable(getNavTab(moduleName))).click();
    }

    public void addProduct(String name, String price) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(getInputField("productName"))).sendKeys(name);
        driver.findElement(getInputField("price")).sendKeys(price);
        driver.findElement(getButtonByText("Save")).click();
    }

    public boolean isSuccessToastDisplayed(String text) {
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(getToastMessage(text)));
        return toast.isDisplayed();
    }
}
