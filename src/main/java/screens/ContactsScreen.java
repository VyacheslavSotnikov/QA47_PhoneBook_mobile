package screens;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactsScreen extends BaseScreen {
    public ContactsScreen(WebDriver driver) {
        super((AppiumDriver) driver);
    }

    public boolean validateContactsScreenOpen(String text) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions
                    .textToBePresentInElementLocated(
                            By.xpath("//android.widget.TextView"), text));
        } catch (TimeoutException e) {
            return false;
        }
    }
}

