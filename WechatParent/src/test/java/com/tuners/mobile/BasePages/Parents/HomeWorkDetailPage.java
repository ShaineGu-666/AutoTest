package com.tuners.mobile.BasePages.Parents;

import com.tuners.mobile.util.Functions.FunctionUtil;
import com.tuners.mobile.util.PageObject;
import com.tuners.mobile.util.Functions.ReusableFunctions;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * @Author Mason
 * @Description
 * @Date 2020/6/22 10:39
 **/
public class HomeWorkDetailPage extends PageObject {

    public HomeWorkDetailPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    @Override
    public void verifyDisplayed() throws Exception {
        waitForPageLoadComplete();




    }


    //请在 什么时间前提交作业
    @FindBy(xpath="//div[@class='tips']//span")
    public WebElement textOfTips;



    //发布年级
    @FindBy(xpath="//span[@class='info-desc']")
    public WebElement textOfPublishToGrade;

    //作业标题
    @FindBy(xpath="//div[@class='title-wrod']")
    public WebElement titleOfHomeWork;

    //作业内容
    @FindBy(xpath="//p[@class='card-desc']")
    public WebElement textOfHomeWorkContent;

    //语音条目
    @FindBy(xpath="//li[@class='record b-box']//div[@class='left']")
    public List<WebElement> listOfHomeworkVoicePieces;

    //视频及语音
    @FindBy(xpath="//div[@class='img-wrap']//span//img[1]")
    public List<WebElement> listOfUploadedImagesOrvideos;

    //上传的文件名称集合
    @FindBy(xpath="//ul[@class='file-wrap']//li//span[@class='file-name']")
    public List<WebElement> listOfUploadedFileNames;


    //上传的文件复制链接集合
    @FindBy(xpath="//ul[@class='file-wrap']//li//span[@class='links']")
    public List<WebElement> listOfUploadedFileCopyLinks;

    //上传文件的文件标志
    @FindBy(xpath="//ul[@class='file-wrap']//li//i[@class='file-icon']")
    public List<WebElement> listOfUploadedFileIcons;

    //上传的链接名称
    @FindBy(xpath="//li//div[@class='file-desc']//span[@class='link-name']")
    public List<WebElement> listOfUploadedLinkNames;

    //上传的链接的复制链接集合
    @FindBy(xpath="//li//div[@class='file-desc']//span[@class='links']")
    public List<WebElement> listOfUploadedLinkCopyLinks;

    //上传的链接的链接图标
    @FindBy(xpath="//li//div[@class='file-desc']//img[@class='icon-link']")
    public List<WebElement> listOfUploadedLinkIcons;

    //截止日期
    @FindBy(xpath="//div[@class='card-remark']")
    public WebElement textOfSubmitEndTime;

    //提交作业按钮
    @FindBy(xpath="//div[@class='o-button']")
    public WebElement btnOfSubmitHomework;


    public void verifyHomeWorkDetailsInDetailsPage(LinkedHashMap homeworkInfo,Boolean submitstatus) throws Exception {

        //作业标题
        verifyHomeWorkTitle(ReusableFunctions.getHomeworkTitleFromStorage(homeworkInfo));
        //作业内容
        verifyHomeworkContent(ReusableFunctions.getHomeworkContentFromStorage(homeworkInfo));

        //语音条数
        if (FunctionUtil.verifyWhetherKeyExistInMap("voiceCount", homeworkInfo)) {
            veirfyVoiceCount(ReusableFunctions.getHomeworkVoiceTotalFromStorage(homeworkInfo));
        }

        //图片视频
        if (FunctionUtil.verifyWhetherKeyExistInMap("imageAndVideoSize", homeworkInfo)) {
            verifyImageVideoSection(ReusableFunctions.getHomeworkImageAndVideoSizeFromStorage(homeworkInfo), ReusableFunctions.getHomeworkImageAndVideoSrcsFromStorage(homeworkInfo));

        }
        //文件
        if (FunctionUtil.verifyWhetherKeyExistInMap("attachments", homeworkInfo)) {
            verifyAttachments(ReusableFunctions.getHomeworkAttachmentsFromStorage(homeworkInfo));
        }

        //链接
        if (FunctionUtil.verifyWhetherKeyExistInMap("linkMap", homeworkInfo)) {
            verifyLinkSection(ReusableFunctions.getHomeworkLinkMapFromStorage(homeworkInfo).keySet());
        }

        //截止日期
        if (submitstatus) {
            String expectedHomeworkEndTime = ReusableFunctions.getHomeworkEndTimeFromStorage(homeworkInfo);
            verifyEndTime(expectedHomeworkEndTime);
            String expectedTips=expectedHomeworkEndTime.split("-")[1]+"-"+expectedHomeworkEndTime.split("-")[2];
            Assert.assertTrue(textOfTips.getText().trim().contains(expectedTips),"actual is:"+textOfTips.getText().trim()+",expected is:"+expectedTips);
            Assert.assertTrue(isElementExist(btnOfSubmitHomework),"提交作业按钮没显示");

        }
        //发布到的年级(若直到预期发布到的班级，验证班级)
        if(FunctionUtil.verifyWhetherKeyExistInMap("publishToGrade",homeworkInfo)) {
            verifyPublishToGrade(ReusableFunctions.getHomeworkPublishedGradeFromStorage(homeworkInfo));
        }



    }


    private void verifyHomeworkContent (String expectedHomeworkContent){
        Assert.assertEquals(getHomeworkContent(), expectedHomeworkContent, "预期的作业内容是:" + expectedHomeworkContent + ",实际获取到的内容是:" + getHomeworkContent());
    }

    public void verifyHomeWorkTitle(String expectedTitle){

        Assert.assertEquals(getHomeworkTitle(),expectedTitle,"expected homework title is:"+expectedTitle+"actual homework title is:"+getHomeworkTitle());

    }

    public void verifyEndTime(String expectedHomeworkEndTime){

        Assert.assertEquals(getEndTime(), expectedHomeworkEndTime, "预期的作业截止日期是:" + expectedHomeworkEndTime + ",实际获取到的截止日期是:" + getEndTime());
    }


    public void verifyImageVideoSection(int expectedImageAndVideoSize,List<String> listOfExpectedMediaSoures){

        int actualImageAndVideoSize=getImageAndVideoCount();
        List<String> listOfActualDisplayedImageOrVideoSrcs =getImageAndVideoSrc();
        Assert.assertEquals(listOfActualDisplayedImageOrVideoSrcs,listOfExpectedMediaSoures,"作业详情页的图片视频与预期不符合");



    }

    public void verifyAttachments(List<String> expectedAttachmentsList){

        List<String> actualAttachmentsNameList=getAttachmentNamesList();
        Assert.assertEquals(actualAttachmentsNameList,expectedAttachmentsList,"上传的文件名称与预期不符合");

        Assert.assertTrue(actualAttachmentsNameList.size()==listOfUploadedFileIcons.size(),"上传的文件名数量:"+actualAttachmentsNameList.size()+"文件标志数量:"+listOfUploadedFileIcons.size());

        Assert.assertTrue(listOfUploadedFileIcons.size()==listOfUploadedFileCopyLinks.size(),"上传的文件标志数量:"+actualAttachmentsNameList.size()+"文件复制链接数量:"+listOfUploadedFileCopyLinks.size());

    }

    public void veirfyVoiceCount(int expectedVoiceCount){
        Assert.assertEquals(getVoiceCount(),expectedVoiceCount,"预期的语音上传条数为:"+expectedVoiceCount+"实际的语音上传条数为:"+getVoiceCount());


    }

    public void verifyLinkSection(Set<String> expectedlinkNameList) throws Exception {

        waitForListVisible(listOfUploadedLinkNames,30);
        List<String> actualLinkNamesList=FunctionUtil.getValuesFromWebElementList(listOfUploadedLinkNames);

        Assert.assertEquals(actualLinkNamesList,expectedlinkNameList,"链接名称集合预期与实际的不符合");

        Assert.assertTrue(actualLinkNamesList.size()==listOfUploadedLinkIcons.size(),"链接的标志数量为:"+listOfUploadedLinkIcons.size()+"链接名称数量为："+actualLinkNamesList.size());
        Assert.assertTrue(listOfUploadedLinkIcons.size()==listOfUploadedLinkCopyLinks.size(),"链接的标志数量为:"+listOfUploadedLinkIcons.size()+"链接复制链接数量为："+listOfUploadedLinkCopyLinks.size());
    }
    private void verifyPublishToGrade(List<String> expectedGdList) {
        String expectedGdStr=expectedGdList.get(0);
        String expectedGdBase=expectedGdList.get(0).substring(0,1);
        String actualGdStr=textOfPublishToGrade.getText().trim();
        if(expectedGdList.size()>1){
            expectedGdStr=expectedGdBase;
            for(int i=1;i<expectedGdList.size();i++){
                expectedGdStr=expectedGdStr+","+expectedGdList.get(i).substring(0,1);
            }
            expectedGdStr=expectedGdStr+"年级";
        }
        System.out.println("expectedGdStr:"+expectedGdStr);
        Assert.assertEquals(actualGdStr,expectedGdStr,"actualGdStr:"+actualGdStr+"expectedGdStr"+expectedGdStr);

    }
    public LinkedHashMap<String,Object> collectHomeWorkInfoOnHDP(){

        LinkedHashMap<String,Object> homeworkInfo=new LinkedHashMap<String,Object>();

        //作业标题
        String actualTitle=getHomeworkTitle();
        //作业内容
        String actualHomeworkContent=getHomeworkContent();
        //语音条数及SRC
        int actualVoiceCount=getVoiceCount();

        //图片视频的张数及SRC
        int actualImageAndVideoSize=getImageAndVideoCount();
        List<String> listOfActualDisplayedImageOrVideoSrcs=getImageAndVideoSrc();
        //链接
        List<String> actualAttachmentsNameList=getAttachmentNamesList();

        //截止时间
        String actualHomeworkEndTime=getEndTime();

        homeworkInfo.put("actualTitle",actualTitle);
        homeworkInfo.put("actualHomeworkContent",actualHomeworkContent);
        homeworkInfo.put("actualVoiceCount",actualVoiceCount);
        homeworkInfo.put("actualImageAndVideoSize",actualImageAndVideoSize);
        homeworkInfo.put("listOfActualDisplayedImageOrVideoSrcs",listOfActualDisplayedImageOrVideoSrcs);
        homeworkInfo.put("actualAttachmentsNameList",actualAttachmentsNameList);
        homeworkInfo.put("actualHomeworkEndTime",actualHomeworkEndTime);

    return homeworkInfo;
    }
    public String getHomeworkTitle(){
        return titleOfHomeWork.getText().trim();
    }
    public String getHomeworkContent(){
        return textOfHomeWorkContent.getText().trim();
    }
    public int getVoiceCount(){
        return listOfHomeworkVoicePieces.size();
    }
    public int getImageAndVideoCount(){
        return listOfUploadedImagesOrvideos.size();
    }
    public List<String> getImageAndVideoSrc(){
        return  FunctionUtil.getAttributesFromWebElementList("src",listOfUploadedImagesOrvideos);
    }
    public List<String> getAttachmentNamesList(){
        return  FunctionUtil.getValuesFromWebElementList(listOfUploadedFileNames);
    }
    public String getEndTime(){
return textOfSubmitEndTime.getText().trim().split("：")[1].trim();
    }
}
