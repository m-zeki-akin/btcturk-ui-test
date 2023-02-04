package com.btcturk.uitest.page.google;

import com.btcturk.uitest.page.MainPage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.*;

@Slf4j
@Getter
public class GoogleMainPage extends MainPage {
    private final String pageName = "Google Main";
    public GoogleMainPage(WebDriver webDriver) {
        super(webDriver);
        logo = getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@class='k1zIA rSk4se']/img")));
        searchInputBar = getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@class='YacQv']/following-sibling::input")));
        searchButton = getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@class='lJ9FBc']//input[@name='btnK']")));
    }

    public void verifyPage() {
        log.info("Verifying the current \"{}\" page.", pageName);
        assertTrue(logo.isDisplayed());
    }

}
