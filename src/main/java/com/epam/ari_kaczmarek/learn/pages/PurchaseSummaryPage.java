package com.epam.ari_kaczmarek.learn.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class PurchaseSummaryPage extends Page {
    private final By emailContainer = By.xpath("//div[@class='ticketInfo']");
    private final By passengerName = By.xpath("//div[contains(@class, 'passengerDataList')]/span[@class='passenger']");
    private final By expandDetailsLink = By.xpath("//a[@class='onFolded']");
    private final By fromStation = By.xpath("//div[starts-with(@class, 'ticktConnectionInfo')]/descendant::*[starts-with(@class, 'from ')]");
    private final By toStation = By.xpath("//div[starts-with(@class, 'ticktConnectionInfo')]/descendant::*[starts-with(@class, 'to ')]");

    public PurchaseSummaryPage() {
        url = "https://kolejedolnoslaskie.pl/zakup-biletu/#podsumowanie";
    }

    public boolean containsEmail(String email) {
        return executeJavaScript("return arguments[0].innerHTML", $(emailContainer))
                    .toString().contains(email);
    }
    
    public boolean isPassengerName(String firstName, String lastName) {
        return $(passengerName).text().equals(firstName + " " + lastName);
    }

    public void expandDetails() {
        $(expandDetailsLink).shouldBe(interactable).click();
    }

    public String getFromStation() {
        return $(fromStation).text();
    }

    public String getToStation() {
        return $(toStation).text();
    }
}
