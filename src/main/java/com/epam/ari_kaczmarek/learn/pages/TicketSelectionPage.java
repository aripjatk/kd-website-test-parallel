package com.epam.ari_kaczmarek.learn.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selenide.$;

public class TicketSelectionPage extends Page {

    private final By nextStepButtonInForm = By.xpath("//div[@id='PurchaseProcess']/descendant::button[@type='submit']");
    private final By nextStepButtonInPopup = By.xpath("//div[@class='ep-modal-footer']/descendant::button[@type='submit']");

    public TicketSelectionPage() {
        url = "https://kolejedolnoslaskie.pl/zakup-biletu/#wybor-biletu";
    }

    public void continueWithDefaultTicketOptions() {
        $(nextStepButtonInForm).shouldBe(interactable).click();
        $(nextStepButtonInPopup).shouldBe(interactable).click();
    }
}
