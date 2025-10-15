package com.epam.ari_kaczmarek.learn.steps;

import com.epam.ari_kaczmarek.learn.pages.PageFactory;
import com.epam.ari_kaczmarek.learn.pages.PassengerDataFormPage;

public class EnterPassengerDataStep extends Step {

    private final String firstName;
    private final String lastName;
    private final String email;

    public EnterPassengerDataStep(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    protected void performStep() {
        PassengerDataFormPage page = PageFactory.createPage(PassengerDataFormPage.class, false);
        page.enterPassengerData(firstName, lastName, email);
        page.clickAgreeToTOSCheckbox();
        page.clickConfirmButton();
    }

}
