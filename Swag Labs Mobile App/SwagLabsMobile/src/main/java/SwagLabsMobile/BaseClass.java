package SwagLabsMobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseClass {

    public AppiumDriver appiumDriver;
    public WebDriverWait wait;

    public BaseClass(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        this.wait = new WebDriverWait(this.appiumDriver, Duration.ofSeconds(10));
    }

    public void clickElement(By locator) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        el.click();
    }

    public boolean isElementDisplayed(By locator) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return el.isDisplayed();
    }

    public String getText(By locator) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return el.getText();
    }

    public void setText(By locator, String text) {
        WebElement el= wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        el.clear();
        el.sendKeys(text);
    }

    public void mobileScrollToTextAndClick(String text) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"))")));
        el.click();
    }

    public void scrollDown(){
        wait.until(ExpectedConditions.elementToBeClickable(appiumDriver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollForward()"))));
    }

    public boolean scrollUntilElementVisible(By locator, int maxScrolls) {
        boolean scrollUntill=false;
        for (int i = 0; i < maxScrolls; i++) {
            try {
                appiumDriver.findElement(locator);
                scrollUntill=true;
                break;
            } catch (Exception e) {
                scrollDown(); // Perform a custom scroll
            }
        }
        return scrollUntill; // Element not found after maxScrolls
    }

}
