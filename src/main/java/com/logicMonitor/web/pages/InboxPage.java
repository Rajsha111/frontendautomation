package com.logicMonitor.web.pages;

import com.logicMonitor.web.common.Constant;
import com.logicMonitor.web.common.PageBase;
import com.logicMonitor.web.common.PropertyManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InboxPage extends PageBase {

    private static final String CSS_HEADER = "table > tbody > tr[role='tablist'] > td >div[aria-label='%s']";
    private static final String CSS_MAIL_SUBJECT = "div.ae4.aDM[style=''] table.F.cf.zt tr:nth-child(%d)  >td > div.yW> span:nth-child(1)";
    private static final String CSS_MAIL_TOTAL_COUNT = "div[aria-label='Show more messages']> span >span:nth-child(2)";
    private static final String CSS_MAIL_ROW = "div.ae4.aDM[style=''] table.F.cf.zt tr";
    private static final String CSS_MAIL_ADVERTISE = "div.ae4.aDM[style=''] table.F.cf.zt tr:nth-child(%d)  >td > div.yW> span:nth-child(2)";

    public InboxPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        return waitForTitle("Inbox - " + PropertyManager.getInstance().getValue("user") + " - Gmail", 10, 5);
    }

    public boolean isHeaderVisible(Constant.Header header) {
        return driver.findElement(By.cssSelector(String.format(CSS_HEADER, header.toString()))).getAttribute("aria-selected").equals("true");
    }

    public void clickOnHeader(Constant.Header header) {
        if (!isHeaderVisible(header))
            driver.findElement(By.cssSelector(String.format(CSS_HEADER, header.toString()))).click();
    }

    public String getSubject(int index) {
        if (getEmailCountOnScreen() > index)
            return driver.findElement(By.cssSelector(String.format(CSS_MAIL_SUBJECT, index))).getText();
        else
            return Constant.EMAIL_NOT_PRESENT.toUpperCase();
    }

    public String getSender(int index) {
        if (getEmailCountOnScreen() < index) {
            return Constant.EMAIL_NOT_PRESENT.toUpperCase();
        } else if (isAdvertiseVisible(index)) {
            return Constant.NO_SENDER_MESSAGE_WHEN_ADD.toUpperCase();
        } else {
            return driver.findElement(By.cssSelector(String.format(CSS_MAIL_SUBJECT, index))).getAttribute("email");
        }
    }

    private int getEmailCountOnScreen() {
        return driver.findElements(By.cssSelector(CSS_MAIL_ROW)).size();
    }

    private boolean isMailBoxEmpty() {
        return !waitForElementNotNul(By.cssSelector(CSS_MAIL_TOTAL_COUNT), 2, 5) ? getEmailCountOnScreen() > 0 : true;
    }

    private boolean isAdvertiseVisible(int index) {
        return waitForElementNotNul(By.cssSelector(String.format(CSS_MAIL_ADVERTISE, index)), 2, 5);
    }

    public int getEmailCount() {
        if (!isMailBoxEmpty())
            return 0;
        else if (waitForElementNotNul(By.cssSelector(CSS_MAIL_TOTAL_COUNT), 2, 5))
            return driver.findElements(By.cssSelector(CSS_MAIL_TOTAL_COUNT)).size();

        else if (getEmailCountOnScreen() > 0)
            return getEmailCountOnScreen();

        else return 0;
    }

}
