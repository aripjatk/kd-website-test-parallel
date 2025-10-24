package com.epam.ari_kaczmarek.learn;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
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
    @Parameters({"browser", "node"})
    public void setUpDriver(String browser, String node) {
        try {
            if(browser.equalsIgnoreCase("Chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.setCapability("browserName", "chrome");
                driver = new RemoteWebDriver(new URL(node), options);
            } else if(browser.equalsIgnoreCase("Firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                options.setCapability("browserName", "firefox");
                driver = new RemoteWebDriver(new URL(node), options);
            } else {
                throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
            setWebDriver(driver);
        } catch(MalformedURLException e) {
            throw new IllegalArgumentException("Invalid node URL: " + node);
        }
   }

   @AfterMethod
   public void tearDown() {
       if(driver != null) {
           driver.quit();
       }
   }
}
