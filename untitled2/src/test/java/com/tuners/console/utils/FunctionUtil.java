package com.tuners.console.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import javax.xml.ws.WebEndpoint;
import java.util.List;

/**
 * @Author Shaine
 * @Description
 * @Date 2020/4/28 15:44
 **/
public class FunctionUtil {


    public static void clickItemByKeywords(List<WebElement> list,String keywords) throws Exception {
//        PageObject.waitForListVisible(list,30);
        Thread.sleep(6000);
        for(WebElement e: list){
            System.out.println("e.getText().trim():"+e.getText().trim());
            if(e.getText().trim().contains(keywords)){
//                FunctionUtil.scrollToElement(e);
//              e.click();
                FunctionUtil.clickByJS(e);
                System.out.println("click"+e.getText().trim());
              PageObject.waitForPageLoadComplete();
            }
        }
    }


    public static void scrollToElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) TestCaseBase.driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }


    public static void clickByJS(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) TestCaseBase.driver;
        js.executeScript("arguments[0].click();",element);
    }
}
