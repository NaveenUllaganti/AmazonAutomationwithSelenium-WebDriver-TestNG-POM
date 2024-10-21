package com.amazon.action.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.amazon.action.base.ActionBaseClass;
import com.amazon.action.pages.ActionPageClass;

public class TestPage extends ActionBaseClass {
    private ActionPageClass actionPage;
    private SoftAssert softAssert;    

    @BeforeMethod                    //[ Naveen Ullagant Linkedin URL = linkedin.com/in/ullaganti-naveen-581459242 ]
    public void setUp() {
        super.setUp(); // Call BaseClass setup
        actionPage = new ActionPageClass(driver); // Initialize ActionPageClass
        softAssert = new SoftAssert(); // Initialize SoftAssert
    }

    @Test(priority = 1, groups = {"search", "sanity"})
    public void testSearchBoxIsDisplayed() {
        softAssert.assertTrue(actionPage.isSearchBoxDisplayed(), "Search box is not displayed.");
    }

    @Test(priority = 2, groups = {"search", "regression"})
    public void testSearchItem() {
        actionPage.searchItem("iPhone 12");
        // Verify the title after search
        String expectedTitle = "Amazon.in : iPhone 12";
        String actualTitle = driver.getTitle();
        softAssert.assertEquals(actualTitle, expectedTitle, "Title does not match after search.");
    }

    @Test(priority = 3, groups = {"logo", "sanity"})
    public void testAmazonLogoIsDisplayed() {
        softAssert.assertTrue(actionPage.isAmazonLogoDisplayed(), "Amazon logo is not displayed.");
    }

    @Test(priority = 4, groups = {"deals", "regression"})
    public void testGoToTodaysDeals() {
        actionPage.goToTodaysDeals();
        // Verify the title for Today's Deals
        String expectedTitle = "Amazon.in Great Indian Festival";
        String actualTitle = driver.getTitle();
        softAssert.assertEquals(actualTitle, expectedTitle, "Title does not match for Today's Deals.");
    }

    @Test(priority = 5, groups = {"search", "regression"})
    public void testSearchFunctionality() {
        actionPage.searchItem("Laptop");
        String expectedTitle = "Amazon.in : Laptop";
        String actualTitle = driver.getTitle();
        softAssert.assertEquals(actualTitle, expectedTitle, "Title does not match after searching for Laptop.");
    }

    @Test(priority = 6, groups = {"search", "sanity"})
    public void testSearchBoxClear() {
        actionPage.searchItem("iPhone 12");
        actionPage.searchItem(""); // Clear the search
        softAssert.assertTrue(actionPage.isSearchBoxDisplayed(), "Search box should still be displayed after clearing.");
    }

    @Test(priority = 7, groups = {"search", "regression"})
    public void testDoubleClickSearchBox() {
        String searchText = "iPhone 12";
        actionPage.searchItem(searchText);
        
        actionPage.doubleClickSearchBox(); // Double-click the search box
        String selectedText = actionPage.getSearchBoxText(); // Get the text from the search box
        softAssert.assertEquals(selectedText, searchText, "Text in search box is not selected correctly.");
        
        // Optionally, you could simulate pressing CTRL + A and check if the text is selected
        driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        softAssert.assertEquals(actionPage.getSearchBoxText(), searchText, "Text in search box is not selected correctly after double-clicking.");
    }

    @AfterMethod
    public void tearDown() {
        softAssert.assertAll(); // Assert all collected soft assertions
        super.tearDown(); // Call BaseClass teardown
    }
}
