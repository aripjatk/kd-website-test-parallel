package com.epam.ari_kaczmarek.learn.steps;

import com.epam.ari_kaczmarek.learn.pages.PageFactory;
import com.epam.ari_kaczmarek.learn.pages.PurchaseSummaryPage;

public class ValidatePurchaseSummaryPageStep extends Step {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String fromStation;
    private final String toStation;

    public ValidatePurchaseSummaryPageStep(String firstName, String lastName, String email, String fromStation, String toStation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.fromStation = fromStation;
        this.toStation = toStation;
    }

    @Override
    protected void performStep() {
        PurchaseSummaryPage page = PageFactory.createPage(PurchaseSummaryPage.class, false);
        page.expandDetails();
        assert page.containsEmail(email);
        assert page.isPassengerName(firstName, lastName);
        assert page.getFromStation().contains(fromStation);
        assert page.getToStation().contains(toStation);
    }
}
