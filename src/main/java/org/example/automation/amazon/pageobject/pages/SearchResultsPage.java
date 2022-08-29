package org.example.automation.amazon.pageobject.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SearchResultsPage {

    public ItemDetailsPage chooseFirstResult() {
        SelenideElement categoryResults = $(By.xpath("//span[@data-component-type=\"s-search-results\"]//img[1]")).shouldBe(Condition.visible);
        categoryResults.click();
        return new ItemDetailsPage();
    }
}
