package pageObjects;

import io.appium.java_client.AppiumDriver;
import pageObjects.nativePageObject.BudgetActivityPage;
import pageObjects.nativePageObject.LoginPage;
import pageObjects.nativePageObject.RegistrationPage;
import pageObjects.webPageObject.WebPageObject;
import setup.IPageObject;

public class PageObject implements IPageObject {

    Object somePageObject; // it should be set of web page or EPAM Test App WebElements

    public PageObject(String appType, AppiumDriver appiumDriver) throws Exception {

        System.out.println("Current app type: " + appType);
        switch (appType) {
            case "web":
                somePageObject = new WebPageObject(appiumDriver);
                break;
            case "native":
                somePageObject = new LoginPage(appiumDriver);
                somePageObject = new RegistrationPage(appiumDriver);
                somePageObject = new BudgetActivityPage(appiumDriver);
                break;
            default:
                throw new Exception("Can't create a page object for " + appType);
        }
    }
}
