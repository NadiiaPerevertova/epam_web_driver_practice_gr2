package org.example.automation.amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.List;

public class VerifyFilterSearchTest extends BaseWebDriverTest{
    @Test
    public void verifyBrandCategory() {
        // test case to check if products shown has Apple word inside to pove that the brand category filter working properly
        webDriver.get("https://www.amazon.com");

        WebElement category= webDriver.findElement(By.xpath("//a[@aria-label='Computers & Accessories']"));
        category.click();

        WebElement brand= webDriver.findElement(By.xpath("//a/span[.='Apple']"));
        brand.click();

        By result = By.xpath("//span[@class='a-size-base-plus a-color-base a-text-normal']");
        List<WebElement> myElements = webDriver.findElements(result);
        int check=0;
        for(WebElement e : myElements) {
            System.out.println(e.getText());
            if (!e.getText().contains("Apple")){
               check++;
            }
        }

        Assert.assertEquals( check,0);

    }


    @Test
    public void verifyPriceRangeCategoryby() {
        // test case to check if price are inside the described range($25-$50)
        //reminder the result will be failed because it's bug from amazon side( already notify Yuliia about it)
        webDriver.get("https://www.amazon.com");

        WebElement category= webDriver.findElement(By.xpath("//a[@aria-label='Computers & Accessories']"));
        category.click();

        WebElement price= webDriver.findElement(By.xpath("//a/span[.='$25 to $50']"));
        price.click();

        By result = By.xpath("//a/span[@class='a-price']/span[@class='a-offscreen']");
        List<WebElement> myElements = webDriver.findElements(result);
        int check=0;
        for(WebElement e : myElements) {
            String num = e.getAttribute("innerHTML");
            double numprice=Double.parseDouble(num.replaceAll("[^0-9]", ""))/100;
            System.out.println(num+"=>"+numprice);
            if(numprice<25||numprice>50){
                check++;
            }
        }
        Assert.assertEquals( check,0);
    }

    @Test
    public void verifySorting() {
        // test case to check if price are sorted in a described setting(High to Low) in this case
        //reminder the result will be failed because it's bug from amazon side
        webDriver.get("https://www.amazon.com");

        WebElement category= webDriver.findElement(By.xpath("//a[@aria-label='Computers & Accessories']"));
        category.click();

        WebElement price= webDriver.findElement(By.xpath("//a/span[.='$25 to $50']"));
        price.click();
        //sorting feature only available after at least 1 category clicked- in this case price range category is choosen

        WebElement featured= webDriver.findElement(By.xpath("//span/span[.='Featured']"));
        featured.click();
        //click featured to show the dropdown

        WebElement sortPrice= webDriver.findElement(By.xpath("//li[@aria-labelledby='s-result-sort-select_2']/a[.='Price: High to Low']"));
        sortPrice.click();

        By result = By.xpath("//a/span[@class='a-price']/span[@class='a-offscreen']");
        List<WebElement> myElements = webDriver.findElements(result);
        List<Double> priceList = new ArrayList<>();
        for(WebElement e : myElements) {
            String num = e.getAttribute("innerHTML");
            double numprice=Double.parseDouble(num.replaceAll("[^0-9]", ""))/100;
            System.out.println(num+"=>"+numprice);
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
                return returnVal;
            }
        }
        return returnVal;

    }
}
