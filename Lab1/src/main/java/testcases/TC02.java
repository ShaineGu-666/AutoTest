package testcases;



import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import util.DriverManager;

public class TC02 {
   WebDriver driver;
	
	@Test
	public void verifyTC02(){
		
		
		
		
		System.out.println("TC02 thread starts!!!");
		driver=DriverManager.getDriver();
//		driver.navigate().to("www.bing.com");
		driver.navigate().to("https://www.baidu.com");
		
	}
	
	
	
	
	
}
