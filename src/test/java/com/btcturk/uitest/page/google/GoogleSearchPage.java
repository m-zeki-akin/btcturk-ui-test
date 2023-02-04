package com.btcturk.uitest.page.google;

import com.btcturk.uitest.page.SearchPage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.*;

@Slf4j
public class GoogleSearchPage extends SearchPage {
    final String pageName = "Google search";

    public GoogleSearchPage(WebDriver webDriver, Integer pageNumber) {
        super(webDriver, pageNumber);
        init(webDriver);
    }

    public GoogleSearchPage(WebDriver webDriver) {
        super(webDriver, 1);
        init(webDriver);
    }

    private void init(WebDriver webDriver) {
        logo = webDriver.findElement(By.id("logo"));
        searchInputBar = getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@class='gLFyf i4ySpb']/following-sibling::input")));
        searchButton = getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("search")));
        searchResults = getWebDriverWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[@class='kvH3mc BToiNc UK95Uc']")));
        searchResultsTitles = getWebDriverWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[@class='kvH3mc BToiNc UK95Uc']//div[@class='yuRUbf']//h3[@class='LC20lb MBeuO DKV0Md']")));
        searchResultsLinks = getWebDriverWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[@class='kvH3mc BToiNc UK95Uc']/div[@class='Z26q7c UK95Uc jGGQ5e']/div[@class='yuRUbf']/a[@data-ved]")));
        searchResultsDescriptions = getWebDriverWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[@class='kvH3mc BToiNc UK95Uc']//div[@class='VwiC3b yXK7lf MUxGbd yDYNvb lyLwlc lEBKkf']/span[last()]")));
        currentPageNavigator = getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//td[@class='YyVfkd']")));
    }

    public void verifyPage() {
        log.info("Verifying the current \"{}\" page.", pageName);
        assertTrue(logo.isDisplayed());
        log.info("Verifing that current page number is \"{}\".", super.getPageNumber());
        assertEquals(currentPageNavigator.getText(), super.getPageNumber().toString());
    }

    public void clickNextPageButton() {
        log.info("Clicking next page button.");
        getJavascriptExecutor().executeScript("arguments[0].click()", getWebDriverWait().until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[@id='pnnext']//span[@class='SJajHc NVbCr']"))));
    }

}
