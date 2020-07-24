package org.example.steps;

import com.github.windpapi4j.WinAPICallFailedException;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;

import static org.example.pages.GoogleLoginPage.loginWithGoogle;
import static org.example.pages.SeleniumMethods.*;

public class StepDefinitions {

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver","./src/main/resources/drivers/chromedriver.exe");
    }
@After
public void closeWebDriver(){
        chromeDriver.quit();
}
    @Given("I can navigate to {string} website")
    public static void iNavigateToWebsiteAndCheckTitle(final String website) {
        chromeDriverSetup();
        connectToWebSite(website);
    }

    @Given("I can login with {string} and {string}")
    public static void iCanLoginWithCredentialsToWebsite(final String emailAddress, final String password) throws IOException, WinAPICallFailedException {
        loginWithGoogle(emailAddress, password);
    }
    @When("I change the Google mail language from {string} to {string}")
    public static void iChangeGoogleMailLanguage(final String initialLanguage, final String languageText){
        WebElement languageSelector =chromeDriver.findElement(By.xpath("//div[@data-value='"+initialLanguage+"']"));
        WebElement englishSpanSelector= chromeDriver.findElement(By.xpath("//div/span[contains(text(), '"+languageText+"')]"));
        languageSelector.click();
        englishSpanSelector.click();
    }

}
