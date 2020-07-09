package com.tuners.mobile.util.Bases;
import com.tuners.mobile.BasePages.Teacher.EntrancePage;
import com.tuners.mobile.testdata.CommonTestData;
import com.tuners.mobile.util.listeners.LogAppiumWebDriverEventListener;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.events.EventFiringWebDriverFactory;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebBase {

    public static AndroidDriver driver;
    static Logger logger = Logger.getLogger(WebBase.class);
    public int ENVFLAG;


    @BeforeTest
    public void config() throws Exception {

        //1. 创建配置对象
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //2.添加配置
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.1.0");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "822QEDTJ22JQH");
        //192.168.56.103:5555
        //192.168.88.101:5555

capabilities.setCapability("chromedriverExecutableDir","D:/Shaine/workspace/WechatParent/src/browserdrivers");


        //初始化
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        //注册监听
       driver = EventFiringWebDriverFactory.getEventFiringWebDriver(driver,new LogAppiumWebDriverEventListener());
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        // 初始化日志
        initLog();


    }

    @Parameters({"env"})
@BeforeMethod
public void setup(String env) throws Exception {

    EntrancePage entrancePage =new EntrancePage(driver);
    logger.info("现在随选的环境是:"+env);
    if(env.equals("PDDD")) {
            driver.get(CommonTestData.PRODUCTIONTEACHER4DDURL);
            Thread.sleep(6000);
            entrancePage.login(CommonTestData.PRDUCTIONTEACHERPWD);
        }
    if(env.equals("PDWX")) {
            driver.get(CommonTestData.PRODUCTIONTEACHER4WXURL);
            Thread.sleep(6000);
            entrancePage.login(CommonTestData.PRDUCTIONTEACHERPWD);
        }

    if(env.equals("PREDD")){
        logger.info("现在准备登录预发钉钉教师端了");
        driver.get(CommonTestData.PRETEACHER4DDURL);
        Thread.sleep(6000);
        entrancePage.login(CommonTestData.PRETEACHERPWD);


    }

        if(env.equals("PREWX")){
            driver.get(CommonTestData.PRETEACHER4WXURL);
            Thread.sleep(6000);
            entrancePage.login(CommonTestData.PRETEACHERPWD);

        }

        entrancePage.loginAsAClassTeacher("第一自动化测试学校","一年级(1)班");
}







    @AfterTest
    public void afterTest( ) {

//       driver.quit();
    }



    public void initLog(){
        PropertyConfigurator.configure("src/test/Resources/log4j.properties");
    }
}


