
package tests;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void verifyLoginPageOpens() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();

        Assert.assertTrue(
            driver.getCurrentUrl().contains("linkedin.com"),
            "LinkedIn page did not open"
        );

        System.out.println("LinkedIn login page opened!");
    }

    @Test
    public void verifyInvalidLogin() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();

        loginPage.login(
            "invalid_user@example.com",
            "WrongPassword123!"
        );

        Assert.assertTrue(
            loginPage.isLoginErrorDisplayed(),
            "Login error message was not displayed"
        );

        // Figure 4 - Invalid Login
        saveScreenshot("figure4_invalid_login");

        System.out.println("Negative login test passed!");
    }

    @Test
    public void verifyValidLogin() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();

        String email = System.getenv("LINKEDIN_EMAIL");
        String password = System.getenv("LINKEDIN_PASSWORD");

        Assert.assertNotNull(
            email,
            "LINKEDIN_EMAIL environment variable is missing"
        );

        Assert.assertNotNull(
            password,
            "LINKEDIN_PASSWORD environment variable is missing"
        );

        loginPage.login(email, password);

        new WebDriverWait(driver, Duration.ofSeconds(20))
            .until(d -> d.getCurrentUrl().contains("/feed"));

        Assert.assertTrue(
            driver.getCurrentUrl().contains("/feed"),
            "Valid login was not successful"
        );

        // Figure 3 - Successful Login
        saveScreenshot("figure3_successful_login");

        System.out.println(
            "URL after login: " + driver.getCurrentUrl()
        );

        System.out.println("Positive login test passed!");
    }
}
