package org.example.automation.amazon.pageobject.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class DeliverLocationPage {
    public DeliverLocationPage enterZipCode(String zipCode) {
        SelenideElement fieldUsZipCode = $(By.id("GLUXZipUpdateInput")).shouldBe(Condition.visible);
        fieldUsZipCode.sendKeys(zipCode);
        return this;
    }

    public DeliverLocationPage apply() {
        SelenideElement buttonApply = $(By.id("GLUXZipUpdate")).shouldBe(Condition.visible);
        buttonApply.click();
        return this;
    }

    public HomePage confirmApply() {
        SelenideElement buttonContinue = $(By.cssSelector("div.a-popover-footer #GLUXConfirmClose")).shouldBe(Condition.visible);
        buttonContinue.click();
        return new HomePage();
    }

    public DeliverLocationPage countryDropDown() {
        SelenideElement countryDropDown = $(By.id("GLUXCountryListDropdown")).shouldBe(Condition.visible);
        countryDropDown.hover();
        countryDropDown.click();
        return this;
    }

    public String getDeliverCountry(String country) {
        SelenideElement countryElement = getCountryElementFromDropdown(country);
        return countryElement.text();
    }

    public DeliverLocationPage selectDeliverCountry(String country) {
        SelenideElement countryElement = getCountryElementFromDropdown(country);
        countryElement.click();
        return this;
    }

    private SelenideElement getCountryElementFromDropdown(String country) {
        return $(By.xpath("//div[@class=\"a-popover-wrapper\"]//a[.='" + country + "']"));
    }

    public HomePage done() {
        SelenideElement buttonDone = $(By.name("glowDoneButton"));
        buttonDone.click();
        return new HomePage();
    }
}
