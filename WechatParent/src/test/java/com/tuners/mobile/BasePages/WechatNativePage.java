package com.tuners.mobile.BasePages;

import com.tuners.mobile.testdata.CommonTestData;
import com.tuners.mobile.util.Functions.FunctionUtil;
import com.tuners.mobile.util.PageObject;
import com.tuners.mobile.util.Functions.SwipeClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * @Author Mason
 * @Description
 * @Date 2020/6/17 10:03
 **/
public class WechatNativePage extends PageObject {
    public WechatNativePage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    @Override
    public void verifyDisplayed() throws Exception {

    }

    @AndroidFindBy(id="com.tencent.mm:id/f8y")
    public AndroidElement iconOfSearchBar;

    @AndroidFindBy(id="com.tencent.mm:id/bhn")
    public AndroidElement editTextOfSearch;

    @AndroidFindBy(id="com.tencent.mm:id/gbv")
    public AndroidElement resultOfSearch;

    @AndroidFindBy(xpath="//android.widget.TextView[@text='我是老师']")
    public AndroidElement teacherEntrance;

    @AndroidFindBy(xpath="//android.widget.TextView[@text='我是家长']")
    public AndroidElement parentsEntrance;


    @AndroidFindBy(xpath="//android.widget.TextView[@text='书僮教育']")
    public AndroidElement shutongWebEntranceInProdTeacher;

    @AndroidFindBy(xpath="//android.widget.TextView[@text='我的书僮']")
    public AndroidElement myshutongEntranceInProdParents;


    //微信原生每个页面的头部元素
    @AndroidFindBy(id="com.tencent.mm:id/dn")
    public AndroidElement iconOfCloseOnTop;

    //点击录音后弹窗上的录制开始按钮
    @AndroidFindBy(xpath="//android.widget.Image[@text='AUbB8B60umrpAAAAAElFTkSuQmCC']")
    public AndroidElement iconOfStartRecordOnVocalPop;

    //点击录音后弹窗上的录制结束按钮
    @AndroidFindBy(xpath="//android.widget.Image[@index='1']")
    public AndroidElement iconOfEndRecordOnVocalPop;

    //左边菜单
    @AndroidFindBy(xpath="//android.widget.ImageButton[@index='0']")
    public AndroidElement iconOfRootMenu;

    //是否允许录音的是
    @AndroidFindBy(id="com.tencent.mm:id/doz")
    public AndroidElement btnOfYesOnRecordAllowPop;

   //微信申请以下权限  允许弹窗
    @AndroidFindBy(id="com.android.packageinstaller:id/permission_allow_button")
    public AndroidElement btnOfAllowOnMicrophonePrivilegePop;



    //录制完的确定按钮
    @AndroidFindBy(xpath="//android.view.View[@text='确定']")
    public AndroidElement btnOfConfirmOnEndingRecordPop;


    //选择图片后出现得文件选项
    @AndroidFindBy(xpath="//android.widget.TextView[@text='文件']")
    public AndroidElement btnOfFileOnSelectSourcePanel;

    //下载
    @AndroidFindBy(xpath="//android.widget.TextView[@text='下载']")
    public AndroidElement categoryOfDownloadsInRootDirectory;

    //文件管理
    @AndroidFindBy(xpath="//android.widget.TextView[@text='文件管理']")
    public AndroidElement categoryOfFileManagementInRootDirectory;

    //pictures 目录
    @AndroidFindBy(xpath="//android.widget.TextView[@text='pictures']")
    public AndroidElement categoryOfPicturesInRootDirectory;


    @AndroidFindBy(id="com.android.documentsui:id/option_menu_search")
    public AndroidElement iconOfSearchInRootDirectory;


    @AndroidFindBy(id="android:id/search_src_text")
    public AndroidElement editTextOfSearchInRootDirectory;

    @AndroidFindBy(xpath="//android.widget.TextView[@text='文档']")
    public AndroidElement menuOfFileFolder;


    @AndroidFindBy(id="com.tencent.mm:id/dn")
    public AndroidElement iconOfReturnAndClose;


    @AndroidFindBy(id="com.tencent.mm:id/rs")
    public AndroidElement iconOfReturn;


    @AndroidFindBy(id="com.tencent.mm:id/aay")
    public AndroidElement iconOfCancel;

    public void selectDownloadFromDeviceRootCategory(String folder) throws InterruptedException {

        //点击显示根目录图标
        Thread.sleep(2000);
        iconOfRootMenu.click();

        //点击 下载目录
        categoryOfDownloadsInRootDirectory.click();

        //点击已经上传到下载的文件夹
        String desiredFolderXpath="//android.widget.TextView[@text='"+folder+"']";

        androidDriver.findElementByXPath(desiredFolderXpath).click();
    }


    public void selectDesiredFileNameManagementFromDeviceRootCategory(String fileName) throws InterruptedException {
        AndroidElement fileElement;
        //点击显示根目录图标
        iconOfRootMenu.click();

        //点击 文件管理
       categoryOfFileManagementInRootDirectory.click();

        //点击文档
        menuOfFileFolder.click();

        //滑动点击文件
 fileElement=androidDriver.findElementByXPath("//android.widget.TextView[@text='"+fileName+"']");
int count=0;
 while(!isElementExist(fileElement)&&count<20){
     SwipeClass.swipeToUp(androidDriver);
     count++;
 }

 fileElement.click();
Thread.sleep(2000);
    }





    public  void searchForConcreteFile(String fileName){
        //搜索
        iconOfSearchInRootDirectory.click();
        editTextOfSearchInRootDirectory.click();
        editTextOfSearchInRootDirectory.sendKeys(fileName);

    }

    public void  searchAndEnterForPublicAccountForTeacher(int env) throws Exception {
        FunctionUtil functionUtil=new FunctionUtil(androidDriver);

//点击公众号
      iconOfSearchBar.click();
      Thread.sleep(1000);
      editTextOfSearch.click();

        if(env==0) {
            editTextOfSearch.sendKeys(CommonTestData.PREPUBLICACCOUNTFORTEACHER);
            FunctionUtil.clickAfterVisible(resultOfSearch);
            FunctionUtil.clickAfterVisible(teacherEntrance);

        }else if(env==1){
            editTextOfSearch.sendKeys(CommonTestData.PRODPUBLICACCOUNTFORTEACHER);
            FunctionUtil.clickAfterVisible(resultOfSearch);
            FunctionUtil.clickAfterVisible(shutongWebEntranceInProdTeacher);
        }

        Thread.sleep(6000);
        functionUtil.switchToDesiredWebView("WEBVIEW");


    }


    public void  searchAndEnterForPublicAccountForParents(int env) throws Exception {
        FunctionUtil functionUtil=new FunctionUtil(androidDriver);


//点击公众号
        FunctionUtil.clickAfterVisible(iconOfSearchBar);
        FunctionUtil.clickAfterVisible(editTextOfSearch);

        if(env==0) {
            editTextOfSearch.sendKeys(CommonTestData.PREPUBLICACCOUNTFORPARENTS);
            FunctionUtil.clickAfterVisible(resultOfSearch);
            FunctionUtil.clickAfterVisible(parentsEntrance);

        }else if(env==1){
            editTextOfSearch.sendKeys(CommonTestData.PRODPUBLICACCOUNTFORPARENTS);
            FunctionUtil.clickAfterVisible(resultOfSearch);
            FunctionUtil.clickAfterVisible(myshutongEntranceInProdParents);
        }

        functionUtil.switchToDesiredWebView("WEBVIEW");


    }


}
