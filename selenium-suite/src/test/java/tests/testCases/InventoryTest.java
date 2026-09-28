package tests.testCases;

import com.ElectroPI.qa.pages.InventoryPage;
import com.ElectroPI.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.testBase.BaseTest;

public class InventoryTest extends BaseTest {


    @Test
    public void testAddProductSuccessfully() {
        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = new InventoryPage(getDriver());

        loginPage.navigateToLogin();
        loginPage.loginWithDefaultAdmin();

        inventoryPage.navigateToModule("Inventory");
        inventoryPage.addProduct("Wireless Mouse", "25.00");

        boolean isDisplayed = inventoryPage.isSuccessToastDisplayed("created successfully");
        Assert.assertTrue(isDisplayed, "Expected toast message was not displayed!");
    }
}
