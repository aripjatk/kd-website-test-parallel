package com.epam.ari_kaczmarek.learn.pages;

import org.openqa.selenium.WebDriver;

public abstract class Page {
    protected String url;
    protected WebDriver webDriver;

    public Page(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getUrl() {
        return url;
    }
}