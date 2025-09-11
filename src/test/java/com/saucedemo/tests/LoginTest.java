package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;

import utils.Log;

import java.util.HashMap;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(utils.TestListener.class)
public class LoginTest extends BaseTest {

	@Test(priority = 0, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
	public void tc010LoginSuiteWithValidCredential(HashMap<String, String> data) {

		//Log.testCaseInfo("tc010LoginSuiteWithValidCredential: Verify Sauce demo user with valid credential <small><b><i>[" + browser + "]</b></i></small>");

		String username = data.get("UserName");
        String password = data.get("Password");
        String productTitle = data.get("Page_Title");
        
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToSauceDemo(username, password);

		ProductsPage productsPage = new ProductsPage(driver);
		Log.assertThat(productsPage.getTitle().equals(productTitle), 
				"User able to see Saucedemo page successfully",
				"User not able to see Saucedemo page not successfully");
	}

	@Test(priority = 1, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
	public void tc020LoginSuiteWithInValidUsername(HashMap<String, String> data) {

		//Log.testCaseInfo("tc020LoginSuiteWithInValidUsername: Verify Sauce demo user with invalid credential <small><b><i>[" + browser + "]</b></i></small>");
		String username = data.get("UserName");
        String password = data.get("Password");
        
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToSauceDemo(username, password);

		Log.assertThat(loginPage.verifyInvalidLoginErrorMessages(LoginPage.LOGIN_ERROR_MESSAGE), 
				"Verified Invalid login error '"+ LoginPage.LOGIN_ERROR_MESSAGE + "' message",
				"Not verified Invalid login error '"+ LoginPage.LOGIN_ERROR_MESSAGE + "' message");
	}
	
	@Test(priority = 2, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
	public void tc030LoginSuiteWithBlankCredential(HashMap<String, String> data) {

		//Log.testCaseInfo("tc030LoginSuiteWithBlankCredential: Verify Sauce demo user with blank credential <small><b><i>[" + browser + "]</b></i></small>");
		String username = data.get("UserName");
        String password = data.get("Password");
        
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToSauceDemo(username, password);

		Log.assertThat(loginPage.verifyInvalidLoginErrorMessages(LoginPage.BLANK_LOGIN_ERROR_MESSAGE), 
				"Verified Blank login error '"+ LoginPage.BLANK_LOGIN_ERROR_MESSAGE + "' message",
				"Not verified Blank login error '"+ LoginPage.BLANK_LOGIN_ERROR_MESSAGE + "' message");
	}
	
	@Test(priority = 3, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
	public void tc040LoginSuiteWithBlankUsername(HashMap<String, String> data) {

		//Log.testCaseInfo("tc040LoginSuiteWithBlankUsername: Verify Sauce demo user with blank user name credential <small><b><i>[" + browser + "]</b></i></small>");
		
		String username = data.get("UserName");
        String password = data.get("Password");
        
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToSauceDemo(username, password);

		Log.assertThat(loginPage.verifyInvalidLoginErrorMessages(LoginPage.BLANK_LOGIN_ERROR_MESSAGE), 
				"Verified Blank user error '"+ LoginPage.BLANK_LOGIN_ERROR_MESSAGE + "' message",
				"Not verified Blank user error '"+ LoginPage.BLANK_LOGIN_ERROR_MESSAGE + "' message");
	}
	
	@Test(priority = 4, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
	public void tc050LoginSuiteWithBlankPassword(HashMap<String, String> data) {

		//Log.testCaseInfo("tc050LoginSuiteWithBlankPassword: Verify blank password error message to Sauce Demo application <small><b><i>[" + browser + "]</b></i></small>");
		String username = data.get("UserName");
        String password = data.get("Password");
        
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToSauceDemo(username, password);

		Log.assertThat(loginPage.verifyInvalidLoginErrorMessages(LoginPage.BLANK_PASSWORD_ERROR_MESSAGE), 
				"Verified Blank password error '"+ LoginPage.BLANK_PASSWORD_ERROR_MESSAGE + "' message",
				"Not verified Blank password error '"+ LoginPage.BLANK_PASSWORD_ERROR_MESSAGE + "' message");
	}
	
	@Test(priority = 5, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
	public void tc060LoginSuiteWithLockedOutUser(HashMap<String, String> data) {

		//Log.testCaseInfo("tc060LoginSuiteWithLockedOutUser: Verify Locked out user error message to Sauce Demo application <small><b><i>[" + browser + "]</b></i></small>");
		String username = data.get("UserName");
        String password = data.get("Password");
        
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToSauceDemo(username, password);
		
		Log.assertThat(loginPage.verifyInvalidLoginErrorMessages(LoginPage.LOCKED_OUT_USER_ERROR_MESSAGE), 
				"Verified Locked out user error '"+ LoginPage.LOCKED_OUT_USER_ERROR_MESSAGE + "' message",
				"Not verified Locked out user error '"+ LoginPage.LOCKED_OUT_USER_ERROR_MESSAGE + "' message");
	}
	
	@Test(priority = 6, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
	public void tc070LoginSuiteWithProblemUser(HashMap<String, String> data) {

		//Log.testCaseInfo("tc070LoginSuiteWithProblemUser: Verify problem user able to see product images <small><b><i>[" + browser + "]</b></i></small>");
		
		String username = data.get("UserName");
        String password = data.get("Password");
        
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToSauceDemo(username, password);
		
		ProductsPage productsPage = new ProductsPage(driver);
	    Log.assertThat(productsPage.verifyBrokenImages(),
	            "All product images are displayed correctly",
	            "Some product images are broken for problem_user");
	}
	
	@Test(priority = 7, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
	public void tc080LoginSuiteWithPerformanceGlitchUser(HashMap<String, String> data) {

		//Log.testCaseInfo("tc080LoginSuiteWithPerformanceGlitchUse: Verify performance Glit user able to load page very slow <small><b><i>[" + browser + "]</b></i></small>");
		String username = data.get("UserName");
        String password = data.get("Password");
        
		LoginPage loginPage = new LoginPage(driver);
		long startTime = System.currentTimeMillis();
		loginPage.loginToSauceDemo(username, password);
		
		ProductsPage productsPage = new ProductsPage(driver);
	    productsPage.waitForPageToLoad();

	    long endTime = System.currentTimeMillis();
	    long loadTime = endTime - startTime;

	    Log.message("Page load time for performance_glitch_user: " + loadTime + " ms");

	    Log.assertThat(loadTime > 3000,
	            "Performance glitch user has slow load time as expected",
	            "Performance glitch user did not show delay in load time");
	}
	
	@Test(priority = 8, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
	public void tc090LoginSuiteWithInValidPassword(HashMap<String, String> data) {

		//Log.testCaseInfo("tc090LoginSuiteWithInValidPassword: Verify invalid password error message to Sauce Demo application <small><b><i>[" + browser + "]</b></i></small>");
		String username = data.get("UserName");
        String password = data.get("Password");
        
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToSauceDemo(username, password);

		Log.assertThat(loginPage.verifyInvalidLoginErrorMessages(LoginPage.LOGIN_ERROR_MESSAGE), 
				"Verified Invalid login error '"+ LoginPage.LOGIN_ERROR_MESSAGE + "' message",
				"Not verified Invalid login error '"+ LoginPage.LOGIN_ERROR_MESSAGE + "' message");
	}
	
	@Test(priority = 9, dataProvider = "excelData", dataProviderClass = utils.ExcelDataProvider.class)
	public void tc100LoginSuiteWithInValidUsername(HashMap<String, String> data) {

		//Log.testCaseInfo("tc100LoginSuiteWithInValidCredential: Verify invalid user credential error message to Sauce Demo application <small><b><i>[" + browser + "]</b></i></small>");
		String username = data.get("UserName");
        String password = data.get("Password");
        
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToSauceDemo(username, password);

		Log.assertThat(loginPage.verifyInvalidLoginErrorMessages(LoginPage.LOGIN_ERROR_MESSAGE), 
				"Verified Invalid login error '"+ LoginPage.LOGIN_ERROR_MESSAGE + "' message",
				"Not verified Invalid login error '"+ LoginPage.LOGIN_ERROR_MESSAGE + "' message");
	}
}
