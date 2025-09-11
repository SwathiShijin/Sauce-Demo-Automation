package com.saucedemo.tests;

import java.util.HashMap;
import java.util.List;

import org.testng.annotations.Test;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.pages.ProductsPage.sortContainer;

import utils.Log;

public class ProductTest extends BaseTest{

	@Test(priority = 0, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
	public void tc010VerifyCartBadgeUpdates(HashMap<String, String> testData){
		
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
		int count = productsPage.getCartBadgeCount();
		Log.assertThat(count == 2, 
				"Verified cast badge count " + count, 
				"Not verified cast badge count " + count);
	}
	
	@Test(priority = 1, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
	public void tc020VerifyRemoveFromProductPage(HashMap<String, String> testData){
		
		List<String> productName = List.of(testData.get("Product_Names").split("\\|"));
		String username = testData.get("UserName");
        String password = testData.get("Password");
        String productTitle = testData.get("Page_Title");
        String[] operations = testData.get("Item_Operations").split("\\|");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToSauceDemo(username, password);

		ProductsPage productsPage = new ProductsPage(driver);
		Log.assertThat(productsPage.getTitle().equals(productTitle), 
				"Login to Saucedemo page successfully",
				"Login to Saucedemo page not successfully");
		
		productsPage.addGivenProductToCart(productName);
		Log.assertThat(productsPage.isButtonDisplayed(productName.get(0), operations[0]), 
				"Verified 'Remove' is diplayed for " + productName.get(0), 
				"Not verified 'Remove' is diplayed for " + productName.get(0));
		
		productsPage.clickRemoveProductFromCart(productName.get(0));
		Log.assertThat(productsPage.isButtonDisplayed(productName.get(0), operations[1]), 
				"Verified 'Add to cart' is diplayed for " + productName.get(0), 
				"Not verified 'Add to cart' is diplayed for " + productName.get(0));
	}
	
	@Test(priority = 2, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
	public void tc030VerifyProductDetailPage(HashMap<String, String> testData){
		
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
		productsPage.clickAndOpenProductDetails(productName.get(0));
		Log.assertThat(productsPage.verifyProductDetailPage(), 
				"Verified 'Product' detail page", 
				"Not verified 'Product' detail page");
	}
	
	@Test(priority = 3, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
	public void tc040VerifyBackToProduct(HashMap<String, String> testData){
		
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
		productsPage.clickAndOpenProductDetails(productName.get(0));
		Log.assertThat(productsPage.verifyProductDetailPage(), 
				"Verified 'Product' detail page", 
				"Not verified 'Product' detail page");
		
		productsPage.clickBackToProducts();
		Log.assertThat(productsPage.getTitle().equals(productTitle), 
				"Verified 'Product' page is displayed successfully", 
				"Not verified 'Product' page is displayed successfully");
	}
	
	@Test(priority = 4, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
    public void tc050verifySortingByNameAZ(HashMap<String, String> testData) {
		String username = testData.get("UserName");
        String password = testData.get("Password");
        String productTitle = testData.get("Page_Title");
        String sortType = testData.get("Sort_Type");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToSauceDemo(username, password);

		ProductsPage productsPage = new ProductsPage(driver);
		Log.assertThat(productsPage.getTitle().equals(productTitle), 
				"Login to Saucedemo page successfully",
				"Login to Saucedemo page not successfully");
        productsPage.selectSortOption(sortType);
        Log.assertThat(productsPage.verifySortingOrder(sortContainer.NameAtoZ), 
				"Verified Products sorted A-Z", 
				"Not verified Products sorted A-Z");
    }

    @Test(priority = 5, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
    public void tc060verifySortingByNameZA(HashMap<String, String> testData) {
    	String username = testData.get("UserName");
        String password = testData.get("Password");
        String productTitle = testData.get("Page_Title");
        String sortType = testData.get("Sort_Type");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToSauceDemo(username, password);

		ProductsPage productsPage = new ProductsPage(driver);
		Log.assertThat(productsPage.getTitle().equals(productTitle), 
				"Login to Saucedemo page successfully",
				"Login to Saucedemo page not successfully");
        productsPage.selectSortOption(sortType);
        Log.assertThat(productsPage.verifySortingOrder(sortContainer.NameZtoA), 
				"Verified Products sorted Z-A", 
				"Not verified Products sorted Z-A");
    }

    @Test(priority = 6, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
    public void tc070verifySortingByPriceLowToHigh(HashMap<String, String> testData) {
    	String username = testData.get("UserName");
        String password = testData.get("Password");
        String productTitle = testData.get("Page_Title");
        String sortType = testData.get("Sort_Type");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToSauceDemo(username, password);

		ProductsPage productsPage = new ProductsPage(driver);
		Log.assertThat(productsPage.getTitle().equals(productTitle), 
				"Login to Saucedemo page successfully",
				"Login to Saucedemo page not successfully");
        productsPage.selectSortOption(sortType);
        Log.assertThat(productsPage.verifySortingOrder(sortContainer.Pricelowtohigh), 
				"Products sorted by Price Low-High", 
				"Products not sorted by Price Low-High");
    }

    @Test(priority = 7, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
    public void tc080verifySortingByPriceHighToLow(HashMap<String, String> testData) {
    	String username = testData.get("UserName");
        String password = testData.get("Password");
        String productTitle = testData.get("Page_Title");
        String sortType = testData.get("Sort_Type");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToSauceDemo(username, password);

		ProductsPage productsPage = new ProductsPage(driver);
		Log.assertThat(productsPage.getTitle().equals(productTitle), 
				"Login to Saucedemo page successfully",
				"Login to Saucedemo page not successfully");
        productsPage.selectSortOption(sortType);
        Log.assertThat(productsPage.verifySortingOrder(sortContainer.Pricehightolow), 
				"Products sorted by Price High-Low", 
				"Products not sorted by Price High-Low");
    }
}
