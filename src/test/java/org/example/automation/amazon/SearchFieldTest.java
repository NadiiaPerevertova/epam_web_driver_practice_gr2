package org.example.automation.amazon;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SearchFieldTest extends BaseWebDriverTest {
    public static final String INCORRECT_INPUT = "890770hhbhb767879zdrjh444:::";
    public static final String PRESENT = "ipad";
    public static final String TEXT = "iPad";

    @FindBy(css = ".nav-search-field input")
    private WebElement searchField;

    @FindBy(xpath = "//input[@id=\"nav-search-submit-button\"]")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@tabindex = 0]/div[@class='a-row']")
    private WebElement searchResult;

    @FindBy(xpath = "//span[@class =\"a-color-state a-text-bold\"]")
    private WebElement searchResultPresent;

    @FindAll({@FindBy(xpath = "//a/span[@class ='a-size-medium a-color-base a-text-normal']")})
    public List<WebElement> searchResultList;

    @BeforeMethod
    public void setup() {
        PageFactory.initElements(webDriver, this);
        webDriver.get("https://www.amazon.com");
    }
    @Test
    public void noResultsForIncorrectInformationTest() {

        searchField.sendKeys(INCORRECT_INPUT);

        searchButton.click();

        String actualResult = searchResult.getText();

        String expectedResult = "No results for " + INCORRECT_INPUT + ".";

        Assert.assertEquals(actualResult, expectedResult);

    }
    @Test
    public void checkResultsForPresentTest() {

        searchField.sendKeys(PRESENT);

        searchButton.click();

        String actualResult = searchResultPresent.getText();

        String expectedResult = "\"" + PRESENT + "\"";

        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void checkResultsForTextTest() {

        searchField.sendKeys(PRESENT);

        searchButton.click();

        boolean result = false;

        for (WebElement element : searchResultList) {
            if (element.getText().contains(TEXT)) {

                result = true;
                break;
            }
        }
        Assert.assertTrue(result);
    }
}




