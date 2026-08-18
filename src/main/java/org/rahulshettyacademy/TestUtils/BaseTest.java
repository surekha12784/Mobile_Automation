package org.rahulshettyacademy.TestUtils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.rahulshettyacademy.pageObjects.android.FormPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.*;
import java.net.MalformedURLException;
    import java.net.URL;
    import java.time.Duration;
    import java.util.Properties;

    public class BaseTest {
        public AndroidDriver driver;
        public AppiumDriverLocalService service;
        public FormPage formPage;

        @BeforeClass
        public void ConfigureAppium() throws IOException {

            Properties prop = new Properties();

            InputStream input = getClass()
                    .getClassLoader()
                    .getResourceAsStream("data.properties");

            if (input == null) {
                throw new FileNotFoundException("data.properties not found in classpath");
            }

            prop.load(input);


            String ipAddress =prop.getProperty("ipAddress");
            String port =prop.getProperty("port");
            //service =startAppiumServer(ipAddress,Integer.parseInt(port));

        //code to start server
            service = new AppiumServiceBuilder()
                    .withIPAddress(ipAddress)
                    .usingPort(Integer.parseInt(port))
                    .build();
        service.start();


        UiAutomator2Options options =new UiAutomator2Options();
        options.setDeviceName("MobileTestSurekha");
        options.setChromedriverExecutable("C://Users//msurekha//chromedriver//chromedriver124//chromedriver-win64/chromedriver.exe");
        //options.setCapability("appium:showChromedriverLog", true);
        options.setApp("C://Users//msurekha//IdeaProjects//MobilePOMFramework1//src//main//resources//General-Store.apk");
        //options.setApp("C://Users//msurekha//IdeaProjects//MobileTest//src//test//java//resources//ApiDemos-debug.apk");
        //options.setCapability("appium:extractChromeAndroidPackageFromContextName", true);
         driver =new AndroidDriver( new URL("http://127.0.0.1:4723"),options);
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
         formPage =new FormPage(driver);


    }

    @AfterClass
    public void tearDown(){
        if(driver!=null){
        driver.quit();}
        service.stop();
    }

}
