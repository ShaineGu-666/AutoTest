package com.tuners.mobile.util.Bases;

import com.tuners.mobile.BasePages.DingDingPage;
import com.tuners.mobile.util.listeners.LogAppiumWebDriverEventListener;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.events.EventFiringWebDriverFactory;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @Author Shaine
 * @Description
 * @Date 2020/6/17 16:05
 **/
public class DingDingBase {
    public static AndroidDriver androidDriver;
    static Logger logger = Logger.getLogger(DingDingBase.class);
    int ENVFLAG;


    @Parameters({"env"})
    @BeforeMethod
    public void config(@Optional("0") String env) throws Exception {
        ENVFLAG=Integer.parseInt(env);
        //1. 创建配置对象
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        //2.添加配置

        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.1.0");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "822QEDTJ22JQH");
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);

        desiredCapabilities.setCapability("appPackage", "com.alibaba.android.rimet");
        desiredCapabilities.setCapability("appActivity", "com.alibaba.android.rimet.biz.LaunchHomeActivity");

        desiredCapabilities.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE, "D:/Shaine/workspace/WechatParent/src/browserdrivers/chromedriver77.03865.exe");

        desiredCapabilities. setCapability("setWebContentsDebuggingEnabled",true);

        ChromeOptions options=new ChromeOptions();
//        options.setExperimentalOption("androidProcess","com.tencent.mm:tools");
        options.setCapability("browserName","");
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY,options);


        //创建驱动 传入两个参数 1.appium通讯地址 2.desiredCapabilities
        androidDriver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);

        //注册监听
        androidDriver = EventFiringWebDriverFactory.getEventFiringWebDriver(androidDriver,new LogAppiumWebDriverEventListener());
        androidDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        androidDriver.setFileDetector(new LocalFileDetector());

        // 初始化日志
        initLog();

       enterShuTongEdu(ENVFLAG);

    }

    private void enterShuTongEdu(int env) throws Exception {
        DingDingPage dingDingPage=new DingDingPage(androidDriver);
        dingDingPage.iconOfShuTongEdu.click();



    }


    public void initLog(){
        PropertyConfigurator.configure("src/test/Resources/log4j.properties");
    }

    @AfterTest
    public void afterTest( ) {

//        androidDriver.quit();
    }


//        @BeforeMethod
//        public void beforeEveryTest() {
//
//
//        }





}
