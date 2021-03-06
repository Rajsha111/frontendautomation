package com.cc.test;

import com.cc.web.common.Constant;
import com.cc.web.common.PageBase;
import com.cc.web.common.PropertyManager;
import com.cc.web.pages.InboxPage;
import com.cc.web.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class MyTest extends BaseTest {

    private InboxPage inboxPage;

    @BeforeClass(alwaysRun = true)
    public void openBrowserWithLogin() {
        driver.get(PropertyManager.getInstance().getBaseUrl());
        driver.manage().window().maximize();
        PageBase.setImplicitTimeout(true, driver);

        // Login to the application
        LoginPage loginPage = new LoginPage(driver);
        inboxPage = loginPage.login(PropertyManager.getInstance().getEmail(), b64decode(PropertyManager.getInstance().getPassword()));
        Assert.assertTrue(inboxPage.isPageLoaded(), "Inbox page is not loaded");
    }

    @AfterClass(alwaysRun = true)
    public void logout() {
        inboxPage.logout();
    }

    @Test(priority = -1, description = "verifyPrimaryHeaderDefaultSelect")
    public void verifyPrimaryHeaderDefaultSelect() {
        Assert.assertTrue(inboxPage.isHeaderVisible(Constant.Header.PRIMARY), "Primary header is not visible by default.");
    }

    @Parameters({"tabName", "mailIndexNo"})
    @Test(description = "Get name of the sender")
    public void getSocialSenderNames(@Optional("SOCIAL") String tabName, @Optional("1") String mailIndexNo) {
        inboxPage.clickOnHeader(Constant.Header.valueOf(tabName));
        Assert.assertTrue(inboxPage.isHeaderVisible(Constant.Header.valueOf(tabName)), "Social header is not visible by default.");

        String subject = inboxPage.getSubject(Integer.valueOf(mailIndexNo));
        System.out.println("Mail subject on tab '" + tabName + "' for mail no- '" + mailIndexNo + "' is: " + subject);
        String sender = inboxPage.getSender(Integer.valueOf(mailIndexNo));

        System.out.println("Mail Sender on tab '" + tabName + "' for mail no- '" + mailIndexNo + "' is: " + sender);
    }

    @Parameters({"tabName"})
    @Test(description = "Get count of emails")
    public void getTotalNoOfEmail(@Optional("SOCIAL") String tabName) {
        inboxPage.clickOnHeader(Constant.Header.valueOf(tabName));
        Assert.assertTrue(inboxPage.isHeaderVisible(Constant.Header.valueOf(tabName)), "Social header is not visible by default.");
        int totalNoOfEmail = inboxPage.getEmailCount();
        System.out.println("'" + tabName + "' mail counts are: " + totalNoOfEmail);
    }
}
