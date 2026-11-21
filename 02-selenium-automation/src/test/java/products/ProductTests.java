package products;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.ProductsPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ProductTests extends BaseTests {

    @Test
    public void testSixProductsShown(){
        ProductsPage productsPage = loginAsStandardUser();
        assertEquals(productsPage.getNumberOfProducts(), 6, "Wrong number of products");
    }

    @Test
    public void testSortByPriceLowToHigh(){
        ProductsPage productsPage = loginAsStandardUser();
        productsPage.sortBy("Price (low to high)");

        List<Double> actualPrices = productsPage.getProductPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);

        assertEquals(actualPrices, expectedPrices, "Prices are not sorted low to high");
    }

    @Test
    public void testSortByNameZToA(){
        ProductsPage productsPage = loginAsStandardUser();
        productsPage.sortBy("Name (Z to A)");

        List<String> actualNames = productsPage.getProductNames();
        List<String> expectedNames = new ArrayList<>(actualNames);
        expectedNames.sort(Collections.reverseOrder());

        assertEquals(actualNames, expectedNames, "Names are not sorted Z to A");
    }
}
