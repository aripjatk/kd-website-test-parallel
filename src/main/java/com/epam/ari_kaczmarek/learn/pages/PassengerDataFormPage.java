package com.epam.ari_kaczmarek.learn.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class PassengerDataFormPage extends Page {

    private final By firstNameInput = By.xpath("//input[contains(@class, 'holderFName')]");
    private final By lastNameInput = By.xpath("//input[contains(@class, 'holderSName')]");
    private final By emailInput = By.xpath("//input[contains(@name, 'TicketH.email')]");
    private final By repeatEmailInput = By.xpath("//input[contains(@name, 'TicketH.reEmail')]");
    private final By agreeToTOSCheckbox = By.xpath("//input[@type='checkbox' and contains(@class, 'ConfirmRules')]");
    private final By confirmButton = By.xpath("//form[@id='defineHolderPForm']/descendant::button[@type='submit']");

    public PassengerDataFormPage() {
        url = "https://kolejedolnoslaskie.pl/zakup-biletu/#dane-podrozujacego";
    }

    public void enterPassengerData(String firstName, String lastName, String email) {
        $(firstNameInput).shouldBe(interactable).setValue(firstName);
        $(lastNameInput).setValue(lastName);
        $(emailInput).setValue(email);
        $(repeatEmailInput).setValue(email);
    }

    public void clickAgreeToTOSCheckbox() {
        executeJavaScript("arguments[0].checked = true;", $(agreeToTOSCheckbox));
    }

    public void clickConfirmButton() {
        executeJavaScript("arguments[0].scrollIntoView();", $(confirmButton));
        $(confirmButton).shouldBe(interactable).click();
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
        }
    }

}
