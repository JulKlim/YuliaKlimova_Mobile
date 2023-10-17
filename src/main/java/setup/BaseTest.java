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


    @Parameters({"platformName", "appType", "deviceName", "browserName", "app", "appPackage", "serial", "bundleId", "udid", "appActivity"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String platformName, String appType, @Optional("") String deviceName,
                      @Optional("") String serial,
                      @Optional("") String browserName,
                      @Optional("") String udid,
                      @Optional("") String app,
                      @Optional("") String appPackage,
                      @Optional("") String appActivity,
                      @Optional("") String bundleId) throws Exception {
        this.platformName = platformName;
        System.out.println("Before: app type - " + appType);
        if (appType.equals("web") && getPlatformName().equals("Android")) {
            setAndroidWebAppiumDriver(platformName, deviceName, browserName, serial);
        } else if (appType.equals("web") && getPlatformName().equals("iOS")) {
            setIOSWebAppiumDriver(platformName, browserName, udid);
        } else if (appType.equals("native") && getPlatformName().equals("Android")){
            setNativeAndroidAppiumDriver(platformName, deviceName, serial, appPackage, appActivity, app);
        } else {
            setNativeIOSAppiumDriver(platformName, deviceName, udid, bundleId, app);
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        System.out.println("After");
        appiumDriver.quit();
    }

    private void setNativeAndroidAppiumDriver(String platformName, String app, String deviceName, String serial,
                                 String appPackage, String appActivity) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //mandatory native Android capabilities
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);

        if (app.endsWith(".apk")) {
            capabilities.setCapability("app", (new File(app)).getAbsolutePath());
        }
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("serial", serial);
        capabilities.setCapability("appActivity", appActivity);

        try {
            appiumDriver = new AppiumDriver(new URL(System.getProperty("ts.appium")), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private void setNativeIOSAppiumDriver(String platformName, String deviceName, String udid,
                                          String bundleId, String app) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //mandatory native iOS capabilities
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);

        if (app.endsWith(".ipa")) {
            capabilities.setCapability("app", (new File(app)).getAbsolutePath());
        }


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

    private void setAndroidWebAppiumDriver(String platformName, String deviceName, String serial, String browserName) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //mandatory web Android capabilities
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("chromedriverDisableBuildCheck", "true");
        capabilities.setCapability("serial", serial);
        capabilities.setCapability("browserName", browserName);

        try {
            appiumDriver = new AppiumDriver(new URL(System.getProperty("ts.appium")), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private void setIOSWebAppiumDriver(String platformName, String udid, String browserName) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //mandatory web iOS capabilities
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("udid", udid);
        capabilities.setCapability("browserName", browserName);

        try {
            appiumDriver = new AppiumDriver(new URL(System.getProperty("ts.appium")), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

}

