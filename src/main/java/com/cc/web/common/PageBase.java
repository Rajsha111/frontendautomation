package com.cc.web.common;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

public class PageBase {

    public WebDriver driver;

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Waits for a limited amount of time for isDisplayed() to return true.
     *
     * @param element        the element
     * @param timeLimitInSec the time limit in sec
     * @param pollingTimeSec the polling time sec
     * @return true, if successful
     */
    public boolean waitForElementDisplayed(By element, long timeLimitInSec, long pollingTimeSec) {
        setImplicitTimeout(false, driver);
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeLimitInSec, TimeUnit.SECONDS).pollingEvery(pollingTimeSec, TimeUnit.MILLISECONDS)
                    .ignoring(org.openqa.selenium.NoSuchElementException.class).ignoring(StaleElementReferenceException.class);
            return wait.until(dr -> driver.findElement(element).isDisplayed());
        } catch (Exception e) {
            return false;
        } finally {
            setImplicitTimeout(true, driver);
        }
    }

    /**
     * Click on a element using JavaScript.
     *
     * @param element WebElement for click event
     */
    public void clickOnElementUsingJquery(WebElement element) {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }

    /**
     * Waits for a limited amount of time for isDisplayed() to return true.
     *
     * @param expectedTitle  the expectedTitle
     * @param timeLimitInSec the time limit in sec
     * @param pollingTimeSec the polling time sec
     * @return true, if successful
     */
    public boolean waitForTitle(String expectedTitle, long timeLimitInSec, long pollingTimeSec) {
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeLimitInSec, TimeUnit.SECONDS).pollingEvery(pollingTimeSec, TimeUnit.MILLISECONDS)
                    .ignoring(org.openqa.selenium.NoSuchElementException.class).ignoring(StaleElementReferenceException.class);
            return wait.until(dr -> driver.getTitle().equals(expectedTitle));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Waits for a limited amount of time for element not null.
     *
     * @param element        the element
     * @param timeLimitInSec the time limit in sec
     * @param pollingTimeSec the polling time sec
     * @return true, if successful
     */
    public boolean waitForElementNotNul(By element, long timeLimitInSec, long pollingTimeSec) {
        setImplicitTimeout(false, driver);
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeLimitInSec, TimeUnit.SECONDS).pollingEvery(pollingTimeSec, TimeUnit.MILLISECONDS)
                    .ignoring(org.openqa.selenium.NoSuchElementException.class).ignoring(StaleElementReferenceException.class);
            return wait.until(dr -> driver.findElement(element) != null);
        } catch (Exception e) {
            return false;
        } finally {
            setImplicitTimeout(true, driver);
        }
    }

    /**
     * Sometimes it is useful to temporarily disable the implicit timeout to make the tests run
     * faster
     *
     * @param enable True to enable the implicit timeout. False to disable the implicit timeout.
     */
    public static void setImplicitTimeout(boolean enable, WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(enable ? 20 : 0, TimeUnit.SECONDS);
    }

}
