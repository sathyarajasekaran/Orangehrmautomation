package com.orangehrm.base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class BaseClass {

    protected static Properties prop ;
    protected WebDriver driver;

    @BeforeSuite
    public void loadConfig() throws IOException {
        //load the config files
        prop = new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
        prop.load(fis);
        staticWait(2);
    }

    private void launchBrowser() {
        //Initialize the Webdriver based on browser defined in config.properties
        String browser = prop.getProperty("browser");
        if(browser.equals("chrome"))
        {
            driver = new ChromeDriver();
        }
        else if(browser.equals("firefox"))
        {
            driver = new FirefoxDriver();
        }
        else {
            throw new IllegalArgumentException("Browser not supported " + browser);
        }
    }

    private void configureBrowser() {

        //implicit Wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //maximize the window
        driver.manage().window().maximize();

        //get url
        try{
            driver.get(prop.getProperty("url"));
        }
        catch(Exception e){
            System.out.println("Failed to Navigate the URL: " + e.getMessage());
        }

    }

    @BeforeMethod
    public void setup() throws IOException, InterruptedException {
        System.out.println("Setting up Browser for :" + this.getClass().getSimpleName());
        launchBrowser();
        configureBrowser();
    }

    @AfterClass
    //to close the browser
    public void tearDown() throws Exception {
        if (driver != null) {
            try{
                driver.quit();
            }
            catch (Exception e){
                System.out.println("Failed to quit Browser: " + e.getMessage());
            }
        }
    }

    //Driver getter method
    public WebDriver getDriver() {
        return driver;
    }

    //Driver setter method
    public void setDriver(WebDriver driver){
        this.driver = driver;
    }

    //Prop getter method
    public static Properties getProp(){
        return prop;
    }

    public void staticWait(int seconds) {
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
    }

}
