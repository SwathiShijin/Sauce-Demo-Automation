package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.base.SauceDemoUtils;

import utils.Log;

public class CheckoutFinishOrderPage {

    WebDriver driver;
    
    public static final String ORDER_SUCCESS_MESSAGE = "Thank you for your order! Your order has been dispatched, and will arrive just as fast as the pony can get there!";

    @FindBy(css = "span.title")
    WebElement pageTitle;

    @FindBy(css = "h2.complete-header")
    WebElement hdConfirmationMessage;
    
    @FindBy(css = "div.complete-text")
    WebElement txtdispatchMessage;
    
    @FindBy(id = "back-to-products")
    WebElement btnBackHome;

    public CheckoutFinishOrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        SauceDemoUtils.waitForPageLoad(driver, d -> pageTitle.isDisplayed());
    }

    /**
     * Verify if page is loaded
     * 
     * @return 
     * 		- true if title is correct else false
     */
    public boolean isPageLoaded() {
        return pageTitle.isDisplayed() && pageTitle.getText().equals("Checkout: Complete!");
    }

    /**
     * To get order confirmation message
     * 
     * @return
     * 		- success message
     */
    public String getConfirmationMessage() {
    	Log.event("Verifying order confirmation message");
        return hdConfirmationMessage.getText() + " " + txtdispatchMessage.getText();
    }
    
    /**
     * Click Back Home button to go back to Products page
     * 
     * @return 
     * 		-Products Page
     */
    public ProductsPage clickBackHome() {
        Log.event("Clicking Back Home button");
        btnBackHome.click();
        Log.message("Clicked Back Home button");
        return new ProductsPage(driver);
    }
}
