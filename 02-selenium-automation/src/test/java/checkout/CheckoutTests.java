package checkout;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.CheckoutCompletePage;
import pages.CheckoutInformationPage;
import pages.CheckoutOverviewPage;
import pages.ProductsPage;

import static org.testng.Assert.assertEquals;

public class CheckoutTests extends BaseTests {

    // Opens the checkout information page with the Backpack ($29.99) and Bike Light ($9.99) in the cart
    private CheckoutInformationPage goToCheckout(){
        ProductsPage productsPage = loginAsStandardUser();
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.addToCart("Sauce Labs Bike Light");
        return productsPage.clickCart().clickCheckout();
    }

    @Test
    public void testCompletePurchase(){
        CheckoutInformationPage informationPage = goToCheckout();
        informationPage.enterDetails("Krutika", "Pawar", "SW1A 1AA");
        CheckoutOverviewPage overviewPage = informationPage.clickContinue();

        assertEquals(overviewPage.getItemTotal(), 39.98, "Item total is wrong");
        assertEquals(overviewPage.getTax(), 3.20, "Tax is wrong");
        assertEquals(overviewPage.getTotal(), 43.18, "Total is wrong");

        CheckoutCompletePage completePage = overviewPage.clickFinish();
        assertEquals(completePage.getCompleteHeader(), "Thank you for your order!", "Order not completed");
    }

    @Test
    public void testFirstNameRequired(){
        CheckoutInformationPage informationPage = goToCheckout();
        informationPage.enterDetails("", "Pawar", "SW1A 1AA");
        informationPage.clickContinue();
        assertEquals(informationPage.getErrorMessage(), "Error: First Name is required", "Wrong error message");
    }

    @Test
    public void testPostcodeRequired(){
        CheckoutInformationPage informationPage = goToCheckout();
        informationPage.enterDetails("Krutika", "Pawar", "");
        informationPage.clickContinue();
        assertEquals(informationPage.getErrorMessage(), "Error: Postal Code is required", "Wrong error message");
    }
}
