package com.epam.ari_kaczmarek.learn.steps;

import com.epam.ari_kaczmarek.learn.pages.PageFactory;
import com.epam.ari_kaczmarek.learn.pages.TicketSelectionPage;

public class ChooseTicketOfferStep extends Step {

    @Override
    protected void performStep() {
        PageFactory.createPage(TicketSelectionPage.class, false)
            .continueWithDefaultTicketOptions();
    }

}
