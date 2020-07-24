package org.example.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.steps.StepDefinitions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.example.pages.SeleniumMethods.chromeDriver;


public class BasePageObject {
    private static final Logger LOGGER = LogManager.getLogger(StepDefinitions.class);

    protected static void waitForPageLoad() {
        Wait<WebDriver> wait = new WebDriverWait(chromeDriver, 30);
        wait.until(chromeDriver -> String.valueOf(((JavascriptExecutor) chromeDriver)
                .executeScript("return document.readyState")).equals("complete"));
    }

    protected static void waitFor(ExpectedCondition<WebElement> condition, Integer... timeoutInSeconds) {
        Integer actualTimeout = timeoutInSeconds != null ? timeoutInSeconds[0] : 30;
        WebDriverWait wait = new WebDriverWait(chromeDriver, actualTimeout);
        wait.until(condition);
    }

    protected static WebElement waitForElementToBeVisible(By locator, Integer timeoutInSeconds) {
        int attempts = 0;
        while (attempts < 1) {
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator), timeoutInSeconds);
                break;
            } catch (StaleElementReferenceException e) {
                LOGGER.error("Element not found ", e);
            } finally {
                attempts++;
            }
        }
        return null;
    }
    
    protected static boolean elementExists(String id) {
        try {
            chromeDriver.findElement(By.id(id));
        } catch (NoSuchElementException e) {
            LOGGER.debug("Could not find element by id: {}", id);
            return false;
        }
        return true;
    }
}
