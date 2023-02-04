package com.btcturk.uitest.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {
    private ThreadLocal<WebDriver> webDriver;

    public BaseTest() {
        this.webDriver = new ThreadLocal<>();
    }

    @BeforeTest
    @Parameters("isLocal")
    protected void setup(String isLocal) throws IOException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--ignore-ssl-errors=yes",
                "--ignore-certificate-errors",
                "start-maximized",
                "--no-sandbox",
                "--disable-dev-shm-usage"
        );
        if(isLocal.equalsIgnoreCase("true")){
            WebDriver webDriver = new ChromeDriver(options);
            webDriver.manage().deleteAllCookies();
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(14));
            setWebDriver(webDriver);
        }else {
            options.addArguments("--headless");
            RemoteWebDriver remoteWebDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
            remoteWebDriver.manage().deleteAllCookies();
            remoteWebDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(14));
            setWebDriver(remoteWebDriver);
        }

    }

    @AfterTest
    protected void teardown() {
        getWebDriver().quit();
    }

    public WebDriver getWebDriver() {
        return this.webDriver.get();
    }

    public void setWebDriver(WebDriver webDriver) {
        this.webDriver.set(webDriver);
    }
}
