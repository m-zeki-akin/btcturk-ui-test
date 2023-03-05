package com.btcturk.uitest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.annotations.Parameters;

public class SuiteListener implements ISuiteListener {

    private final Browser browser;

    @Parameters("browser")
    public SuiteListener(Browser browser) {
        this.browser = browser;
    }

    @Override
    public void onStart(ISuite suite) {
        ISuiteListener.super.onStart(suite);
        switch (browser){
            case CHROME -> WebDriverManager.chromedriver().setup();
            case FIREFOX -> WebDriverManager.firefoxdriver().setup();
            case EDGE -> WebDriverManager.edgedriver().setup();
        }
    }
}
