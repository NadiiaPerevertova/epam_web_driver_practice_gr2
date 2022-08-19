package org.example.automation.amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class DeliverToTest extends BaseWebDriverTest {

    @Test
    public void deliveryLocationChanging() {
        webDriver.get("https://www.amazon.com");

        WebElement deliverIcon = webDriver.findElement(By.id("nav-global-location-data-modal-action"));
        deliverIcon.click();

        WebElement fieldUsZipCode = new WebDriverWait(webDriver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("GLUXZipUpdateInput")));
        fieldUsZipCode.sendKeys("95814");

        WebElement buttonApply = webDriver.findElement(By.id("GLUXZipUpdate"));
        buttonApply.click();

        WebElement buttonContinue = new WebDriverWait(webDriver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.a-popover-footer #GLUXConfirmClose")));
        buttonContinue.click();

        boolean isTrueDeliver = new WebDriverWait(webDriver, Duration.ofSeconds(20))
                .until(ExpectedConditions.textToBe(By.id("glow-ingress-line2"), "Sacramento 95814\u200C"));

        Assert.assertTrue(isTrueDeliver, "Not updated, expected: Sacramento 95814");
    }

    @Test
    public void isCountryPresent() {
        webDriver.get("https://www.amazon.com");

        WebElement deliverIcon = webDriver.findElement(By.id("nav-global-location-data-modal-action"));
        deliverIcon.click();

        WebElement countryDropDown = new WebDriverWait(webDriver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("GLUXCountryListDropdown")));
        countryDropDown.click();

        boolean isPolandPresent = new WebDriverWait(webDriver, Duration.ofSeconds(20))
                .until(ExpectedConditions.textToBe(By.id("GLUXCountryList_178"), "Poland"));
        Assert.assertTrue(isPolandPresent, "Not found Poland");

    }

    @Test
    public void deliveryOfSelectedProduct() {
        webDriver.get("https://www.amazon.com");

        WebElement deliverIcon = webDriver.findElement(By.id("nav-global-location-data-modal-action"));
        deliverIcon.click();

        WebElement countryDropDown = new WebDriverWait(webDriver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("GLUXCountryListDropdown")));
        countryDropDown.click();

        WebElement chooseAustralia = new WebDriverWait(webDriver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("GLUXCountryList_0")));
        chooseAustralia.click();

        WebElement buttonDone = webDriver.findElement(By.name("glowDoneButton"));
        buttonDone.click();

        boolean isTrueDeliver = new WebDriverWait(webDriver, Duration.ofSeconds(20))
                .until(ExpectedConditions.textToBe(By.id("glow-ingress-line2"), "Australia"));
        Assert.assertTrue(isTrueDeliver, "Not updated, expected: Australia");

        WebElement chooseChairs = new WebDriverWait(webDriver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt=\"Chairs\"]")));
        chooseChairs.click();

        WebElement categoryResuls = new WebDriverWait(webDriver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-component-type=\"s-search-results\"]//img[1]")));
        categoryResuls.click();

        boolean isAustraliaDeliveryPresent = new WebDriverWait(webDriver, Duration.ofSeconds(20))
                .until(ExpectedConditions.textToBe(By.id("contextualIngressPtLabel_deliveryShortLine"), "Deliver to Australia"));
        Assert.assertTrue(isAustraliaDeliveryPresent, "Not found 'Deliver to Australia'");

    }
}
