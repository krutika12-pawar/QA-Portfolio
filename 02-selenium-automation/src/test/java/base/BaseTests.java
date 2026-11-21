package base;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import pages.ProductsPage;
import utils.EventReporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseTests {

    protected WebDriver driver;
    protected LoginPage loginPage;

    @BeforeMethod
    public void setUp(){
        WebDriver chrome = new ChromeDriver(getChromeOptions());
        driver = new EventFiringDecorator<>(new EventReporter()).decorate(chrome);
        driver.manage().window().maximize();
        goHome();
    }

    public void goHome(){
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    // Most tests start as a logged in user, so this saves repeating the login steps
    public ProductsPage loginAsStandardUser(){
        return loginPage.loginAs("standard_user", "secret_sauce");
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        if(ITestResult.FAILURE == result.getStatus()){
            takeScreenshot(result.getName());
        }
        driver.quit();
    }

    private void takeScreenshot(String testName){
        var camera = (TakesScreenshot) driver;
        File screenshot = camera.getScreenshotAs(OutputType.FILE);
        File destination = new File("resources/screenshots/" + testName + ".png");
        try{
            Files.copy(screenshot.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private ChromeOptions getChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", List.of("enable-automation"));

        // Stops Chrome's "Change your password" popup, which appears after
        // logging in with the public SauceDemo password and blocks the page
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);

        // Run with -Dheadless=true to run without opening a browser window
//        if(Boolean.getBoolean("headless")){
//            options.addArguments("--headless=new");
//        }
        return options;
    }
}
