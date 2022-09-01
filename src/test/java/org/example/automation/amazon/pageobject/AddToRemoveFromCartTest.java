package org.example.automation.amazon.pageobject;

import org.example.automation.amazon.pageobject.pages.CartPage;
import org.example.automation.amazon.pageobject.pages.HomePage;
import org.example.automation.amazon.pageobject.pages.SmartWagonPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.example.automation.amazon.pageobject.pages.CartPage.YOUR_AMAZON_CART_IS_EMPTY;
import static org.example.automation.amazon.pageobject.pages.CartPage.ZERO_VALUE;
import static org.example.automation.amazon.pageobject.pages.SmartWagonPage.ADDED_TO_CART;
import static org.example.automation.amazon.pageobject.pages.SmartWagonPage.CART_COUNT;

public class AddToRemoveFromCartTest extends BaseTest {
    @Test(description = "checking if an item is added to cart")
    public void addToCart() {
        SmartWagonPage smartWagonPage = addItemToCart();

        Assert.assertEquals(smartWagonPage.getCartCountText(), CART_COUNT);

        Assert.assertTrue(smartWagonPage.getAddedToCartText().contains(ADDED_TO_CART));
    }

    @Test(description = "checking that correct message and price is displayed after item is deleted from cart")
    public void removeFromCart() {
        SmartWagonPage smartWagonPage = addItemToCart();

        CartPage cartPage = smartWagonPage.cartButtonClick();

        cartPage.deleteButtonClick();

        Assert.assertEquals(cartPage.getPriceAfterDeleteText().trim(), ZERO_VALUE);

        Assert.assertEquals(cartPage.getDeleteConfirmationText(), YOUR_AMAZON_CART_IS_EMPTY);
    }

    private SmartWagonPage addItemToCart() {
        return new HomePage()
                .open()
                .categoryClick()
                .categoryItemClick()
                .addToCartButtonClick();
    }

}
