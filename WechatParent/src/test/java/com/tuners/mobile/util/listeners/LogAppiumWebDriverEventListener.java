package com.tuners.mobile.util.listeners;

import io.appium.java_client.events.api.general.AppiumWebDriverEventListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogAppiumWebDriverEventListener implements AppiumWebDriverEventListener {

    private Log log = LogFactory.getLog(this.getClass());
    private By lastFindBy;
    private String originalValue;
    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver) {
//       originalValue = webElement.getAttribute("value");
    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver) {
//        log.info("WebDriver changing value in element found " + lastFindBy + " from '" + originalValue + "' to '"
//             + webElement.getAttribute("value") + "'");

    }

    @Override
    public void beforeAlertAccept(WebDriver driver) {

    }

    @Override
    public void afterAlertAccept(WebDriver driver) {

    }

    @Override
    public void afterAlertDismiss(WebDriver driver) {

    }

    @Override
    public void beforeAlertDismiss(WebDriver driver) {

    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        log.info("WebDriver navigating to:'" + url + "'");
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {

            log.info("WebDriver already navigating to:'" + url + "'");
    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {

    }

    @Override
    public void afterNavigateBack(WebDriver driver) {

    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {

    }

    @Override
    public void afterNavigateForward(WebDriver driver) {

    }

    @Override
    public void beforeNavigateRefresh(WebDriver driver) {

    }

    @Override
    public void afterNavigateRefresh(WebDriver driver) {

    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {

    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        lastFindBy = by;
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        log.info("Click the element found " + lastFindBy);

    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {

    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        originalValue = element.getAttribute("value");
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
//        log.info("driver changing value in element found " + lastFindBy
//                + " from '" + originalValue + "' to '"
//                + element.getAttribute("value") + "'");
    }

    @Override
    public void beforeScript(String script, WebDriver driver) {

    }

    @Override
    public void afterScript(String script, WebDriver driver) {

    }

    @Override
    public void beforeSwitchToWindow(String windowName, WebDriver driver) {

    }

    @Override
    public void afterSwitchToWindow(String windowName, WebDriver driver) {

    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {

    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> target) {

    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {

    }

    @Override
    public void beforeGetText(WebElement element, WebDriver driver) {

    }

    @Override
    public void afterGetText(WebElement element, WebDriver driver, String text) {

    }
}
