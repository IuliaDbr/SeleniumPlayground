package org.example.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.example.pages.SeleniumMethods.chromeDriver;

public class AmazonShoppingCartPage {
    private static final Logger LOGGER = LogManager.getLogger(AmazonShoppingCartPage.class);

    private static By productName = new By.ById("productTitle");
    private static By addToCartButton = new By.ByXPath("//input[@value='Add to Cart']");
    private static By viewCart = new By.ById("hlb-view-cart");
    private static By productTitleFromCart = new By.ByXPath("//span[@class='a-size-medium sc-product-title a-text-bold']");
    private static By compareProductButton = new By.ByXPath("//input[@aria-label='Compare with similar items ']");
    private static By itemPrice = new By.ByXPath("//p[@class='a-spacing-small']/span");
    private static By shoppingCartListOfPrices = new By.ByXPath("//div[@data-name='Active Items']//p/span");

    public static void addProductToCartAndViewCart(){
        String initialProductName = chromeDriver.findElement(productName).getText();
        LOGGER.info("The text read is: " + initialProductName);
        chromeDriver.findElement(addToCartButton).click();
        chromeDriver.findElement(viewCart).click();
        String productTitle = chromeDriver.findElement(productTitleFromCart).getText();
        Assert.assertEquals("The product name is: ", initialProductName, productTitle);
    }

    public static void compareWithSimilarProducts() throws InterruptedException {
        String cartItemPrice = chromeDriver.findElement(itemPrice).getText().replaceAll("\\$", "");
        LOGGER.info("Initial product price is: " + cartItemPrice);
        TimeUnit.SECONDS.sleep(2);
        chromeDriver.findElement(compareProductButton).click();
        List<WebElement> productList = chromeDriver.findElements(By.xpath("//tr[3]/td/div"));
        for (int i = 2; i <= productList.size(); i++) {
            String productPrice = chromeDriver.findElement(By.xpath("//tr[3]/td[" + i + "]/div")).getText().replaceAll("\\$", "");
            if (!productPrice.equals(cartItemPrice)) {
                if (!productPrice.equalsIgnoreCase("")) {
                    if (Double.parseDouble(productPrice) < Double.parseDouble(cartItemPrice)) {
                        LOGGER.info("Found a smaller price: " + productPrice);
                        chromeDriver.findElement(By.xpath("//tr[6]/td[" + i + "]/span")).click();
                        TimeUnit.SECONDS.sleep(3);
                        break;
                    }
                }

            }
        }
    }

    public static void deleteItemWithHigherPriceFromCart() throws InterruptedException {
        List<WebElement> shoppingCartList = chromeDriver.findElements(shoppingCartListOfPrices);
        ArrayList<String> cartItemsPrices = new ArrayList<>();
        for (WebElement prod : shoppingCartList) {
            cartItemsPrices.add(prod.getText().replaceAll("\\$", ""));
            LOGGER.info("The values from the list are: " + cartItemsPrices);
        }
        Arrays.sort(cartItemsPrices.toArray());
        for (int i = 1; i <cartItemsPrices.size(); i++) {
            chromeDriver.findElement(By.xpath(" //div[@data-price='"+cartItemsPrices.get(i)+"']//input[@data-action='delete']")).click();
            LOGGER.info("Removing the item with price: " + cartItemsPrices.get(i));
        }

    }
}
