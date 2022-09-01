package org.example.automation.amazon.pageobject.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SmartWagonPage {

    public static final String CART_COUNT = "1";
    public static final String ADDED_TO_CART = "Added to Cart";

    public String getCartCountText() {
        SelenideElement navCartCount = $(By.id("nav-cart-count")).shouldBe(Condition.visible);

        return navCartCount.getText();
    }

    public String getAddedToCartText() {
        SelenideElement addToCartConfirmation = $(By.id("sw-atc-details-single-container")).shouldBe(Condition.visible);

        return addToCartConfirmation.getText();
    }

    public CartPage cartButtonClick() {
        SelenideElement cartButton = $(By.id("nav-cart")).shouldBe(Condition.visible);
        cartButton.click();

        return new CartPage();
    }

}
