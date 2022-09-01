package org.example.automation.amazon.pageobject.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CategoryPage {

    public CategoryItemPage categoryItemClick() {
        SelenideElement categoryItem = $(By.xpath("//div[contains(@class, \"aok-relative\")]//img")).shouldBe(Condition.visible);
        categoryItem.click();

        return new CategoryItemPage();
    }

}
