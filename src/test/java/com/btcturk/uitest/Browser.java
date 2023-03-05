package com.btcturk.uitest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public enum Browser {

    CHROME {
        public ChromeOptions options() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments(
                    "--disable-extensions",
                    "--ignore-ssl-errors=yes",
                    "--ignore-certificate-errors",
                    "--start-maximized",
                    "--no-sandbox",
                    "--disable-dev-shm-usage",
                    "--incognito",
                    "--headless"
            );
            options.setHeadless(true);
            return options;
        }

        public WebDriver driver() {
            return new ChromeDriver(options());
        }
    },

    FIREFOX {
        public FirefoxOptions options() {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments(
                    "-width=1280",
                    "-height=720",
                    "--headless"
            );
            options.addPreference("dom.webnotifications.enabled", false);
            return options;
        }
        public WebDriver driver() {
            return new FirefoxDriver(options());
        }

    },

    EDGE {
        public EdgeOptions options() {
            EdgeOptions options = new EdgeOptions();
            options.addArguments(
                    "start-maximized",
                    "disable-notifications",
                    "--headless"
            );
            return options;
        }

        public WebDriver driver() {
            return new EdgeDriver(options());
        }
    };

    @SuppressWarnings("rawtypes")
    public abstract AbstractDriverOptions options();
    public abstract WebDriver driver();
}