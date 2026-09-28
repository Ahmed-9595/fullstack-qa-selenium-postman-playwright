package com.ElectroPI.qa.factoryPage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    private WebDriver driver;


    public WebDriver initialization() {
        String browser = System.getProperty("browser","chrome");
        String headless = System.getProperty("headless","false");
        boolean isHeadless = Boolean.parseBoolean(headless);

        switch (browser) {

            case "chrome"-> {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                // إضافة الخيارات قبل إنشاء الـ driver
                options.addArguments("--force-device-scale-factor=1");
                options.addArguments("--high-dpi-support=1");
                options.addArguments("--incognito");

                options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
                options.addArguments("--disable-blink-features=AutomationControlled");

                if (isHeadless) {
                    options.addArguments("--headless=new"); // or --headless
                }

                // إنشاء الـ driver بعد إعداد جميع الخيارات
                ChromeDriver chromeDriver = new ChromeDriver(options);
                driver = chromeDriver;
                break;
            }
            case "firefox"-> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addPreference("layout.css.devPixelsPerPx", "0.8");
                if (isHeadless) {
                    firefoxOptions.addArguments("--headless");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;
            }
            default -> {
                throw new RuntimeException("Browser not supported");
            }
        }

        driver.manage().window().setSize(new Dimension(1920, 1080));
        return driver;

    }
}
