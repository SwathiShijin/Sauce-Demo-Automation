package com.saucedemo.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.base.SauceDemoUtils;
import utils.Log;

public class CheckoutOverviewPage {

    WebDriver driver;

    @FindBy(css = "span.title")
    WebElement pageTitle;

    @FindBy(css = "div.inventory_item_name")
    List<WebElement> itemNames;

    @FindBy(css = "button#finish")
    WebElement btnFinish;

    @FindBy(id = "cancel")
    WebElement btnCancel;

    @FindBy(css = ".summary_subtotal_label")
    WebElement txtSubtotal;

    @FindBy(css = ".summary_tax_label")
    WebElement txtTax;

    @FindBy(css = ".summary_total_label")
    WebElement txtTotal;

    /**
     * Constructor for Checkout Step Two page
     * 
     * @param driver
     */
    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        // Wait for page to load (title visible)
        SauceDemoUtils.waitForPageLoad(driver, d -> pageTitle.isDisplayed());
    }

    /**
     * To verify if page is loaded
     * 
     * @return 
     * 		- true if title is correct else false
     */
    public boolean isPageLoaded() {
        return pageTitle.isDisplayed() && pageTitle.getText().equals("Checkout: Overview");
    }

    /**
     * To get list of products displayed in checkout summary
     * 
     * @return 
     * 		- List of product names
     */
    public List<String> getProductNamesInSummary() {
    	Log.event("Getting list of products displayed in checkout summary");
        List<String> products = new ArrayList<>();
        for (WebElement item : itemNames) {
            products.add(item.getText().trim());
        }
        return products;
    }

    /**
     * To click Finish button to complete checkout
     * 
     * @return 
     * 		- CheckoutCompletePage
     */
    public CheckoutFinishOrderPage clickFinish() {
        Log.event("Clicking Finish button");
        btnFinish.click();
        Log.message("Clicked Finish button");
        return new CheckoutFinishOrderPage(driver);
    }

    /**
     * To click Cancel button to go back to Checkout user info page
     * 
     * @return 
     * 		- Products page
     */
    public ProductsPage clickCancel() {
        Log.event("Clicking Cancel button");
        btnCancel.click();
        Log.message("Clicked Cancel button");
        return new ProductsPage(driver);
    }

    /**
     * To get subtotal value
     * 
     * @return 
     * 		- subtotal
     */
    public Double getSubtotal() {
    	Log.event("Getting subtotal in checkout summary");
    	return Double.parseDouble(txtSubtotal.getText().replace("Item total: $", "").trim());//return txtSubtotal.getText();
    }

    /**
     * To get tax value
     * 
     * @return 
     * 		- tax 
     */
    public Double getTax() {
    	Log.event("Getting Tax in checkout summary");
    	return Double.parseDouble(txtTax.getText().replace("Tax: $", "").trim());//return txtTax.getText();
    }

    /**
     * To get total value
     * 
     * @return 
     * 		- total
     */
    public Double getTotal() {
    	Log.event("Getting total in checkout summary");
    	return Double.parseDouble(txtTotal.getText().replace("Total: $", "").trim());//return txtTotal.getText();
    }
}
