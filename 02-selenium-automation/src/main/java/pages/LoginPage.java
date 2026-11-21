package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;
    private By pageTitle = By.className("login_logo");
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public String getLoginPageTitle(){
        return driver.findElement(pageTitle).getText();
    }

    public String getUsernamePlaceholder(){
        return driver.findElement(usernameField).getAttribute("placeholder");
    }

    public String getPasswordPlaceholder(){
        return driver.findElement(passwordField).getAttribute("placeholder");
    }

    public String getLoginButtonName(){
        return driver.findElement(loginButton).getAttribute("value");
    }

    public void setUsername(String username){
        driver.findElement(usernameField).sendKeys(username);
    }

    public void setPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    public ProductsPage clickLoginButton(){
        driver.findElement(loginButton).click();
        return new ProductsPage(driver);
    }

    public ProductsPage loginAs(String username, String password){
        setUsername(username);
        setPassword(password);
        return clickLoginButton();
    }

    public String getErrorMessage(){
        return driver.findElement(errorMessage).getText();
    }

    public boolean isLoginButtonDisplayed(){
        return driver.findElements(loginButton).size() > 0;
    }
}
