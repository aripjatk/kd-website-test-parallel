package com.epam.ari_kaczmarek.learn;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.epam.ari_kaczmarek.learn.pages.KDHomePage;
import com.epam.ari_kaczmarek.learn.pages.PageFactory;
import com.epam.ari_kaczmarek.learn.steps.DenyCookiesStep;
import com.epam.ari_kaczmarek.learn.steps.EnterConnectionQueryStep;
import com.epam.ari_kaczmarek.learn.steps.SearchStep;
import com.epam.ari_kaczmarek.learn.steps.Step;
import com.epam.ari_kaczmarek.learn.steps.ValidateConnectionSearchResultPageStep;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class ConnectionSearchTest {

    private List<Step> createSteps(ConnectionQuery query) {
        KDHomePage homePage = PageFactory.createPage(KDHomePage.class, true);
        List<Step> steps = Arrays.asList(new Step[] {
            new DenyCookiesStep(homePage),
            new EnterConnectionQueryStep(homePage, query),
            new SearchStep(homePage),
            new ValidateConnectionSearchResultPageStep(query)
        });
        for(int i = 0; i < steps.size() - 1; i++) {
            steps.get(i).setNextStep(steps.get(i + 1));
        }
        return steps;
    }

    @AfterMethod
    public void closeBrowser() {
        closeWebDriver();
    }

    @Test
    public void testConnectionSearchToday() {
        ConnectionQuery query = new ConnectionQueryBuilder()
            .setFromStation("Wrocław Główny")
            .setToStation("Zielona Góra Główna")
            .setDate(LocalDate.now())
            .setTime(LocalTime.of(15, 30))
            .build();
        createSteps(query).get(0).run();
    }

    @Test
    public void testConnectionSearchNextMonth() {
        ConnectionQuery query = new ConnectionQueryBuilder()
            .setFromStation("Wrocław Główny")
            .setToStation("Zielona Góra Główna")
            .setDate(LocalDate.now().plusMonths(1))
            .setTime(LocalTime.of(15, 30))
            .build();
        createSteps(query).get(0).run();
    }
}
