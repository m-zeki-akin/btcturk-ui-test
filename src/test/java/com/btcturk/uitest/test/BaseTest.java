package com.btcturk.uitest.test;

import com.btcturk.uitest.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class BaseTest {
    private final ThreadLocal<WebDriver> webDriver;
    private static final String HUB_HOST = System.getenv("SELENIUM_HUB_HOST");
    private static final String HUB_PORT = System.getenv("SELENIUM_HUB_PORT");
    private static final String HUB_URL = "http://" + HUB_HOST + ":" + HUB_PORT + "/wd/hub";

    public BaseTest() {
        this.webDriver = new ThreadLocal<>();
    }

    @Parameters("browser")
    @BeforeTest
    protected void setup(Browser browser) throws IOException {
        System.out.println("LOGGING -> " + HUB_URL);
        System.out.println("LOGGING -> " + browser.toString());
        if (Objects.nonNull(HUB_HOST)) {
            setWebDriver(new RemoteWebDriver(new URL(HUB_URL), browser.options()));
        } else {
            setWebDriver(browser.driver());
        }
    }

    @AfterTest
    protected void teardown() {
        getWebDriver().quit();
        webDriver.remove();
    }

    public WebDriver getWebDriver() {
        return this.webDriver.get();
    }

    public void setWebDriver(WebDriver webDriver) {
        this.webDriver.set(webDriver);
    }
}
