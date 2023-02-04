package com.btcturk.uitest.page;

import com.btcturk.uitest.model.SearchItem;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.LinkedList;
import java.util.List;

import static org.testng.Assert.assertEquals;

@Slf4j
@Getter
public abstract class SearchPage extends BasePage{
    private final Integer pageNumber;
    protected WebElement logo;
    protected WebElement searchInputBar;
    protected WebElement searchButton;
    protected List<WebElement> searchResults;
    protected List<WebElement> searchResultsTitles;
    protected List<WebElement> searchResultsLinks;
    protected List<WebElement> searchResultsDescriptions;
    protected WebElement nextPageButton; //lazy load
    protected WebElement currentPageNavigator;

    public SearchPage(WebDriver webDriver) {
        super(webDriver);
        this.pageNumber = 1;
    }

    public SearchPage(WebDriver webDriver, Integer pageNumber) {
        super(webDriver);
        this.pageNumber = pageNumber;
    }

    public void verifyInput(String searchInput) {
        log.info("Verifing that search input has \"{}\" value.", searchInput);
        assertEquals(searchInputBar.getAttribute("value"), searchInput);
    }

    public List<SearchItem> getSearchResults() {
        log.info("Getting search results.");
        List<SearchItem> searchItems = new LinkedList<>();
        // make sure sizes are same. If not there is a xpath issue need to be fixed.
        assertEquals(searchResultsTitles.size(), searchResultsLinks.size());
        assertEquals(searchResultsTitles.size(), searchResultsDescriptions.size());
        for (int i = 0; i < searchResults.size(); i++) {
            String searchResultsTitle = searchResultsTitles.get(i).getText();
            String searchResultsLink = searchResultsLinks.get(i).getAttribute("href");
            String searchResultsDescription = searchResultsDescriptions.get(i).getText();
            log.info("  [{}]Getting search result with -> title: {}, link: {}, description: {}",
                    i, searchResultsTitle, searchResultsLink, searchResultsDescription);
            SearchItem searchItem = new SearchItem(
                    searchResultsTitle,
                    searchResultsLink,
                    searchResultsDescription
            );
            searchItems.add(searchItem);
        }
        return searchItems;
    }

    public abstract void clickNextPageButton(); // lazy load (nextPageButton)

}
