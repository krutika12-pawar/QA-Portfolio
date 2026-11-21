# Selenium Automation: SauceDemo

**Author:** Krutika Pawar

UI test automation for the [SauceDemo](https://www.saucedemo.com/) online shop, using **Selenium WebDriver 4, Java, TestNG and Maven** with the **Page Object Model**.

This is the automation part of my portfolio. In [Project 1](../01-saucedemo-manual-testing) I tested the same website manually. Here I automated the main user journeys from those test cases.

## What is tested

| Area | Tests |
|------|-------|
| Login | Valid login, 4 invalid logins (empty username, empty password, wrong password, locked out user), logout |
| Products | 6 products shown, sort by price low to high, sort by name Z to A |
| Cart | Add products, remove a product, continue shopping |
| Checkout | Full purchase with item total, tax and total check, first name required, postcode required |

15 tests in total.

## Tools

- Java 17+, Maven
- Selenium WebDriver 4 (Selenium Manager downloads the browser driver)
- TestNG (assertions, data provider for login tests)
- IntelliJ IDEA


How it works:

- **Page Object Model:** each page has its own class with its locators and actions. Tests only call page methods, so if the website changes, I only fix the page class.
- **Pages return the next page:** for example `clickCart()` returns a `CartPage`, so a test reads like the user journey.
- **Fresh browser for every test:** each test starts on the login page, so tests do not depend on each other.
- **Screenshots on failure:** saved in `resources/screenshots` with the test name.
- **Event listener:** `EventReporter` prints every page opened, click and text entry, which helps when debugging.

## How to run

You need Java 17 or higher, Maven and Chrome.

In IntelliJ: right click `testng.xml` and click **Run**.

From the terminal:

```bash
mvn test

# without opening the browser window
mvn test -Dheadless=true
```

## Learning

I learned Selenium with the **Selenium WebDriver with Java** course by Test Automation University (Applitools) and built this project using the same approach.
Certificate: https://testautomationu.applitools.com/certificate/?id=15db2d41
