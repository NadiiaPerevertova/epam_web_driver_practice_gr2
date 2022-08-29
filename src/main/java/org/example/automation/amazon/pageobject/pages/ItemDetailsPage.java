package org.example.automation.amazon.pageobject.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ItemDetailsPage {

    public String getItemDeliveryLocation() {
        SelenideElement isAustraliaDeliveryPresent = $(By.id("contextualIngressPtLabel_deliveryShortLine")).shouldBe(Condition.visible);
        return isAustraliaDeliveryPresent.text();
    }
}
