package org.example.automation.amazon.pageobject.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResultsPage {

    public ItemDetailsPage chooseFirstResult() {
        SelenideElement categoryResults = $(By.xpath("//span[@data-component-type=\"s-search-results\"]//img[1]")).shouldBe(Condition.visible);
        categoryResults.click();
        return new ItemDetailsPage();
    }

    public String getResultTextForIncorrectInput() {

        SelenideElement searchResult = $(By.xpath("//div[@tabindex = 0]/div[@class='a-row']")).shouldBe(Condition.visible);

        return searchResult.getText();
    }

    public String getResultTextForCorrectInput() {

        SelenideElement searchResult = $(By.xpath("//span[@class =\"a-color-state a-text-bold\"]")).shouldBe(Condition.visible);

        return searchResult.getText();
    }

    public List<String> getTextForAllResults() {

        List<SelenideElement> elementList = $$(By.xpath("//a/span[@class ='a-size-medium a-color-base a-text-normal']"));

        List<String> elementsText = elementList.stream().map(SelenideElement::getText).collect(Collectors.toList());

        return elementsText;
    }
}
