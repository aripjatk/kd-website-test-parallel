package com.epam.ari_kaczmarek.learn.steps;

import java.time.LocalDate;
import java.time.LocalTime;

import com.epam.ari_kaczmarek.learn.ConnectionQuery;
import com.epam.ari_kaczmarek.learn.pages.ConnectionSearchResultPage;
import com.epam.ari_kaczmarek.learn.pages.PageFactory;

public class ValidateConnectionSearchResultPageStep extends Step {
    private final ConnectionQuery query;

    public ValidateConnectionSearchResultPageStep(ConnectionQuery query) {
        this.query = query;
    }

    @Override
    protected void performStep() {
        ConnectionSearchResultPage page = PageFactory.createPage(ConnectionSearchResultPage.class, false);
        
        String expectedFromStation = query.getFromStation();
        String expectedToStation = query.getToStation();
        LocalTime expectedTime = query.getTime();
        LocalDate expectedDate = query.getDate();

        page.waitUntilSearchResultsReady();
        
        assert page.getFirstResultFromStation().startsWith(expectedFromStation);
        assert page.getFirstResultToStation().startsWith(expectedToStation);
        assert expectedDate.compareTo(page.getFirstResultDepartureDate()) <= 0;
        assert expectedTime.compareTo(page.getFirstResultDepartureTime()) <= 0;
    }

}
