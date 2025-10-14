package com.epam.ari_kaczmarek.learn.pages;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverConditions.urlStartingWith;

public class PageFactory {
    public static <T extends Page> T createPage(Class<T> pageClass, boolean navigate) {
        try {
            T page = pageClass.getDeclaredConstructor().newInstance();
            if(navigate)
                open(page.getUrl());
            else {
                urlStartingWith(page.getUrl());
            }
            return page;
        } catch(Exception e) {
            throw new RuntimeException("Cannot create page: " + pageClass.getName(), e);
        }
    }
}
