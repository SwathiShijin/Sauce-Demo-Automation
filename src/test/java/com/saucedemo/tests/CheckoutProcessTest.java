package com.saucedemo.tests;

import java.util.HashMap;
import java.util.List;

import org.testng.annotations.Test;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutFinishOrderPage;
import com.saucedemo.pages.CheckoutOverviewPage;
import com.saucedemo.pages.CheckoutUserInfoPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;

import utils.Log;

public class CheckoutProcessTest extends BaseTest {

	@Test(priority = 0, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
	public void tc010VerifyUserWithBlankFirstName(HashMap<String, String> testData) {
		
		String username = testData.get("UserName");
        String password = testData.get("Password");
        String firstName = testData.get("FirstName");
		String lastName = testData.get("LastName");
		String postalCode = testData.get("PostalCode");
		String productName = testData.get("Product_Names");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToSauceDemo(username, password);

	    ProductsPage productsPage = new ProductsPage(driver);
	    productsPage.addGivenProductToCart(List.of(productName));

	    CartPage cartPage = productsPage.goToCart();
	    CheckoutUserInfoPage checkoutUserInfoPage = cartPage.clickCheckout();

	    Log.assertThat(checkoutUserInfoPage.isPageLoaded(),
				"User able to see 'Checkout: Your information' page after clicking Checkout button", 
				"User not able to see 'Checkout: Your information' page after clicking Checkout button");
	    
	    checkoutUserInfoPage.enterAndSubmitUserInfo(firstName, lastName, postalCode);
	    Log.assertThat(checkoutUserInfoPage.verifyBlankFieldErrorMessages(CheckoutUserInfoPage.BLANK_FIRST_NAME_ERROR_MESSAGE), 
				"Blank first name error '"+ CheckoutUserInfoPage.BLANK_FIRST_NAME_ERROR_MESSAGE + "' message is displayed successfully",
				"Blank first name error '"+ CheckoutUserInfoPage.BLANK_FIRST_NAME_ERROR_MESSAGE + "' message is not displayed successfully");
	}
	
	@Test(priority = 1, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
	public void tc020VerifyUserWithBlankLastName(HashMap<String, String> testData) {

		String username = testData.get("UserName");
        String password = testData.get("Password");
        String firstName = testData.get("FirstName");
		String lastName = testData.get("LastName");
		String postalCode = testData.get("PostalCode");
		String productName = testData.get("Product_Names");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToSauceDemo(username, password);

	    ProductsPage productsPage = new ProductsPage(driver);
	    productsPage.addGivenProductToCart(List.of(productName));

	    CartPage cartPage = productsPage.goToCart();
	    CheckoutUserInfoPage checkoutUserInfoPage = cartPage.clickCheckout();

	    Log.assertThat(checkoutUserInfoPage.isPageLoaded(),
				"User able to see 'Checkout: Your information' page after clicking Checkout button", 
				"User not able to see 'Checkout: Your information' page after clicking Checkout button");
	    
	    checkoutUserInfoPage.enterAndSubmitUserInfo(firstName, lastName, postalCode);
	    Log.assertThat(checkoutUserInfoPage.verifyBlankFieldErrorMessages(CheckoutUserInfoPage.BLANK_LAST_NAME_ERROR_MESSAGE), 
				"Blank last name error '"+ CheckoutUserInfoPage.BLANK_LAST_NAME_ERROR_MESSAGE + "' message is displayed successfully",
				"Blank last name error '"+ CheckoutUserInfoPage.BLANK_LAST_NAME_ERROR_MESSAGE + "' message is not displayed successfully");
	}
	
	@Test(priority = 2, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
	public void tc030VerifyUserWithBlankPostalCodeName(HashMap<String, String> testData) {

		String username = testData.get("UserName");
        String password = testData.get("Password");
        String firstName = testData.get("FirstName");
		String lastName = testData.get("LastName");
		String postalCode = testData.get("PostalCode");
		String productName = testData.get("Product_Names");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToSauceDemo(username, password);

	    ProductsPage productsPage = new ProductsPage(driver);
	    productsPage.addGivenProductToCart(List.of(productName));

	    CartPage cartPage = productsPage.goToCart();
	    CheckoutUserInfoPage checkoutUserInfoPage = cartPage.clickCheckout();

	    Log.assertThat(checkoutUserInfoPage.isPageLoaded(),
				"User able to see 'Checkout: Your information' page after clicking Checkout button", 
				"User not able to see 'Checkout: Your information' page after clicking Checkout button");
	    
	    checkoutUserInfoPage.enterAndSubmitUserInfo(firstName, lastName, postalCode);
	    Log.assertThat(checkoutUserInfoPage.verifyBlankFieldErrorMessages(CheckoutUserInfoPage.BLANK_POSTAL_CODE_ERROR_MESSAGE), 
				"Blank postal code error '"+ CheckoutUserInfoPage.BLANK_POSTAL_CODE_ERROR_MESSAGE + "' message is displayed successfully",
				"Blank postal code error '"+ CheckoutUserInfoPage.BLANK_POSTAL_CODE_ERROR_MESSAGE + "' message is not displayed successfully");
	}
	
	@Test(priority = 3, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
	public void tc040VerifyUserWithValidInfo(HashMap<String, String> testData) {

		String username = testData.get("UserName");
        String password = testData.get("Password");
        String firstName = testData.get("FirstName");
		String lastName = testData.get("LastName");
		String postalCode = testData.get("PostalCode");
		String productName = testData.get("Product_Names");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToSauceDemo(username, password);

	    ProductsPage productsPage = new ProductsPage(driver);
	    productsPage.addGivenProductToCart(List.of(productName));

	    CartPage cartPage = productsPage.goToCart();
	    CheckoutUserInfoPage checkoutUserInfoPage = cartPage.clickCheckout();

	    Log.assertThat(checkoutUserInfoPage.isPageLoaded(),
				"User able to see 'Checkout: Your information' page after clicking Checkout button", 
				"User not able to see 'Checkout: Your information' page after clicking Checkout button");
	    
	    checkoutUserInfoPage.enterAndSubmitUserInfo(firstName, lastName, postalCode);
	    CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
	    Log.assertThat(checkoutOverviewPage.isPageLoaded(), 
				"User able to see checkout overview page",
				"User not able to see checkout overview page");
	}
	
	@Test(priority = 4, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
	public void tc050VerifyCancelFromUserInfo(HashMap<String, String> testData) {
		
		String username = testData.get("UserName");
        String password = testData.get("Password");
        String firstName = testData.get("FirstName");
		String lastName = testData.get("LastName");
		String postalCode = testData.get("PostalCode");
		String productName = testData.get("Product_Names");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToSauceDemo(username, password);

	    ProductsPage productsPage = new ProductsPage(driver);
	    productsPage.addGivenProductToCart(List.of(productName));

	    CartPage cartPage = productsPage.goToCart();
	    CheckoutUserInfoPage checkoutUserInfoPage = cartPage.clickCheckout();

	    Log.assertThat(checkoutUserInfoPage.isPageLoaded(),
				"User able to see 'Checkout: Your information' page after clicking Checkout button", 
				"User not able to see 'Checkout: Your information' page after clicking Checkout button");
	    
	    checkoutUserInfoPage.enterCheckoutInformation(firstName, lastName, postalCode);
	    cartPage = checkoutUserInfoPage.clickCancel();
	    Log.assertThat(cartPage.isPageLoaded(),
				"User able to see 'Cart' page after clicking 'Cancel' button", 
				"User not able to see 'Cart' page after clicking 'Cancel' button");
	}
	
	// Checkout Overview Page
	
	@Test(priority = 5, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
	public void tc060VerifyItemAndPriceDetails(HashMap<String, String> testData) {
		
		String username = testData.get("UserName");
        String password = testData.get("Password");
        String firstName = testData.get("FirstName");
		String lastName = testData.get("LastName");
		String postalCode = testData.get("PostalCode");
		List<String> productName = List.of(testData.get("Product_Names").split("\\|"));

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToSauceDemo(username, password);

	    ProductsPage productsPage = new ProductsPage(driver);
	    productsPage.addGivenProductToCart(productName);

	    CartPage cartPage = productsPage.goToCart();
	    CheckoutUserInfoPage checkoutUserInfoPage = cartPage.clickCheckout();

	    Log.assertThat(checkoutUserInfoPage.isPageLoaded(),
				"User able to see 'Checkout: Your information' page after clicking Checkout button", 
				"User not able to see 'Checkout: Your information' page after clicking Checkout button");
	    
	    checkoutUserInfoPage.enterAndSubmitUserInfo(firstName, lastName, postalCode);
	    CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
	    Log.assertThat(checkoutOverviewPage.isPageLoaded(), 
				"User able to see checkout overview page",
				"User not able to see checkout overview page");
	    
	    Log.assertThat(checkoutOverviewPage.getProductNamesInSummary().equals(productName), 
				"User able to see correct product list in checkout overview page",
				"User not able to see correct product list in checkout overview page");
	    Log.assertThat(checkoutOverviewPage.getTotal() == checkoutOverviewPage.getSubtotal() + checkoutOverviewPage.getTax(), 
				"User able to see correct total in checkout overivew page after sum of subtotal + tax",
				"User not able to see correct total in checkout overivew page after sum of subtotal + tax");
	}
	
	@Test(priority = 6, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
	public void tc070VerifyCancelFromCheckoutOverview(HashMap<String, String> testData) {
		
		String username = testData.get("UserName");
        String password = testData.get("Password");
		String firstName = testData.get("FirstName");
		String lastName = testData.get("LastName");
		String postalCode = testData.get("PostalCode");
		String productTitle = testData.get("Page_Title");
		List<String> productName = List.of(testData.get("Product_Names").split("\\|"));

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToSauceDemo(username, password);

	    ProductsPage productsPage = new ProductsPage(driver);
	    productsPage.addGivenProductToCart(productName);

	    CartPage cartPage = productsPage.goToCart();
	    CheckoutUserInfoPage checkoutUserInfoPage = cartPage.clickCheckout();

	    Log.assertThat(checkoutUserInfoPage.isPageLoaded(),
				"User able to see 'Checkout: Your information' page after clicking Checkout button", 
				"User not able to see 'Checkout: Your information' page after clicking Checkout button");
	    
	    checkoutUserInfoPage.enterAndSubmitUserInfo(firstName, lastName, postalCode);
	    CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
	    Log.assertThat(checkoutOverviewPage.isPageLoaded(), 
				"User able to see checkout overview page",
				"User not able to see checkout overview page");
	    
	    productsPage = checkoutOverviewPage.clickCancel();
	    Log.assertThat(productsPage.getTitle().equals(productTitle), 
				"User able to see Products page",
				"User not able to see Products page");
	}
	
	@Test(priority = 7, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
	public void tc080VerifyFinishFromCheckoutOverview(HashMap<String, String> testData) {
		
		String username = testData.get("UserName");
        String password = testData.get("Password");
		String firstName = testData.get("FirstName");
		String lastName = testData.get("LastName");
		String postalCode = testData.get("PostalCode");
		List<String> productName = List.of(testData.get("Product_Names").split("\\|"));

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToSauceDemo(username, password);

	    ProductsPage productsPage = new ProductsPage(driver);
	    productsPage.addGivenProductToCart(productName);

	    CartPage cartPage = productsPage.goToCart();
	    CheckoutUserInfoPage checkoutUserInfoPage = cartPage.clickCheckout();

	    Log.assertThat(checkoutUserInfoPage.isPageLoaded(),
				"User able to see 'Checkout: Your information' page after clicking Checkout button", 
				"User not able to see 'Checkout: Your information' page after clicking Checkout button");
	    
	    checkoutUserInfoPage.enterAndSubmitUserInfo(firstName, lastName, postalCode);
	    CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
	    Log.assertThat(checkoutOverviewPage.isPageLoaded(), 
				"User able to see checkout overview page",
				"User not able to see checkout overview page");
	    
	    CheckoutFinishOrderPage checkoutFinishOrderPage = checkoutOverviewPage.clickFinish();
	    Log.assertThat(checkoutFinishOrderPage.isPageLoaded(), 
				"User able to see Checkout: Complete page",
				"User not able to see Checkout: Complete page");
	}
	
	//Checkout Finish page
	
	@Test(priority = 8, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
	public void tc090VerifyCheckOutMessageFromCheckoutFinishOrder(HashMap<String, String> testData) {
		
		String username = testData.get("UserName");
        String password = testData.get("Password");
		String firstName = testData.get("FirstName");
		String lastName = testData.get("LastName");
		String postalCode = testData.get("PostalCode");
		List<String> productName = List.of(testData.get("Product_Names").split("\\|"));

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToSauceDemo(username, password);
	    ProductsPage productsPage = new ProductsPage(driver);
	    productsPage.addGivenProductToCart(productName);

	    CartPage cartPage = productsPage.goToCart();
	    CheckoutUserInfoPage checkoutUserInfoPage = cartPage.clickCheckout();

	    Log.assertThat(checkoutUserInfoPage.isPageLoaded(),
				"User able to see 'Checkout: Your information' page after clicking Checkout button", 
				"User not able to see 'Checkout: Your information' page after clicking Checkout button");
	    
	    checkoutUserInfoPage.enterAndSubmitUserInfo(firstName, lastName, postalCode);
	    CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
	    Log.assertThat(checkoutOverviewPage.isPageLoaded(), 
				"User able to see checkout overview page",
				"User not able to see checkout overview page");
	    
	    CheckoutFinishOrderPage checkoutFinishOrderPage = checkoutOverviewPage.clickFinish();
	    Log.assertThat(checkoutFinishOrderPage.isPageLoaded(), 
				"User able to see Checkout: Complete page",
				"User not able to see Checkout: Complete page");
	    
	    String msgSuccess = checkoutFinishOrderPage.getConfirmationMessage();
	    Log.assertThat(msgSuccess.equals(CheckoutFinishOrderPage.ORDER_SUCCESS_MESSAGE), 
				"User able to see " + CheckoutFinishOrderPage.ORDER_SUCCESS_MESSAGE,
				"User not able to see " + CheckoutFinishOrderPage.ORDER_SUCCESS_MESSAGE);
	}
	
	@Test(priority = 9, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
	public void tc100VerifyBackHomeFromCheckoutFinishOrder(HashMap<String, String> testData) {
		
		String username = testData.get("UserName");
        String password = testData.get("Password");
		String firstName = testData.get("FirstName");
		String lastName = testData.get("LastName");
		String postalCode = testData.get("PostalCode");
		String productTitle = testData.get("Page_Title");
		List<String> productName = List.of(testData.get("Product_Names").split("\\|"));

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToSauceDemo(username, password);

	    ProductsPage productsPage = new ProductsPage(driver);
	    productsPage.addGivenProductToCart(productName);

	    CartPage cartPage = productsPage.goToCart();
	    CheckoutUserInfoPage checkoutUserInfoPage = cartPage.clickCheckout();

	    Log.assertThat(checkoutUserInfoPage.isPageLoaded(),
				"User able to see 'Checkout: Your information' page after clicking Checkout button", 
				"User not able to see 'Checkout: Your information' page after clicking Checkout button");
	    
	    checkoutUserInfoPage.enterAndSubmitUserInfo(firstName, lastName, postalCode);
	    CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
	    Log.assertThat(checkoutOverviewPage.isPageLoaded(), 
				"User able to see checkout overview page",
				"User not able to see checkout overview page");
	    
	    CheckoutFinishOrderPage checkoutFinishOrderPage = checkoutOverviewPage.clickFinish();
	    Log.assertThat(checkoutFinishOrderPage.isPageLoaded(), 
				"User able to see Checkout: Complete page",
				"User not able to see Checkout: Complete page");
	    
	    productsPage = checkoutFinishOrderPage.clickBackHome();
	    Log.assertThat(productsPage.getTitle().equals(productTitle), 
				"User able to see Products page",
				"User not able to see Products page");
	}
}
