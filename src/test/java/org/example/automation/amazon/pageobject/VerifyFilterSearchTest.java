package org.example.automation.amazon.pageobject;

import org.example.automation.amazon.pageobject.pages.HomePage;
import org.example.automation.amazon.pageobject.LogicElement.WebElementListProcessor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyFilterSearchTest extends BaseTest {
    private final String CATEGORY = "Computers & Accessories";
    private final String BRAND1 = "Apple";
    private final String BRAND2 = "Roku";
    private final int LOWLIMIT = 25;
    private final int UPLIMIT = 50;
    private final String SORTMODE = "Price: High to Low";


    @Test(description = "test case to check if products shown has Apple word inside to " +
            "prove that the brand category filter working properly")
    public void verifyOneBrandCategory() {
        HomePage home = new HomePage();
        WebElementListProcessor elementIterator = new WebElementListProcessor();
        int sameBrandCheck = elementIterator.checkNotSame1Brand(
                home
                        .open()
                        .chooseShopbyCategory(CATEGORY)
                        .getBrandlist()
                , BRAND1
        );
        Assert.assertEquals(sameBrandCheck, 0, "Expected 0: There should be (0) no item that doesn't has the speficied brand");
    }

    @Test(description = "test case to check if multiple brand category filter working properly")
    public void verifyTwoBrandCategory() {
        HomePage home = new HomePage();
        WebElementListProcessor elementIterator = new WebElementListProcessor();
        boolean sameBrandCheck = elementIterator.checkNotSame2Brand(
                home
                        .open()
                        .chooseShopbyCategory(CATEGORY)
                        .getBrandlist()
                , BRAND1, BRAND2
        );
        Assert.assertTrue(sameBrandCheck, "Expected true: True will be obtained if all item in search result have either Apple / Roku in their product title");
    }

    @Test(description = "positive test case to check if price are inside the described range($25-$50)" +
            "reminder the result will be failed because it's bug from amazon side( already notify Yuliia about it)")
    public void verifyPriceRangeCategoryby() {
        HomePage home = new HomePage();
        WebElementListProcessor elementIterator = new WebElementListProcessor();

        boolean sameBrandCheck = elementIterator.checkInRange(
                home
                        .open()
                        .chooseShopbyCategory(CATEGORY)
                        .choosePriceRange(LOWLIMIT, UPLIMIT)
                        .getPricelist()
                , LOWLIMIT, UPLIMIT
        );
        Assert.assertTrue(sameBrandCheck, "Expected true: True will be obtained if all item price is in range");
    }

    @Test(description = "positive test case to check if price are inside the described range($25-$50)" +
            "reminder the result will be failed because it's bug from amazon side( already notify Yuliia about it)")
    public void verifyPriceSortingCategoryby() {
        HomePage home = new HomePage();
        WebElementListProcessor elementIterator = new WebElementListProcessor();
        boolean sameBrandCheck = elementIterator.checkSorted(
                home
                        .open()
                        .chooseShopbyCategory(CATEGORY)
                        .choosePriceRange(LOWLIMIT, UPLIMIT)
                        .chooseSortFeatured()
                        .chooseSort(SORTMODE)
                        .getPricelist()
        );
        Assert.assertTrue(sameBrandCheck, "Expected true: True will be obtained if product price list is sorted");
    }


}
