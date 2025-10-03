package com.epam.ari_kaczmarek.learn.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.ari_kaczmarek.learn.WebDriverSingleton;

public class PageFactory {
    public static <T extends Page> T createPage(Class<T> pageClass, boolean navigate) {
        try {
            WebDriver driver = WebDriverSingleton.getWebDriver();
            T page = pageClass.getDeclaredConstructor(WebDriver.class).newInstance(driver);
            if(navigate)
                driver.get(page.getUrl());
            else {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(d -> d.getCurrentUrl().startsWith(page.getUrl()));
            }
            return page;
        } catch(Exception e) {
            throw new RuntimeException("Cannot create page: " + pageClass.getName(), e);
        }
    }
}
