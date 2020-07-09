package util;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class DriverManager {
	/**
	2 * 每个DriverManager只管理一个driver，所以他是static的，但是我引入ThreadLocal来处理多线程
	3 * shares the same web driver and use thread local to handle the multi-thread
	4 */
	 public static ThreadLocal<WebDriver> ThreadDriver=new ThreadLocal<WebDriver>() ;
	 
	 
	 
	 public static WebDriver getDriver(){
		 WebDriver driver= DriverManager.ThreadDriver.get();

		 if (driver==null){
			 File file = new File("./lib/chromedriver.exe");
	         System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			 driver = new EventFiringWebDriver(new ChromeDriver()).register(new LogWebDriverEventListener());
		     ThreadDriver.set(driver);
		//找东西前等三秒wait 3 second for every find by
		      DriverManager.getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		 }
		//有需求的同学自己在这里添加IE等浏览器的支持
	    //you can add ie/chrome or other driver here
		
		return driver;
	 }
	 
}
