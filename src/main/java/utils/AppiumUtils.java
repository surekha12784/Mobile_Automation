package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class AppiumUtils {

    AppiumDriver driver;
    public AppiumUtils(AppiumDriver driver){
        this.driver=driver;
    }
    public Double getFormattedAmount(String amount){
        return Double.parseDouble(amount.substring(1));
    }
    public void waitForElementToAppear(WebElement ele){
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(ele,"text","Cart"));
    }
    public String getScreenshotPath(String testCaseName,AppiumDriver driver) throws  IOException {
       File source = driver.getScreenshotAs(OutputType.FILE);
       String destinationFile =System.getProperty("user.dir")+"//reports"+testCaseName+".png";
       FileUtils.copyFile(source,new File(destinationFile));
       return destinationFile;
    }
    public List<HashMap<String, String>> getJsonData(String jsonFilePath)
            throws IOException {
        String filePath =System.getProperty("user.dir")+"//src//main//java//org//rahulshettyacademy//testData//eCommerce.json";
        //String filePath = System.getProperty("user.dir")
           //     + "//src//test//resources//data//"
           //     + jsonFilePath;

        String jsonContent = FileUtils.readFileToString(
                new File(filePath),
                StandardCharsets.UTF_8
        );

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(
                jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {}
        );



    }

}
