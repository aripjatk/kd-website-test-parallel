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
    protected WebDriver driver;

    @BeforeSuite
    public void setParameters() {
        ScreenShooter.captureSuccessfulTests = true;
    }

    @BeforeMethod
    @Parameters("browser")
    public void setUpDriver(String browser) {
        if(browser.equalsIgnoreCase("Chrome")) {
            driver = new ChromeDriver();
        } else if(browser.equalsIgnoreCase("Firefox")) {
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        setWebDriver(driver);
   }

   @AfterMethod
   public void tearDown() {
       if(driver != null) {
           driver.quit();
       }
   }
}
