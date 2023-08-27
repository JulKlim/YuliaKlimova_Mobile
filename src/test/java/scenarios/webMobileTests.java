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

    @Test(groups = {"web"}, description = "Make sure that we've opened Google search homepage", priority = 1)
    public void goToGoogleSearchPage() throws InterruptedException {
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
    }

    @Test(groups = {"web"}, description = "Search for EPAM", priority = 2, dataProvider = "searchValues",
          dataProviderClass = TestDataProvider.class)
    public void performSearchAndValidateSearchResults(String searchValue) throws InterruptedException {
        // Enter search value in the search field
        WebPageObject.enterValueIntoSearchField(searchValue);
        System.out.println("Search is performed for: " + searchValue);
        // Wait for search results to load
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(
            wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );
        // Verify page with search results contain correct title
        assertThat(((WebDriver) getDriver()).getTitle()).contains(searchValue)
                                                        .as("Page with search results is opened");
        System.out.println("Page title with search results for: " + searchValue + " is correct");
        // Verify search results contain search value
        assertThat(WebPageObject.checkHeadersText(searchValue))
            .as("All headers on search result page contain search value in the headers").isTrue();

        // Log that test finished
        System.out.println("Verification of search page and search results for " + searchValue + " is done");
    }
}

