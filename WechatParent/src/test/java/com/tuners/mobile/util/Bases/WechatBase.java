package com.tuners.mobile.util.Bases;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import com.tuners.mobile.BasePages.WechatNativePage;
import com.tuners.mobile.util.Functions.FunctionUtil;
import com.tuners.mobile.util.PageObject;
import com.tuners.mobile.util.listeners.ContextEventListener;
import com.tuners.mobile.util.listeners.ElementListener;
import com.tuners.mobile.util.listeners.LogAppiumWebDriverEventListener;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.events.EventFiringWebDriverFactory;
import io.appium.java_client.events.api.Listener;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @Author Shaine
 * @Description
 * @Date 2020/6/16 17:48
 **/
@Listeners(ExtentITestListenerClassAdapter.class)
public class WechatBase {

    public static AndroidDriver androidDriver;
    static Logger logger = Logger.getLogger(WechatBase.class);
     public int ENVFLAG=0;

    @Parameters({"env"})
    @BeforeTest
    public void config(@Optional("0") String env) throws Exception {
        //与appium建立连接
        configAppium();

        //添加、注册监听
        addRegisterListeners();

        // 初始化日志
        initLog();

        //文件探测
        androidDriver.setFileDetector(new LocalFileDetector());

        //设置全局等待
        androidDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


        ENVFLAG=Integer.parseInt(env);
        logger.info("ENVFLAG is:" + ENVFLAG);



    }


public void configAppium() throws IOException {
        FunctionUtil functionUtil=new FunctionUtil(androidDriver);

Properties prop=functionUtil.loadProperties("android");


    //1. 创建配置对象
    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    //2.添加配置

    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, prop.getProperty(MobileCapabilityType.PLATFORM_NAME));
    desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,prop.getProperty(MobileCapabilityType.AUTOMATION_NAME));
    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, prop.getProperty(MobileCapabilityType.PLATFORM_VERSION));
    desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty(MobileCapabilityType.DEVICE_NAME));
    desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);

    desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,prop.getProperty(AndroidMobileCapabilityType.APP_PACKAGE));
    desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, prop.getProperty(AndroidMobileCapabilityType.APP_ACTIVITY));

    desiredCapabilities.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE, prop.getProperty(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE));

    desiredCapabilities. setCapability("setWebContentsDebuggingEnabled",true);

    ChromeOptions options=new ChromeOptions();
    options.setExperimentalOption("androidProcess",prop.getProperty("androidProcess"));
    options.setCapability("browserName","");
    desiredCapabilities.setCapability(ChromeOptions.CAPABILITY,options);


    //创建驱动 传入两个参数 1.appium通讯地址 2.desiredCapabilities
    androidDriver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);


}


public void addRegisterListeners(){

    //添加监听

    List<Listener> listeners = new ArrayList<>();
    listeners.add(new LogAppiumWebDriverEventListener());
    listeners.add(new ContextEventListener());
    listeners.add(new ElementListener());

    //注册监听
    androidDriver = EventFiringWebDriverFactory.getEventFiringWebDriver(androidDriver,listeners);

}



    public void initLog(){
        PropertyConfigurator.configure("src/test/Resources/log4j.properties");
    }



@AfterMethod
public void afterEachTestMethod() throws Exception {

    WechatNativePage wechatHomePage=new WechatNativePage(androidDriver);

    FunctionUtil functionUtil=new FunctionUtil(androidDriver);
    functionUtil.switchToNative();

    wechatHomePage.iconOfReturnAndClose.click();
    wechatHomePage.iconOfReturn.click();
    wechatHomePage.iconOfCancel.click();

}







    }








