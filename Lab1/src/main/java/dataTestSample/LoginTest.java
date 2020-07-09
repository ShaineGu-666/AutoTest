package dataTestSample;

import java.util.Map;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import util.TestCaseBase;


public class LoginTest extends TestCaseBase{
	//test receive data from testng dataProvider
	//make sure the function name and this class name to match with specific xml file
	@Test(dataProvider="loginProvider",dataProviderClass=LoginDataProvider.class)
	public  void loginRight(Map<?, ?> param) throws InterruptedException{
  
     logger.info((String)param.get("username"));
     logger.info((String)param.get("password"));

    
    }
	
	//test receive data from testng <parameter>

    @Parameters({"dataDriven"})
    @Test
	public void testPara(@Optional("1") String dataDriven){
		 logger.info("testDataFromTestNGParameter"+dataDriven);

	}

}
