package com.btcturk.uitest.test;

import com.btcturk.uitest.dataprovider.SearchEngineDataProvider;
import com.btcturk.uitest.model.SearchItem;
import com.btcturk.uitest.page.SearchPage;
import com.btcturk.uitest.page.google.GoogleMainPage;
import com.btcturk.uitest.page.google.GoogleSearchPage;
import com.btcturk.uitest.page.bing.BingMainPage;
import com.btcturk.uitest.page.bing.BingSearchPage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class SearchEngineTest extends BaseTest {

    @Test(dataProvider = "search-inputs", dataProviderClass = SearchEngineDataProvider.class)
    public void should_return_common_search_items_when_compare_google_and_bing_search_engines_with_same_searched_keyword(String searchInput) {
        // given
        log.info("Searching -> {}", searchInput);
        final int searchResultCount = 10;

        WebDriver webDriver = getWebDriver();
        webDriver.get("https://www.google.com.tr/");

        // google search
        GoogleMainPage googleMainPage = new GoogleMainPage(webDriver);
        googleMainPage.verifyPage();
        googleMainPage.enterInputToSearchBar(searchInput);
        googleMainPage.clickSearchButton();

        GoogleSearchPage googleSearchPage = new GoogleSearchPage(webDriver);
        googleSearchPage.verifyPage();
        googleSearchPage.verifyInput(searchInput);
        Set<SearchItem> googleSearchItemList = Set.copyOf(findSearchItems(googleSearchPage, searchResultCount));

        // bing search
        webDriver.navigate().to("https://bing.com/");
        BingMainPage bingMainPage = new BingMainPage(webDriver);
        bingMainPage.verifyPage();
        bingMainPage.enterInputToSearchBar(searchInput);
        bingMainPage.clickSearchButton();

        BingSearchPage bingSearchPage = new BingSearchPage(webDriver);
        bingSearchPage.verifyPage();
        bingSearchPage.verifyInput(searchInput);
        Set<SearchItem> bingSearchItemList = Set.copyOf(findSearchItems(bingSearchPage, searchResultCount));

        // find commons between bing and google
        Set<SearchItem> commonsSearchItemList = bingSearchItemList.stream()
                .filter(a -> googleSearchItemList.stream().anyMatch(b -> b.getLink().equals(a.getLink())))
                .collect(Collectors.toSet());
        log.info("Common items: \"{}\"", commonsSearchItemList);
        log.info("Search completed for -> {}", searchInput);
    }

    private List<SearchItem> findSearchItems(SearchPage searchPage, final int count) {
        List<SearchItem> searchItems = new LinkedList<>();
        while (searchItems.size() < count) {
            List<SearchItem> tmpSearchItems = searchPage.getSearchResults();
            // make sure that newly adding search items are not exceeding required count.
            if(searchItems.size() + tmpSearchItems.size() <= count){
                searchItems.addAll(tmpSearchItems);
            }else {
                for (int i = 0; i < (count - searchItems.size()); i++){
                    searchItems.add(tmpSearchItems.get(i));
                }
            }

            // after adding of new search items if we're not finish we have to change the page.
            int countLeft = count - searchItems.size();
            if (countLeft > 0) {
                searchPage.clickNextPageButton();
                if (searchPage instanceof GoogleSearchPage) {
                    searchPage = new GoogleSearchPage(getWebDriver(), searchPage.getPageNumber() + 1);

                } else if (searchPage instanceof BingSearchPage) {
                    searchPage = new BingSearchPage(getWebDriver(), searchPage.getPageNumber() + 1);

                }
                searchPage.verifyPage();
            }
            // implementing some additional controls.
            else if (countLeft == 0) {
                break;
            }
            else {
                for (int i = 0; i > countLeft; i--) {
                    log.info("  [{}]Removing extra cached search item.", searchItems.size() - 1);
                    searchItems.remove(searchItems.size() - 1);
                }
                break;
            }
        }
        // it is done, lets log
        log.info("All \"{}\" search items cached:", count);
        for (SearchItem s: searchItems) {
            log.info("  title: {}, link: {}, description: {}",
                    s.getTitle(), s.getLink(), s.getDescription());
        }
        return searchItems;
    }

}
