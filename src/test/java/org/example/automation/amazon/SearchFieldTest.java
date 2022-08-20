package org.example.automation.amazon;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchFieldTest extends BaseWebDriverTest {

    public static final String INCORRECT_INPUT = "890770hhbhb767879zdrjh444:::";

    @FindBy(css = ".nav-search-field input")
    private WebElement searchField;

    @FindBy(xpath = "//input[@id=\"nav-search-submit-button\"]")
    private WebElement searchButton;

    //890770hhbhb767879zdrjh444:::
    @FindBy(xpath = "//div[@tabindex = 0]/div[@class='a-row']")
    private WebElement searchResult;

    @Test
    public void noResultsForIncorrectInformationTest() {

        PageFactory.initElements(webDriver, this);
        webDriver.get("https://www.amazon.com");

        searchField.sendKeys(INCORRECT_INPUT);


        searchButton.click();

        String actualResult = searchResult.getText();

        String expectedResult = "No results for " + INCORRECT_INPUT + ".";

        Assert.assertEquals(actualResult, expectedResult);
    }


}
