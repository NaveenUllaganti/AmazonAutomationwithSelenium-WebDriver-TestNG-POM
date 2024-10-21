package com.amazon.action.pages;

import org.openqa.selenium.WebDriver;import java.time.Duration;
import org.openqa.selenium.WebElement;import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionPageClass {
    private WebDriver driver;
    private Actions actions;     //[ Naveen Ullagant Linkedin URL = linkedin.com/in/ullaganti-naveen-581459242 ]
    private WebDriverWait wait;

    // Locators using XPath
    @FindBy(xpath = "//input[@id='twotabsearchtextbox']")
    private WebElement searchBox;

    @FindBy(id = "nav-logo-sprites")
    private WebElement amazonLogo; // Amazon logo locator

    @FindBy(xpath = "//input[@id='nav-search-submit-button']")
    private WebElement searchSubmitButton;

    @FindBy(linkText = "Today's Deals")
    private WebElement todaysDealsLink; // Today's Deals link locator

    public ActionPageClass(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize WebDriverWait
        PageFactory.initElements(driver, this);  // Initialize the Page Factory
    }
    // Method to search for an item
    public void searchItem(String itemName) {
        waitUntilVisible(searchBox);
        searchBox.sendKeys(itemName);
        searchSubmitButton.click();
    }

    // Method to check if the search box is displayed
    public boolean isSearchBoxDisplayed() {
        return searchBox.isDisplayed();
    }

    // Method to check if the Amazon logo is displayed
    public boolean isAmazonLogoDisplayed() {
        return amazonLogo.isDisplayed();
    }

    // Method to go to Today's Deals page
    public void goToTodaysDeals() {
        waitUntilVisible(todaysDealsLink).click(); // Click on the Today's Deals link
    }
    // Method to double-click the search box
    public void doubleClickSearchBox() {
        actions.doubleClick(searchBox).perform();
    }

    // Method for waiting until an element is visible
    private WebElement waitUntilVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)); // Use the instance variable wait
    }

    // Method to get text from the search box
    public String getSearchBoxText() {
        return searchBox.getAttribute("value");
    }

    // Method to navigate to a specified URL
    public void navigateToUrl(String url) {
        driver.get(url); // Use driver to navigate
    }
}
