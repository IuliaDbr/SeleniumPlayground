package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.example.pages.SeleniumMethods.chromeDriver;

public class AmazonProductPage {
    private static By productSearchResult = new By.ByXPath("//div[@class='s-main-slot s-result-list s-search-results sg-row']/div");

    public static void selectProduct(String articleNumber) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        List<WebElement> productList = chromeDriver.findElements(productSearchResult);
        WebElement chosenProduct = productList.get(Integer.parseInt(articleNumber));
        chosenProduct.click();
    }
}
