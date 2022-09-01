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

    public CategoryPage categoryClick() {
        SelenideElement category = $(By.xpath("//div[contains(@class,\"a-section a-spacing-none fluid-image-container\")]//img")).shouldBe(Condition.visible);
        category.click();

        return new CategoryPage();
    }

}
