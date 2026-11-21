package login;

import base.BaseTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTests extends BaseTests {

    // Verify all UI elements present on login page: UI tests
    @Test
    public void verifyLoginPageElements(){
        assertEquals(loginPage.getLoginPageTitle(), "Swag Labs", "Page title not correct.");
        assertEquals(loginPage.getUsernamePlaceholder(), "Username", "Placeholder is different.");
        assertEquals(loginPage.getPasswordPlaceholder(), "Password", "Placeholder is different.");
        assertEquals(loginPage.getLoginButtonName(), "Login", "Button name is different.");
    }


    // Functionality check
    @Test
    public void testValidLogin(){
        ProductsPage productsPage = loginPage.loginAs("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(), "Products", "Products page not shown");
        assertTrue(driver.getCurrentUrl().endsWith("/inventory.html"), "Wrong URL after login");
    }

    // Each row is one test: username, password, expected error message
    @DataProvider(name = "invalidLogins")
    public Object[][] invalidLogins(){
        return new Object[][]{
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"standard_user", "wrong_password",
                        "Epic sadface: Username and password do not match any user in this service"},
                {"locked_out_user", "secret_sauce",
                        "Epic sadface: Sorry, this user has been locked out."}
        };
    }

    @Test(dataProvider = "invalidLogins")
    public void testInvalidLogin(String username, String password, String expectedError){
        loginPage.loginAs(username, password);
        assertEquals(loginPage.getErrorMessage(), expectedError, "Wrong error message");
        assertTrue(loginPage.isLoginButtonDisplayed(), "User should stay on the login page");
    }

    @Test
    public void testLogout(){
        LoginPage afterLogout = loginAsStandardUser().logout();
        assertTrue(afterLogout.isLoginButtonDisplayed(), "Login page not shown after logout");
    }
}
