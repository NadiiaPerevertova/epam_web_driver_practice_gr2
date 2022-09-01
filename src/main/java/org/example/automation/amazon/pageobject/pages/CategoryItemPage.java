package org.example.automation.amazon.pageobject.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CategoryItemPage {

    public SmartWagonPage addToCartButtonClick() {
        SelenideElement addToCartButton = $(By.id("add-to-cart-button")).shouldBe(Condition.visible);
        addToCartButton.click();

        return new SmartWagonPage();
    }

}
