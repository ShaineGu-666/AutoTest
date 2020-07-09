package com.tuners.mobile.BasePages.Teacher;

import com.tuners.mobile.util.Functions.FunctionUtil;
import com.tuners.mobile.util.PageObject;
import com.tuners.mobile.util.Functions.ReusableFunctions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.LinkedHashMap;
import java.util.List;


/**
 * @Author Shaine
 * @Description
 * @Date 2020/6/16 12:09
 **/
public class HomeworkManagementPage extends PageObject {


    @FindBy(xpath="//i[@class='van-icon']//img")
    public WebElement btnOfPublish;

    @FindBy(xpath="title")
    public WebElement titleOfPage;

    @FindBy(xpath="//ul[@class='homework-wrapper']//li//span[@class='title']")
    public List<WebElement> listOfPublishedHomeworkList;

    @FindBy(xpath="//p[@class='content-detail']")
    public List<WebElement> listOfPublishedHomeworkContent;


    @FindBy(xpath="//span[@class='tags']")
    public List<WebElement> listOfPublishedHomeworkSubjects;

//
    @FindBy(xpath="//li//span[@class='date-wrap']")
    public List<WebElement> listOfPublishedHomeworkEndDate;

   //发布者的姓名
    @FindBy(xpath="//div[@class='head-content']//span[contains(@class,'con-desc')]")
    public List<WebElement> listOfPublishers;

    //发布时间
    @FindBy(xpath="//div[@class='head-content']//span[contains(@class,'con-time')]")
    public List<WebElement> listOfPublishTime;

    //发布者头像
    @FindBy(xpath="//span[@class='head-icon']")
    public List<WebElement> listOfPublisherHeadIcon;

   //删除作业
    @FindBy(xpath="//div[@class='delete-wrap']")
    public List<WebElement> listOfDeleteHomeWork;


    @FindBy(xpath="//li[@class='item-foot']//span[contains(text(),'已完成')]")
    public List<WebElement> listOfCompletion;


    @FindBy(xpath="//li[contains(@class,'item-foot')]//span[contains(text(),'已查看')]")
     public List<WebElement> listOfLookRate;


    public HomeworkManagementPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    @Override
    public void verifyDisplayed() throws Exception {
        waitForPageLoadComplete();
        waitForElementVisible(btnOfPublish,30);
        Assert.assertTrue(btnOfPublish.isDisplayed(),"发布作业按钮未显示");
    }



    public void clickPublishButton() throws Exception {
        FunctionUtil.clickAfterVisible(btnOfPublish);
        waitForPageLoadComplete();
    }

    public void verifyPublishedHomeWorkSection(Boolean submitStatus) throws Exception {

        waitForPageLoadComplete();

        //已经发布的作业标题
       waitForListVisible(listOfPublishedHomeworkList,30);
       //已经发布的作业内容
       waitForListVisible(listOfPublishedHomeworkContent,30);
        //已经发布的作业科目
      waitForListVisible(listOfPublishedHomeworkSubjects,30);
        //发布者
        waitForListVisible(listOfPublishers,30);
        //已经发布的作业的删除作业键
        waitForListVisible( listOfDeleteHomeWork,30);

        if(submitStatus) {
            //已经发布的作业的完成率
            waitForListVisible(listOfCompletion, 30);
            //已经发布的作业截止日期
            waitForListVisible(listOfPublishedHomeworkEndDate, 30);
        }else{

            //已经发布的作业的完成率
            waitForListVisible(listOfLookRate, 30);

        }

    }


    public void verifyDetailedPublishedHomeworkContent(LinkedHashMap<String,Object> homeworkInfo,Boolean submitStatus) throws Exception {

        verifyPublishedHomeWorkSection(submitStatus);
        //作业管理页面的内容：验证信息性的内容

        //作业标题
        String expectedTitle = ReusableFunctions.getHomeworkTitleFromStorage(homeworkInfo);
        int i = FunctionUtil.getIndexOfDesiredElementInList(listOfPublishedHomeworkList, expectedTitle);
        Assert.assertTrue(i != -1, "找不到预期的作业标题");


        //科目
        String expectedSubject = ReusableFunctions.getHomeworkSubjectFromStorage(homeworkInfo);
        String actualSubject = listOfPublishedHomeworkSubjects.get(i).getText().trim().split(" ")[1];
        Assert.assertEquals(actualSubject, expectedSubject, "预期的科目是:" + expectedSubject + ",实际获取到的科目是:" + actualSubject);


        //作业内容
        String expectedHomeworkContent =ReusableFunctions.getHomeworkContentFromStorage(homeworkInfo);
        String actualHomeworkContent = listOfPublishedHomeworkContent.get(i).getText().trim();
        Assert.assertEquals(actualHomeworkContent, expectedHomeworkContent, "预期的作业内容是:" + expectedHomeworkContent + ",实际获取到的内容是:" + actualHomeworkContent);

        //语音条数
        if(FunctionUtil.verifyWhetherKeyExistInMap("voiceCount",homeworkInfo)){
            int expectedVoiceCount =ReusableFunctions.getHomeworkVoiceTotalFromStorage(homeworkInfo);
            int actualVoiceCount = androidDriver.findElements(By.xpath("(//ul[@class='homework-wrapper'])[" + (i + 1) + "]//li[@class='record b-box']//div[@class='left']")).size();

            Assert.assertEquals(actualVoiceCount, expectedVoiceCount, "预期的作业语音条数是:" + expectedVoiceCount + ",实际获取到语音条数是:" + actualVoiceCount);
        }

        //若超过三张及三张以上，显示前三张图片，若小于三张，显示实际张数的图片

        if(FunctionUtil.verifyWhetherKeyExistInMap("imageAndVideoSize",homeworkInfo)) {
            int expectedImageAndVideoSize =ReusableFunctions.getHomeworkImageAndVideoSizeFromStorage(homeworkInfo);

            List<String> listOfExpectedMediaSoures = ReusableFunctions.getHomeworkImageAndVideoSrcsFromStorage(homeworkInfo);

            List<AndroidElement> listOfActualDisplayedImageOrVideo = androidDriver.findElements(By.xpath("(//ul[@class='homework-wrapper'])[" + (i + 1) + "]//div[@class='van-image']//img"));

            List<String> listOfActualDisplayedImageOrVideoSrcs = FunctionUtil.getAttributesFromAndroidElementList("data-src", listOfActualDisplayedImageOrVideo);

            List<String> listOfExpectedDisplayedImageOrVideoSrcs;

            if (expectedImageAndVideoSize > 3) {
                listOfExpectedDisplayedImageOrVideoSrcs = FunctionUtil.getAFewElementInAListFromStart(listOfExpectedMediaSoures, 3);
            } else {
                listOfExpectedDisplayedImageOrVideoSrcs = listOfExpectedMediaSoures;
            }


            Assert.assertEquals(listOfActualDisplayedImageOrVideoSrcs, listOfExpectedDisplayedImageOrVideoSrcs, "作业管理界面第:" + i + "个作业展示的图片及视频缩略内容不正确");
        }

        if(submitStatus) {
            WebElement endTime=androidDriver.findElement(By.xpath("(//ul[@class='homework-wrapper'])["+(i+1)+"]//span[@class='date-wrap']"));
            //截止时间
            String expectedHomeworkEndTime =ReusableFunctions.getHomeworkEndTimeFromStorage(homeworkInfo);
            String actualHomeworkEndTime =endTime.getText().trim().split("：")[1].trim();
            Assert.assertEquals(actualHomeworkEndTime, expectedHomeworkEndTime, "预期的作业截止日期是:" + expectedHomeworkEndTime + ",实际获取到的截止日期是:" + actualHomeworkEndTime);

        }

    }




}
