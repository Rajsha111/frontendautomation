package com.logicMonitor;

import com.logicMonitor.web.common.CreateDriver;
import com.logicMonitor.web.common.PropertyManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    protected WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void initializeDriver() {
        CreateDriver createDriver = new CreateDriver();
        driver = createDriver.getDriver(PropertyManager.getInstance().getBrowserName());
    }

    @AfterSuite(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }
}
