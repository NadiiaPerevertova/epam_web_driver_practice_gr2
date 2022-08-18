package org.example.automation.amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.NumberFormat;
import java.util.List;

public class VerifyFilterSearchTest extends BaseWebDriverTest{
    @Test
    public void verifyBrandCategory() {
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
        webDriver.get("https://www.amazon.com");

        WebElement category= webDriver.findElement(By.xpath("//a[@aria-label='Computers & Accessories']"));
        category.click();

        WebElement price= webDriver.findElement(By.xpath("//li[@aria-labelledby=\"s-result-sort-select_2\"]/a[.=\"Price: High to Low\"]"));
        price.click();

        By result = By.xpath("//a/span[@class='a-price']/span[@class='a-offscreen']");
        List<WebElement> myElements = webDriver.findElements(result);
        int check=0;
        for(WebElement e : myElements) {
            String num = e.getAttribute("innerHTML");
            double numprice=Double.parseDouble(num.replaceAll("[^0-9]", ""))/100;
            System.out.println(num+"=>"+numprice);
        }
        Assert.assertEquals( check,0);
    }
}
