package util;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class LogWebDriverEventListener extends AbstractWebDriverEventListener {
	private Log log = LogFactory.getLog(this.getClass());
	private By lastFindBy;
	private String originalValue;

	@Override
	public void beforeNavigateTo(String url, WebDriver selenium) {
		log.info("WebDriver navigating to:'" + url + "'");
		//Screenshot.bufferLastImage(selenium);
	}

	public void beforeChangeValueOf(WebElement element, WebDriver selenium) {
		originalValue = element.getAttribute("value");
		//Screenshot.bufferLastImage(selenium);
	}

	public void afterChangeValueOf(WebElement element) {
		log.info("WebDriver changing value in element found " + lastFindBy
				+ " from '" + originalValue + "' to '"
				+ element.getAttribute("value") + "'");
	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver selenium) {
		lastFindBy = by;
	}

	@Override
	public void onException(Throwable error, WebDriver selenium) {
		//Screenshot.takeForError(selenium);
		if (error.getClass().equals(NoSuchElementException.class)) {
			log.error("WebDriver error: Element not found " + lastFindBy);
		} else {
			log.error("WebDriver error:", error);
		}
	}

	@Override
	public void beforeNavigateBack(WebDriver selenium) {}

	@Override
	public void beforeNavigateForward(WebDriver selenium) {}

	@Override
	public void beforeClickOn(WebElement element, WebDriver selenium) {
		log.info("WebDriver clicking on" + element.toString());
//		Screenshot.bufferLastImage(selenium);
	}

	@Override
	public void beforeScript(String script, WebDriver selenium) {
// 		Screenshot.bufferLastImage(selenium);
	}

	@Override
	public void afterClickOn(WebElement element, WebDriver selenium) {
		log.info("WebDriver clicked on" + element.toString());
		selenium.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
	
	@Override
	public void afterFindBy(By by, WebElement element, WebDriver selenium) {
		log.info("WebDriver found " + by );
	}

	@Override
	public void afterNavigateBack(WebDriver selenium) {}

	@Override
	public void afterNavigateForward(WebDriver selenium) {}


	@Override
	public void afterNavigateTo(String url, WebDriver selenium) {
		selenium.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}

	@Override
	public void afterScript(String script, WebDriver selenium) {}

}