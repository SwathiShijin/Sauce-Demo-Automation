package com.saucedemo.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.base.SauceDemoUtils;

import utils.Log;

public class CartPage {
	WebDriver driver;
	
	private static final String btnRemove = "[id*='remove-sauce-labs']";

	@FindBy(css = "div[class*='item_name']")
	List<WebElement> cartItems;
	
	@FindBy(css = btnRemove)
	WebElement buttonRemove;
	
	@FindBy(css = ".shopping_cart_badge")
	WebElement iconBadge;
	
	@FindBy(id = "continue-shopping")
	WebElement btnContinueShopping;

	@FindBy(id = "checkout")
	WebElement btnCheckout;
	
	@FindBy(css = "span.title")
	WebElement txtYourCart;
	
	/**
	 * Constructor class for Products page
	 * 
	 * @param driver
	 */
	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

		// Wait for page to load (title visible)
		SauceDemoUtils.waitForPageLoad(driver, d -> cartItems != null && !cartItems.isEmpty());
	}
	
	 /**
     * Verify if page is loaded
     * 
     * @return 
     * 		- true if title is correct else false
     */
	public boolean isPageLoaded() {
        return txtYourCart.isDisplayed() 
               && txtYourCart.getText().equalsIgnoreCase("Your Cart");
    }

	/**
	 * Verify if all specific products are in the cart
	 * 
	 * @param productNames 
	 * 		- List of product names to verify
	 * @return boolean 
	 * 		- true if all products are in the cart
	 */
	public boolean verifyProductsInCart(List<String> productNames) {
		Log.event("Verify selected items displayed in cart page");
		for (String productName : productNames) {
			boolean found = false;
			for (WebElement item : cartItems) {
				if (item.getText().trim().equalsIgnoreCase(productName)) {
					found = true;
					break;
				}
			}
			if (!found) {
				return false; // one of the products is missing
			}
		}
		return true; // all products found
	}

	/**
	 * Verify which products are missing from the cart
	 * 
	 * @param productNames 
	 * 		- List of product names to verify
	 * @return List<String> 
	 * 		- List of missing products (empty if all are in the cart)
	 */
	public List<String> getMissingProductsFromCart(List<String> productNames) {
		Log.event("Getting missing products from the cart page");
		List<String> missingProducts = new ArrayList<>();

		for (String productName : productNames) {
			boolean found = false;
			for (WebElement item : cartItems) {
				if (item.getText().trim().equalsIgnoreCase(productName)) {
					found = true;
					break;
				}
			}
			if (!found) {
				missingProducts.add(productName);
			}
		}

		return missingProducts;
	}
	
	/**
	 * To click remove button in given product in cart page
	 * 
	 * @param productName
	 * 		- name of the product
	 */
	public void clickOnRemoveButton(String productName) {
		Log.event("Clicking remove button for " + productName);
		for (WebElement item : cartItems) {
			if (item.getText().trim().equalsIgnoreCase(productName)) {
				item.findElement(By.xpath("../..")).findElement(By.cssSelector(btnRemove)).click();
				break;
			}
		}
		Log.message("Clicked 'Remove' button for " + productName);
	}
	
	/**
	 * To check if cart badge is not displayed (either hidden or removed from DOM).
	 * 
	 * @return boolean
	 * 		- true if not displayed, false if visible
	 */
	public boolean isCartBadgeNotDisplayed() {
		try {
	        return !iconBadge.isDisplayed();
	    } catch (NoSuchElementException e) {
	        return true;
	    }
	}
	
	/**
	 * To click the 'Continue Shopping' button
	 * 
	 * @return
	 * 		- product page object
	 */
	public ProductsPage clickContinueShopping() {
	    Log.event("Clicking 'Continue Shopping' button");
	    btnContinueShopping.click();
	    Log.message("Clicked 'Continue Shopping' button");
	    return new ProductsPage(driver);
	}
	
	/**
	 * To click the 'Checkout' button
	 * 
	 * @return
	 * 		- Checkout page object
	 */
	public CheckoutUserInfoPage clickCheckout() {
	    Log.event("Clicking 'Checkout' button");
	    btnCheckout.click();
	    return new CheckoutUserInfoPage(driver); 
	}

}
