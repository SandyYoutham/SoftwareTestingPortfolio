package SwagLabsMobile;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Cart extends BaseClass{
    public Cart(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    By addBtn = By.xpath("(//android.view.ViewGroup[@content-desc=\"test-Item\"])[3]//android.view.ViewGroup[@content-desc='test-ADD TO CART']");
    By cartBtn = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]");
    By your_cart = By.xpath("//android.widget.TextView[@text=\"YOUR CART\"]");
    public void addProduct(String product) throws InterruptedException {
        By productlocator = By.xpath("//android.widget.TextView[@text=\""+product+"\"]");
        if(scrollUntilElementVisible(productlocator,3)){
            clickElement(addBtn);
        }
    }

    public void gotoCart(){
        clickElement(cartBtn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(your_cart));
    }

}
