package com.tuners.mobile.util.Functions;
import java.time.Duration;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;


    public class SwipeClass {
        static Duration duration=Duration.ofSeconds(1);
        public static void swipeToUp(AndroidDriver driver) {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
         TouchAction action1=new TouchAction(driver).press(PointOption.point(width/2, height*3/4)).waitAction(WaitOptions.waitOptions(duration)).moveTo(PointOption.point(width/2, height*1/2)).release();
         action1.perform();
        }

        public static void swipeToUp2(AndroidDriver driver) {
            int width = driver.manage().window().getSize().width;
            int height = driver.manage().window().getSize().height;
            TouchAction action1=new TouchAction(driver).press(PointOption.point(width/2, height*9/10)).waitAction(WaitOptions.waitOptions(duration)).moveTo(PointOption.point(width/2, height*85/100)).release();
            action1.perform();
        }

        public static void swipeToUp1(AndroidDriver driver,double starty,double endy) {
            int width = driver.manage().window().getSize().width;
            int height = driver.manage().window().getSize().height;
            TouchAction action1=new TouchAction(driver).press(PointOption.point(width/2, (int) (height*starty))).waitAction(WaitOptions.waitOptions(duration)).moveTo(PointOption.point(width/2, (int) (height*endy))).release();
            action1.perform();
        }
public static void swipeToDown(AndroidDriver driver) {
int width = driver.manage().window().getSize().width;
int height = driver.manage().window().getSize().height;
TouchAction action2=new TouchAction(driver).press(PointOption.point(width/2, height/4)).waitAction(WaitOptions.waitOptions(duration)).moveTo(PointOption.point(width/2, height*3/4)).release();
action2.perform();
}


public static void swipeToLeft(AndroidDriver driver) {
int width = driver.manage().window().getSize().width;
int height = driver.manage().window().getSize().height;
TouchAction action3=new TouchAction(driver).press(PointOption.point(width*3/4, height/2)).waitAction(WaitOptions.waitOptions(duration)).moveTo(PointOption.point(width/4,height/2)).release();
action3.perform();
}




public static void swipeToRight(AndroidDriver driver) {
int width = driver.manage().window().getSize().width;
int height = driver.manage().window().getSize().height;
TouchAction action4=new TouchAction(driver).press(PointOption.point(width / 4, height / 2)).waitAction(WaitOptions.waitOptions(duration)).moveTo(PointOption.point(width*3/4,height/2)).release();
action4.perform();
}


        public static void swipeToUpFreedom(AndroidDriver driver,double starty,double endy,int w) {
            int width = driver.manage().window().getSize().width;
            int height = driver.manage().window().getSize().height;
            TouchAction action1=new TouchAction(driver).press(PointOption.point(width/w, (int) (height*starty))).waitAction(WaitOptions.waitOptions(duration)).moveTo(PointOption.point(width/w, (int) (height*endy))).release();
            action1.perform();
        }





    }

