package com.tuners.mobile.testcase.parents.wechat.homework;
import com.tuners.mobile.BasePages.Parents.HomePage;
import com.tuners.mobile.BasePages.Parents.HomeworkManagement;
import com.tuners.mobile.BasePages.Parents.HomeWorkDetailPage;
import com.tuners.mobile.BasePages.Parents.SubmitHomeWorkPage;
import com.tuners.mobile.BasePages.WechatNativePage;
import com.tuners.mobile.util.Bases.WechatBase;
import com.tuners.mobile.testcase.teacher.wechat.homework.publishHomework.TC01_发布作业文字语音视频文件链接到一个常用班级允许互看;
import com.tuners.mobile.util.Functions.FunctionUtil;
import com.tuners.mobile.util.PageObject;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;

/**
 * @Author Mason
 * @Description
 * @Date 2020/6/24 11:50
 **/
public class TC01_提交需要在线提交的作业 extends WechatBase {
    public String homeworkTitle;
    public LinkedHashMap<String,Object> homeworkInfo;


   // @Test
    public void verify_TC01_提交需要在线提交的作业() throws Exception {
       TC01_发布作业文字语音视频文件链接到一个常用班级允许互看 tc=new TC01_发布作业文字语音视频文件链接到一个常用班级允许互看();
       tc.verifyTC01_TeacherPublishHomework();
       homeworkInfo=tc.homeworkInfo;
       homeworkTitle=tc.homeworkTitle;
       System.out.println("homeworkTitle from teacher is :"+homeworkTitle);


       //关闭老师端、打开家长端
        afterEachTestMethod();

       //初始化家长端页面
        HomePage homePage=new HomePage(androidDriver);
        HomeworkManagement homeworkManagement=new HomeworkManagement(androidDriver);
        HomeWorkDetailPage homeWorkDetailsPage=new HomeWorkDetailPage(androidDriver);
        WechatNativePage wechatNativePage=new WechatNativePage(androidDriver);

        //登录家长的微信账号进入书僮智慧校园
        wechatNativePage.searchAndEnterForPublicAccountForParents(ENVFLAG);

        //作业->作业管理 未提交列表,验证作业管理的作业内容
        homePage.enterHomeWorkManagementPage();
        homeworkManagement.verifyHomeWorkInfo(homeworkInfo,true);

        int index= FunctionUtil.getIndexOfDesiredElementInList(homeworkManagement.listOfHomeworkTitles,homeworkTitle);

        //点击选择该作业
        homeworkManagement.listOfHomeworkTitles.get(index).click();
        homeWorkDetailsPage.verifyDisplayed();
        homeWorkDetailsPage.verifyHomeWorkDetailsInDetailsPage(homeworkInfo,true);




    }

    @Test
    public void submit() throws Exception {
        //初始化家长端页面
        HomePage homePage=new HomePage(androidDriver);
        HomeworkManagement homeworkManagement=new HomeworkManagement(androidDriver);
        HomeWorkDetailPage homeWorkDetailsPage=new HomeWorkDetailPage(androidDriver);
        SubmitHomeWorkPage submitHomeWorkPage=new SubmitHomeWorkPage(androidDriver);
        //登录家长的微信账号进入书僮智慧校园

        //作业->作业管理
        homePage.enterHomeWorkManagementPage();
        homeworkManagement.verifyDisplayed();


        //未提交列表，选择该作业
        PageObject.waitForListVisible(homeworkManagement.listOfHomeworkTitles,30);
        int index= FunctionUtil.getIndexOfDesiredElementInList(homeworkManagement.listOfHomeworkTitles,"自动化语文1593669220501");

        homeworkManagement.listOfHomeworkTitles.get(index).click();
        homeWorkDetailsPage.verifyDisplayed();

        //收集作业详情该作业的信息
        LinkedHashMap<String,Object> homeworkInfoOnHDP=homeWorkDetailsPage.collectHomeWorkInfoOnHDP();

        //点击提交按钮
        FunctionUtil.clickAfterVisible(homeWorkDetailsPage.btnOfSubmitHomework);
        submitHomeWorkPage.verifyDisplayed();

        //收集该作业提交作业界面的信息
        LinkedHashMap<String,Object> homeworkInfoOnSHP=submitHomeWorkPage.collectHomeWorkInfoOnSHP();

        submitHomeWorkPage.submitContent();

    }
}
