package com.tuners.console.utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @Author Shaine
 * @Description
 * @Date 2020/4/15 14:06
 **/
public class TestCaseBase {

   public static WebDriver driver;


@BeforeMethod
public void setup() throws InterruptedException {
    File file=new File("src/browserdrivers/chromedriver80.exe");
    System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
    driver=new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();

    driver.get("https://console.2035kids.com/#/login?redirect=%2F");
    PageObject.waitForPageLoadComplete();


}






//    @AfterMethod
//    public void afterMethod(){
//        driver.close();
//    }


}
