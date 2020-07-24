package org.example.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static org.example.pages.AmazonHomepage.*;
import static org.example.pages.AmazonProductPage.*;
import static org.example.pages.AmazonShoppingCartPage.*;
import static org.example.pages.SeleniumMethods.chromeDriver;

public class AmazonStepsDefinitions extends BasePageObject {
    @Then("I search for {string} product in the Search bar")
    public static void iSearchForProductInSearchBar(String searchBarText) throws InterruptedException {
        waitForPageLoad();
        shipmentAddressPopup();
        WebElement searchBar = chromeDriver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        searchBar.click();
        searchBar.sendKeys(searchBarText);
        searchBar.sendKeys(Keys.ENTER);
    }

    @And("I sort the search based on {string}")
    public static void iSortTheSearchBasedOn(String sortOption) {
        sortProductBy(sortOption);
    }

    @When("I click on the {string} article in the search list")
    public static void iClickOnArticle(String articleNumber) throws InterruptedException {
        selectProduct(articleNumber);
    }
    @And("I add the item to the cart")
    public static void iAddTheItemToTheCart(){
        addProductToCartAndViewCart();
    }

    @And("I check if any similar products are with lower price are available")
    public static void iCheckForSimilarCheaperProducts() throws InterruptedException {
        compareWithSimilarProducts();
    }

    @Then ("I delete the item with the highest price from cart")
    public static void iDeleteTheItemWithTheHighestPriceFromList() throws InterruptedException {
        deleteItemWithHigherPriceFromCart();
    }

}
