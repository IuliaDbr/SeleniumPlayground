package org.example.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

import static org.example.pages.SeleniumMethods.chromeDriver;

public class AmazonHomepage extends BasePageObject {
    private static final Logger LOGGER = LogManager.getLogger(AmazonHomepage.class);

    private static By sortDropDown = new By.ById("a-autoid-0-announce");
    private static By listOfSortOptions = new By.ByXPath("//ul[@role='listbox']/li");
    private static By shipmentPopup = new By.ByXPath("//input[@data-action-type='DISMISS']");

    public static void shipmentAddressPopup() {
        chromeDriver.findElement(shipmentPopup).click();
    }

    public static void sortProductBy(String sortSelection) {
        chromeDriver.findElement(sortDropDown).click();
        List<WebElement> dropDownOptionList = chromeDriver.findElements(listOfSortOptions);
        for (WebElement opt : dropDownOptionList) {
            if (opt.getText().equals(sortSelection)) {
                opt.click();
                return;
            }
        }
        throw new NoSuchElementException("Can't find " + sortSelection + " in dropdown");
    }

}

