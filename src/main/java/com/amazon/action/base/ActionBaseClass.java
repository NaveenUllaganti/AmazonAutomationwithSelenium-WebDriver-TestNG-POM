package com.amazon.action.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class ActionBaseClass {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod         //[ Naveen Ullagant Linkedin URL = linkedin.com/in/ullaganti-naveen-581459242 ]
    public void setUp() {
        // Setup WebDriver using WebDriverManager
        WebDriverManager.edgedriver().setup(); // Automatically manage the Edge driver
        driver = new EdgeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait
        driver.get("https://www.amazon.in/");  // Open the specified URL
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close the browser
        }
    }
}
