package util;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomAssertion {
    private Log log = LogFactory.getLog(this.getClass());
    private WebDriver driver;
    private ExtentTest test = null;
    String currentPath = ".\\test-output\\errorImages";
    String returnPath = ".\\errorImages";

    public CustomAssertion(WebDriver d, ExtentTest extentTest) {
        driver = d;
        test = extentTest;

    }

    public enum ErrorType {
        ELEMENT_NOTFOUND("Element was not found, "),
        ELEMENT_STALE("Element was no longer located in the DOM and has become stale, "),
        WAIT_TIMEOUT("Wait timeout occured, "),
        ASSERTED("Assertion failed, ");

        String errorMsg;

        private ErrorType(String errorMsg) {
            this.errorMsg = errorMsg;
        }
    }

    public boolean assertEquals(String actual, String expected, String message) {
        try {
            Assert.assertEquals(actual, expected, message);
            test.log(Status.PASS, "actual: " + actual + " expected: " + expected);
            return true;
        } catch (AssertionError e) {
            String path = snapshot((TakesScreenshot) driver);
            printError(e, message, path);
            return false;
        }

    }

    public boolean assertEquals(String actual, String expected) throws IOException {
        try {
            Assert.assertEquals(actual, expected);
            test.log(Status.PASS, "actual: " + actual + " expected: " + expected);
            return true;
        } catch (AssertionError e) {
            String path = snapshot((TakesScreenshot) driver);
            printError(e, path);
            return false;
        }

    }

    public boolean assertNotNull(Object obj, String message) {
        try {
            Assert.assertNotNull(obj, message);
            test.log(Status.PASS, "Object " + obj.toString() + " is not null.");
            return true;
        } catch (AssertionError e) {
            String path = snapshot((TakesScreenshot) driver);
            printError(e, message, path);
            return false;
        }

    }

    public boolean assertNotNull(Object obj) {
        try {
            Assert.assertNotNull(obj);
            test.log(Status.PASS, "Object " + obj.toString() + " is not null.");
            return true;
        } catch (AssertionError e) {
            String path = snapshot((TakesScreenshot) driver);
            printError(e,path);
            return false;
        }

    }

    public boolean assertTrue(boolean expression, String message) {
        try {
            Assert.assertTrue(expression, message);
            test.log(Status.PASS, "Expression " + expression + " is true.");
            return true;
        } catch (AssertionError e) {
            String path = snapshot((TakesScreenshot) driver);
            printError(e, message, path);
            return false;
        }
    }

    public boolean assertTrue(boolean expression) {
        try {
            Assert.assertTrue(expression);
            test.log(Status.PASS, "Expression " + expression + " is true.");
            return true;
        } catch (AssertionError e) {
            String path = snapshot((TakesScreenshot) driver);
            printError(e, path);
            return false;
        }
    }


    public String snapshot(TakesScreenshot drivername) {

        File scrFile = drivername.getScreenshotAs(OutputType.FILE);
        String dt=getDatetime();
//        try {
//        	log.info("save snapshot path is:" + currentPath + "\\"
//                    + dt + ".png");
//            FileUtils
//                    .copyFile(scrFile, new File(currentPath + "\\" + dt + ".png"));
//            FileUtils
//                    .copyFile(scrFile, new File(returnPath + "\\" + dt + ".png"));
//        } catch (IOException e) {
//            log.info("Can't save screenshot", e);
//        }
        log.info("screen shot finished, it's in " + currentPath
                    + " folder");
        return returnPath + "\\" + dt + ".png";
    }

    public String getDatetime() {

        SimpleDateFormat date = new SimpleDateFormat("yyyymmdd_hhmmss");

        return date.format(new Date());

    }

    public void printError(AssertionError e, String message, String path) {
        log.info(path);
        try {
            test.log(Status.FAIL, message  + e + "Screencast below: " );
            test.addScreenCaptureFromPath(path);
        } catch (IOException e1) {
            log.info("Assertion Failed: ",e1);
        }

    }

    public void printError(AssertionError e, String path) {

        try {
            test.log(Status.FAIL, "\n" + e + "\n Screencast below: " + test.addScreenCaptureFromPath(path));
        } catch (IOException e1) {
            log.info("Assertion Failed", e1);
        }
    }
}
