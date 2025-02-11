package SwagLabsMobile;

import dev.failsafe.internal.util.Assert;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckOut extends BaseClass{
    public CheckOut(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    By checkoutbtn = AppiumBy.accessibilityId("test-CHECKOUT");
    By firstname = AppiumBy.accessibilityId("test-First Name");
    By lastname = AppiumBy.accessibilityId("test-Last Name");
    By zipCode = AppiumBy.accessibilityId("test-Zip/Postal Code");
    By continueBtn = AppiumBy.accessibilityId("test-CONTINUE");
    By finishBtn = AppiumBy.accessibilityId("test-FINISH");
    By text = By.xpath("//android.widget.TextView[@text=\"THANK YOU FOR YOU ORDER\"]");
    public void checkOut(String[] info) throws InterruptedException {
        scrollUntilElementVisible(checkoutbtn,3);
        clickElement(checkoutbtn);
        setText(firstname,info[0]);
        setText(lastname,info[1]);
        setText(zipCode,info[2]);
        Thread.sleep(2000);
        clickElement(continueBtn);
    }

    public String finishCheckout(){
        scrollUntilElementVisible(finishBtn,3);
        clickElement(finishBtn);
        boolean displayed = isElementDisplayed(text);
        if(displayed) return getText(text);
        else return "";
    }


}
