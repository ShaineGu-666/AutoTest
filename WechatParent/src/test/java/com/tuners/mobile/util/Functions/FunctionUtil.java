package com.tuners.mobile.util.Functions;


import com.tuners.mobile.util.PageObject;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;

public class FunctionUtil {
    public  AndroidDriver<AndroidElement> androidDriver;


    public FunctionUtil(AndroidDriver androidDriver) {
        this.androidDriver=androidDriver;

    }
public static  void clickAfterVisible(WebElement element) throws Exception {

    PageObject.waitForElementVisible(element,30);
    element.click();

}

    public static  void selectFromDropdown(List<WebElement> list, String value) throws Exception {

    Thread.sleep(6000);
//        PageObject.waitForListExist(list, 30);
        for(int i=0;i<list.size();i++){
            if(list.get(i).getText().trim().contains(value)){
                list.get(i).click();
                System.out.println("value is:"+value);
            }
         PageObject.waitForPageLoadComplete();
        }

    }

    public static  void sendkeysAfterVisible(WebElement element,String value) throws Exception {

        PageObject.waitForElementVisible(element,30);
        element.sendKeys(value);

    }



    public void safeJavaScriptClick(WebElement element) throws Exception {
        try {
            if (element.isEnabled() && element.isDisplayed()) {
                System.out.println("Clicking on element with using java script click");

                ((JavascriptExecutor)androidDriver).executeScript("arguments[0].click();", element);
            } else {
                System.out.println("Unable to click on element");
            }
        } catch (StaleElementReferenceException e) {
            System.out.println("Element is not attached to the page document "+ e.getStackTrace());
        } catch (NoSuchElementException e) {
            System.out.println("Element was not found in DOM "+ e.getStackTrace());
        } catch (Exception e) {
            System.out.println("Unable to click on element "+ e.getStackTrace());
        }
    }


public void singleTab(WebElement element){
    TouchActions action = new TouchActions(androidDriver);
    action.singleTap(element);
    action.perform();
}

    public void singleTabByCoordinate(double x,double y){
        int x1=(int)x;
        int y1=(int)x;
        new TouchAction(androidDriver).press(PointOption.point(x1,y1)).release().perform();

    }


    public static String getCurrentDateFormat(){
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yy");
        System.out.println(date.format(formatter));
        return date.format(formatter);
    }

    public static String getCurrentHourMiniuteFormat(){
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        System.out.println(time.format(formatter));
        return time.format(formatter);
    }

    public static String getAfterDayDate(String days, String dateFormat) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdfd = new SimpleDateFormat(dateFormat);
        return sdfd.format(date);



    }



    public void  switchToDesiredWebView(String desiredWebView){
        Set<String> contexts = androidDriver.getContextHandles();

        for (String s : contexts) {
            System.out.println("context:" + s);
            if (s.contains(desiredWebView)) {
                androidDriver.context(s);
            }
        }
    }


    public void  switchToNative(){
        Set<String> contexts = androidDriver.getContextHandles();

        for (String s : contexts) {
            System.out.println("context:" + s);
            if (s.contains("NATIVE_APP")) {
                androidDriver.context(s);
            }
        }
    }

    public void selectDesiredValue(String desiredValue, List<WebElement> list, WebElement selectedItem) throws InterruptedException {
        int count=0;
        Thread.sleep(2000);
        int max=list.size();

        while((!selectedItem.getText().trim().equals(desiredValue))&&count<max){
            //上滑
            System.out.println("上滑前的选中项为:"+selectedItem.getText().trim());
            SwipeClass.swipeToUp2(androidDriver);
            System.out.println("上滑后的选中项为:"+selectedItem.getText().trim());
            Thread.sleep(1000);
            count++;
        }


    }

public static List<String> getValuesFromWebElementList(List<WebElement> list){

List<String> list1=new ArrayList<>();
for(WebElement e:list){

    list1.add(e.getText().trim());
}
return list1;

}



public static int getIndexOfDesiredElementInList(List<WebElement> list,String desiredValue){

        for(int i=0;i<list.size();i++){

           if(list.get(i).getText().trim().contains(desiredValue)){

               return i;
           }


        }


    return -1;
}

public void clickByAction(WebElement e){
    Actions action = new Actions(androidDriver);
    action.click(e);


}

    public static <T> List<T> castList(Object obj, Class<T> clazz)
    {
        List<T> result = new ArrayList<T>();
        if(obj instanceof List<?>)
        {
            for (Object o : (List<?>) obj)
            {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }



    public static List<String>  getAFewElementInAListFromStart(List<String> list, int n){
        List<String> list1 = new ArrayList<String>();
        for(int i=0;i<n;i++){

          list1.add(list.get(i));
        }

        return list1;

}


public static List<String> getAttributesFromWebElementList(String attributeName,List<WebElement> list){

List<String> attributeList=new ArrayList<String>();
    for(WebElement element:list){
        attributeList.add(element.getAttribute(attributeName).trim());
    }

return attributeList;
}

    public static List<String> getAttributesFromAndroidElementList(String attributeName,List<AndroidElement> list){

        List<String> attributeList=new ArrayList<String>();
        for(AndroidElement element:list){
            attributeList.add(element.getAttribute(attributeName).trim());
        }

        return attributeList;
    }


    // 大集合用linkedlist
    // 小集合用hashset
    // 采用Iterator迭代器进行数据的操作
    public static List<String> receiveDefectList(List<String> firstArrayList, List<String> secondArrayList) {
        List<String> resultList = new ArrayList<String>();
        LinkedList<String> result = new LinkedList<String>(firstArrayList);
        HashSet<String> othHash = new HashSet<String>(secondArrayList);
        Iterator<String> iter = result.iterator();
        while(iter.hasNext()){
            if(othHash.contains(iter.next())){
                iter.remove();
            }
        }
        resultList = new ArrayList<String>(result);
        return resultList;
    }



public static Boolean verifyWhetherKeyExistInMap(String key, Map<String,Object> map){

Set<String> keyset=map.keySet();
Boolean Flag=false;
    for(String s:keyset){
        if(s.equals(key)){
            Flag=true;
        }
    }
    return Flag;


}



    public static Map<?, ?> objectToMap(Object obj) {
        if(obj == null)
            return null;

        return new org.apache.commons.beanutils.BeanMap(obj);
    }


    //propertyFilePath  相对路径
    public Properties loadProperties(String propertyFileName) throws IOException {

        Properties props = new Properties();
        props.load(new FileInputStream("./target/test-classes/properties/"+propertyFileName+".properties"));


return  props;
    }




    public Properties loadPropertiesMethod1(String path) throws IOException {
        InputStream ips = FunctionUtil.class
                .getResourceAsStream(path);

        Properties props = new Properties();
        props.load(ips);
        ips.close();

        return  props;
    }

public Properties loadPropertiesMethod2(String path) throws IOException {

    InputStream ips = FunctionUtil.class
            .getClassLoader()
            .getResourceAsStream(path);

    Properties props = new Properties();
    props.load(ips);
    ips.close();

    return props;

}




}
















