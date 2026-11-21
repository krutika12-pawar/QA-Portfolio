package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class EventReporter implements WebDriverListener {

    @Override
    public void beforeGet(WebDriver driver, String url){
        System.out.println("Opening: " + url);
    }

    @Override
    public void beforeClick(WebElement element){
        System.out.println("Clicking on: " + element.getAccessibleName());
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend){
        System.out.println("Typing into: " + element.getAttribute("id"));
    }
}
