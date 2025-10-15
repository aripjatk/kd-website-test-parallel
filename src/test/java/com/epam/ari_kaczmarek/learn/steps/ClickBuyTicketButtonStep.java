package com.epam.ari_kaczmarek.learn.steps;

import com.epam.ari_kaczmarek.learn.pages.ConnectionSearchResultPage;
import com.epam.ari_kaczmarek.learn.pages.PageFactory;

public class ClickBuyTicketButtonStep extends Step {
    @Override
    public void performStep() {
        PageFactory.createPage(ConnectionSearchResultPage.class, false)
            .clickFirstResultBuyTicketButton();
    }
}
