import org.rahulshettyacademy.TestUtils.BaseTest;
import org.rahulshettyacademy.pageObjects.android.CartPage;
import org.rahulshettyacademy.pageObjects.android.ProductCatalog;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.AppiumUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class eCommerce_tc_3 extends BaseTest {
 @Test(dataProvider =  "getData")
 public void FillForm(HashMap<String,String> input) throws InterruptedException {
  formPage.setName(input.get("name"));
  formPage.setGender(input.get("gender"));
  formPage.selectCountrySelection(input.get("country"));
  ProductCatalog product = formPage.submitForm();
  product.addItemToCartByIndex(0);
  product.addItemToCartByIndex(0);
  CartPage cartPage = product.AddToCartbutton();
  Double totalSum = cartPage.getProductSum();
  Double displaySum = cartPage.amounttDisplayed();
  Assert.assertEquals(totalSum, displaySum);
  cartPage.acceptTermsConditions();
  cartPage.setCheckBox();
  cartPage.proceedButtonClick();
 }
     @BeforeMethod
     public void preSetup() {
             driver.terminateApp("com.androidsample.generalstore");
            driver.activateApp("com.androidsample.generalstore");
 }

//   @Before
//    public void preSetup(){
//        ((JavascriptExecutor)driver).executeScript("mobile: startActivity", ImmutableMap.of("intent","com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"));
//    }

   @DataProvider
   public Object[][] getData() throws IOException {
       AppiumUtils appiumUtils =new AppiumUtils(driver);
       List<HashMap<String,String>> data =appiumUtils.getJsonData(System.getProperty("user.dir")+"//src//main//java//org//rahulshettyacademy//testData//eCommerce.json");
       return new Object[][] {{data.get(0)},{data.get(1)}};
  }
/*
   Set<String> contexts =driver.getContextHandles();
  //hybrid app contexts
     for(String conName :contexts){
        System.out.println(conName);
     }
     driver.context("WEBVIEW_com.androidsample.generalstore");
  driver.findElement(By.name("q")).sendKeys("rahul shetty academy");
  driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
  //driver.pressKey(new KeyEvent(AndroidKey.BACK));
  //driver.context("NATIVE_APP");*/


}
