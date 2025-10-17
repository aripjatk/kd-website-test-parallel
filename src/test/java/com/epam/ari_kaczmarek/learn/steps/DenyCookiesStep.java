package com.epam.ari_kaczmarek.learn.steps;

import com.epam.ari_kaczmarek.learn.pages.KDHomePage;

public class DenyCookiesStep extends Step {

    private final KDHomePage homePage;

    public DenyCookiesStep(KDHomePage homePage) {
        this.homePage = homePage;
    }

    @Override
    protected void performStep() {
        try {
            homePage.denyCookies();
        } catch(Throwable t) {
            // cookies have already been denied, just continue
        }
    }
}
