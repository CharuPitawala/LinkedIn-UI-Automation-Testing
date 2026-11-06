
package tests;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.SearchPage;

public class ClickResultTest extends BaseTest {

    @Test
    public void verifyFirstSearchResultClick() {

        LoginPage loginPage = new LoginPage(driver);
        SearchPage searchPage = new SearchPage(driver);

        String email = System.getenv("LINKEDIN_EMAIL");
        String password = System.getenv("LINKEDIN_PASSWORD");

        Assert.assertNotNull(email,
            "LINKEDIN_EMAIL environment variable is missing");
        Assert.assertNotNull(password,
            "LINKEDIN_PASSWORD environment variable is missing");

        // Step 1: Login
        loginPage.openLoginPage();
        loginPage.login(email, password);

        new WebDriverWait(driver, Duration.ofSeconds(20))
            .until(d -> d.getCurrentUrl().contains("/feed"));

        // Step 2: Search
        searchPage.search("software testing");

        Assert.assertTrue(
            searchPage.isSearchResultsDisplayed(),
            "Search results were not displayed"
        );

        // Step 3: Click first profile/company result
        String selectedUrl = searchPage.clickFirstSearchResult();

        // Step 4: Verify destination page
        new WebDriverWait(driver, Duration.ofSeconds(20))
            .until(d -> d.getCurrentUrl().contains("/in/")
                || d.getCurrentUrl().contains("/company/"));

        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(
            currentUrl.contains("/in/")
                || currentUrl.contains("/company/"),
            "Profile or company page did not open"
        );

        Assert.assertEquals(
    currentUrl.split("\\?")[0].replaceAll("/$", ""),
    selectedUrl.split("\\?")[0].replaceAll("/$", ""),
    "Opened page does not match the selected result"
);
        
        try {
    Thread.sleep(5000);
} catch (InterruptedException e) {
    Thread.currentThread().interrupt();
}
saveScreenshot("figure6_clicked_result");

        System.out.println("Clicked result URL: " + currentUrl);
        System.out.println("Click result test passed!");
    }
}

