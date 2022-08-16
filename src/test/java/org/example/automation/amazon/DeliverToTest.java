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
    public void verifyDeliverTo() {
        webDriver.get("https://www.amazon.com");

    }
}
