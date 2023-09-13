package pageObjects.nativePageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BudgetActivityPage {
    @AndroidFindBy(xpath = "//*[contains(@text, 'BudgetActivity')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name = 'Budget']")
    private WebElement BudgetActivityHeader;

    public BudgetActivityPage(AppiumDriver appiumDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }

    public String getBudgetActivityPageHeader() {
        String budgetActivityHeaderText = BudgetActivityHeader.getText();
        return budgetActivityHeaderText;
    }
}
