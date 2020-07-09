package com.tuners.console.utils;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.rmi.runtime.Log;

import java.util.List;
import java.util.function.Function;


//this class contains many method could be reuse in tests
//it should always be re-factor to keep this class clean
//for example, the wait method is put in a separate class now, it was a part of this class
public abstract class PageObject {
//    protected Log log = LogFactory.getLog(this.getClass());
public static WebDriver driver;


    public PageObject(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    public abstract void verifyDisplayed() throws Exception;


    public static void waitForElementClickable(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver,
                timeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));

    }

    public static void waitForElementVisible(WebElement element,int timeout)
            throws Exception {

        WebDriverWait wait = new WebDriverWait(driver,
                timeout);
        wait.until(ExpectedConditions.visibilityOf(element));

    }


    public static void waitForElementDisappear(String locator) throws Exception {

        WebDriverWait waitforelement = new WebDriverWait(driver,
                60);
        waitforelement.until(ExpectedConditions.invisibilityOfElementLocated(By
                .xpath(locator)));

    }

    public static Function<WebDriver, Boolean> isPageLoaded() {
        return new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState").equals("complete");
            }
        };
    }

    public static void waitForPageLoadComplete() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(TestCaseBase.driver,
                200000);
        wait.until(isPageLoaded());

    }


    public static void waitForElementVisible(String xpath,int timeout) throws Exception {

        WebDriverWait wait = new WebDriverWait(TestCaseBase.driver,
                timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

    }


    public static void waitForListVisible(List<WebElement> list, int timeout)
            throws Exception {

        WebDriverWait wait = new WebDriverWait(driver,
                timeout);
        wait.until(ExpectedConditions.visibilityOfAllElements(list));

    }

}






