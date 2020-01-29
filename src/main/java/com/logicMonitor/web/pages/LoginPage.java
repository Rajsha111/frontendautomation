package com.logicMonitor.web.pages;

import com.logicMonitor.web.common.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageBase {

    private static final String ID_USERNAME = "identifierId";
    private static final String XPATH_PASSWORD = "//*[@id='password']//input[@type='password']";
    private static final String ID_NEXT = "identifierNext";
    private static final String ID_PASSWORD_NEXT = "passwordNext";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        return driver.getTitle().equals("Sign in – Google accounts");
    }

    private LoginPage enterEmailOrPhone(String emailOrPhone) {
        if (waitForElementDisplayed(By.id(ID_USERNAME), 10, 20)) {
            driver.findElement(By.id(ID_USERNAME)).sendKeys(emailOrPhone);
            return this;
        }
        return null;
    }

    private LoginPage enterPassword(String password) {
        if (waitForElementDisplayed(By.xpath(XPATH_PASSWORD), 10, 20)) {
            driver.findElement(By.xpath(XPATH_PASSWORD)).sendKeys(password);
            return this;
        }
        return null;
    }

    private LoginPage clickOnNext() {
        if (waitForElementDisplayed(By.id(ID_NEXT), 10, 20)) {
            clickOnElementUsingJquery(driver.findElement(By.id(ID_NEXT)));
            return this;
        }
        return null;
    }

    private InboxPage clickPasswordNext() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        if (waitForElementDisplayed(By.id(ID_PASSWORD_NEXT), 10, 20)) {
            clickOnElementUsingJquery(driver.findElement(By.id(ID_PASSWORD_NEXT)));
            return new InboxPage(driver);
        }
        return null;
    }

    public InboxPage login(String userName, String password) {
        enterEmailOrPhone(userName).clickOnNext().enterPassword(password);
        return clickPasswordNext();
    }
}
