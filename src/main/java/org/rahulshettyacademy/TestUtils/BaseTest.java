package org.rahulshettyacademy.TestUtils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.rahulshettyacademy.pageObjects.android.FormPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

            FileInputStream fis = new FileInputStream(
                    System.getProperty("user.dir") + "\\src\\main\\resources\\data.properties"
            );

            prop.load(fis);
            String ipAddress =prop.getProperty("ipAddress");
            String port =prop.getProperty("port");
            //service =startAppiumServer(ipAddress,Integer.parseInt(port));

        //code to start server

        service = AppiumDriverLocalService.buildService(
                new AppiumServiceBuilder()
                        .withAppiumJS(new File("C:\\Users\\msurekha\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                        .withIPAddress("127.0.0.1")
                        .usingPort(4723)
        );
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
