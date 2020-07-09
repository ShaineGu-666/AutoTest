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

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * @Author Mason
 * @Description
 * @Date 2020/6/22 10:39
 **/
public class HomeWorkDetailsPage extends PageObject {
    public HomeWorkDetailsPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    @Override
    public void verifyDisplayed() throws Exception {
    waitForPageLoadComplete();

    //复制发布链接、添加讲解
        waitForElementVisible(linkOfCopyToPublish,30);
        waitForElementVisible(linkOfAddReference,30);


    }


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
    @FindBy(xpath="//li[@class='record b-box']//div")
    public List<WebElement> listOfHomeworkVoicePieces;

    //视频及语音
    @FindBy(xpath="//div[@class='van-image']//img")
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
    @FindBy(xpath="//span[@class='date-wrap']")
    public WebElement textOfSubmitEndTime;

    //添加讲解
    @FindBy(xpath="//span[contains(text(),'添加讲解')]")
    public WebElement linkOfAddReference;


    @FindBy(xpath="//section[@class='bff detail-list']//div[@class='row-wrap']//div[@class='con-titles']")
    public List<WebElement> listOfPublishToClassesNames;


    //修改作业
    @FindBy(xpath="//div[@class='operate']//span[contains(text(),'修改作业')]")
    public WebElement linkOfModifyHomework;


    //复制发布
    @FindBy(xpath="//div[@class='operate']//span[contains(text(),'复制发布')]")
    public WebElement linkOfCopyToPublish;

    //完成度
    @FindBy(xpath="//div[@class='con-desc']")
    public List<WebElement> listOfPublishToClasseCompleteRate;

    //花的图标
    @FindBy(xpath="//div[@class='right-content']//img")
    public List<WebElement> listOfPublishToClasseFlowers;




    public void verifyHomeWorkDetailsInDetailsPage(LinkedHashMap homeworkInfo,Boolean submitstatus) throws Exception {

        //作业标题
        String expectedTitle = ReusableFunctions.getHomeworkTitleFromStorage(homeworkInfo);
        verifyHomeWorkTitle(expectedTitle);
        //作业内容
        String expectedHomeworkContent =ReusableFunctions.getHomeworkContentFromStorage(homeworkInfo);
        verifyHomeworkContent(expectedHomeworkContent);

        //语音条数
        if (FunctionUtil.verifyWhetherKeyExistInMap("voiceCount", homeworkInfo)) {
            int expectedVoiceCount = ReusableFunctions.getHomeworkVoiceTotalFromStorage(homeworkInfo);
            veirfyVoiceCount(expectedVoiceCount);
        }

        //图片视频
        if (FunctionUtil.verifyWhetherKeyExistInMap("imageAndVideoSize", homeworkInfo)) {
            int expectedImageAndVideoSize = ReusableFunctions.getHomeworkImageAndVideoSizeFromStorage(homeworkInfo);
            List<String> listOfExpectedMediaSoures =ReusableFunctions.getHomeworkImageAndVideoSrcsFromStorage(homeworkInfo);

            verifyImageVideoSection(expectedImageAndVideoSize, listOfExpectedMediaSoures);

        }
        //文件
        if (FunctionUtil.verifyWhetherKeyExistInMap("attachments", homeworkInfo)) {
            List<String> expectedAttachmentsList = ReusableFunctions.getHomeworkAttachmentsFromStorage(homeworkInfo);
            expectedAttachmentsList.forEach(System.out::println);
            verifyAttachments(expectedAttachmentsList);
        }

        //链接
        if (FunctionUtil.verifyWhetherKeyExistInMap("linkMap", homeworkInfo)) {
            LinkedHashMap<String, String> linkMap1 = ReusableFunctions.getHomeworkLinkMapFromStorage(homeworkInfo);
            Set<String> linkNameList = linkMap1.keySet();
            verifyLinkSection(linkNameList);
        }

        //截止日期
        if (submitstatus) {
            String expectedHomeworkEndTime =ReusableFunctions.getHomeworkEndTimeFromStorage(homeworkInfo);
            verifyEndTime(expectedHomeworkEndTime);

            //完成度 班级
            List<String> expectedPublishedToClasses = ReusableFunctions.getHomeworkPublishedObjectFromStorage(homeworkInfo);
            verifyCompleteStatusSection(expectedPublishedToClasses);


            //修改作业
            Assert.assertTrue(linkOfModifyHomework.isDisplayed(),"修改作业没有显示");
        }
        //发布到的年级(若直到预期发布到的班级，验证班级)
        if(FunctionUtil.verifyWhetherKeyExistInMap("publishToGrade",homeworkInfo)) {
            List<String> expectedGdList =ReusableFunctions.getHomeworkPublishedGradeFromStorage(homeworkInfo);
            verifyPublishToGrade(expectedGdList);
        }

        //若发布到分组，验证具体的发布对象
        if(FunctionUtil.verifyWhetherKeyExistInMap("publishToStudentsList",homeworkInfo)) {
List<String> expectedStudentList= ReusableFunctions.getHomeworkPublishedStudentsFromStorage(homeworkInfo);

            Collections.sort(expectedStudentList, (a, z) -> a.compareTo(z));
            verifyConcretePublishedStudents(expectedStudentList,submitstatus);
        }

    }

    private void verifyConcretePublishedStudents(List<String> expectedStudentList,Boolean submitStatus) throws Exception {

        List<String> actualPublishedStudent =new ArrayList<String>();
    //点击每个班级的完成情况，获取所有学生
CompleteStatusPage completeStatusPage=new CompleteStatusPage(androidDriver);

Thread.sleep(6000);
        //箭头与班级数一致
        List<AndroidElement> listOfArrowsOfClasses = androidDriver.findElements(By.cssSelector("div.rows-content-wrap.rightArrow.pr"));

        System.out.println("listOfPublishToClassesNames size:"+listOfPublishToClassesNames.size());
//箭头定位不了先点击花朵
      for(WebElement e:listOfPublishToClassesNames){
          e.click();
          Thread.sleep(5000);
          actualPublishedStudent.addAll(completeStatusPage.collectWholePublishedStudentName(submitStatus));
          androidDriver.navigate().back();
          waitForPageLoadComplete();
          Thread.sleep(5000);

      }

        Collections.sort( actualPublishedStudent, (a, z) -> a.compareTo(z));


      for(String s1:actualPublishedStudent){
          System.out.println("S1:::::::::"+s1);
      }

        for(String s2:expectedStudentList){
            System.out.println("S2:::::::::"+s2);
        }

      Assert.assertEquals(actualPublishedStudent,expectedStudentList,"实际的发布对象与预期的不符合");


    }

    private void verifyHomeworkContent (String expectedHomeworkContent){
            String actualHomeworkContent = textOfHomeWorkContent.getText().trim();
            Assert.assertEquals(actualHomeworkContent, expectedHomeworkContent, "预期的作业内容是:" + expectedHomeworkContent + ",实际获取到的内容是:" + actualHomeworkContent);
        }


    public void verifyHomeWorkTitle(String expectedTitle){

       String actualTitle=titleOfHomeWork.getText().trim();
       Assert.assertEquals(actualTitle,expectedTitle,"expected homework title is:"+expectedTitle+"actual homework title is:"+actualTitle);

    }

    public void verifyEndTime(String expectedHomeworkEndTime){

        String actualHomeworkEndTime=textOfSubmitEndTime.getText().trim().split("：")[1].trim();
        Assert.assertEquals(actualHomeworkEndTime, expectedHomeworkEndTime, "预期的作业截止日期是:" + expectedHomeworkEndTime + ",实际获取到的截止日期是:" + actualHomeworkEndTime);
    }


    public void verifyImageVideoSection(int expectedImageAndVideoSize,List<String> listOfExpectedMediaSoures){

     int actualImageAndVideoSize=listOfUploadedImagesOrvideos.size();
     List<String> listOfActualDisplayedImageOrVideoSrcs = FunctionUtil.getAttributesFromWebElementList("data-src",listOfUploadedImagesOrvideos);

        Assert.assertEquals(listOfActualDisplayedImageOrVideoSrcs,listOfExpectedMediaSoures,"作业详情页的图片视频与预期不符合");



    }

    public void verifyAttachments(List<String> expectedAttachmentsList){

List<String> actualAttachmentsNameList=FunctionUtil.getValuesFromWebElementList(listOfUploadedFileNames);
Assert.assertEquals(actualAttachmentsNameList,expectedAttachmentsList,"上传的文件名称与预期不符合");

Assert.assertTrue(actualAttachmentsNameList.size()==listOfUploadedFileIcons.size(),"上传的文件名数量:"+actualAttachmentsNameList.size()+"文件标志数量:"+listOfUploadedFileIcons.size());

        Assert.assertTrue(listOfUploadedFileIcons.size()==listOfUploadedFileCopyLinks.size(),"上传的文件标志数量:"+actualAttachmentsNameList.size()+"文件复制链接数量:"+listOfUploadedFileCopyLinks.size());

}

    public void veirfyVoiceCount(int expectedVoiceCount){

      int actualVoiceCount=listOfHomeworkVoicePieces.size();
      Assert.assertEquals(actualVoiceCount,expectedVoiceCount,"预期的语音上传条数为:"+expectedVoiceCount+"实际的语音上传条数为:"+actualVoiceCount);


    }

    public void verifyLinkSection(Set<String> expectedlinkNameList) throws Exception {

        waitForListVisible(listOfUploadedLinkNames,30);
        List<String> actualLinkNamesList=FunctionUtil.getValuesFromWebElementList(listOfUploadedLinkNames);

       Assert.assertEquals(actualLinkNamesList,expectedlinkNameList,"链接名称集合预期与实际的不符合");

Assert.assertTrue(actualLinkNamesList.size()==listOfUploadedLinkIcons.size(),"链接的标志数量为:"+listOfUploadedLinkIcons.size()+"链接名称数量为："+actualLinkNamesList.size());
        Assert.assertTrue(listOfUploadedLinkIcons.size()==listOfUploadedLinkCopyLinks.size(),"链接的标志数量为:"+listOfUploadedLinkIcons.size()+"链接复制链接数量为："+listOfUploadedLinkCopyLinks.size());
    }

    public void verifyCompleteStatusSection(List<String> expectedPublishedClasses) throws Exception {

            waitForListVisible(listOfPublishToClassesNames, 30);
            List<String> actualPublishedClasseslist = FunctionUtil.getValuesFromWebElementList(listOfPublishToClassesNames);

            for (String s : actualPublishedClasseslist) {

                System.out.println("actualPublishClass:" + s);
            }

            for (String s : expectedPublishedClasses) {

                System.out.println("expectedPublishedClasses:" + s);
            }


            Assert.assertEquals(actualPublishedClasseslist, expectedPublishedClasses, "实际发布的班级与预期不符");

            //箭头与班级数一致
//            List<AndroidElement> listOfArrowsOfClasses = androidDriver.findElements(By.cssSelector("div.rows-content-wrap.bottomLine.rightArrow.pr>div"));

//            System.out.println("~~~~~~~~~~:"+listOfArrowsOfClasses.size());


//            Assert.assertEquals(listOfArrowsOfClasses.size(), expectedPublishedClasses.size(), "右侧箭头的数量为:" + listOfArrowsOfClasses.size() + "预期应有:" + expectedPublishedClasses.size());
//
//            Assert.assertEquals(listOfArrowsOfClasses.size(), expectedPublishedClasses.size(), "右侧箭头的数量为:" + listOfArrowsOfClasses.size() + "预期应有:" + expectedPublishedClasses.size());

            Assert.assertEquals(listOfPublishToClasseCompleteRate.size(), expectedPublishedClasses.size(), "完成度的数量为:" + listOfPublishToClasseCompleteRate.size() + "预期应有:" + expectedPublishedClasses.size());

            Assert.assertEquals(listOfPublishToClasseFlowers.size(), expectedPublishedClasses.size(), "小花的数量为:" + listOfPublishToClasseFlowers.size() + "预期应有:" + expectedPublishedClasses.size());
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

    }
