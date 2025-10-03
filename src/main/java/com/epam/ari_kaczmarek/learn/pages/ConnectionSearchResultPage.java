package com.epam.ari_kaczmarek.learn.pages;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConnectionSearchResultPage extends Page {

    private final By firstResultFromStation = By.xpath("//div[@class='searching-results-list']/div[@class='searching-result'][1]/descendant::span[@class='departure-place']");
    private final By firstResultToStation = By.xpath("//div[@class='searching-results-list']/div[@class='searching-result'][1]/descendant::span[@class='arrival']");
    private final By firstResultDepartureTime = By.xpath("//div[@class='searching-results-list']/div[@class='searching-result'][1]/descendant::div[@class='departure-time']/span[@class='time']");
    private final By firstResultDepartureDate = By.xpath("//div[@class='searching-results-list']/div[@class='searching-result'][1]/descendant::div[@class='departure-time']/span[@class='date']");
    
    public ConnectionSearchResultPage(WebDriver webDriver) {
        super(webDriver);
        this.url = "https://kolejedolnoslaskie.pl/wyszukiwarka-polaczen/";
    }

    public void waitUntilSearchResultsReady() {
        WebDriverWait wait;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        wait.until(d -> d.findElement(firstResultFromStation).isDisplayed());
    }

    public String getFirstResultFromStation() {
        return webDriver.findElement(firstResultFromStation).getText();
    }

    public String getFirstResultToStation() {
        return webDriver.findElement(firstResultToStation).getText();
    }

    public LocalTime getFirstResultDepartureTime() {
        String timeString = webDriver.findElement(firstResultDepartureTime).getText();
        return LocalTime.parse(timeString);
    }

    public LocalDate getFirstResultDepartureDate() {
        String dateString = webDriver.findElement(firstResultDepartureDate).getText();
        String[] parts = dateString.split(" ");
        return LocalDate.of(LocalDate.now().getYear(), getMonthFromString(parts[1]), Integer.parseInt(parts[0]));
    }

    public Month getMonthFromString(String month) {
        // DateTimeFormatter uses nominative case for month names, but the website uses genitive case
        switch(month) {
            case "stycznia": return Month.JANUARY;
            case "lutego": return Month.FEBRUARY;
            case "marca": return Month.MARCH;
            case "kwietnia": return Month.APRIL;
            case "maja": return Month.MAY;
            case "czerwca": return Month.JUNE;
            case "lipca": return Month.JULY;
            case "sierpnia": return Month.AUGUST;
            case "września": return Month.SEPTEMBER;
            case "października": return Month.OCTOBER;
            case "listopada": return Month.NOVEMBER;
            case "grudnia": return Month.DECEMBER;
            default: throw new IllegalArgumentException("Invalid month string: " + month);
        }
    }
}
