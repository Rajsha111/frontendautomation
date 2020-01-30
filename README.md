# frontendautomation

Its an maven project to test scanerio around the gmail header tabs and count of the mail in the respective header tab.

To run the test cases please go through the below details-
Preqesites-

1. Java 1.8 or higher should be installed.
2. Maven Apache Maven 3.6.0 or higher should be installed.
3. Make sure chrome is installed.

Steps to run-

1. Checkout this repository.
2. Go to the workspace
3. Open projectConfig.properties(can be found in project root folder)
4. Set 'chrome' or 'firefox' as the browser, by default 'chrome' is selected.
5. Set email and password(Base64encoded). Now i have added a gmail test account which can be use by anyone.
6. Open myAssesmentTest.xml(can be found in project root folder).
7. Change the parameter value if required else test will run on default parameter values. Now mail index set to 1 as there is only 1 mail in the test account-
      <parameter name="tabName" value="SOCIAL"/> (tabName value can be 'SOCIAL', 'PRIMARY', 'PROMOTION')
        <parameter name="mailIndexNo" value="1"/> (mailIndexNo value should be an index of mail)
8. Run maven command to execute the test cases->
      mvn test -DxmlPath='myAssesmentTest.xml'
9. Check console which prints->
    Mail subject on tab '<tabName>' for mail no - '<mailIndexNo>' is:
    Mail Sender on tab '<tabName>' for mail no - '<mailIndexNo>' is:
    '<tabName>' mail counts are
 
