package com.tuners.mobile.BasePages.Teacher;

import com.tuners.mobile.util.Functions.FunctionUtil;
import com.tuners.mobile.util.PageObject;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @Author Shaine
 * @Description
 * @Date 2020/6/16 11:51
 **/
public class HomePage extends PageObject {


    public HomePage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    @Override
    public void verifyDisplayed() throws Exception {

    }

//作业
    @FindBy(xpath="//div[@class='iconClass'][contains(text(),'作业')]//ancestor::div[contains(@class,'item_')]")
    public WebElement iconOfHomeWork;

    //更多内的我的
    @FindBy(xpath="//div[@class='rightOverItem'][not(@style)]/p[contains(text(),'我的')]")
    public WebElement iconOfMy;

    //更多内的学生请假
    @FindBy(xpath="//div[@class='rightOverItem'][not(@style)]/p[contains(text(),'学生请假')]")
    public WebElement iconOfStudentLeave;


    @FindBy(xpath="//div[@class='iconClass'][text()='更多']//ancestor::div[contains(@class,'item_')]")
    public WebElement iconOfMore;




    public void enterHomeWorkManagementPage() throws Exception {
        if(iconOfHomeWork.isDisplayed()){
            iconOfHomeWork.click();
        }else {
            iconOfMore.click();
            waitForPageLoadComplete();
            FunctionUtil.clickAfterVisible(iconOfHomeWork);

        }
        waitForPageLoadComplete();
    }


    public void enterMyPage() throws Exception {
        iconOfMore.click();
        FunctionUtil.clickAfterVisible(iconOfMy);
        waitForPageLoadComplete();
    }


}
