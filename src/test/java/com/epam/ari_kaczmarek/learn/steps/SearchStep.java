package com.epam.ari_kaczmarek.learn.steps;

import com.epam.ari_kaczmarek.learn.pages.KDHomePage;

public class SearchStep extends Step {
    private final KDHomePage homePage;
    public SearchStep(KDHomePage homePage) {
        this.homePage = homePage;
    }

    @Override
    protected void performStep() {
        homePage.clickSearchButton();
    }
}
