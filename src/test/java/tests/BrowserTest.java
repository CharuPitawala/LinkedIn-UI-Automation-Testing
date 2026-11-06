
package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class BrowserTest extends BaseTest {

    @Test
    public void verifyBrowserOpens() {
        driver.get("https://example.com");

        Assert.assertTrue(
            driver.getTitle().contains("Example Domain"),
            "Browser did not open the expected page"
        );

        System.out.println("Browser test passed!");
    }
}

