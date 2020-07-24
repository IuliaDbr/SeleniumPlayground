package org.example.pages;

import com.github.windpapi4j.WinAPICallFailedException;
import com.github.windpapi4j.WinDPAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.steps.StepDefinitions;
import org.example.utils.EncryptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Properties;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.example.pages.SeleniumMethods.chromeDriver;

public class GoogleLoginPage {
    private static final Logger LOGGER = LogManager.getLogger(StepDefinitions.class);
    private static WinDPAPI winDPAPI;

    public static void loginWithGoogle(final String emailAddress, final String password) throws IOException, WinAPICallFailedException {
        String errorMessage = chromeDriver.findElement(By.xpath("//div[2]/span")).getText();
        WebElement emailAddressElement = chromeDriver.findElement(By.id("identifierId"));
        emailAddressElement.click();
        emailAddressElement.sendKeys(EncryptionUtils.decrypt(getPropValue(emailAddress)));
        chromeDriver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
        WebElement passwordElement = chromeDriver.findElement(By.name("password"));
        passwordElement.sendKeys(EncryptionUtils.decrypt(getPropValue(password)));
        chromeDriver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
        if (errorMessage.contains("Wrong password")) {
            LOGGER.info("Password is incorrect. Please enter the correct password");
        }

    }

    /*Getting the values from credentials.properties and encrypting and decrypting them for login*/
    public static String encryptLoginCred(String param) throws WinAPICallFailedException, IOException {
        byte[] encryptedBytes = winDPAPI.protectData(param.getBytes(UTF_8));

        String encrypted = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println(encrypted);
        return encrypted;
    }

    public static String getPropValue(String property) throws IOException {
        String result = "";
        InputStream inputStream = null;
        try {
            Properties prop = new Properties();
            String propFileName = "credentials.properties";
            inputStream = SeleniumMethods.class.getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            result = prop.getProperty(property);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }
}
