
package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.SearchPage;

public class SearchTest extends BaseTest {

    @Test
    public void verifySearchFunctionality() {

        LoginPage loginPage = new LoginPage(driver);
        SearchPage searchPage = new SearchPage(driver);

        loginPage.openLoginPage();

        String email = System.getenv("LINKEDIN_EMAIL");
        String password = System.getenv("LINKEDIN_PASSWORD");

        Assert.assertNotNull(email,
            "LINKEDIN_EMAIL environment variable is missing");

        Assert.assertNotNull(password,
            "LINKEDIN_PASSWORD environment variable is missing");

        loginPage.login(email, password);

        // Wait until LinkedIn login is successful
        new org.openqa.selenium.support.ui.WebDriverWait(
            driver, java.time.Duration.ofSeconds(20)
        ).until(d -> d.getCurrentUrl().contains("/feed"));

        // Search for the keyword
        searchPage.search("software testing");

        // Verify search results
        
        // Verify search results
        Assert.assertTrue(
            searchPage.isSearchResultsDisplayed(),
            "Search results were not displayed"
        );

        // Figure 5 - Automatically capture search results
        saveScreenshot("figure5_search_results");

        System.out.println("LinkedIn search test passed!");

    }
}
