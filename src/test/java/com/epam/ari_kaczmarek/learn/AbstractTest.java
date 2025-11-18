package com.epam.ari_kaczmarek.learn;

import org.testng.annotations.BeforeSuite;

import com.codeborne.selenide.testng.ScreenShooter;

public abstract class AbstractTest {

    @BeforeSuite
    public void setParameters() {
        ScreenShooter.captureSuccessfulTests = true;
    }
}
