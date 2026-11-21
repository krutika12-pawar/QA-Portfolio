package cart;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductsPage;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class CartTests extends BaseTests {

    @Test
    public void testAddProductsToCart(){
        ProductsPage productsPage = loginAsStandardUser();
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.addToCart("Sauce Labs Bike Light");
        assertEquals(productsPage.getCartCount(), 2, "Cart badge count is wrong");

        CartPage cartPage = productsPage.clickCart();
        assertEquals(cartPage.getItemNames(), List.of("Sauce Labs Backpack", "Sauce Labs Bike Light"),
                "Wrong items in cart");
    }

    @Test
    public void testRemoveProductFromCart(){
        ProductsPage productsPage = loginAsStandardUser();
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.addToCart("Sauce Labs Bike Light");
        productsPage.removeFromCart("Sauce Labs Backpack");
        assertEquals(productsPage.getCartCount(), 1, "Cart badge count is wrong");

        CartPage cartPage = productsPage.clickCart();
        assertEquals(cartPage.getItemNames(), List.of("Sauce Labs Bike Light"), "Wrong items in cart");
    }

    @Test
    public void testContinueShopping(){
        CartPage cartPage = loginAsStandardUser().clickCart();
        assertEquals(cartPage.getTitle(), "Your Cart", "Cart page not shown");

        ProductsPage productsPage = cartPage.clickContinueShopping();
        assertEquals(productsPage.getTitle(), "Products", "Did not go back to products");
    }
}
