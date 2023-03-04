package com.btcturk.uitest.page;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Slf4j
public abstract class MainPage extends BasePage{
    protected WebElement logo;
    protected WebElement searchInputBar;
    protected WebElement searchButton;
    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void enterInputToSearchBar(String searchInput) {
        log.info("Enter \"{}\" input to search bar.", searchInput);
        searchInputBar.sendKeys(searchInput);
    }

    public void clickSearchButton() {
        log.info("Click search button.");
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }
}
