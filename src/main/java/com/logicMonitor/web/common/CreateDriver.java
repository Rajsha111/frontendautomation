package com.logicMonitor.web.common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreateDriver {

    public WebDriver getDriver(String browserType) {
        switch (browserType) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();

            case "firefox":
                WebDriverManager.chromedriver().setup();
                return new FirefoxDriver();
            default:
                break;
        }

        return null;
    }
}
