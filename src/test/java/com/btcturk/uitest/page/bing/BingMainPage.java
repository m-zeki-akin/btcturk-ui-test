package com.btcturk.uitest.page.bing;

import com.btcturk.uitest.page.MainPage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.*;

@Slf4j
public class BingMainPage extends MainPage {
    private final String pageName = "Google Main";
    public BingMainPage(WebDriver webDriver) {
        super(webDriver);
        logo = getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h1[@class='logo_cont']//*[name()='svg']")));
        searchInputBar = getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(By.id("sb_form_q")));
        searchButton = getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(By.id("search_icon")));
    }

    public void verifyPage() {
        log.info("Verifying the current \"{}\" page.", pageName);
        assertTrue(logo.isDisplayed());
    }

}
