package testcases;



import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import util.DriverManager;

public class TC03 {
   WebDriver driver;
	
	@Test
	public void verifyTC03(){
		System.out.println("TC03 thread starts!!!");
		driver=DriverManager.getDriver();
		driver.navigate().to("https://www.bing.com");
		
	}
	
	
	
	
	
}
