package com.epam.ari_kaczmarek.learn;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import com.codeborne.selenide.testng.ScreenShooter;

public abstract class AbstractTest {
    protected ThreadLocal<WebDriver> driver;

    @BeforeSuite
    public void setParameters() {
        ScreenShooter.captureSuccessfulTests = true;
    }

    @BeforeMethod
    @Parameters("browser")
    public void setUpDriver(String browser) {
        if(browser.equalsIgnoreCase("Chrome")) {
            driver = ThreadLocal.withInitial(() -> new ChromeDriver());
        } else if(browser.equalsIgnoreCase("Firefox")) {
            driver = ThreadLocal.withInitial(() -> new FirefoxDriver());
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        setWebDriver(driver.get());
   }

   @AfterMethod
   public void tearDown() {
       if(driver != null) {
           driver.get().quit();
       }
   }
}
