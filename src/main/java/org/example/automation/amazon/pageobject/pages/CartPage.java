package org.example.automation.amazon.pageobject.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CartPage {
    public static final String ZERO_VALUE = "$0.00";
    public static final String YOUR_AMAZON_CART_IS_EMPTY = "Your Amazon Cart is empty.";

    public CartPage deleteButtonClick() {
        SelenideElement deleteButton = $(By.xpath("//input[contains(@value,\"Delete\")]")).shouldBe(Condition.visible);
        deleteButton.click();

        return this;
    }

    public String getPriceAfterDeleteText() {
        SelenideElement priceAfterDelete = $(By.id("sc-subtotal-amount-activecart")).shouldBe(Condition.visible);

        return priceAfterDelete.getText();
    }

    public String getDeleteConfirmationText() {
        SelenideElement deleteConfirmation = $(By.tagName("h1")).shouldBe(Condition.visible);

        return deleteConfirmation.getText();
    }

}
