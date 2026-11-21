package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductsPage {

    private WebDriver driver;
    private By pageTitle = By.className("title");
    private By productNames = By.className("inventory_item_name");
    private By productPrices = By.className("inventory_item_price");
    private By sortDropdown = By.className("product_sort_container");
    private By cartBadge = By.className("shopping_cart_badge");
    private By cartLink = By.className("shopping_cart_link");
    private By menuButton = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");

    public ProductsPage(WebDriver driver){
        this.driver = driver;
    }

    public String getTitle(){
        return driver.findElement(pageTitle).getText();
    }

    public int getNumberOfProducts(){
        return driver.findElements(productNames).size();
    }

    public List<String> getProductNames(){
        List<String> names = new ArrayList<>();
        for(WebElement product : driver.findElements(productNames)){
            names.add(product.getText());
        }
        return names;
    }

    public List<Double> getProductPrices(){
        List<Double> prices = new ArrayList<>();
        for(WebElement price : driver.findElements(productPrices)){
            prices.add(Double.parseDouble(price.getText().replace("$", "")));
        }
        return prices;
    }

    public void sortBy(String option){
        Select dropdown = new Select(driver.findElement(sortDropdown));
        dropdown.selectByVisibleText(option);
    }

    /**
     * Button ids on SauceDemo are built from the product name,
     * for example "Sauce Labs Backpack" becomes "add-to-cart-sauce-labs-backpack"
     */
    public void addToCart(String productName){
        driver.findElement(By.id("add-to-cart-" + toId(productName))).click();
    }

    public void removeFromCart(String productName){
        driver.findElement(By.id("remove-" + toId(productName))).click();
    }

    public int getCartCount(){
        if(driver.findElements(cartBadge).size() == 0){
            return 0;
        }
        return Integer.parseInt(driver.findElement(cartBadge).getText());
    }

    public CartPage clickCart(){
        driver.findElement(cartLink).click();
        return new CartPage(driver);
    }

    public LoginPage logout(){
        driver.findElement(menuButton).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
        return new LoginPage(driver);
    }

    private String toId(String productName){
        return productName.toLowerCase().replace(" ", "-");
    }
}
