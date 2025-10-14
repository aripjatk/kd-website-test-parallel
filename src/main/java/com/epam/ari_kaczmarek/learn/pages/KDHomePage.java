package com.epam.ari_kaczmarek.learn.pages;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

import static org.testng.Assert.fail;

import com.epam.ari_kaczmarek.learn.ConnectionQuery;

public class KDHomePage extends Page {
    private final By denyCookiesButton = By.id("CybotCookiebotDialogBodyButtonDecline");

    private final By fromStationInput = By.xpath("//input[@placeholder='Stacja początkowa']");
    private final By toStationInput = By.xpath("//input[@placeholder='Stacja końcowa']");

    private final By calendarButton = By.xpath("//div[contains(@class, 'dateSelect')]");
    private final By monthAndYearLabel = By.xpath("//div[@id='datepicker']/descendant::h2");
    private final By previousMonthButton = By.xpath("//a[@title='Previous month']");
    private final By nextMonthButton = By.xpath("//a[@title='Next month']");
    private final String dayButtonFormat = "//div[@class='dp-calendar']/descendant::*[text()='%s']";

    private final By timeButton = By.xpath("//div[contains(@class, 'timeSelect')]");
    private final String hourButtonFormat = "//div[contains(@class, 'tcHoursContainer')]/span[contains(@class, 'btnHour')]/span[text()='%s']";
    private final String minuteButtonFormat = "//div[contains(@class, 'tcMinutesContainer')]/span[contains(@class, 'btnMinute')]/span[text()='%s']";

    private final By searchButton = By.xpath("//button[@type='submit']");

    public KDHomePage() {
        this.url = "https://kolejedolnoslaskie.pl/";
    }

    public void denyCookies() {
        $(denyCookiesButton)
            .shouldBe(clickable)
            .click();
    }

    private void enterFromStation(String station) {
        // must wait because page refreshes after cookie denial
        try {
            Thread.sleep(5000);
        } catch(InterruptedException e) {
            fail("Failed to wait for refresh after cookie denial");
        }
        $(fromStationInput).shouldBe(clickable).type(station);
    }

    private void enterToStation(String station) {
        $(toStationInput).shouldBe(clickable).type(station);
    }

    private void enterDate(LocalDate date) {
        $(calendarButton).click();

        Locale polish = new Locale("pl", "PL");
        DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("dd LLLL yyyy", polish);
        String currentlySelectedDateString = $(monthAndYearLabel).text();
        LocalDate currentlySelectedDate = LocalDate.parse("01 " + currentlySelectedDateString, monthYearFormatter);

        switch(date.getMonth().compareTo(currentlySelectedDate.getMonth())) {
            case 0:
                break;
            case 1:
                while(date.getMonth().compareTo(currentlySelectedDate.getMonth()) > 0) {
                    $(nextMonthButton).click();
                    currentlySelectedDate = currentlySelectedDate.plusMonths(1);
                }
                break;
            case -1:
                while(date.getMonth().compareTo(currentlySelectedDate.getMonth()) < 0) {
                    $(previousMonthButton).click();
                    currentlySelectedDate = currentlySelectedDate.minusMonths(1);
                }
                break;
        }

        String dayButtonXPath = String.format(dayButtonFormat, date.getDayOfMonth());
        $x(dayButtonXPath).click();
    }

    private void enterTime(LocalTime time) {
        if(time.getMinute() % 5 != 0) {
            throw new IllegalArgumentException("Minutes must be multiple of 5");
        }

        $(timeButton).click();

        String hourButtonXPath = String.format(hourButtonFormat, String.format("%02d", time.getHour()));
        $x(hourButtonXPath).click();

        String minuteButtonXPath = String.format(minuteButtonFormat, String.format("%02d", time.getMinute()));
        $x(minuteButtonXPath).click();
    }

    public void enterConnectionQuery(ConnectionQuery query) {
        enterFromStation(query.getFromStation());
        enterToStation(query.getToStation());
        enterDate(query.getDate());
        enterTime(query.getTime());
    }

    public By getToStationInput() {
        return toStationInput;
    }

    public void clickSearchButton() {
        $(searchButton).click();
    }
}