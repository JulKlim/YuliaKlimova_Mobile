package pageObjects.webPageObject;

import io.appium.java_client.AppiumDriver;
import java.util.List;
import java.util.Locale;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebPageObject {
    @FindBy(className = "gLFyf")
    private WebElement searchField;
    @FindBy(xpath = "//*[@id='tsuid_5']/div[1]/div/ul/li[1]/div[1]/div[2]")
    private WebElement firstElementFromSuggestionsList;
    @FindBy(className = "LC20lb")
    private List<WebElement> searchResults;

    public WebPageObject(AppiumDriver appiumDriver) {
        PageFactory.initElements(appiumDriver, this);
    }

    public void enterValueIntoSearchField(String searchText) {
        searchField.click();
        searchField.sendKeys(searchText);
    }

    public void selectFirstElementFromSuggestionsList() {
        firstElementFromSuggestionsList.click();
    }

    public boolean checkHeadersText(String expectedSearchValue) {
        boolean areHeaderTextsMatchSearchValue = true;

        for (WebElement result : searchResults) {
            String resultText = result.getText().toLowerCase(Locale.ROOT);
            if (!resultText.contains(expectedSearchValue)) {
                areHeaderTextsMatchSearchValue = false;
            }
        }
        return areHeaderTextsMatchSearchValue;
    }
}
