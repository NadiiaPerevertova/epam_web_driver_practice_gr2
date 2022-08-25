package org.example.automation.amazon.pageobject.pages;

import com.codeborne.selenide.Selenide;

public class HomePage {

    public HomePage open() {
        Selenide.open("/");
        return this;
    }
}
