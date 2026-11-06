
package base;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    // Save screenshot at the current browser page
    protected void saveScreenshot(String name) {
        if (driver == null) {
            return;
        }

        try {
            Path folder = Path.of("screenshots");
            Files.createDirectories(folder);

            String timestamp = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")
            );

            String filename = name + "_" + timestamp + ".png";

            File screenshot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

            Files.copy(
                screenshot.toPath(),
                folder.resolve(filename),
                StandardCopyOption.REPLACE_EXISTING
            );

            System.out.println(
                "Screenshot saved: " + filename
            );

        } catch (IOException e) {
            System.out.println(
                "Screenshot capture failed: " + e.getMessage()
            );
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        if (driver != null) {

            if (result.getStatus() == ITestResult.FAILURE) {
                saveScreenshot(
                    "FAILED_" + result.getName()
                );
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                saveScreenshot(
                    "PASSED_" + result.getName()
                );
            }

            driver.quit();
        }
    }
}
