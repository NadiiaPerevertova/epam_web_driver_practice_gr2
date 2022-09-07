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

    @Test(description = "The test checks that in the list of countries Poland is present")
    public void verifyCountryPresent(){
        HomePage home = new HomePage();
        String presentCountry = home
                .open()
                .changeDeliverLocation()
                .countryDropDown()
                .getDeliverCountry("Poland");
        Assert.assertEquals(presentCountry, "Poland");
    }

    @Test(description = "The test checks that changed delivery location is reflected in selected product.")
    public void verifyDeliveryOfSelectedProduct(){
        HomePage home = new HomePage();
        String deliveryPlace = home
                .open()
                .changeDeliverLocation()
                .countryDropDown()
                .selectDeliverCountry("Australia")
                .done()
                .getDeliverLocation("Australia");
        Assert.assertEquals(deliveryPlace, "Australia");

        String itemDeliveryInfo = home
                .chooseCategory("Chairs")
                .chooseFirstResult()
                .getItemDeliveryLocation();
        Assert.assertEquals(itemDeliveryInfo,"Deliver to Australia");
    }
}
