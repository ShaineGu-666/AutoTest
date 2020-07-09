package util;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by cathy.zhang on 8/19/2016.
 */
public class PageManager {

    private WebDriver driver;
    protected String browserFlag;
    protected ExtentTest test;
    protected Log log = LogFactory.getLog(this.getClass());

    public PageManager(WebDriver driverRemote, String browser, ExtentTest extentTest) {
        driver = driverRemote;
        browserFlag = browser;
        test=extentTest;
//        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getBrowserFlag() {
        return browserFlag;
    }


    public void navigate(String url) {
        try {
            driver.navigate().to(url);
            test.log(Status.PASS, "Page navigates to " + url);
        } catch (WebDriverException e) {
            printError(e);
        } 
    }

    public String getTitle() {
        String t = null;
        try {
            t = driver.getTitle();
            test.log(Status.PASS, "Page Title is " + t);
        } catch (WebDriverException e) {
            printError(e);
        }
        return t;
    }

    public void switchToFrame(WebElement frame) {
        try {
            driver.switchTo().defaultContent();
            driver.switchTo().frame(frame);
            test.log(Status.PASS, "Switch to Frame " + frame.toString());
        } catch (WebDriverException e) {
            printError(e);
        } 
    }

    //to switch to a new window
    public void switchToNewWindow() {
        try {
            // Switch to new window opened
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
            }
            test.log(Status.PASS, "Switch to a new window.");
        } catch (NoSuchElementException | NoSuchWindowException e) {
            log.info(" No such element or no such window error Error", e);
        } catch (WebDriverException e) {
            log.info("WebDriverException Error", e);
        }

    }

    public void click(WebElement element) {
        try {
            element.click();
            test.log(Status.PASS, "Click " + element.toString());
        } catch (NoSuchElementException|StaleElementReferenceException e ) {
            printError(e, element);
        } catch ( WebDriverException e) {
            printError(e, element);
        }

    }

    public void sendKeys(WebElement element, String keys) {
        try {
            element.sendKeys(keys);
            test.log(Status.PASS, "Send " + keys + " to " + element.toString());
        } catch (NoSuchElementException|StaleElementReferenceException e) {
            printError(e, element);
        } catch (WebDriverException e) {
            printError(e);
        }
    }

    public String getText(WebElement element) {
        String t = null;
        try {
            t = element.getText();
            test.log(Status.PASS, "Get Text " + t + " of " + element.toString());
        } catch (NoSuchElementException|StaleElementReferenceException e) {
            printError(e, element);
        } catch (WebDriverException e){
            printError(e, element);
        }
        return t;
    }

    public void clickByAction(WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().build().perform();
            test.log(Status.PASS, "clickByAction " + element.toString());
        } catch (NoSuchElementException|StaleElementReferenceException e) {
            printError(e, element);
        } catch (WebDriverException e) {
            printError(e);
        }


    }

    public void maximeBrowser() {
        try {
            driver.manage().window().maximize();
        } catch (NoSuchElementException e) {
            printError(e);
        } catch (WebDriverException e) {
            printError(e);
        }
    }

    public void until(WebElement element, int timeout) {
            WebDriverWait wait = new WebDriverWait(driver,
                    timeout);
            wait.until(ExpectedConditions.visibilityOf(element));      
       
    }

    public void until(WebElement element) {
      
            WebDriverWait wait = new WebDriverWait(driver,
                    10);
            wait.until(ExpectedConditions.visibilityOf(element));
         
    }

    public void untilClickable(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver,
                    timeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));
      
    }


    public void select(WebElement element, String value) {
        try {
            if (browserFlag.matches("firefox|safari")) {
                element.sendKeys(value); // firefox
                test.log(Status.PASS, "Select " + value + "from option list " + element.toString());
            } else if (browserFlag.matches("ie|chrome")) {
                element.click();
                List<WebElement> optionList = element.findElements(By
                        .tagName("option"));
                optionList.stream()
                        .filter(option -> option.getText().trim().equalsIgnoreCase(value))
                        .forEach(option -> {option.click(); test.log(Status.PASS,
                                "Select " + value + "from option list " + element.toString()); });

            }
        } catch (NoSuchElementException|StaleElementReferenceException e) {
            printError(e, element);
        } catch (WebDriverException e) {
            printError(e, element);
        }

    }

    public boolean isVisible(WebElement element) throws IOException {
        try {
            element.isDisplayed();
            test.log(Status.PASS, element.toString() + " is visible.");
        } catch (NoSuchElementException | NullPointerException var3) {
            String path = snapshot((TakesScreenshot) driver);
            test.log(Status.FAIL, ErrorType.ELEMENT_NOTFOUND + element.toString() + " is not visible. " + "Screencast below: " + test.addScreenCaptureFromPath (path));
            log.info(var3);
            return false;
        } catch (StaleElementReferenceException var4) {
            String path = snapshot((TakesScreenshot) driver);
            test.log(Status.FAIL, ErrorType.ELEMENT_STALE + element.toString() + "is not visible. " + "Screencast below: " + test.addScreenCaptureFromPath (path));
            log.info(var4);
            return false;
        }
        return element.isDisplayed();
    }

    public String snapshot(TakesScreenshot drivername) {
        String currentPath = "./test-output/errorImages";
        String returnPath = "./errorImages";
        File scrFile = drivername.getScreenshotAs(OutputType.FILE);
//        try {
//            log.info("save snapshot path is:" + currentPath + "/"
//                    + getDatetime() + ".png");
//            FileUtils
//                    .copyFile(scrFile, new File(currentPath + "\\" + getDatetime() + ".png"));
//            FileUtils
//                    .copyFile(scrFile, new File(returnPath + "\\" + getDatetime() + ".png"));
//        } catch (IOException e) {
//            log.info("Can't save screenshot");
//            log.info(e);
//            return "";
//        }
        log.info("screen shot finished, it's in " + currentPath
                    + " folder");
        return returnPath + "/" + getDatetime() + ".png";
    }

    public static String getDatetime() {

        SimpleDateFormat date = new SimpleDateFormat("yyyymmdd_hhmmss");

        return date.format(new Date());

    }
    // select a value from a selector

    public void printError(Object e) {
        log.error(e);
        String path = snapshot((TakesScreenshot) driver);
        try {
            test.log(Status.FAIL, e + "\nScreencast below: " + test.addScreenCaptureFromPath(path));
        } catch (IOException e1) {
            log.info(e1);
        }
    }

    public void printError(Object e, WebElement element) {
        log.error(e);
        String path = snapshot((TakesScreenshot) driver);
        try {
            test.log(Status.FAIL, element.toString() + "\n" + e + "\nScreencast below: " + test.addScreenCaptureFromPath(path));
        } catch (IOException e1) {
            log.info(e1);
        }
    }

    public void printError( WebDriverException e) {
        log.error("webdriver is not reachable");
        test.log(Status.FAIL, "Webdriver is not reachable: "+e);
    }

    public enum ErrorType {
        ELEMENT_NOTFOUND("Element was not found, "),
        ELEMENT_STALE("Element was no longer located in the DOM and has become stale, "),
        WAIT_TIMEOUT("Wait timeout occured, ");
        private final String errorMsg;

        private ErrorType(String errorMsg) {
            this.errorMsg = errorMsg;
        }
    }

}
