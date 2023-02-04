package com.btcturk.uitest.page;

import lombok.Getter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public abstract class BasePage{
    private final WebDriver webDriver;
    private final JavascriptExecutor javascriptExecutor;
    private final WebDriverWait webDriverWait;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.javascriptExecutor = (JavascriptExecutor)webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    public abstract void verifyPage();
}
