package com.saucedemo.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.base.SauceDemoUtils;

import utils.Log;

public class ProductsPage {
    WebDriver driver;

    @FindBy(css = "span.title")
    WebElement title;

    @FindBy(css = "button.btn_inventory")
    List<WebElement> btnAddToCart;
    
    @FindBy(css = "div.inventory_item_name")
    List<WebElement> txtProductNames;
    
    @FindBy(css = ".inventory_item_price")
    List<WebElement> productPrices;
    
    @FindBy(css = "select.product_sort_container")
    WebElement drpSort;
    
    @FindBy(css = ".shopping_cart_link")
    WebElement cartIcon;
    
    @FindBy(css = ".shopping_cart_badge")
    WebElement iconCartBadge;
    
    @FindBy(css = ".inventory_item_img img")
    List<WebElement> imgProducts;
    
    @FindBy(css = "div.inventory_details_name")
    WebElement txtProductDetailName;
    
    @FindBy(css = "div.inventory_details_desc")
    WebElement txtProductDetailDescription;
    
    @FindBy(css = "div.inventory_details_price")
    WebElement txtProductDetailPrice;
    
    @FindBy(id = "back-to-products")
    WebElement btnBackToProducts;
    
    @FindBy(css = "select.product_sort_container")
    WebElement sortDropdown;
    
    public enum sortContainer{
    	NameAtoZ("Name (A to Z)"),
    	NameZtoA("Name (Z to A)"),
    	Pricelowtohigh("Price (low to high)"),
    	Pricehightolow("Price (high to low)");
    	
    	private String name;

    	sortContainer(String name) {
			this.name = name;
		}

		public String getsortContainer() {
			return this.name;
		}
    }
    
    
    /**
     * Constructor class for Products page
     * 
     * @param driver
     */
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        
        // Wait for page to load (title visible)
        SauceDemoUtils.waitForPageLoad(driver, d -> title.isDisplayed());
    }

    /**
     * To get the title
     * 
     * @return
     * 		- title
     */
    public String getTitle() {
    	Log.event("Getting page title");
        return title.getText();
    }
    
    /**
     * To add given number product to cart from the sequence order
     * 
     * @param n
     * 		- product count
     */
    public void addFirstNProductsToCart(int n) {
    	Log.event("Adding given number of products to cart");
        for (int i = 0; i < n && i < btnAddToCart.size(); i++) {
            btnAddToCart.get(i).click();
            Log.message("Added product " + (i + 1) + " to cart");
        }
    }
    
    /**
     * To select given product from the product list
     * 
     * @param product
     * 		- list of products
     * @throws Exception 
     */
    public void addGivenProductToCart(List<String> productNames){
    	Log.event("Selecting given product " + productNames + " from the list");
    	WebElement element;
    	try {
    		for(int count = 0; count < productNames.size(); count++) {
    			element = SauceDemoUtils.getMatchingTextElementFromListWithScrollEnabled(txtProductNames, productNames.get(count), driver);
    			element.findElement(By.xpath("../../..")).findElement(By.cssSelector("button.btn_inventory")).click();
    		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
    	Log.message("Selected given " + productNames + " product from the list");        
    }
    
    /**
     * To get all the product names from product page
     * 
     * @return
     * 		- list of product title
     */
    public List<String> getProductNames() {
        List<String> names = new ArrayList<>();
        for (WebElement e : txtProductNames) {
            names.add(e.getText().trim());
        }
        return names;
    }

    /**
     * To get all the product prices from product page
     * 
     * @return
     * 		- list of product prices
     */
    public List<Double> getProductPrices() {
        List<Double> prices = new ArrayList<>();
        for (WebElement e : productPrices) {
            prices.add(Double.parseDouble(e.getText().replace("$", "")));
        }
        return prices;
    }
    
    /**
     * To click remove button from given product in product page
     * 
     * @param productName
     */
    public void clickRemoveProductFromCart(String productName) {
    	Log.event("Clicking 'Remove' button for given product " + productName);
        WebElement removeBtn;
		try {
			removeBtn = SauceDemoUtils.getMatchingTextElementFromListWithScrollEnabled(txtProductNames, productName, driver);
			removeBtn.findElement(By.xpath("../../..")).findElement(By.cssSelector("button[id*='remove-sauce-labs']")).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.message("Clicked 'Remove' button for given product " + productName);
    }
    
    /**
     * To verify AddedToCart/Remove button displayed under given product name
     * 
     * @param productName
     * 		- name of the product
     * @param expectedText
     * 		- Add to cart / Remove
     * @return
     * 		- boolean
     */
    public boolean isButtonDisplayed(String productName, String expectedText) {
        WebElement button = driver.findElement(By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button"));
        return button.getText().trim().equalsIgnoreCase(expectedText);
    }
    
    /**
     * To click product name and open the product detail page
     * 
     * @param productName
     */
    public void clickAndOpenProductDetails(String productName) {
        driver.findElement(By.xpath("//div[text()='" + productName + "']")).click();
    }

    /**
     * To navigate to cart page
     */
    public CartPage goToCart() {
    	Log.event("Navigating to cart page");
    	cartIcon.click();
    	Log.message("Navigated to cart page");
    	return new CartPage(driver);
    }
    
    /**
     * To get the added product count from cart badge
     * 
     * @return
     * 		- badge count
     */
    public int getCartBadgeCount() {
        return Integer.parseInt(iconCartBadge.getText());
    }

    /**
     * To click back to product button and navigate to product page
     */
    public void clickBackToProducts() {
    	Log.event("Navigating back to product page");
        btnBackToProducts.click();
        Log.message("Navigated back to product page");
    }
    
    /**
     * To verify product detail page
     * 
     * @return
     * 		- boolean
     */
    public boolean verifyProductDetailPage() {
        return txtProductDetailName.isDisplayed() && txtProductDetailDescription.isDisplayed() && txtProductDetailPrice.isDisplayed();
    }
    
    /**
     * To verify broken images are displayed in product page
     * 
     * @return
     * 		- boolean
     */
    public boolean verifyBrokenImages() {
    	Log.event("To verifying broken images in product page");
    	for (WebElement image : imgProducts) {
    	    if (!image.isDisplayed()) {
    	        Log.message("Broken image found: " + image.getAttribute("src"));
    	        return false;
    	    }
    	}
    	return true;
    }
    
    /**
     * To wait for product page load
     */
    public void waitForPageToLoad() {
        Log.event("Waiting for Products page to load completely");

        try {
            SauceDemoUtils.waitForPageLoad(driver, d -> 
                title.isDisplayed() && !txtProductNames.isEmpty()
            );
            Log.message("Products page loaded successfully");
        } catch (Exception e) {
            Log.errorEvent("Products page did not load properly: " + e.getMessage());
            Log.fail("Products page did not load properly: " + e.getMessage());
        }
    }
    
    /**
     * To select given value in dropdown
     * 
     * @param value
     * 		- az, za, lohi, hilo
     */
    public void selectSortOption(String value) {
        SauceDemoUtils.selectDropdownByValue(sortDropdown, value);
    }
    
    /**
     * To verify sorting order in product page
     * 
     * @param sortType
     * 		- Name A to Z, Name Z to A, Price low to high, Price high to low
     * @return
     * 		- boolean
     */
    public boolean verifySortingOrder(sortContainer sortType) {
    	List<String> actualName = new ArrayList<>();
    	List<String> expectedName  = new ArrayList<>();
    	List<Double> actualPrice = new ArrayList<>();
    	List<Double> expectedPrice  = new ArrayList<>();
    	boolean status = false;
    	
    	switch(sortType) {
    	case NameAtoZ:
    		actualName = getProductNames();
    		expectedName = new ArrayList<>(actualName);
            Collections.sort(expectedName);
            status = actualName.equals(expectedName);
            break;
    	case NameZtoA:
    		actualName = getProductNames();
    		expectedName = new ArrayList<>(actualName);
            Collections.sort(expectedName, Collections.reverseOrder());
            status = actualName.equals(expectedName);
            break;
    	case Pricelowtohigh:
    		actualPrice = getProductPrices();
    		expectedPrice = new ArrayList<>(actualPrice);
            Collections.sort(expectedPrice);
            status = actualPrice.equals(expectedPrice);
            break;
    	case Pricehightolow:
    		actualPrice = getProductPrices();
    		expectedPrice = new ArrayList<>(actualPrice);
            Collections.sort(expectedPrice, Collections.reverseOrder());
            status = actualPrice.equals(expectedPrice);
            break;
    	default:
    		Log.message("Invalid sorting type passed");
    		break;
    	}
    	return status;
    }
}
