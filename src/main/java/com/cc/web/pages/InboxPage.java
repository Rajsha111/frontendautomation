package com.cc.web.pages;

import com.cc.web.common.Constant;
import com.cc.web.common.PageBase;
import com.cc.web.common.PropertyManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InboxPage extends PageBase {

    private static final String CSS_HEADER = "table > tbody > tr[role='tablist'] > td >div[aria-label='%s']";
    private static final String CSS_MAIL_SUBJECT = "div.ae4.aDM[style=''] table.F.cf.zt tr:nth-child(%d)  >td > div.yW> span:nth-child(1)";
    private static final String CSS_MAIL_TOTAL_COUNT = "div[aria-label='Show more messages']> span >span:nth-child(2)";
    private static final String CSS_MAIL_ROW = "div.ae4.aDM[style=''] table.F.cf.zt tr";
    private static final String CSS_MAIL_ADVERTISE = "div.ae4.aDM[style=''] table.F.cf.zt tr:nth-child(%d)  >td > div.yW> span:nth-child(2)";
    private static final String ID_LOGOUT = "gb_71";

    public InboxPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Verify inbox page loaded
     *
     * @return True if inbox page loaded
     */
    public boolean isPageLoaded() {
        return waitForTitle(PropertyManager.getInstance().getEmail() + " - Gmail", 10, 5);
    }

    /**
     * Verify header tab is selected
     *
     * @param header
     * @return True if given header tab is selected
     */
    public boolean isHeaderVisible(Constant.Header header) {
        return driver.findElement(By.cssSelector(String.format(CSS_HEADER, header.toString()))).getAttribute("aria-selected").equals("true");
    }

    /**
     * Click on given header tab if respective tab is selected
     *
     * @param header
     */
    public void clickOnHeader(Constant.Header header) {
        if (!isHeaderVisible(header))
            driver.findElement(By.cssSelector(String.format(CSS_HEADER, header.toString()))).click();
    }

    /**
     * Get the subject for the given mail index if total mail are greater than the given index else return Constant.EMAIL_NOT_PRESENT
     *
     * @param index
     * @return Subject for the given mail index
     */
    public String getSubject(int index) {
        if (getEmailCountOnScreen() > index)
            return driver.findElement(By.cssSelector(String.format(CSS_MAIL_SUBJECT, index))).getText();
        else
            return Constant.EMAIL_NOT_PRESENT.toUpperCase();
    }

    /**
     * Get the sender name for the given mail index if total mails are greater than the given index and if mail is advertise return  else return Constant.NO_SENDER_MESSAGE_WHEN_ADD
     *
     * @param index
     * @return Subject for the given mail index
     */
    public String getSender(int index) {
        if (getEmailCountOnScreen() < index) {
            return Constant.EMAIL_NOT_PRESENT.toUpperCase();
        } else if (isAdvertiseVisible(index)) {
            return Constant.NO_SENDER_MESSAGE_WHEN_ADD.toUpperCase();
        } else {
            return driver.findElement(By.cssSelector(String.format(CSS_MAIL_SUBJECT, index))).getAttribute("email");
        }
    }

    /**
     * Get email count on the visible screen
     *
     * @return no of email present in the screen
     */
    private int getEmailCountOnScreen() {
        return driver.findElements(By.cssSelector(CSS_MAIL_ROW)).size();
    }

    /**
     * Check if mail box is empty
     *
     * @return True if mail box is empty
     */
    private boolean isMailBoxEmpty() {
        return waitForElementNotNul(By.cssSelector(CSS_MAIL_TOTAL_COUNT), 2, 5) || getEmailCountOnScreen() > 0;
    }

    /**
     * Check whether the given mail is add
     *
     * @return True if given mail is add
     */
    private boolean isAdvertiseVisible(int index) {
        return waitForElementNotNul(By.cssSelector(String.format(CSS_MAIL_ADVERTISE, index)), 2, 5);
    }

    /**
     * Get total no of email present in the given header tab
     *
     * @return No of mails present in the given header
     */
    public int getEmailCount() {
        if (!isMailBoxEmpty())
            return 0;
        else if (waitForElementNotNul(By.cssSelector(CSS_MAIL_TOTAL_COUNT), 2, 5))
            return driver.findElements(By.cssSelector(CSS_MAIL_TOTAL_COUNT)).size();

        else if (getEmailCountOnScreen() > 0)
            return getEmailCountOnScreen();

        else return 0;
    }

    /**
     * Logout the application
     */
    public void logout() {
        clickOnElementUsingJquery(driver.findElement(By.id(ID_LOGOUT)));
    }
}
