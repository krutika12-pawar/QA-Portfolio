package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage {

    private WebDriver driver;
    private By itemTotal = By.className("summary_subtotal_label");
    private By tax = By.className("summary_tax_label");
    private By total = By.className("summary_total_label");
    private By finishButton = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver){
        this.driver = driver;
    }

    public double getItemTotal(){
        return getAmount(itemTotal);
    }

    public double getTax(){
        return getAmount(tax);
    }

    public double getTotal(){
        return getAmount(total);
    }

    public CheckoutCompletePage clickFinish(){
        driver.findElement(finishButton).click();
        return new CheckoutCompletePage(driver);
    }

    // The label looks like "Item total: $39.98", so take the number after the $
    private double getAmount(By label){
        String text = driver.findElement(label).getText();
        return Double.parseDouble(text.substring(text.indexOf("$") + 1));
    }
}
