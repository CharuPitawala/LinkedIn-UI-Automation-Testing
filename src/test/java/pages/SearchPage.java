
package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By searchBox = By.cssSelector(
    "input[role='combobox'], " +
    "input[aria-label*='Search'], " +
    "input[placeholder*='Search'], " +
    "input.search-global-typeahead__input"
);

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
            driver, Duration.ofSeconds(15)
        );
    }

    
public void search(String keyword) {

    wait.until(d -> {
        try {
            for (WebElement element : d.findElements(searchBox)) {

                if (element.isDisplayed() && element.isEnabled()) {

                    element.click();
                    element.clear();
                    element.sendKeys(keyword);
                    element.sendKeys(Keys.ENTER);

                    return true;
                }
            }
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            return false;
        }

        return false;
    });
}


    public boolean isSearchResultsDisplayed() {

        try {
            wait.until(d ->
                d.getCurrentUrl().contains("/search/")
            );

            List<WebElement> results =
                wait.until(d -> {
                    List<WebElement> elements =
                        d.findElements(
                            By.cssSelector(
                                "main a[href*='/in/'], " +
                                "main a[href*='/company/'], " +
                                "main a[href*='/posts/']"
                            )
                        );

                    for (WebElement element : elements) {
                        if (element.isDisplayed()) {
                            return elements;
                        }
                    }

                    return null;
                });

            return results != null;

        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

public String clickFirstSearchResult() {

    By resultLinks = By.cssSelector(
        "main a[href*='/in/'], " +
        "main a[href*='/company/']"
    );

    String resultUrl = wait.until(d -> {
        try {
            for (WebElement link : d.findElements(resultLinks)) {

                if (link.isDisplayed()
                        && link.isEnabled()
                        && link.getAttribute("href") != null) {

                    String href = link.getAttribute("href");

                    if (href.contains("/in/")
                            || href.contains("/company/")) {
                        return href;
                    }
                }
            }
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            return null;
        }

        return null;
    });

    // Open the selected result by clicking its link
    wait.until(d -> {
        try {
            for (WebElement link : d.findElements(resultLinks)) {

                if (link.isDisplayed()
                        && resultUrl.equals(link.getAttribute("href"))) {

                    link.click();
                    return true;
                }
            }
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            return false;
        }

        return false;
    });

    return resultUrl;
}
}
