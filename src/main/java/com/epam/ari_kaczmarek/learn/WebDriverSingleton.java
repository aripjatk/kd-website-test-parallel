package com.epam.ari_kaczmarek.learn;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSingleton {
    private static WebDriver instance = null;

    public static WebDriver getWebDriver() {
        if (instance == null)
            instance = new ChromeDriver();
        return instance;
    }
}
