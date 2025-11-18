package com.epam.ari_kaczmarek.learn;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.codeborne.selenide.testng.ScreenShooter;
import com.epam.ari_kaczmarek.learn.pages.KDHomePage;
import com.epam.ari_kaczmarek.learn.pages.PageFactory;
import com.epam.ari_kaczmarek.learn.steps.ChooseTicketOfferStep;
import com.epam.ari_kaczmarek.learn.steps.ClickBuyTicketButtonStep;
import com.epam.ari_kaczmarek.learn.steps.DenyCookiesStep;
import com.epam.ari_kaczmarek.learn.steps.EnterConnectionQueryStep;
import com.epam.ari_kaczmarek.learn.steps.EnterPassengerDataStep;
import com.epam.ari_kaczmarek.learn.steps.SearchStep;
import com.epam.ari_kaczmarek.learn.steps.Step;
import com.epam.ari_kaczmarek.learn.steps.ValidateConnectionSearchResultPageStep;
import com.epam.ari_kaczmarek.learn.steps.ValidatePurchaseSummaryPageStep;

@Listeners({ ScreenShooter.class })
public class TicketPurchaseTest extends AbstractTest {

    private List<Step> createSteps(ConnectionQuery query, String firstName, String lastName, String email) {
        KDHomePage homePage = PageFactory.createPage(KDHomePage.class, true);
        List<Step> steps = Arrays.asList(new Step[] {
            new DenyCookiesStep(homePage),
            new EnterConnectionQueryStep(homePage, query),
            new SearchStep(homePage),
            new ValidateConnectionSearchResultPageStep(query),
            new ClickBuyTicketButtonStep(),
            new ChooseTicketOfferStep(),
            new EnterPassengerDataStep(firstName, lastName, email),
            new ValidatePurchaseSummaryPageStep(firstName, lastName, email, query.getFromStation(), query.getToStation())
        });
        for(int i = 0; i < steps.size() - 1; i++) {
            steps.get(i).setNextStep(steps.get(i + 1));
        }
        return steps;
    }

    @Test(groups = "purchase")
    public void testPurchaseTicket() {
        ConnectionQuery query = new ConnectionQueryBuilder()
            .setFromStation("Wrocław Główny")
            .setToStation("Zielona Góra Główna")
            .setDate(LocalDate.now())
            .setTime(LocalTime.of(15, 30))
            .build();
        createSteps(query, "Test", "Testowski", "test@user.com").get(0).run();
    }
}
