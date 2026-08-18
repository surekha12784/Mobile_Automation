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

        String ipAddress = prop.getProperty("ipAddress");
        String port = prop.getProperty("port");
        String deviceName = System.getenv("ANDROID_DEVICE_NAME");

        if (deviceName == null || deviceName.isEmpty()) {
            deviceName = prop.getProperty("AndroidDeviceName");
        }



        UiAutomator2Options options = new UiAutomator2Options();

        options.setDeviceName(deviceName);

        options.setApp(
                System.getProperty("user.dir")
                        + "/src/main/resources/General-Store.apk"
        );

        URL appiumServerUrl =
                new URL("http://" + ipAddress + ":" + port);

        driver = new AndroidDriver(
                appiumServerUrl,
                options
        );

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