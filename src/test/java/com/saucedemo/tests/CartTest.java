package com.saucedemo.tests;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.Test;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutUserInfoPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;

import utils.Log;

public class CartTest extends BaseTest{
	
	@Test(priority = 0, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
	public void tc010VerifyCartItems(HashMap<String, String> testData){
		
		List<String> productName = List.of(testData.get("Product_Names").split("\\|"));
		String username = testData.get("UserName");
        String password = testData.get("Password");
        String productTitle = testData.get("Page_Title");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToSauceDemo(username, password);

		ProductsPage productsPage = new ProductsPage(driver);
		Log.assertThat(productsPage.getTitle().equals(productTitle), 
				"Login to Saucedemo page successfully",
				"Login to Saucedemo page not successfully");
		
		productsPage.addGivenProductToCart(productName);
		CartPage cartPage = productsPage.goToCart();
		Log.assertThat(cartPage.verifyProductsInCart(productName), 
				"Selected items are displayed in cart page: " + productName, 
				"Selected items are not displayed in cart page: " + productName);
	}
	
	@Test(priority = 1, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
	public void tc020VerifyRemoveCartItem(HashMap<String, String> testData){
		
		String productName = testData.get("Product_Names");
		String username = testData.get("UserName");
        String password = testData.get("Password");
        String productTitle = testData.get("Page_Title");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToSauceDemo(username, password);
		
		ProductsPage productsPage = new ProductsPage(driver);
		Log.assertThat(productsPage.getTitle().equals(productTitle), 
				"Login to Saucedemo page successfully",
				"Login to Saucedemo page not successfully");
		
		productsPage.addGivenProductToCart(Arrays.asList(productName));
		CartPage cartPage = productsPage.goToCart();
		Log.assertThat(cartPage.verifyProductsInCart(Arrays.asList(productName)), 
				"Selected items are displayed in cart page: " + productName, 
				"Selected items are not displayed in cart page: " + productName);
		
		cartPage.clickOnRemoveButton(productName);
		Log.assertThat(!cartPage.verifyProductsInCart(Arrays.asList(productName)), 
				"Removed item not displayed in cart page: " + productName, 
				"Removed item displayed in cart page: " + productName);
	}
	
	@Test(priority = 2, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
	public void tc030VerifytestCartBadgeUpdatesAfterRemoval(HashMap<String, String> testData){
		
		List<String> productNames = List.of(testData.get("Product_Names").split("\\|"));
		String username = testData.get("UserName");
        String password = testData.get("Password");
        String productTitle = testData.get("Page_Title");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToSauceDemo(username, password);

		ProductsPage productsPage = new ProductsPage(driver);
		Log.assertThat(productsPage.getTitle().equals(productTitle), 
				"Login to Saucedemo page successfully",
				"Login to Saucedemo page not successfully");
		
		productsPage.addGivenProductToCart(productNames);
		CartPage cartPage = productsPage.goToCart();
		Log.assertThat(cartPage.verifyProductsInCart(productNames), 
				"Selected items are displayed in cart page: " + productNames, 
				"Selected items are not displayed in cart page: " + productNames);
		
		cartPage.clickOnRemoveButton(productNames.get(0));
		Log.assertThat(!cartPage.verifyProductsInCart(Arrays.asList(productNames.get(0))), 
				"Removed item not displayed in cart page: " + productNames.get(0), 
				"Removed item displayed in cart page: " + productNames.get(0));
		int count = productsPage.getCartBadgeCount();
		Log.assertThat(productsPage.getCartBadgeCount() == 1, 
				"Cart badge count is decreased after removal" + count, 
				"Cart badge count not decrease after removal" + count);
		
		cartPage.clickOnRemoveButton(productNames.get(1));
		Log.assertThat(cartPage.isCartBadgeNotDisplayed(), 
				"Cart badge is not displayed when cart is empty", 
				"Cart badge is displayed when cart is empty");
	}

	@Test(priority = 3, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
	public void tc040VerifyContinueShoppingNavigation(HashMap<String, String> testData) {

		String productName = testData.get("Product_Names");
		String username = testData.get("UserName");
        String password = testData.get("Password");
        String productTitle = testData.get("Page_Title");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToSauceDemo(username, password);

	    ProductsPage productsPage = new ProductsPage(driver);
	    productsPage.addGivenProductToCart(List.of(productName));

	    CartPage cartPage = productsPage.goToCart();
	    productsPage = cartPage.clickContinueShopping();

	    Log.assertThat(productsPage.getTitle().equals(productTitle), 
				"Verified Navigated back to Products page using Continue Shopping button", 
				"Not verified: Navigated back to Products page using Continue Shopping button");
	}
	
	@Test(priority = 4, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
	public void tc050VerifyCheckoutNavigation(HashMap<String, String> testData) {

		String productName = testData.get("Product_Names");
		String username = testData.get("UserName");
        String password = testData.get("Password");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToSauceDemo(username, password);

	    ProductsPage productsPage = new ProductsPage(driver);
	    productsPage.addGivenProductToCart(List.of(productName));

	    CartPage cartPage = productsPage.goToCart();
	    CheckoutUserInfoPage checkoutUserInfoPage = cartPage.clickCheckout();

	    Log.assertThat(checkoutUserInfoPage.isPageLoaded(),
				"Verified Navigated to 'Checkout: Your imformation' page using Checkout button", 
				"Not verified: Navigated to 'Checkout: Your imformation' page using Checkout button");
	}
}
