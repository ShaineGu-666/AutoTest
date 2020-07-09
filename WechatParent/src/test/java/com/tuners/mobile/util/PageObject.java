package com.tuners.mobile.util;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.function.Function;

//this class contains many method could be reuse in tests
//it should always be re-factor to keep this class clean
//for example, the wait method is put in a separate class now, it was a part of this class
public abstract class PageObject {

    protected Log log = LogFactory.getLog(this.getClass());
public static AndroidDriver<AndroidElement> androidDriver;

    public PageObject(AndroidDriver androidDriver) {
        this.androidDriver=androidDriver;
        PageFactory.initElements(new AppiumFieldDecorator(androidDriver),this);
    }

    public abstract void verifyDisplayed() throws Exception;


    public static void waitForElementClickable(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(androidDriver,
                timeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));

    }

    public static void waitForElementVisible(WebElement element,int timeout)
            throws Exception {

        WebDriverWait wait = new WebDriverWait(androidDriver,
                timeout);
        wait.until(ExpectedConditions.visibilityOf(element));

    }

    public static void waitForTextAppearInElement(WebElement element,String text)
            throws Exception {

        WebDriverWait wait = new WebDriverWait(androidDriver,
               30);
        wait.until(ExpectedConditions.textToBePresentInElement(element,text));

    }


    public static void waitForElementDisappear(String locator) throws Exception {

        WebDriverWait waitforelement = new WebDriverWait(androidDriver,
                60);
        waitforelement.until(ExpectedConditions.invisibilityOfElementLocated(By
                .xpath(locator)));

    }

    public static Function<WebDriver, Boolean> isPageLoaded() {
        return new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) androidDriver)
                        .executeScript("return document.readyState").equals("complete");
            }
        };
    }

    public static void waitForPageLoadComplete() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(androidDriver,
                200000);
        wait.until(isPageLoaded());


    }


    public static void waitForElementVisible(String xpath,int timeout) throws Exception {

        WebDriverWait wait = new WebDriverWait(androidDriver,
                timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

    }


    public static void waitForListVisible(List<WebElement> list, int timeout)
            throws Exception {

        WebDriverWait wait = new WebDriverWait(androidDriver,
                timeout);
        wait.until(ExpectedConditions.visibilityOfAllElements(list));

    }



    public Boolean isElementExist(WebElement element){

        Boolean Flag=false;
        if(element!=null&&element.isDisplayed()){

            Flag=true;
        }

        return Flag;
    }


}






