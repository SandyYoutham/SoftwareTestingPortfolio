package SwagLabMobileTests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TestBase {

    public AppiumDriver appiumDriver;


    @BeforeTest
    @Parameters({"platformName", "platformVersion", "deviceName", "automationName", "APP_Path", "appWaitActivity"})
    public void setUp(@Optional("Android") String platformName, @Optional("13.0") String platformVersion, @Optional("emulator-5554") String deviceName, @Optional("UIAutomator2") String automationName,
                      @Optional("C:\\Users\\USER\\IdeaProjects\\SwagLabsMobile\\src\\main\\resources\\APPS\\Android.SauceLabs.Mobile.Sample.app.2.7.1.apk") String APP_Path, @Optional("com.swaglabsmobileapp.MainActivity") String appWaitActivity) throws InterruptedException, MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName(platformName);
        options.setPlatformVersion(platformVersion);
        options.setDeviceName(deviceName); // Replace with your device name
        options.setApp(APP_Path); // Replace with your app's path
        options.setAutomationName(automationName);
        options.setAppWaitActivity(appWaitActivity);
        int portNo = 4723;
        /*options.setNewCommandTimeout(Duration.ofSeconds(300));
        options.setFullReset(true);
        options.setNoReset(false);*/

        // Initialize the Appium Driver
        this.appiumDriver = new AndroidDriver(
                new URL("http://127.0.0.1:" + portNo + "/"), // Appium server URL

                options
        );


    }

    @AfterTest
    public void tearDown() {
        this.appiumDriver.quit();
    }
}
