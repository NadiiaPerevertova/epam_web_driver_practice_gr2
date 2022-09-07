package org.example.automation.amazon.pageobject;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public abstract class BaseTest {

    @BeforeClass
    public static void setup() {
        Configuration.baseUrl = "https://www.amazon.com";
        Configuration.browser = "chrome";
    }

    @AfterClass
    public static void close() {
        closeWebDriver();
    }
}
