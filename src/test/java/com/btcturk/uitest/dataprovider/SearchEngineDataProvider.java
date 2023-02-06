package com.btcturk.uitest.dataprovider;

import org.testng.annotations.DataProvider;

public class SearchEngineDataProvider {

    @DataProvider(name = "search-inputs")
    public Object[][] searchInputs(){
        return new Object[][]{
                {"bitcoin"},
                {"etherium"},
                {"tether"},
                {"XRP"},
                {"Cardano"}
        };
    }
}
