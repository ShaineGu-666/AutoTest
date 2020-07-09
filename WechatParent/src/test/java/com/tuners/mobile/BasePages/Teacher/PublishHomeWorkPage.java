package com.tuners.mobile.BasePages.Teacher;

import com.tuners.mobile.BasePages.WechatNativePage;
import com.tuners.mobile.util.Functions.FunctionUtil;
import com.tuners.mobile.util.PageObject;
import com.tuners.mobile.util.Functions.SwipeClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.tuners.mobile.util.Functions.FunctionUtil.clickAfterVisible;

/**
 * @Author Mason
 * @Description
 * @Date 2020/6/16 14:59
 **/
public class PublishHomeWorkPage extends PageObject {

    FunctionUtil functionUtil;


@FindBy(xpath="//ul//li//div[@class='van-ellipsis']")
public List<WebElement> listOfSubjectOptions;

    //当前选中科目
    @FindBy(xpath="//ul//li[contains(@class,'selected')]//div[@class='van-ellipsis']")
    public WebElement itemOfSelectedSubject;

    //全部科目
    @FindBy(xpath="//span[@class='header-btn-change']")
    public WebElement iconOfWholeSubject;

    @FindBy(xpath="//div[@class='rows-content-wrap rightArrow']")
    public WebElement arrowOfSelectSubject;


    @FindBy(xpath="//span[@class='header-btn-confirm']")
    public WebElement btnOfConfirmOnSubjectPop;

    //已经选中展示的科目名称
    @FindBy(xpath="//div[@class='rows-content-wrap rightArrow']//div[@class='row-content content']//span")
    public WebElement textOfSelectedSubject;

    //作业标题输入框
    @FindBy(xpath="//input[@placeholder='请输入作业标题']")
    public WebElement inputOfHomeWorkTitle;


    @FindBy(xpath="//textarea[contains(@placeholder,'作业内容')]")
    public WebElement textareaOfHomeworkContent;


    //语音标志
    @FindBy(xpath="//li//img[contains(@src,'QQAAAABJRU5ErkJggg==')]")
    public WebElement iconOfMicrophone;


//图片标志
@FindBy(xpath="//li//img[contains(@src,'svT5ZouxdpdjkixnitXv+WIlMuzxdq9bCLyF9PWxFE2UkE/AAAAAElFTkSuQmCC')]")
public WebElement iconOfImage;


//视频标志
    @FindBy(xpath="//li//img[contains(@src,'URC2KAAAAAElFTkSuQmCC')]")
    public WebElement iconOfvideo;


//文件标志
@FindBy(xpath="//li//img[contains(@src,'oB4manSHDdAfKAAAAAElFTkSuQmCC')]")
public WebElement iconOfFile;

    //连接标志
    @FindBy(xpath="//li//img[contains(@src,'4B7ti7P+8b3ywAAAAASUVORK5CYII')]")
    public WebElement iconOfLink;

    //已经上传的语音条
    @FindBy(xpath="//img[contains(@src,'DhpPuL5njWynVmrFvKzbv8BP2ySAR7YjUcAAAAASUVORK5CYII=')]")
    public List<WebElement> listOfUploadedPiecesOfVoices;


    //上传的图片块
    @FindBy(xpath="//li[@class='pic-wrap-item']//div[@class='van-image']//img")
    public List<WebElement> listOfUploadedBlockes;

    //上传的连接条
    @FindBy(xpath="//div[@class='file-desc']//span")
    public List<WebElement> listOfUploadedAttachments;

//附件的删除按钮
@FindBy(xpath="//div[@class='file-rarea']//div[@class='btn-del']")
public List<WebElement> listOfDeleteAttachments;

    //上传失败的文字
    @FindBy(xpath="//li[@class=‘pic-wrap-item’]//div[@class=‘van-circle__text’]")
    public List<WebElement> listOfUploadFailures;

    //已经上传的链接的图片标志
    @FindBy(xpath="//img[@class='icon-link']")
    public List<WebElement> listOfImagesOfLinkIcons;

    //link text
    @FindBy(xpath="//span[@class='link-name']")
    public List<WebElement> listOfLinkTexts;


//已上传的链接的删除按钮
    @FindBy(xpath="//li[@class='link-single']//div[@class='btn-del']")
    public List<WebElement> listOfDeleteLink;

    @FindBy(xpath="(//div[@class='rows-titles'][contains(text(),'需要家长在线提交')]//following-sibling::div[@class='row-content']//div[contains(@class,'van-radio__icon')])[1]")
    public WebElement btnOfNoOfSubmitOnline;

    @FindBy(xpath="(//div[@class='rows-titles'][contains(text(),'需要家长在线提交')]//following-sibling::div[@class='row-content']//div[contains(@class,'van-radio__icon')])[2]")
    public WebElement btnOfYesOfSubmitOnline;


    @FindBy(xpath="(//div[@class='rows-titles'][contains(text(),'是否允许学生互看作业')]//following-sibling::div[@class='row-content']//div[contains(@class,'van-radio__icon')])[1]")
    public WebElement btnOfNoOfViewHomeWork;

    @FindBy(xpath="(//div[@class='rows-titles'][contains(text(),'是否允许学生互看作业')]//following-sibling::div[@class='row-content']//div[contains(@class,'van-radio__icon')])[2]")
    public WebElement btnOfYesOfViewHomework;


@FindBy(xpath="//div[contains(@class,'van-pop')][not(contains(@style,'none'))]//button[@class='van-picker__confirm']")
public WebElement btnOfConfirmOnSelectEndDatePanel;



@FindBy(xpath="//div[@class='van-picker-column column1']//ul//li[contains(@class,'selected')]//div[@class='van-ellipsis']")
public WebElement selectedDayOnSelectEndTimePanel;

    @FindBy(xpath="//div[@class='van-picker-column column2']//ul//li[contains(@class,'selected')]//div[@class='van-ellipsis']")
    public WebElement selectedTimeOnSelectEndTimePanel;


@FindBy(xpath="//button[@class='base-btn'][contains(text(),'布置作业')]")
public WebElement btnOfArrangeHomework;

    public PublishHomeWorkPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public void selectDesiredSubject(String subject) throws Exception {
        functionUtil=new FunctionUtil(androidDriver);
        Thread.sleep(3000);
functionUtil.safeJavaScriptClick(androidDriver.findElementsByCssSelector("div.rows-content-wrap.rightArrow>div").get(0));
        clickAfterVisible(iconOfWholeSubject);
        functionUtil.selectDesiredValue(subject,listOfSubjectOptions,itemOfSelectedSubject);
        clickAfterVisible(btnOfConfirmOnSubjectPop);
        waitForElementVisible(textOfSelectedSubject,30);
        Assert.assertEquals(textOfSelectedSubject.getText().trim(),subject,"current selected subject shown is:"+textOfSelectedSubject.getText().trim()+"expected selected subject is:"+subject);

    }

    @Override
    public void verifyDisplayed() throws Exception {

    }


    public void fillInHomeworkTitle(String homeworkTitle) throws Exception {
        clickAfterVisible(inputOfHomeWorkTitle);
      inputOfHomeWorkTitle.sendKeys(homeworkTitle);
    }


    public void fillInHomeworkContent(String homeworkContent) throws Exception {
      clickAfterVisible(textareaOfHomeworkContent);
      textareaOfHomeworkContent.sendKeys(homeworkContent);
    }


    public void addVoice(int seconds,int piece) throws Exception {
        WechatNativePage wechatHomePage=new WechatNativePage(androidDriver);
        functionUtil=new FunctionUtil(androidDriver);

        for(int i=0;i<piece;i++) {
            FunctionUtil.clickAfterVisible(iconOfMicrophone);

            //switch to native view
            functionUtil.switchToNative();
            Thread.sleep(2000);
           wechatHomePage.iconOfStartRecordOnVocalPop.click();
            Thread.sleep(seconds * 1000);
            wechatHomePage.iconOfEndRecordOnVocalPop.click();
            wechatHomePage.btnOfConfirmOnEndingRecordPop.click();

            functionUtil.switchToDesiredWebView("WEBVIEW_com.tencent.mm:tools");
            Thread.sleep(2000);
            waitForListVisible(listOfUploadedPiecesOfVoices, 30);

            Assert.assertEquals(listOfUploadedPiecesOfVoices.size(),i+1, "actual uploaded voice piece is :" + listOfUploadedPiecesOfVoices.size());

                }

            }



    public void uploadImageOrVideo(String directorySourceFromPC, String[] desiredObjectNameArray,int flag) throws Exception {

        AndroidElement imageOrVideoElement;
        functionUtil = new FunctionUtil(androidDriver);
        WechatNativePage wechatHomePage = new WechatNativePage(androidDriver);

        int uploadedBlockSize;
        int uploadFailureSize;
        int actualUploadedObjectSize = 0;
        int alreadyUploadedCount=listOfUploadedBlockes.size();

        //Push file to device
        try {
            if(flag==0){
                androidDriver.pushFile("/sdcard/download/pictures", new File(directorySourceFromPC)); }else if(flag==1){
                androidDriver.pushFile("/sdcard/download/videos", new File(directorySourceFromPC));
            }


        } catch (IOException e) {
            System.out.println("push到手机的文件已经存在");
        }

        for (String s : desiredObjectNameArray) {
            if(flag==0) {
                FunctionUtil.clickAfterVisible(iconOfImage);
            }else if(flag==1){
                FunctionUtil.clickAfterVisible(iconOfvideo);
            }
            functionUtil.switchToNative();
            //点击文件
            wechatHomePage.btnOfFileOnSelectSourcePanel.click();

            if(flag==0) {
                wechatHomePage.selectDownloadFromDeviceRootCategory("pictures");
            }else if(flag==1){
                wechatHomePage.selectDownloadFromDeviceRootCategory("videos");
            }
          String imageOrVideoXpath="//android.widget.TextView[@text='" + s + "']";
            System.out.println("imageOrVideoXpath:"+imageOrVideoXpath);
           int c=0;
           while(androidDriver.findElementByXPath(imageOrVideoXpath).equals(null)&&c<5){
               SwipeClass.swipeToUp(androidDriver);
               c++;
           }
            androidDriver.findElementByXPath(imageOrVideoXpath).click();
            functionUtil.switchToDesiredWebView("WEBVIEW");
            Thread.sleep(3000);

            waitForListVisible(listOfUploadedBlockes, 30);
            uploadedBlockSize = listOfUploadedBlockes.size();
            uploadFailureSize = listOfUploadFailures.size();
            actualUploadedObjectSize = uploadedBlockSize - uploadFailureSize;


        }
        Assert.assertTrue(actualUploadedObjectSize == (desiredObjectNameArray.length+alreadyUploadedCount), "，之前上传了:"+alreadyUploadedCount+"张图片或视频，这次尝试上传了:" + desiredObjectNameArray.length + "张图片或视频,实际上传了:" + (actualUploadedObjectSize-alreadyUploadedCount) + "张图片或视频,总共已经上传:"+actualUploadedObjectSize);
    }

    public void uploadAttachment(String directorySourceFromPC, String[] desiredObjectNameArray) throws Exception {

        AndroidElement attachmentElement;
        functionUtil = new FunctionUtil(androidDriver);
        WechatNativePage wechatHomePage = new WechatNativePage(androidDriver);

        int actualUploadedObjectSize = 0;
        int alreadyUploadedCount=listOfUploadedAttachments.size();
        int uploadedAttachmentsSize=0;
        //Push file to device
        try {
            androidDriver.pushFile("/sdcard/download/attachments", new File(directorySourceFromPC));
        } catch (IOException e) {
            System.out.println("push到手机的文件已经存在");
        }

        for (String s : desiredObjectNameArray) {

            FunctionUtil.clickAfterVisible(iconOfFile);
            functionUtil.switchToNative();
            wechatHomePage.selectDownloadFromDeviceRootCategory("attachments");

            attachmentElement= androidDriver.findElementByXPath("//android.widget.TextView[@text='" + s + "']");

            int c=0;
            while(!isElementExist(attachmentElement)&&c<5){
                SwipeClass.swipeToUp(androidDriver);
                c++;
            }
            attachmentElement.click();


            Thread.sleep(3000);
            functionUtil.switchToDesiredWebView("WEBVIEW");

            waitForListVisible(listOfUploadedAttachments, 30);
            uploadedAttachmentsSize = listOfUploadedAttachments.size();

        }
        Assert.assertTrue(uploadedAttachmentsSize == (desiredObjectNameArray.length+alreadyUploadedCount), "，之前上传了:"+alreadyUploadedCount+"个附件，这次尝试上传了:" + desiredObjectNameArray.length + "个附件,实际上传了:" + (uploadedAttachmentsSize-alreadyUploadedCount) + "张图片或视频,总共已经上传:"+uploadedAttachmentsSize);

        Assert.assertEquals(listOfDeleteAttachments.size(),uploadedAttachmentsSize,"实际显示了"+listOfDeleteAttachments.size()+"个删除按钮");
    }

    public void uploadAttachmentNew( List<String> desiredObjectNameArray) throws Exception {
        AndroidElement fileElement;
        WechatNativePage wechatHomePage=new WechatNativePage(androidDriver);
        functionUtil=new FunctionUtil(androidDriver);
        List<String> actualUploadedAttachment=new ArrayList<String>();

        for(String s:desiredObjectNameArray) {
            FunctionUtil.clickAfterVisible(iconOfFile);
            functionUtil.switchToNative();

            wechatHomePage.selectDesiredFileNameManagementFromDeviceRootCategory(s);
            functionUtil.switchToDesiredWebView("WEBVIEW");
            waitForListVisible(listOfUploadedAttachments, 30);
            waitForListVisible(listOfDeleteAttachments, 30);
        }


        for(String fileName:FunctionUtil.getValuesFromWebElementList(listOfUploadedAttachments)){
            actualUploadedAttachment.add(fileName.split(":")[1].trim());

        }

       Assert.assertEquals(actualUploadedAttachment,desiredObjectNameArray,"已经上传的附件与要上传的不完全一致");
        Assert.assertEquals(actualUploadedAttachment.size(), listOfDeleteAttachments.size(),"已上传文件的文件名数量与删除附件数量不一致");

    }

    public void addLink(LinkedHashMap<String,String> map) throws Exception {
    Map.Entry<String, String> entry;
    List<String> keyList=new ArrayList<String>();
    List<String> addedLinkTextList;
    AddLinkPage addLinkPage=new AddLinkPage(androidDriver);
    Iterator maplit=map.entrySet().iterator();

    while(maplit.hasNext()) {
        entry = (Map.Entry<String, String>) maplit.next();
        //点击链接标志
        FunctionUtil.clickAfterVisible(iconOfLink);
        addLinkPage.verifyDisplayed();
        keyList.add(entry.getKey());
        addLinkPage.setLinkContent(entry.getKey(),entry.getValue());

        waitForListVisible(listOfImagesOfLinkIcons,30);
        waitForListVisible(listOfLinkTexts,30);
        waitForListVisible(listOfDeleteLink,30);

    }

    Assert.assertTrue(listOfImagesOfLinkIcons.size()==listOfLinkTexts.size()&&listOfLinkTexts.size()==listOfDeleteLink.size(),"已上传的链接图标与链接名称、删除链接按钮的数量不匹配");

    addedLinkTextList=FunctionUtil.getValuesFromWebElementList(listOfLinkTexts);

    Assert.assertEquals(addedLinkTextList,keyList,"实际添加的链接名与预期添加的不符合");


}




    public void selectPublishObjectToClass(String[] classes) throws Exception {
        ConfigureClassesPage configureClassesPage=new ConfigureClassesPage(androidDriver);
        clickSelectPublishObject();
        configureClassesPage.verifyDisplayed();
        configureClassesPage.chooseClass(classes);
        verifyOjectIsSelected();

    }


    public Set<String> selectPublishObjectToGroup(int n) throws Exception {

        ConfigureClassesPage configureClassesPage=new ConfigureClassesPage(androidDriver);
        clickSelectPublishObject();
        configureClassesPage.verifyDisplayed();
        Set<String> selectedStudentIngroup=configureClassesPage.chooseGroup(n);
        verifyOjectIsSelected();
        return selectedStudentIngroup;

    }

    private void clickSelectPublishObject() {
        //点击选择发布对象
        WebElement iconOfSelectObject=androidDriver.findElementsByCssSelector("div.rows-content-wrap.bottomLine.rightArrow>div").get(1);

        ((JavascriptExecutor)androidDriver).executeScript("arguments[0].click();", iconOfSelectObject);
    }

    private void verifyOjectIsSelected() {

WebElement selectedObject=androidDriver.findElement(By.xpath("//div[@class='rows-titles'][contains(text(),' 选择发布对象')]//following-sibling::div"));

String[] selectedObjectArray=selectedObject.getText().trim().split("、");

Assert.assertTrue(selectedObjectArray.length!=0,"没有选中的发布对象");


    }


public void selectSubmitOnlineOrOffline(Boolean submitStatus){

    if(submitStatus&&(!btnOfYesOfSubmitOnline.getAttribute("class").contains("checked"))){
        btnOfYesOfSubmitOnline.click();
    }else if((!submitStatus)&&(!btnOfNoOfSubmitOnline.getAttribute("class").contains("checked"))){
      btnOfNoOfSubmitOnline.click();

    }

}


public String selectEndDate(String date) throws Exception {

        System.out.println("sssssssssssssssssssssssssssssss:"+androidDriver.findElementsByCssSelector("div.rows-content-wrap.bottomLine.rightArrow>div").size());

    WebElement iconOfSelectEndDate=androidDriver.findElementsByCssSelector("div.rows-content-wrap.bottomLine.rightArrow>div").get(3);

    ((JavascriptExecutor)androidDriver).executeScript("arguments[0].click();", iconOfSelectEndDate);

    //选择一周后的日期，时间点
    int count=0;
    while((!selectedDayOnSelectEndTimePanel.getText().trim().equals(date))&&count<20) {
        SwipeClass.swipeToUpFreedom(androidDriver, 0.8, 0.75, 4);
        count++;
    }

    String selectTime=selectedTimeOnSelectEndTimePanel.getText().trim();
      btnOfConfirmOnSelectEndDatePanel.click();
    String expectedSelectedDateStr=date+" "+selectTime;
    verifyEndTimeAddSuccessfully(expectedSelectedDateStr);
return expectedSelectedDateStr;
}

    private void verifyEndTimeAddSuccessfully(String expectedSelectedDateStr) {

            WebElement selectedDate=androidDriver.findElement(By.xpath("//div[@class='rows-titles'][contains(text(),' 选择截止时间')]//following-sibling::div//span"));

            String sdAfterSelectEndTime=selectedDate.getText().trim();
Assert.assertEquals(sdAfterSelectEndTime,expectedSelectedDateStr,"expected select date&time is :"+expectedSelectedDateStr+"actual selected date is :"+sdAfterSelectEndTime);


        }


    public void selectAllowViewHomeWork(Boolean viewStatus){

        if(viewStatus&&(!btnOfYesOfViewHomework.getAttribute("class").contains("checked"))){
            btnOfYesOfSubmitOnline.click();
        }else if((!viewStatus)&&(!btnOfNoOfViewHomeWork.getAttribute("class").contains("checked"))){
            btnOfNoOfSubmitOnline.click();

        }

    }


}
