package org.example.automation.amazon.pageobject.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class SearchResultsPage {

    public ItemDetailsPage chooseFirstResult() {
        SelenideElement categoryResults = $(By.xpath("//span[@data-component-type=\"s-search-results\"]//img[1]")).shouldBe(Condition.visible);
        categoryResults.click();
        return new ItemDetailsPage();
    }
    public List<WebElement> getBrandlist(){
        return WebDriverRunner.getWebDriver().findElements(By.xpath("//div[@class='s-main-slot s-result-list s-search-results sg-row']/descendant::span[@class='a-size-base-plus a-color-base a-text-normal']"));
    }
    public List<WebElement> getPricelist(){
        return WebDriverRunner.getWebDriver().findElements(By.xpath("//div[@class='s-main-slot s-result-list s-search-results sg-row']/descendant::a/span[@class='a-price']/span[@class='a-offscreen']"));
    }

    public SearchResultsPage choosePriceRange(int lowLimit, int upLimit) {
        SelenideElement shopCategory = $(By.xpath("//a/span[.='$"+lowLimit+" to $"+upLimit+"']")).shouldBe(Condition.visible);
        shopCategory.click();
        return  this;
    }

    public SearchResultsPage chooseSortFeatured() {
        SelenideElement shopSort = $(By.xpath("//span[@aria-label='Sort by:']/descendant::span[.='Featured']")).shouldBe(Condition.visible);
        shopSort.click();
        return  this;
    }
    public SearchResultsPage chooseSort(String sortMode) {
        SelenideElement shopSort = $(By.xpath("//li[@aria-labelledby='s-result-sort-select_2']/a[.='"+sortMode+"']")).shouldBe(Condition.visible);
        shopSort.click();
        return  this;
    }
}
