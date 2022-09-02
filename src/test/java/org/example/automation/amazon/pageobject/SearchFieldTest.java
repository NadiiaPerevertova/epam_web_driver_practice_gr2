package org.example.automation.amazon;

import org.example.automation.amazon.pageobject.BaseTest;
import org.example.automation.amazon.pageobject.pages.HomePage;
import org.example.automation.amazon.pageobject.pages.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SearchFieldTest extends BaseTest {
    private static final String INCORRECT_INPUT = "890770hhbhb767879zdrjh444:::";
    private static final String CORRECT_INPUT = "ipad";
    private static final String TEXT = "iPad";

    private HomePage home;

    @BeforeMethod
    public void navigate() {
        home = new HomePage();
        home.open();
    }

    @Test
    public void noResultsForIncorrectInformationTest() {
        SearchResultsPage searchResultsPage = home.inputTextAndClickSearchButton(INCORRECT_INPUT);

        String actualResult = searchResultsPage.getResultTextForIncorrectInput();

        // Expected text : No results for 890770hhbhb767879zdrjh444:::.
        String expectedResult = String.format("No results for %s.", INCORRECT_INPUT);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void checkResultsForCorrectInputTest() {

        SearchResultsPage searchResultsPage = home.inputTextAndClickSearchButton(CORRECT_INPUT);

        String actualResult = searchResultsPage.getResultTextForCorrectInput();

        // Expected text : "ipad"
        String expectedResult = "\"" + CORRECT_INPUT + "\"";
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void getTextForAllResultsTest() {

        SearchResultsPage searchResultsPage = home.inputTextAndClickSearchButton(TEXT);

        List<String> allResultsText = searchResultsPage.getTextForAllResults();

        boolean result = false;

        for (String text : allResultsText) {
            if (text.contains(TEXT)) {

                result = true;
                break;
            }
        }
        Assert.assertTrue(result);
    }
}
