package org.rahulshettyacademy.pageObjects.android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.AndroidActions;

import java.util.List;

public class CartPage extends AndroidActions {

    AndroidDriver driver;

    @AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productList;
    @AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement amtDisplayed;
    @AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
    private WebElement terms;

    @AndroidFindBy(className = "android.widget.CheckBox")
    private WebElement checkBox;

    @AndroidFindBy(id="android:id/button1")
    private WebElement acceptButton;

    @AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
    private WebElement proceed;

    public CartPage(AndroidDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }
    public List<WebElement> getProductList(){
        return productList;
    }
    public double getProductSum(){
        int count =productList.size();
        double totalSum=0;
        for(int i=0;i<count;i++) {
            String amountString= productList.get(i).getText();
            Double price =Double.parseDouble(amountString.substring(1));
            totalSum=totalSum+price;
        }
        return totalSum;
    }
    public Double amounttDisplayed(){
        return getFormattedAmount(amtDisplayed.getText());
    }

    public void acceptTermsConditions(){
        longpress(terms);
        acceptButton.click();
    }
    public void proceedButtonClick() throws InterruptedException {
        proceed.click();
        Thread.sleep(6000);
    }
    public void setCheckBox(){
        checkBox.click();
    }
}
