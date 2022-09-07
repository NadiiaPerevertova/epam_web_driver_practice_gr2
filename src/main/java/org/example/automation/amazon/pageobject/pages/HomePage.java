package org.example.automation.amazon.pageobject.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {

    public HomePage open() {
        Selenide.open("/");
        return this;
    }

    public DeliverLocationPage changeDeliverLocation() {
        SelenideElement deliverIcon = $(By.id("nav-global-location-data-modal-action"));
        deliverIcon.click();
        return new DeliverLocationPage();
    }

    public String getDeliverLocation(String city) {
        SelenideElement deliverToElement = $(By.id("glow-ingress-line2")).shouldHave(Condition.text(city));
        return deliverToElement.text();
    }

    public SearchResultsPage chooseCategory(String category) {
        SelenideElement chooseChairs = $(By.xpath("//img[@alt=\"" + category + "\"]")).shouldBe(Condition.visible);
        chooseChairs.click();
        return new SearchResultsPage();
    }
}
