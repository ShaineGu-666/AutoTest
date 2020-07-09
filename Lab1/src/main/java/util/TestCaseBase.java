package util;
import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


@Listeners(TestListener.class)







public class TestCaseBase {
    public static Logger logger;
    private ExtentReports extent;
    private ExtentTest parentTest;
    private ExtentTest test;
    
    private WebDriver driverOriginal;
    protected EventFiringWebDriver driver;
    private LogWebDriverEventListener eventListener;

    protected CustomAssertion customAssertion;
    protected PageManager pageManager;
	
    
    public static final String fireFoxBrowser = "firefox";
    public static final String chromeBrowser = "chrome";
    public static final String safariBrowser = "safari";
    public static String browser;
    public static String env;
    private String hubhost;
    private String hubport;
    private  Boolean onGrid;

    String fileName;
    
    
    //initiate log 4j logger and set log level
	public void initateLog4j() {
		// TODO Auto-generated method stub
		
       logger=Logger.getLogger(TestCaseBase.class);
//       BasicConfigurator.configure();  \
       PropertyConfigurator.configure("log4j.properties");
      
       /**   
        *  logger.setLevel(Level.INFO);
        logger.info("this is an info");
        logger.warn("this is an warn");
        logger.error("this is an error");
        logger.fatal("this is an fatal");
        * 
        */      
       logger.info("log initiate complete");
	 }
	

	
	//select desired browser by passing browserFlag
	 public void selectBrowser(String browserFlag) throws Exception {
	        if ("ie".equals(browserFlag)) {
	            setUpIEWin64(onGrid);
	        } else if (fireFoxBrowser.equals(browserFlag)) {
	            setUpFirefoxWithDefaultProfile(onGrid);
	        } else if (chromeBrowser.equals(browserFlag)) {
	            setUpChromeWin32(onGrid);
	        } else if ("safari".equals(browserFlag)) {
	          setUpSafari(onGrid);
	        } else if ("random".equals(browserFlag)) {
	            setUpRandomBrowserPerCase(onGrid);
	        } else if ("percentage_specified".equals(browserFlag)) {
//	            setupBrowserPerPercentage();
	        }


	    }
	
	    private void setUpIEWin64(Boolean onGrid) throws MalformedURLException {
//
//	        if (!onGrid) {
//	            File file = new File("./lib/IEDriverServer.exe");
//	            System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
//	            driverOriginal = new InternetExplorerDriver();
//	        } else {
//	            DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
//	            driverOriginal = new RemoteWebDriver(new URL(urlPrefix + host + ":" + port + urlSuffix), capability);
//	        }
//

	    }

	    private void setUpChromeWin32(Boolean onGrid) throws MalformedURLException {

	        if (!onGrid) {
	            File file = new File("./lib/chromedriver.exe");
	            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
	            driverOriginal = new ChromeDriver();
	        } else {
	            DesiredCapabilities capability = DesiredCapabilities.chrome();
//	            driverOriginal = new RemoteWebDriver(new URL(urlPrefix + host + ":" + port + urlSuffix), capability);
	        }

	    }

	    private void setUpFirefoxWithDefaultProfile(Boolean onGrid) throws MalformedURLException {

//	        if (!onGrid) {
//	        	 File file = new File("./lib/geckodriver16.exe");
//	             System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
//	             driverOriginal = new FirefoxDriver();
//	        } else {
//	            DesiredCapabilities capability = DesiredCapabilities.firefox();
//	            driverOriginal = new RemoteWebDriver(new URL(urlPrefix + host + ":" + port + urlSuffix), capability);
//	        }

	    }

	    private void setUpSafari(Boolean onGrid) throws MalformedURLException {

//	        if (!onGrid) {
//	        	driverOriginal = new SafariDriver();
//	        } else {
//	            DesiredCapabilities capability = DesiredCapabilities.safari();
//	            driverOriginal = new RemoteWebDriver(new URL(urlPrefix + host + ":" + port + urlSuffix), capability);
//	        }


	    }

	    private void setUpFirefoxWithNewProfile(Boolean onGrid) throws MalformedURLException {
//	        FirefoxProfile fp = new FirefoxProfile();
//	        fp.setEnableNativeEvents(true);
//	        if (onGrid == false) {
//	        	driverOriginal = new FirefoxDriver(fp);
//	        } else {
//	            DesiredCapabilities capability = DesiredCapabilities.firefox();
//	            capability.setCapability(FirefoxDriver.PROFILE, fp);
//	            driverOriginal = new RemoteWebDriver(new URL(urlPrefix + host + ":" + port + urlSuffix), capability);
//	        }
//	        private void setUpFirefoxWithNewProfile(Boolean onGrid) throws MalformedURLException {
//	            FirefoxProfile fp = new FirefoxProfile();
//	            fp.setEnableNativeEvents(true);
//	            if (onGrid == false) {
//	            	driverOriginal = new FirefoxDriver(fp);
//	            } else {
//	                DesiredCapabilities capability = DesiredCapabilities.firefox();
//	                capability.setCapability(FirefoxDriver.PROFILE, fp);
//	                driverOriginal = new RemoteWebDriver(new URL(urlPrefix + host + ":" + port + urlSuffix), capability);
//	            }

	        }

	    private void setUpRandomBrowserPerCase(Boolean onGrid) throws Exception {
//	            log.info("Setting up random browser...");
//	            Random rndObj = new Random();
//	            int rndBrowserIndex = rndObj.nextInt(3);
//	            if (rndBrowserIndex == 0) {
//	                setUpIEWin64(onGrid);
//	                browserFlag = "ie";
//	            } else if (rndBrowserIndex == 1) {
//	                setUpFirefoxWithDefaultProfile(onGrid);
//	                browserFlag = fireFoxBrowser;
//	            } else if (rndBrowserIndex == 2) {
//	                setUpChromeWin32(onGrid);
//	                browserFlag = chromeBrowser;
//	            } else {
//	                log.error("Random select browser fails");
//	                throw new Exception(
//	                        "No browser is specified for the random number: "
//	                                + rndBrowserIndex + ".");
//	            }

	        }
	        
//	    private void setupBrowserPerPercentage() throws Exception {
//	        	SystemUtil systemUtil = new SystemUtil();
//	            Properties propertiesResources = systemUtil
//	                    .loadPropertiesResources("/browser-percentage.properties");
//	            
//	            String ie = propertiesResources.getProperty("ie.percentage");
//	            String firefox = propertiesResources
//	                    .getProperty("firefox.percentage");
//	            String chrome = propertiesResources
//	                    .getProperty("googlechrome.percentage");
//	            int testcaseCount = Integer.parseInt(propertiesResources.getProperty("testcase.count"));
//	            newBrowserPerPercentage(ie, firefox, chrome, testcaseCount, onGrid);

	
	    @BeforeSuite(alwaysRun = true)
	    public void beforeSuite(){
	    	//initiate logger
	    	initateLog4j();
	    	
	    	//generate  extent Report
	        fileName=SystemUtil.getDatetime();
	        extent = ExtentManager.createInstance(fileName);
	        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
	        extent.attachReporter(htmlReporter);
	        logger.info("before suite");
	    }  
	    
	    @BeforeSuite(alwaysRun = true)
	    @Parameters({"browserFlag", "runningOnGrid", "hubHost", "hubPort","envFlag"})
		public void initiateParams(@Optional("chrome")String browserFlag,@Optional("false")String runningOnGrid,@Optional("0")String hubHost,@Optional("0")String hubPort,@Optional("QA1")String envFlag ){
			browser=browserFlag;
			env=envFlag;
			onGrid=Boolean.valueOf(runningOnGrid);
			hubhost=hubHost;
			hubport=hubPort;
			if (onGrid) {
	            logger.info("hubHost=" + hubHost);
	            logger.info("hubPort=" + hubPort);
	        }
	    	
	    	logger.info("before Class method initiateParams is executed");
		}
	    
	    @BeforeClass(alwaysRun = true)
	    public void initateExtent(){
	        logger.info("before class:"+fileName);
	        extent=ExtentManager.getInstance(fileName);
	        parentTest = extent.createTest(getClass().getName());
	    	logger.info("before Class method initiateParams is executed");
	    }
	    
	    
	    
	    
	@BeforeMethod
    public void initiate(Method method) throws Exception{
		//create extent node
		 test= parentTest.createNode(method.getName()); 
		//print test case name
		 String tc=this.getClass().getName();
	     logger.info("running TEST Case:" + this.getClass().getName());
	     
	     //select Browser
	     logger.info("Broswer choose is " + browser);
	     selectBrowser(browser);
	     customAssertion = new CustomAssertion(driverOriginal,test);
		    
	     //register eventlistener
	     eventListener = new LogWebDriverEventListener();
	     driver = new EventFiringWebDriver(driverOriginal);
	     driver.register(eventListener);
	     
	     
	     pageManager = new PageManager(driver,browser,test);
	     
	     logger.info(tc+"Test Started!");
	     test.log(Status.PASS, "Test Started!");
		 
    }
	
	
	 @AfterMethod(alwaysRun = true)
	    public void afterMethod(ITestResult result) {

	        if (result.getStatus() == ITestResult.FAILURE)
	            test.fail(result.getThrowable());
	        else if (result.getStatus() == ITestResult.SKIP)
	            test.skip(result.getThrowable());
	        else
	            test.pass("Test passed");


//	        driver.quit();
	        

	    }
	 
	 
	 
	    @AfterSuite(alwaysRun = true)
	    public void afterSuite() {
	        extent.flush();
	    }
}
