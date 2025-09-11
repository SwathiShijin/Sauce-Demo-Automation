package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.base.SauceDemoUtils;

import utils.Log;

public class LoginPage {
	
	public static final String LOGIN_ERROR_MESSAGE = "Epic sadface: Username and password do not match any user in this service";
	public static final String BLANK_LOGIN_ERROR_MESSAGE = "Epic sadface: Username is required";
	public static final String BLANK_PASSWORD_ERROR_MESSAGE = "Epic sadface: Password is required";
	public static final String LOCKED_OUT_USER_ERROR_MESSAGE = "Epic sadface: Sorry, this user has been locked out.";

	WebDriver driver;
	String saucedemoUrl;
	
	@FindBy(id = "user-name")
    WebElement txtUserName;

    @FindBy(id = "password")
    WebElement txtPassWord;

    @FindBy(id = "login-button")
    WebElement btnLogin;

    @FindBy(css = ".error-message-container")
    WebElement txtIncorrectLoginError;
	
	
	 /**
     * 
     * Constructor class for Login page Here we initializing the driver for page
     * factory objects. For ajax element waiting time has added while
     * initialization
     * 
     * @param driver
     * @param url
     */
	public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        
        // Wait for page to load (username input visible)
        SauceDemoUtils.waitForPageLoad(driver, d -> txtUserName.isDisplayed());
    }
	
	/**
	 * To enter Login details in Saucedemo page
	 * 
	 * @param username
	 * @param password
	 */
	public void loginToSauceDemo(String username, String password) {
		Log.event("Login to the Saucedemo");
		txtUserName.sendKeys(username);
		txtPassWord.sendKeys(password);
		btnLogin.click();
		Log.message("Logged into Saucedemo as (" + username + "/" + password + ")"); 
    }
	
	/**
	 * To get the incorrect login error message
	 * 
	 * @return
	 * 		- Error message
	 */
	public String getIncorrectLoginErrorMessage() {
		Log.event("Getting the incorrect login error message");
        return txtIncorrectLoginError.getText();
    }
	
	/**
	 * To verify incorrect error messages
	 * @param errorMessage
	 * @return
	 */
	public boolean verifyInvalidLoginErrorMessages(String errorMessage){
		Log.event("Verifying the incorrect login error message");
		
		String message = "";
		switch(errorMessage) {
		case LOGIN_ERROR_MESSAGE:
			message = errorMessage;
			break;
		case BLANK_LOGIN_ERROR_MESSAGE:
			message = errorMessage;
			break;
		case BLANK_PASSWORD_ERROR_MESSAGE:
			message = errorMessage;
			break;
		case LOCKED_OUT_USER_ERROR_MESSAGE:
			message = errorMessage;
			break;
		}
		
		if(getIncorrectLoginErrorMessage().equals(message))
			return true;
		else
			return false;
	}
	
}
