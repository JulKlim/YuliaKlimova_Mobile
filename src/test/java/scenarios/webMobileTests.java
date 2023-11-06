package scenarios;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pageObjects.webPageObject.WebPageObject;
import setup.BaseTest;
import testData.TestDataProvider;

public class webMobileTests extends BaseTest {

    @Test(groups = {"web"}, description = "Search for EPAM", dataProvider = "searchValues",
          dataProviderClass = TestDataProvider.class)
    public void goToGoogleSearchPage(String searchValue) throws InterruptedException {
        getDriver().get("https://www.google.com"); // open Google search homepage

        // Make sure that page has been loaded completely
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(
            wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );
        // Check Google homepage title
        assertThat(((WebDriver) getDriver()).getTitle()).isEqualTo("Google")
                                                        .as("Google search page is opened");
        // Log that test finished
        System.out.println("Site opening done");

        WebPageObject webPageObject = new WebPageObject(getDriver());
        // Enter search value in the search field
        webPageObject.enterValueIntoSearchField(searchValue);
        System.out.println(searchValue + " is entered into the search field");

        //Select the first suggestion from the list
        webPageObject.selectFirstElementFromSuggestionsList();
        System.out.println("Search is performed for: " + searchValue);

        // Wait for search results to load
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(
            wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );
        // Verify page with search results contain correct title
        String titleText = ((WebDriver) getDriver()).getTitle().toUpperCase();
        assertThat(titleText).contains(searchValue)
                             .as("Page with search results is opened");
        System.out.println("Page title with search results for: " + searchValue + " is correct");
        // Verify search results contain search value
        assertThat(webPageObject.checkHeadersText(searchValue))
            .as("All headers on search result page contain search value in the headers").isTrue();

        // Log that test finished
        System.out.println("Verification of search page and search results for " + searchValue + " is done");
    }
}

