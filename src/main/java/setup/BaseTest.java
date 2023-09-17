package setup;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest implements IDriver {

    private static AppiumDriver appiumDriver; // singleton
    private static String platformName;

    public static String getPlatformName() {
        return platformName;
    }

    @Override
    public AppiumDriver getDriver() {
        return appiumDriver;
    }


    @Parameters({"platformName", "appType", "deviceName", "browserName", "app", "appPackage", "serial", "bundleId", "udid"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String platformName, String appType, @Optional("") String deviceName,
                      @Optional("") String serial,
                      @Optional("") String browserName,
                      @Optional("") String app,
                      @Optional("") String appPackage,
                      @Optional("") String bundleId,
                      @Optional("") String udid) throws Exception {
        this.platformName = platformName;
        System.out.println("Before: app type - " + appType);
        setAppiumDriver(platformName, deviceName, browserName, app, appPackage, serial, bundleId, udid);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        System.out.println("After");
        appiumDriver.quit();
    }

    private void setAppiumDriver(String platformName, String deviceName, String serial, String browserName, String app,
                                 String appPackage, String bundleId, String udid) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //mandatory Android capabilities
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);

        if (app.endsWith(".apk")) {
            capabilities.setCapability("app", (new File(app)).getAbsolutePath());
        }

        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("chromedriverDisableBuildCheck", "true");
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("serial", serial);


        capabilities.setCapability("bundleId", bundleId);
        capabilities.setCapability("udid", udid);

        try {
            appiumDriver = new AppiumDriver(new URL(System.getProperty("ts.appium")), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

}

