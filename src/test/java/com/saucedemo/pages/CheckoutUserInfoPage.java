package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.base.SauceDemoUtils;
import utils.Log;

public class CheckoutUserInfoPage {

    WebDriver driver;
    
    public static final String BLANK_FIRST_NAME_ERROR_MESSAGE = "Error: First Name is required";
	public static final String BLANK_LAST_NAME_ERROR_MESSAGE = "Error: Last Name is required";
	public static final String BLANK_POSTAL_CODE_ERROR_MESSAGE = "Error: Postal Code is required";

    @FindBy(id = "first-name")
    WebElement txtFirstName;

    @FindBy(id = "last-name")
    WebElement txtLastName;

    @FindBy(id = "postal-code")
    WebElement txtPostalCode;

    @FindBy(id = "continue")
    WebElement btnContinue;

    @FindBy(id = "cancel")
    WebElement btnCancel;

    @FindBy(css = "span.title")
    WebElement pageTitle;
    
    @FindBy(css = "h3[data-test='error']")
    WebElement errorMessage;

    /**
     * Constructor for Checkout Step One page
     * 
     * @param driver
     */
    public CheckoutUserInfoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        // Wait for page to load (title visible)
        SauceDemoUtils.waitForPageLoad(driver, d -> pageTitle.isDisplayed());
    }

    /**
     * Verify if page is loaded
     * 
     * @return 
     * 		- true if title is displayed else false
     */
    public boolean isPageLoaded() {
        return pageTitle.isDisplayed() && pageTitle.getText().equals("Checkout: Your Information");
    }

    /**
     * To enter checkout information
     * 
     * @param firstName
     * @param lastName
     * @param postalCode
     */
    public void enterCheckoutInformation(String firstName, String lastName, String postalCode) {
        Log.event("Entering checkout information");
        txtFirstName.clear();
        txtFirstName.sendKeys(firstName);

        txtLastName.clear();
        txtLastName.sendKeys(lastName);

        txtPostalCode.clear();
        txtPostalCode.sendKeys(postalCode);

        Log.message("Entered Checkout information: " + firstName + " " + lastName + " " + postalCode);
    }

   /**
    * To click 'Continue' button
    * 
    * @return
    * 		- checkout overview page 
    */
    public CheckoutOverviewPage clickContinue() {
        Log.event("Clicking Continue button");
        btnContinue.click();
        Log.message("Clicked Continue button");
        return new CheckoutOverviewPage(driver);
    }

    /**
     * To click 'Cancel' button
     * 
     * @return
     * 		- cart page 
     */
    public CartPage clickCancel() {
        Log.event("Clicking Cancel button");
        btnCancel.click();
        Log.message("Clicked Cancel button");
        return new CartPage(driver);
    }
    
    /**
     * To get invalid error message 
     * 
     * @return
     * 		- message
     */
   public String getErrorMessage() {
	   Log.event("Getting invalid error message");
       return errorMessage.getText().trim();
   }
   
   /**
    * To enter all user details and click continue button
    * 
    * @param firstName
    * 		- user first name
    * @param lastName
    * 		- user last name
    * @param postalCode
    * 		- postal code
    */
   public void enterAndSubmitUserInfo(String firstName, String lastName, String postalCode) {
	   enterCheckoutInformation(firstName, lastName, postalCode);
       clickContinue();
   }
   
   /**
	 * To verify incorrect error messages
	 * 
	 * @param errorMessage
	 * @return
	 * 		- boolean
	 */
	public boolean verifyBlankFieldErrorMessages(String errorMessage){
		Log.event("Verifying the incorrect login error message");
		return getErrorMessage().equals(errorMessage);
	}
}
