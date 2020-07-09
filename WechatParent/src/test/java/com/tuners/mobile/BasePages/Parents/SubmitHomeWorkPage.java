package com.tuners.mobile.BasePages.Parents;

import com.tuners.mobile.util.Functions.FunctionUtil;
import com.tuners.mobile.util.PageObject;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Author Mason
 * @Description
 * @Date 2020/7/2 15:07
 **/
public class SubmitHomeWorkPage extends PageObject {
    public SubmitHomeWorkPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }


    //作业标题
    @FindBy(xpath="//div[@class='title-wrod']")
    public WebElement titleOfHomeWork;

    //作业内容
    @FindBy(xpath="//p[@class='card-desc']")
    public WebElement textOfHomeWorkContent;

    //语音条目
    @FindBy(xpath="//li[@class='record b-box']//div[@class='left']")
    public List<WebElement> listOfHomeworkVoicePieces;

    //上传的文件名称集合
    @FindBy(xpath="//ul[@class='file-wrap']//li//span[@class='file-name']")
    public List<WebElement> listOfUploadedFileNames;

    //视频及语音
    @FindBy(xpath="//div[@class='img-wrap']//span//img[1]")
    public List<WebElement> listOfUploadedImagesOrvideos;

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
    @FindBy(xpath="//span[@class='card-remark']")
    public WebElement textOfSubmitEndTime;


    //输入提交作业的内容文本域
    @FindBy(xpath="//textarea")
    public WebElement textareaOfHomeworkContent;

    //语音标识
    @FindBy(xpath="//div[@class='show-btn']//section//img[1]")
    public WebElement iconOfVoice;

    //图片标识
    @FindBy(xpath="//div[@class='show-btn']//section//img[2]")
    public WebElement iconOfPicture;

    //视频标识
    @FindBy(xpath="//div[@class='show-btn']//section//img[3]")
    public WebElement iconOfVideo;

    //附件标识
    @FindBy(xpath="//div[@class='show-btn']//section//img[4]")
    public WebElement iconOfFile;

    //链接标识
    @FindBy(xpath="//div[@class='show-btn']//section//img[5]")
    public WebElement iconOfLink;

    //提交作业的提示
    @FindBy(xpath="//div[@class='tips']")
    public WebElement textOfTips;

    //提交作业按钮
    @FindBy(xpath="//div[contains(@class,'btn-submit')]//div")
    public WebElement btnOfSubmit;

    @Override
    public void verifyDisplayed() throws Exception {
        waitForPageLoadComplete();

        verifyHomeWorkFromTeacherSection(true,true,false,true);
        verifySubmitSection();

    }

    private void verifyHomeWorkFromTeacherSection(Boolean voiceFlag,Boolean imageVideoFlag,Boolean attachmentsFlag,Boolean linkFlag) throws Exception {

        waitForElementVisible(titleOfHomeWork,30);
        waitForElementVisible(textOfHomeWorkContent,30);
        waitForElementVisible(textOfSubmitEndTime,30);

        //
        if(voiceFlag) {
            waitForListVisible(listOfHomeworkVoicePieces, 30);
        }
        if(imageVideoFlag) {
            waitForListVisible(listOfUploadedImagesOrvideos, 30);
        }
        if(attachmentsFlag) {
            waitForListVisible(listOfUploadedFileNames, 30);
            waitForListVisible(listOfUploadedFileCopyLinks, 30);
            waitForListVisible(listOfUploadedFileIcons, 30);

        }
        if(linkFlag){
            waitForListVisible(listOfUploadedLinkNames, 30);
            waitForListVisible(listOfUploadedLinkIcons, 30);
            waitForListVisible(listOfUploadedLinkCopyLinks, 30);
        }

    }

    public void verifySubmitSection() throws Exception {

        waitForElementVisible(textareaOfHomeworkContent,30);
        waitForElementVisible(iconOfVoice,30);
        waitForElementVisible(iconOfPicture,30);
        waitForElementVisible(iconOfVideo,30);
        waitForElementVisible(iconOfFile,30);
        waitForElementVisible(iconOfLink,30);
        waitForElementVisible(textOfTips,30);
        waitForElementVisible(btnOfSubmit,30);

    }



    public LinkedHashMap<String,Object> collectHomeWorkInfoOnSHP(){

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
        return textOfSubmitEndTime.getText().trim();
    }

    public void submitContent() {

       //输入提交文字


        //上传语音


        //上传图片


        //上传视频


        //上传文件


        //上传链接


    }
}
