package com.epam.ari_kaczmarek.learn.steps;

import com.epam.ari_kaczmarek.learn.ConnectionQuery;
import com.epam.ari_kaczmarek.learn.pages.KDHomePage;

public class EnterConnectionQueryStep extends Step {

    private final KDHomePage homePage;
    private final ConnectionQuery query;

    public EnterConnectionQueryStep(KDHomePage homePage, ConnectionQuery query) {
        this.homePage = homePage;
        this.query = query;
    }

    @Override
    protected void performStep() {
        homePage.enterConnectionQuery(query);
    }
}
