package org.example.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.steps.StepDefinitions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SeleniumMethods {
    public static WebDriver chromeDriver = new ChromeDriver();
    private static final Logger LOGGER = LogManager.getLogger(StepDefinitions.class);

    public static WebDriver chromeDriverSetup() {
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        LOGGER.debug("Started chrome driver");
        return chromeDriver;
    }

    public static String connectToWebSite(final String website) {
        chromeDriver.get(website);
        String title = chromeDriver.getTitle();
        WebDriverWait waitForElement = new WebDriverWait(chromeDriver, 20);
        waitForElement.until(ExpectedConditions.titleIs(title));
        return website;
    }


}
