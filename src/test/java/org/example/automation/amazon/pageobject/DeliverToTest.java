package org.example.automation.amazon.pageobject;

import org.example.automation.amazon.pageobject.pages.HomePage;
import org.testng.annotations.Test;

public class DeliverToTest extends BaseTest {

    @Test
    public void verifyDeliveryLocationChanging() {
        HomePage home = new HomePage();
        home.open();
    }
}
