package org.example.automation.amazon.pageobject;

import org.example.automation.amazon.pageobject.pages.HomePage;
import org.example.automation.amazon.pageobject.LogicElement.WebElementListProcessor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyFilterSearchTest extends BaseTest{

    @Test(description = "test case to check if products shown has Apple word inside to " +
            "prove that the brand category filter working properly")
    public void verifyOneBrandCategory(){
        HomePage home= new HomePage();
        WebElementListProcessor elementIterator= new WebElementListProcessor();
        int sameBrandCheck=elementIterator.checkNotSame1Brand(
                home
                        .open()
                        .chooseShopbyCategory("Computers & Accessories")
                        .getBrandlist()
                ,"Apple"
        );
        Assert.assertEquals(sameBrandCheck, 0,"Expected 0: There should be (0) no item that doesn't has the speficied brand");
    }

    @Test(description = "test case to check if multiple brand category filter working properly")
    public void verifyTwoBrandCategory(){
        HomePage home= new HomePage();
        WebElementListProcessor elementIterator= new WebElementListProcessor();
        boolean sameBrandCheck=elementIterator.checkNotSame2Brand(
                home
                        .open()
                        .chooseShopbyCategory("Computers & Accessories")
                        .getBrandlist()
                ,"Apple","Roku"
        );
        Assert.assertTrue(sameBrandCheck,"Expected true: True will be obtained if all item in search result have either Apple / Roku in their product title");
    }

    @Test(description = "positive test case to check if price are inside the described range($25-$50)"+
            "reminder the result will be failed because it's bug from amazon side( already notify Yuliia about it)")
    public void verifyPriceRangeCategoryby() {
        HomePage home= new HomePage();
        WebElementListProcessor elementIterator= new WebElementListProcessor();

        boolean sameBrandCheck=elementIterator.checkInRange(
                home
                        .open()
                        .chooseShopbyCategory("Computers & Accessories")
                        .choosePriceRange(25,50)
                        .getPricelist()
                ,25,50
        );
        Assert.assertTrue(sameBrandCheck,"Expected true: True will be obtained if all item price is in range");
    }

    @Test(description = "positive test case to check if price are inside the described range($25-$50)"+
            "reminder the result will be failed because it's bug from amazon side( already notify Yuliia about it)")
    public void verifyPriceSortingCategoryby() {
        HomePage home= new HomePage();
        WebElementListProcessor elementIterator= new WebElementListProcessor();
        boolean sameBrandCheck=elementIterator.checkSorted(
                home
                        .open()
                        .chooseShopbyCategory("Computers & Accessories")
                        .choosePriceRange(25,50)
                        .chooseSortFeatured()
                        .chooseSort("Price: High to Low")
                        .getPricelist()
        );
        Assert.assertTrue(sameBrandCheck,"Expected true: True will be obtained if product price list is sorted");
    }


}
