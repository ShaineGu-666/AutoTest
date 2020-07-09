package testcase2;

import org.testng.annotations.Test;

import basePages.HomePage;
import util.TestCaseBase;

public class TC01 extends TestCaseBase {

	@Test
	public void verifyTest01(){
		
		//initiate pages
		
		HomePage homePage=new HomePage(pageManager);
		
		
		driver.navigate().to("https://cn.bing.com/");
		
		
		
	}
	
	
}
