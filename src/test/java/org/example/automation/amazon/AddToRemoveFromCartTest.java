package org.example.automation.amazon;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddToRemoveFromCartTest extends BaseWebDriverTest {
    public static final String SITE_URL = "https://www.amazon.com";
    public static final String ADDED_TO_CART = "Added to Cart";
    public static final String ZERO_VALUE = "$0.00";
    public static final String YOUR_AMAZON_CART_IS_EMPTY = "Your Amazon Cart is empty.";
    public static final String CART_COUNT = "1";
    public static final int WAIT_SECONDS = 10;

    @FindBy(xpath = "//div[contains(@class,\"a-section a-spacing-none fluid-image-container\")]//img")
    private WebElement category;

    @FindBy(xpath = "//div[contains(@class, \"aok-relative\")]//img")
    private WebElement categoryItem;

    @FindBy(id = "add-to-cart-button")
    private WebElement addToCartButton;

    @FindBy(id = "nav-cart-count")
    private WebElement navCartCount;

    @FindBy(id = "sw-atc-details-single-container")
    private WebElement addToCartConfirmation;

    @FindBy(id = "nav-cart")
    private WebElement navCartButton;

    @FindBy(xpath = "//input[contains(@value,\"Delete\")]")
    private WebElement deleteButton;

    @FindBy(id = "sc-subtotal-amount-activecart")
    private WebElement priceAfterDelete;

    @FindBy(tagName = "h1")
    private WebElement deleteConfirmation;

    @BeforeMethod
    public void setup() {
        PageFactory.initElements(webDriver, this);
    }

    @Test
    public void addToCart() {
        addItemToCart();

        Assert.assertEquals(navCartCount.getText(), CART_COUNT);

        Assert.assertTrue(addToCartConfirmation.getText().contains(ADDED_TO_CART));
    }

    @Test
    public void removeFromCart() {
        addItemToCart();

        navCartButton.click();

        deleteButton.click();

        waitUntilVisible(priceAfterDelete);

        Assert.assertEquals(priceAfterDelete.getText().trim(), ZERO_VALUE);

        Assert.assertEquals(deleteConfirmation.getText(), YOUR_AMAZON_CART_IS_EMPTY);
    }

    private void addItemToCart() {
        webDriver.get(SITE_URL);

        category.click();

        categoryItem.click();

        addToCartButton.click();
    }

    private void waitUntilVisible(WebElement webElement) {
        new WebDriverWait(webDriver, Duration.ofSeconds(WAIT_SECONDS))
                .until(ExpectedConditions.visibilityOf(webElement));
    }

}