package org.example.automation.amazon.pageobject;

import org.example.automation.amazon.pageobject.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeliverToTest extends BaseTest {

    @Test(description = "The test checks shipping changes by US state zip codes.")
    public void verifyDeliveryLocationChanging() {
        HomePage home = new HomePage();
        String deliveryToPlace = home
                .open()
                .changeDeliverLocation()
                .enterZipCode("95814")
                .apply()
                .confirmApply()
                .getDeliverLocation("Sacramento 95814\u200C");
        Assert.assertEquals(deliveryToPlace, "Sacramento 95814\u200C");
    }
}
