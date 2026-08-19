package org.rahulshettyacademy.TestUtils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.rahulshettyacademy.pageObjects.android.FormPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public AndroidDriver driver;
    public FormPage formPage;

    @BeforeClass
    public void ConfigureAppium() throws Exception {

        Properties prop = new Properties();

        InputStream input = getClass()
                .getClassLoader()
                .getResourceAsStream("data.properties");

        if (input == null) {
            throw new FileNotFoundException(
                    "data.properties not found in classpath"
            );
        }

        prop.load(input);

        String ipAddress = prop.getProperty("ipAddress", "127.0.0.1");
        String port = prop.getProperty("port", "4723");

        String deviceName = System.getenv("ANDROID_DEVICE_NAME");

        if (deviceName == null || deviceName.isEmpty()) {
            deviceName = prop.getProperty("AndroidDeviceName");
        }

        if (deviceName == null || deviceName.isEmpty()) {
            throw new RuntimeException("Android device name is not configured");
        }

        String appPath = System.getProperty("user.dir")
                + "/src/main/resources/General-Store.apk";

        System.out.println("========== APPIUM CONFIG ==========");
        System.out.println("Appium IP      : " + ipAddress);
        System.out.println("Appium Port    : " + port);
        System.out.println("Device Name    : " + deviceName);
        System.out.println("App Path       : " + appPath);
        System.out.println("====================================");

        UiAutomator2Options options = new UiAutomator2Options();

        options.setDeviceName(deviceName);
        options.setUdid("emulator-5554");
        options.setAutomationName("UiAutomator2");
        options.setPlatformName("Android");
        options.setApp(appPath);

        URL appiumServerUrl =
                new URL("http://" + ipAddress + ":" + port);

        System.out.println("Appium Server  : " + appiumServerUrl);
        System.out.println("Appium Server: " + appiumServerUrl);
        System.out.println("APK exists: " +
                new java.io.File(appPath).exists());
        System.out.println("Appium Server : " + appiumServerUrl);
        System.out.println("Capabilities  : " + options);
        try {
            System.out.println("Creating AndroidDriver...");

            driver = new AndroidDriver(
                    appiumServerUrl,
                    options
            );

            System.out.println("AndroidDriver created successfully");

        } catch (Exception e) {
            System.out.println("========== APPIUM SESSION CREATION FAILED ==========");
            e.printStackTrace();
            System.out.println("=====================================================");
            throw e;
        }

        driver.manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        formPage = new FormPage(driver);
    }
    @AfterClass
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}