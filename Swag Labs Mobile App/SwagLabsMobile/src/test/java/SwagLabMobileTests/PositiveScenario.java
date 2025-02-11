package SwagLabMobileTests;

import SwagLabsMobile.Cart;
import SwagLabsMobile.CheckOut;
import SwagLabsMobile.LoginClass;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PositiveScenario extends TestBase {


    @Test(priority = 1)
    public void Login() throws InterruptedException {

        LoginClass loginClass = new LoginClass(appiumDriver);
        boolean res = loginClass.Login("standard_user");
        //assertion
        Assert.assertTrue(res);
    }


    @Test(dependsOnMethods = "Login")
    public void AddToCart() throws InterruptedException {
        String product_name = "Sauce Labs Onesie";
        String itemXpath = "//android.view.ViewGroup[@content-desc=\"test-Item\"]";

        By item = By.xpath(itemXpath);
        Cart cart = new Cart(appiumDriver);

        //action
        cart.addProduct(product_name);
        cart.gotoCart();

        //assertion
        int size = cart.appiumDriver.findElements(item).size();
        Assert.assertEquals(size, 1);
    }


    @Test(dependsOnMethods = {"AddToCart","Login"})
    public void CheckOut() throws InterruptedException {
        //data
        String product_name = "Sauce Labs Onesie";
        String itemXpath = "//android.view.ViewGroup[@content-desc=\"test-Item\"]";
        String productXpath = itemXpath + "//android.widget.TextView[@text=\"" + product_name + "\"]";
        String priceXpath = itemXpath + "//android.widget.TextView[@text=\"$7.99\"]";
        String[] info={"test-First Name","test-Last Name","test-Zip/Postal Code"};

        By product_name_path = By.xpath(productXpath);
        By product_price_path = By.xpath(priceXpath);
        By total_pay = By.xpath("//android.widget.TextView[contains(@text,\"Total\")]");

        SoftAssert softAssert = new SoftAssert();
        CheckOut checkOut = new CheckOut(appiumDriver);

        //action
        checkOut.checkOut(info);

        //Assertion
        softAssert.assertTrue(checkOut.isElementDisplayed(product_name_path)&&
                checkOut.getText(product_name_path).contains(product_name));

        softAssert.assertTrue(checkOut.isElementDisplayed(product_price_path)&&
                checkOut.getText(product_price_path).contains("$7.99"));

        checkOut.scrollUntilElementVisible(total_pay, 3);
        softAssert.assertTrue(checkOut.getText(total_pay).contains("$8.63"));


        //action
        String res = checkOut.finishCheckout();
        //Assertion
        softAssert.assertEquals(res,"THANK YOU FOR YOU ORDER");
        softAssert.assertAll();
    }

}
