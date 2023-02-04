package com.btcturk.uitest.page.bing;

import com.btcturk.uitest.page.SearchPage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.*;

@Slf4j
public class BingSearchPage extends SearchPage {
    final String pageName = "Bing search";

    public BingSearchPage(WebDriver webDriver, Integer pageNumber) {
        super(webDriver, pageNumber);
        init(webDriver);
    }

    public BingSearchPage(WebDriver webDriver) {
        super(webDriver, 1);
        init(webDriver);
    }

    private void init(WebDriver webDriver) {
        logo = getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[@class='b_logoArea']")));
        searchInputBar = getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//input[@id='sb_form_q']")));
        searchButton = getWebDriverWait().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@class='b_searchboxSubmit']")));
        searchResults = getWebDriverWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//li[@class='b_algo']")));
        searchResultsTitles = getWebDriverWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//li[@class='b_algo']//div[@class='b_title']/h2/a")));
        searchResultsLinks = getWebDriverWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//li[@class='b_algo']//div[@class='b_title']/h2/a")));
        searchResultsDescriptions = getWebDriverWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//li[@class='b_algo']//p")));
        currentPageNavigator = getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//li[@class='b_pag']//a[contains(@class,'sb_pagS')]")));
    }

    public void verifyPage() {
        log.info("Verifying the current \"{}\" page.", pageName);
        assertTrue(logo.isDisplayed());
        log.info("Verifing that current page number is \"{}\".", super.getPageNumber());
        assertEquals(currentPageNavigator.getText(), super.getPageNumber().toString());
    }

    public void clickNextPageButton() {
        log.info("Clicking next page button.");
        nextPageButton = getWebDriverWait().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[@class='b_pag']//a[contains(@class,'sb_pagN')]")));
        getJavascriptExecutor().executeScript("arguments[0].click()", nextPageButton);
    }

}
