
# LinkedIn UI Automation Testing

## Project Overview
This project automates LinkedIn UI testing using Selenium WebDriver, Java, TestNG, and Maven.

The project was developed for the IT3153 Software Quality Assurance assignment.

## Technologies Used
- Java
- Selenium WebDriver
- TestNG
- Maven
- Google Chrome
- Visual Studio Code

## Test Scenarios
1. Browser initialization and closing
2. Valid LinkedIn login
3. Invalid LinkedIn login
4. Searching for "software testing"
5. Clicking a search result and verifying the destination page

## Project Structure
- `src/test/java/base/` - Browser setup and teardown, including failure screenshot capture
- `src/test/java/pages/` - Page Object Model classes
- `src/test/java/tests/` - TestNG test classes
- `screenshots/` - Test evidence and screenshots
- `pom.xml` - Maven dependencies and configuration

## Prerequisites
- Java 17 or later
- Maven
- Google Chrome
- Visual Studio Code or another Java IDE
- A LinkedIn account authorized for testing

## Credential Configuration
Credentials are supplied through environment variables and are not stored in the source code.

In PowerShell, run:

```powershell
$env:LINKEDIN_EMAIL = Read-Host "Enter LinkedIn email"
$env:LINKEDIN_PASSWORD = Read-Host "Enter LinkedIn password" -MaskInput
```

## Running the Tests
Open a terminal in the project directory and run:

```powershell
mvn test
```

## Test Reports
Maven Surefire generates test results in the `target/surefire-reports/` directory.

Screenshots captured on test failures are saved in the `screenshots/` directory.

## Challenges and Limitations
- LinkedIn uses dynamic web elements that may change over time.
- Selenium tests may fail because of stale elements or loading delays.
- Login security checkpoints or CAPTCHA may require manual intervention.
- Automated tests cannot guarantee that every LinkedIn feature works correctly.
- LinkedIn's applicable usage restrictions must be respected.

## Security Considerations
- Credentials must not be committed to GitHub.
- Sensitive information should be hidden in screenshots and screen recordings.
- Security checkpoints must not be bypassed.

## Conclusion
This project demonstrates UI automation, test validation, Page Object Model design, and automated reporting using Selenium WebDriver and TestNG.
