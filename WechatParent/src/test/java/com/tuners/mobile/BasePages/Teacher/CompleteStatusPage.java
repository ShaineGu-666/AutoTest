package com.tuners.mobile.BasePages.Teacher;

import com.tuners.mobile.util.Functions.FunctionUtil;
import com.tuners.mobile.util.PageObject;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Mason
 * @Description
 * @Date 2020/6/24 17:00
 **/
public class CompleteStatusPage extends PageObject {
    public CompleteStatusPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    @Override
    public void verifyDisplayed() throws Exception {

    }
  //未提交
    @FindBy(xpath="//div[@role='tab']//span[contains(text(),'未提交')]")
    public WebElement tabOfUncomplishedList;

   //已提交
    @FindBy(xpath="//div[@role='tab']//span[contains(text(),'已提交')]")
    public WebElement tabOfCompleteList;

    //未订正
    @FindBy(xpath="//div[@role='tab']//span[contains(text(),'未订正')]")
    public WebElement tabOfUnrevisedList;

   //已提交 未订正 未提交下的学生列表
    @FindBy(xpath="//div[@class='con-titles']")
    public List<WebElement> listOfStudentNameUnderTabs;

    //已查看
    @FindBy(xpath="//div[@role='tab']//span[contains(text(),'已查看')]")
    public WebElement tabOfViewedList;

    //未查看
    @FindBy(xpath="//div[@role='tab']//span[contains(text(),'未查看')]")
    public WebElement tabOfNotViewedList;



    public List<String> collectWholePublishedStudentName(Boolean submitstatus) throws InterruptedException {
        List<String> wholeStudents=new ArrayList<String>();

    if(submitstatus){
        //已提交
        if(!tabOfCompleteList.getText().trim().contains("(0)")){
            tabOfCompleteList.click();
            Thread.sleep(3000);
            wholeStudents.addAll(FunctionUtil.getValuesFromWebElementList(listOfStudentNameUnderTabs));
        }

        //未订正
        if(!tabOfUnrevisedList.getText().trim().contains("(0)")){
            tabOfUnrevisedList.click();
            Thread.sleep(3000);
            wholeStudents.addAll(FunctionUtil.getValuesFromWebElementList(listOfStudentNameUnderTabs));
        }

        //未提交
        if(!tabOfUncomplishedList.getText().trim().contains("(0)")){
            tabOfUncomplishedList.click();
            Thread.sleep(3000);
            wholeStudents.addAll(FunctionUtil.getValuesFromWebElementList(listOfStudentNameUnderTabs));
        }

    }


    if(!submitstatus){

        //已查看

        if(!tabOfViewedList.getText().trim().contains("(0)")){
            tabOfViewedList.click();
            Thread.sleep(3000);
            wholeStudents.addAll(FunctionUtil.getValuesFromWebElementList(listOfStudentNameUnderTabs));
        }
        //未查看
        if(!tabOfNotViewedList.getText().trim().contains("(0)")){
            tabOfNotViewedList.click();
            Thread.sleep(3000);
            wholeStudents.addAll(FunctionUtil.getValuesFromWebElementList(listOfStudentNameUnderTabs));
        }

    }

        return wholeStudents;


    }
}
