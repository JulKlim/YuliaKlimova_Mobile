package pageObjects.webPageObject;

import io.appium.java_client.AppiumDriver;
import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebPageObject {

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchField;

    @FindBy(className = "LC20lb")
    private List<WebElement> searchResults;

    public WebPageObject(AppiumDriver appiumDriver) {
        PageFactory.initElements(appiumDriver, this);
    }

    public void enterValueIntoSearchField(String searchText) {
        searchField.click();
        searchField.sendKeys(searchText);
        searchField.sendKeys(Keys.ENTER);
    }

    public boolean checkHeadersText(String expectedSearchValue) {
        boolean areHeaderTextsMatchSearchValue = true;

        for (WebElement result : searchResults) {
            String resultText = result.getText();
            if (!resultText.contains(expectedSearchValue)) {
                areHeaderTextsMatchSearchValue = false;
            }
        }
        return areHeaderTextsMatchSearchValue;
    }
}
