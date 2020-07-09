package com.tuners.mobile.BasePages.Teacher;

import com.tuners.mobile.util.Functions.FunctionUtil;
import com.tuners.mobile.util.PageObject;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

/**
 * @Author Mason
 * @Description
 * @Date 2020/6/18 13:56
 **/
public class AddLinkPage extends PageObject {
    public AddLinkPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    @Override
    public void verifyDisplayed() throws Exception {
    waitForPageLoadComplete();
    waitForElementVisible("//label[@for='title'][contains(text(),'链接名称')]",30);
  waitForElementVisible(inputOfLinkName,30);
  waitForElementVisible(textareaOfLinkAddress,30);
  waitForElementVisible(btnOfComplete,30);

        Assert.assertTrue(isElementExist(inputOfLinkName),"inputOfLinkName is not displayed");
        Assert.assertTrue(isElementExist(textareaOfLinkAddress),"textareaOfLinkAddress is not displayed");
        Assert.assertTrue(isElementExist(btnOfComplete),"btnOfComplete is not displayed");

    }

    //链接名称输入框
    @FindBy(xpath="//input[@name='title']")
    public WebElement inputOfLinkName;


    //链接地址输入文本域
    @FindBy(xpath="//textarea[@placeholder='链接地址']")
    public WebElement textareaOfLinkAddress;


    //完成按钮
    @FindBy(xpath="//button[@class='base-btn'][contains(text(),'完成')]")
    public WebElement btnOfComplete;

    public void setLinkContent(String linkName,String linkAddress) throws Exception {
            FunctionUtil functionUtil=new FunctionUtil(androidDriver);
            inputOfLinkName.sendKeys(linkName);
            textareaOfLinkAddress.sendKeys(linkAddress);
            waitForElementClickable(btnOfComplete,30);
            functionUtil.singleTabByCoordinate(200,200);
            functionUtil.safeJavaScriptClick(btnOfComplete);


        }





}
