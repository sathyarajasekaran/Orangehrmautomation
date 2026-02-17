package com.orangehrm.actiondriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionDriver {

    private WebDriver driver;
    private WebDriverWait wait;
    public ActionDriver(WebDriver driver){
        this.driver = driver;
        new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    //Click the Element
    public void click(By by){
        try{
            waitForElementToBeClickable(by);
            driver.findElement(by).click();
        }
        catch(Exception e){
            System.out.println("Unable to click element : " + e.getMessage());
        }
    }

    //enter Text
    public void enterText(By by, String value){
        try {
            waitForElementToBeVisible(by);
            ////driver.findElement(by).clear();
           // driver.findElement(by).sendKeys(value);
            WebElement element = driver.findElement(by);
            element.clear();
            element.sendKeys(value);
        }
        catch(Exception e){
            System.out.println("Unable to enter text : " + e.getMessage());
        }
    }

    //Method to get text from input field
    public void getText(By by){
        try{
            waitForElementToBeVisible(by);
            driver.findElement(by).getText();
        }
        catch(Exception e){
            System.out.println("Unable to get text : " + e.getMessage());
        }
    }

    //Method to compare two text
    public void comapreText(By by, String value){
        try {
            waitForElementToBeVisible(by);
            String text = driver.findElement(by).getText();
            if (value.equals(text)) {
                System.out.println("Text are matching : " + text);
            } else {
                System.out.println("Text are not matching : " + text);
            }
        }
        catch (Exception e ){
            System.out.println("Unable to comapre text : " + e.getMessage());
        }
    }
    //Method to check if an element is display
    /*public void isDisplayed(By by){
        try{
            waitForElementToBeVisible(by);
            boolean isDisplayed = driver.findElement(by).isDisplayed();
            if(isDisplayed == true){
                System.out.println("Element is Displayed : " + isDisplayed);
            }
            else{
                System.out.println("Element is not Displayed : " + isDisplayed);
            }
        }
        catch(Exception e){
            System.out.println("Unable to isDisplayed element : " + e.getMessage());
        }
    }*/

    public boolean isDisplayed(By by){
        try{
            waitForElementToBeVisible(by);
            return driver.findElement(by).isDisplayed();
        }
        catch (Exception e){
            System.out.println("Unable to display text : " + e.getMessage());
            return false;
        }
    }

    //scroll to an element
    public void scrollToElement(By by){
        try{
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
        }
        catch(Exception e){
            System.out.println("Unable to scroll to element : " + e.getMessage());
        }
    }

    //wwWait until page load
    public void waitForPageToLoad(int timeoutsinsec){
        try{
            wait.withTimeout(Duration.ofSeconds(timeoutsinsec)).until(WebDriver -> ((JavascriptExecutor) WebDriver)
                    .executeScript("return document.readyState").equals("complete"));
            System.out.println("Waiting for page to load");
        }
        catch(Exception e){
            System.out.println("Unable to wait for page to load : " + e.getMessage());
        }
    }

    //Wait for element  to be clickable
    private void waitForElementToBeClickable(By by){
        try {
            wait.until(ExpectedConditions.elementToBeClickable(by));
        }
        catch (Exception e){
            System.out.println("element to be clickable failed: " + e.getMessage());
        }
    }

    //wait for the element
    private void waitForElementToBeVisible(By by){
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        }
        catch (Exception e){
            System.out.println("element to be visible failed: " + e.getMessage());
        }
    }
}
