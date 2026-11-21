package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInformationPage {

    private WebDriver driver;
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postcodeField = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By errorMessage = By.cssSelector("[data-test='error']");

    public CheckoutInformationPage(WebDriver driver){
        this.driver = driver;
    }

    public void enterDetails(String firstName, String lastName, String postcode){
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(postcodeField).sendKeys(postcode);
    }

    public CheckoutOverviewPage clickContinue(){
        driver.findElement(continueButton).click();
        return new CheckoutOverviewPage(driver);
    }

    public String getErrorMessage(){
        return driver.findElement(errorMessage).getText();
    }
}
