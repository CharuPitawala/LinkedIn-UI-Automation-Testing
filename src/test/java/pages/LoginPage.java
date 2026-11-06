
package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By emailField = By.cssSelector("input[type='email'], input[name='session_key']");
    private By passwordField = By.cssSelector("input[type='password']");
    private By signInButton =
            By.cssSelector("button[type='submit']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(15));
    }

    public void openLoginPage() {
    driver.get("https://www.linkedin.com/login");

    System.out.println("Current URL: " + driver.getCurrentUrl());
    System.out.println("Page Title: " + driver.getTitle());
}

    



public void enterEmail(String email) {

    WebElement field = wait.until(driver -> {
        for (WebElement input :
                driver.findElements(By.cssSelector("input[type='email']"))) {

            if (input.isDisplayed() && input.isEnabled()) {
                return input;
            }
        }
        return null;
    });

    field.clear();
    field.sendKeys(email);
}





    
public void enterPassword(String password) {

    WebElement field = wait.until(driver -> {
        for (WebElement input :
                driver.findElements(By.cssSelector("input[type='password']"))) {

            if (input.isDisplayed() && input.isEnabled()) {
                return input;
            }
        }
        return null;
    });

    field.clear();
    field.sendKeys(password);
}


    
public void clickSignIn() {

    WebElement button = wait.until(driver -> {
        for (WebElement element :
                driver.findElements(By.cssSelector("button"))) {

            if (element.isDisplayed()
                    && element.isEnabled()
                    && element.getText().trim().equalsIgnoreCase("Sign in")) {
                return element;
            }
        }
        return null;
    });

    button.click();
}


    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickSignIn();
    }



public boolean isLoginErrorDisplayed() {
    try {
        return new WebDriverWait(driver, Duration.ofSeconds(20))
            .until(d -> {
                for (WebElement element : d.findElements(
                    By.xpath(
                        "//*[contains(translate(normalize-space(.), " +
                        "'ABCDEFGHIJKLMNOPQRSTUVWXYZ', " +
                        "'abcdefghijklmnopqrstuvwxyz'), " +
                        "'wrong email or password') " +
                        "or @id='error-for-password' " +
                        "or @id='error-for-username']"
                    )
                )) {
                    if (element.isDisplayed()
                            && !element.getText().trim().isEmpty()) {
                        return true;
                    }
                }
                return false;
            });

    } catch (org.openqa.selenium.TimeoutException e) {
        System.out.println(
            "Login error was not detected. Current URL: "
            + driver.getCurrentUrl()
        );
        return false;
    }
}

}



