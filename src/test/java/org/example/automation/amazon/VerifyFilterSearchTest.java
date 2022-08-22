package org.example.automation.amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class VerifyFilterSearchTest extends BaseWebDriverTest{
    public static final String WEB_ADDRESS="https://www.amazon.com";
    public static final String BRAND_APPLE="Apple";
    public static final String BRAND_ROKU="Roku";

    @FindBy(xpath = "//a[@aria-label='Computers & Accessories']")
    private WebElement filterCategory;

    @FindBy(xpath = "//div[@class='a-section a-spacing-none']/descendant::span[.='"+BRAND_APPLE+"']")
    private WebElement filterBrand;

    @FindBy(xpath = "//div[@class='a-section a-spacing-none']/descendant::span[.='"+BRAND_ROKU+"']")
    private WebElement filterBrandRoku;
    
    @FindBy(xpath = "//a/span[.='$25 to $50']")
    private WebElement filterPrice;
    
    @FindAll({@FindBy(xpath = "//div[@class='s-main-slot s-result-list s-search-results sg-row']/descendant::span[@class='a-size-base-plus a-color-base a-text-normal']")})
    public List<WebElement> productTitleList;

    @FindAll({@FindBy(xpath = "//div[@class='s-main-slot s-result-list s-search-results sg-row']/descendant::a/span[@class='a-price']/span[@class='a-offscreen']")})
    public List<WebElement> productPriceList;

    @FindBy(xpath = "//span[@aria-label='Sort by:']/descendant::span[.='Featured']")
    public WebElement sortFeatured;

    @FindBy(xpath = "//li[@aria-labelledby='s-result-sort-select_2']/a[.='Price: High to Low']")
    public WebElement sortHighToLow;

    @BeforeMethod
    public void setup() {
        PageFactory.initElements(webDriver, this);
        webDriver.get(WEB_ADDRESS);
        filterCategory.click();
    }

    @Test (description = "test case to check if products shown has Apple word inside to " +
            "prove that the brand category filter working properly")
    public void verifyBrandCategory() {
        filterBrand.click();
        int check=0;
        for(WebElement e : productTitleList) {
            if (!e.getText().contains(BRAND_APPLE)){
               check++;
            }
        }
        Assert.assertEquals( check,0);
    }

    @Test (description = "test case to check if multiple brand category filter working properly")
    public void verifyMultipleBrandCategory() {
        filterBrand.click();
        filterBrandRoku.click();
        boolean check= true;
        for(WebElement e : productTitleList) {
            if (e.getText().contains(BRAND_APPLE)||e.getText().contains(BRAND_ROKU)){
            } else{
                check=false;
                break;
            }
        }
        Assert.assertTrue( check);
    }

    @Test(description = "positive test case to check if price are inside the described range($25-$50)"+
    "reminder the result will be failed because it's bug from amazon side( already notify Yuliia about it)")
    public void verifyPriceRangeCategoryby() {
        filterPrice.click();
        boolean inRange=true;
        for(WebElement e : productPriceList) {
            String num = e.getAttribute("innerHTML");
            double numprice=Double.parseDouble(num.replaceAll("[^0-9]", ""))/100;
            if(numprice<25||numprice>50){
                System.err.println(numprice+" out of range");
                inRange=false;
                break;
            }
        }
        Assert.assertTrue(inRange);
    }

    @Test(description = "positive test case to check if price are sorted in a described setting(High to Low) in this case"+
    "reminder the result will be failed because it's bug from amazon side")
    public void verifySorting() {
        filterPrice.click();        
        //sorting feature only available after at least 1 category clicked- in this case price range category is choosen
        //click featured to show the dropdown
        sortFeatured.click();
        //click the chosen sort mode
        sortHighToLow.click();

        List<Double> priceList = new ArrayList<>();
        for(WebElement e : productPriceList) {
            String num = e.getAttribute("innerHTML");
            double numprice=Double.parseDouble(num.replaceAll("[^0-9]", ""))/100;
            priceList.add(numprice);
        }
        boolean checkSort=isSorted(priceList,priceList.size());
        Assert.assertTrue(checkSort);
    }

    public static boolean isSorted(List<Double> listOfDouble, int index) {
        if (index < 2) {
            return true;
        }
        boolean returnVal=true;
        for (int i = 0; i < (index-2); i++) {
            if (listOfDouble.get(i + 1).compareTo(listOfDouble.get(i)) <= 0) {
                // less than 0 mean that  first value is less than the second  value
                //which mean the sort high to low is incorrect
                returnVal=false;
                break;
            }
        }
        return returnVal;

    }
}
