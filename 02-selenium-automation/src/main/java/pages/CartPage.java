package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage {

    private WebDriver driver;
    private By pageTitle = By.className("title");
    private By cartItemNames = By.className("inventory_item_name");
    private By checkoutButton = By.id("checkout");
    private By continueShoppingButton = By.id("continue-shopping");

    public CartPage(WebDriver driver){
        this.driver = driver;
        waitForUrl("cart.html");
    }

    public String getTitle(){
        return driver.findElement(pageTitle).getText();
    }

    private void waitForUrl(String urlPart){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains(urlPart));
    }

    public List<String> getItemNames(){
        List<String> names = new ArrayList<>();
        for(WebElement item : driver.findElements(cartItemNames)){
            names.add(item.getText());
        }
        return names;
    }

    public CheckoutInformationPage clickCheckout(){
        driver.findElement(checkoutButton).click();
        return new CheckoutInformationPage(driver);
    }

    public ProductsPage clickContinueShopping(){
        driver.findElement(continueShoppingButton).click();
        waitForUrl("inventory.html");
        return new ProductsPage(driver);
    }
}
