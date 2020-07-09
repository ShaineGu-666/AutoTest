package com.tuners.mobile.BasePages.Parents;

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
 * @Author Mason
 * @Description
 * @Date 2020/6/29 14:00
 **/
public class HomeworkManagement extends PageObject {
    public HomeworkManagement(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    //选中状态的列表
    @FindBy(xpath="//div[@role='tablist']//div[@role='tab'][@aria-selected]")
    public WebElement tabOfSelectedTab;

    //未提交列表
    @FindBy(xpath="(//div[@role='tablist']//div[@role='tab'])[1]//span")
    public WebElement tabOfNotSubmitList;

    //无需提交列表
    @FindBy(xpath="(//div[@role='tablist']//div[@role='tab'])[2]//span")
    public WebElement tabOfNoNeedSubmitList;

    //已提交列表
    @FindBy(xpath="(//div[@role='tablist']//div[@role='tab'])[3]//span")
    public WebElement tabOfSubmittedList;


    //选择时间标志
    @FindBy(xpath="(//div[@tabindex='0']//i[contains(@class,'left-icon')])[1]")
    public WebElement iconOfSelectTime;


    //选择科目标志
    @FindBy(xpath="(//div[@tabindex='0']//i[contains(@class,'left-icon')])[2]")
    public WebElement iconOfSelectSubject;

    //选择时间箭头
    @FindBy(xpath="(//div[@tabindex='0']//i[contains(@class,'arrow')])[1]")
    public WebElement arrowOfSelectTime;

    //选择科目箭头
    @FindBy(xpath="(//div[@tabindex='0']//i[contains(@class,'arrow')])[2]")
    public WebElement arrowOfSelectSubject;


    //列表中的作业标题
    @FindBy(xpath="//div[@role='tabpanel'][not(contains(@style,'none'))]//ul[@class='homework-wrapper']//span[@class='sub-title']")
    public List<WebElement> listOfHomeworkTitles;

    //列表中的作业科目
    @FindBy(xpath="//div[@role='tabpanel'][not(contains(@style,'none'))]//ul[@class='homework-wrapper']//span[@class='tag']")
    public List<WebElement> listOfHomeworkSubjects;


    //列表中的作业内容
    @FindBy(xpath="//div[@role='tabpanel'][not(contains(@style,'none'))]//ul[@class='homework-wrapper']//p[@class='content-detail']")
    public List<WebElement> listOfHomeworkContent;

//    //每个作业的缩略图   如果图片小于三张显示实际张数，如果大于三张则显示三张
//    @FindBy(xpath="((//div[@role='tabpanel'][not(contains(@style,'none'))]//ul[@class='homework-wrapper']//div[@class='img-wrap'])[1]//span)//img[1]")
//    public List<WebElement> listOfHomeworkThumbnails;

    //每个作业的截止时间
//    @FindBy(xpath="//div[@role='tabpanel'][not(contains(@style,'none'))]//ul[@class='homework-wrapper']//li//span[contains(text(),'截止时间')]")
//    public List<WebElement> listOfEndTime;


    @FindBy(xpath="//div[@role='tabpanel'][not(contains(@style,'none'))]//ul[@class='homework-wrapper']//li//span[contains(text(),'已提交')]")
    public List<WebElement> listOfSubmittedSpan;


    //无需查看列表下的已查看
    @FindBy(xpath="//div[@role='tabpanel'][not(contains(@style,'none'))]//ul[@class='homework-wrapper']//li//span[contains(text(),'已查看')]")
    public List<WebElement> listOfViewedSpan;



    @Override
    public void verifyDisplayed() throws Exception {
        waitForPageLoadComplete();

        //选择时间、选择科目
        waitForElementVisible(iconOfSelectSubject,30);
        waitForElementVisible(iconOfSelectTime,30);
        waitForElementVisible(arrowOfSelectSubject,30);
        waitForElementVisible(arrowOfSelectTime,30);

        //未提交 无需提交 已提交tab
        waitForElementVisible(tabOfNotSubmitList,30);
        waitForElementVisible(tabOfNoNeedSubmitList,30);
        waitForElementVisible(tabOfSubmittedList,30);

        //默认选中的是未提交tab
        Assert.assertTrue(tabOfSelectedTab.getText().trim().contains("未提交"),"默认选中的tab 不是未提交列表，而是:"+tabOfSelectedTab.getText().trim());
    }


    /**
     * 未提交列表
     * 根据作业标题查找作业
     * 验证作业信息
     */

    public int findHomeworkIndexInList(String homeworkTitle) throws Exception {

        waitForListVisible(listOfHomeworkTitles,30);
        return FunctionUtil.getIndexOfDesiredElementInList(listOfHomeworkTitles,homeworkTitle);


    }


    /**
     *
     * @param homeworkInfo
     * @param submitStatus
     * @throws Exception
     */
        public void verifyHomeWorkInfo(LinkedHashMap<String,Object> homeworkInfo,Boolean submitStatus) throws Exception {

            waitForListVisible(listOfHomeworkTitles,30);
                //作业标题
                String expectedTitle = ReusableFunctions.getHomeworkTitleFromStorage(homeworkInfo);
                int i = FunctionUtil.getIndexOfDesiredElementInList(listOfHomeworkTitles, expectedTitle);
                Assert.assertTrue(i != -1, "找不到预期的作业标题");


                //科目
                String expectedSubject = ReusableFunctions.getHomeworkSubjectFromStorage(homeworkInfo);
                String actualSubject = listOfHomeworkSubjects.get(i).getText().trim().split(" ")[1];
                Assert.assertEquals(actualSubject, expectedSubject, "预期的科目是:" + expectedSubject + ",实际获取到的科目是:" + actualSubject);


                //作业内容
                String expectedHomeworkContent =ReusableFunctions.getHomeworkContentFromStorage(homeworkInfo);
                String actualHomeworkContent = listOfHomeworkContent.get(i).getText().trim();
                Assert.assertEquals(actualHomeworkContent, expectedHomeworkContent, "预期的作业内容是:" + expectedHomeworkContent + ",实际获取到的内容是:" + actualHomeworkContent);

                //语音条数
                if(FunctionUtil.verifyWhetherKeyExistInMap("voiceCount",homeworkInfo)){
                    int expectedVoiceCount =ReusableFunctions.getHomeworkVoiceTotalFromStorage(homeworkInfo);
                  //  int actualVoiceCount = androidDriver.findElements(By.xpath("(//ul[@class='homework-wrapper'])[" + (i + 1) + "]//li[@class='record b-box']//div[@class='left']")).size();

                   // Assert.assertEquals(actualVoiceCount, expectedVoiceCount, "预期的作业语音条数是:" + expectedVoiceCount + ",实际获取到语音条数是:" + actualVoiceCount);
                }

                //若超过三张及三张以上，显示前三张图片，若小于三张，显示实际张数的图片

                if(FunctionUtil.verifyWhetherKeyExistInMap("imageAndVideoSize",homeworkInfo)) {
                    int expectedImageAndVideoSize =ReusableFunctions.getHomeworkImageAndVideoSizeFromStorage(homeworkInfo);

                    List<String> listOfExpectedMediaSoures = ReusableFunctions.getHomeworkImageAndVideoSrcsFromStorage(homeworkInfo);

                    List<AndroidElement> listOfActualDisplayedImageOrVideo = androidDriver.findElements(By.xpath("((//div[@role='tabpanel'][not(contains(@style,'none'))]//ul[@class='homework-wrapper']//div[@class='img-wrap'])["+(i+1)+"]//span)//img[1]"));

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
                    WebElement endTime=androidDriver.findElement(By.xpath("(//ul[@class='homework-wrapper'])["+(i+1)+"]//span[contains(text(),'截止时间')]"));
                    //截止时间
                    String expectedHomeworkEndTime =ReusableFunctions.getHomeworkEndTimeFromStorage(homeworkInfo);
                    String actualHomeworkEndTime =endTime.getText().trim().split("：")[1].trim();
                    Assert.assertEquals(actualHomeworkEndTime, expectedHomeworkEndTime, "预期的作业截止日期是:" + expectedHomeworkEndTime + ",实际获取到的截止日期是:" + actualHomeworkEndTime);




                }

            }





}
