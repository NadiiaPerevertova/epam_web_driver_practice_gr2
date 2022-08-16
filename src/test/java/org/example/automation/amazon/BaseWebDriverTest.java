package org.example.automation.amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;

public abstract class BaseWebDriverTest {

    @BeforeClass
    public static void setupWebDriverPath() throws URISyntaxException {
        URL driverResourceUrl = BaseWebDriverTest.class.getClassLoader().getResource("webdriver/chromedriver_104.exe");
        String driverAbsolutePath = Paths.get(Objects.requireNonNull(driverResourceUrl).toURI()).toFile().getAbsolutePath();
        System.setProperty("webdriver.chrome.driver", driverAbsolutePath);
    }

    protected WebDriver webDriver;

    @BeforeMethod
    public void runChromeWebDriver() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
    }

    @AfterMethod
    public void closeChromeWebDriver() {
        webDriver.close();
        webDriver.quit();
    }
}
