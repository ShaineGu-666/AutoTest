package com.tuners.mobile.BasePages.Parents;

import com.tuners.mobile.util.Functions.FunctionUtil;
import com.tuners.mobile.util.PageObject;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @Author Mason
 * @Description
 * @Date 2020/6/29 13:51
 **/
public class HomePage extends PageObject {
    public HomePage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    @Override
    public void verifyDisplayed() throws Exception {

    }

    //作业
    @FindBy(xpath="//div[@class='function-module']//span[contains(text(),'作业')]//preceding-sibling::i")
    public WebElement iconOfHomeWork;

    //全部
    @FindBy(xpath="//div[@class='function-module']//span[contains(text(),'全部')]//preceding-sibling::i")
    public WebElement iconOfAll;

    public void enterHomeWorkManagementPage() throws Exception {
        if(iconOfHomeWork.isDisplayed()){
            iconOfHomeWork.click();
        }else {
            iconOfAll.click();
            waitForPageLoadComplete();
            FunctionUtil.clickAfterVisible(iconOfHomeWork);

        }
        waitForPageLoadComplete();
    }

}
