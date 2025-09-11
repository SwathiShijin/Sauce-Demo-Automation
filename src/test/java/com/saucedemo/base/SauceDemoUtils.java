package com.saucedemo.base;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

public class SauceDemoUtils {
	
	private static final int DEFAULT_WAIT = 10;   // max wait time
    private static final int POLLING_INTERVAL = 500; // ms
    
    protected WebDriver driver;
    
    /**
     * Generic wait using FluentWait until the given condition returns true
     * 
     * @param driver    WebDriver instance
     * @param condition Lambda that returns boolean (page or element ready)
     */
    public static void waitForPageLoad(WebDriver driver, Function<WebDriver, Boolean> condition) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(DEFAULT_WAIT))
                .pollingEvery(Duration.ofMillis(POLLING_INTERVAL))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        wait.until(condition);
    }
	
	
	/**
	 * To get matching text element from List of web elements. 
	 * Element will be scrolled in to view before comparing
	 * 
	 * @param elements
	 * 			- list of elements
	 * @param contenttext
	 * 			- Element to compare
	 * @param driver
	 * @return
	 * 			- return as WebElement
	 * @throws Exception
	 */
	public static WebElement getMatchingTextElementFromListWithScrollEnabled(List<WebElement> elements, String contenttext,WebDriver driver) throws Exception {
        WebElement elementToBeReturned = null;
        boolean found = false;

        if (elements.size() > 0) {
            for (WebElement element : elements) {
            	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
                if (element.getText().trim().replaceAll("\\s+", " ").equalsIgnoreCase(contenttext)) {
                    elementToBeReturned = element;
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new Exception("Didn't find the correct text(" + contenttext + ")..! on the page");
            }
        } else {
            throw new Exception("Expected element list is not available");
        }
        return elementToBeReturned;
    } 

	/**
	 * To select given value in dropdown
	 * 
	 * @param dropdownElement
	 * @param value
	 */
	public static void selectDropdownByValue(WebElement dropdownElement, String value) {
	    Select select = new Select(dropdownElement);
	    select.selectByValue(value);
	}

}
