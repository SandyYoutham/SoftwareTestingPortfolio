package SwagLabsMobile;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class LoginClass extends BaseClass {
    public LoginClass(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    By producttext = By.xpath("//android.widget.TextView[@text=\"PRODUCTS\"]");

    public boolean Login(String text) {
        //can add scroll
        mobileScrollToTextAndClick(text);
        //can add scrollup
        mobileScrollToTextAndClick("LOGIN");
        //clickElement(loginbtn);
        return isElementDisplayed(producttext);
    }
}
