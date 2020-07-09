package com.tuners.mobile.testcase.teacher.wechat.homework.publishHomework;

import com.tuners.mobile.BasePages.Teacher.ConfigureClassesPage;
import com.tuners.mobile.BasePages.Teacher.HomePage;
import com.tuners.mobile.BasePages.Teacher.HomeWorkDetailsPage;
import com.tuners.mobile.BasePages.Teacher.HomeworkManagementPage;
import com.tuners.mobile.BasePages.Teacher.PublishHomeWorkPage;
import com.tuners.mobile.BasePages.WechatNativePage;
import com.tuners.mobile.util.Bases.WechatBase;
import com.tuners.mobile.testdata.CommonTestData;
import com.tuners.mobile.util.Functions.FunctionUtil;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * @Author Mason
 * @Description
 * @Date 2020/6/24 10:58
 **/
public class TC03_发布作业到多个常用班级无需提交 extends WechatBase {

    @Test
    public void testTC03() throws Exception {
        HomeworkManagementPage homeworkManagementPage=new HomeworkManagementPage(androidDriver);
        HomePage homePage=new HomePage(androidDriver);
        PublishHomeWorkPage publishHomeWorkPage=new PublishHomeWorkPage(androidDriver);
        HomeWorkDetailsPage homeWorkDetailsPage=new HomeWorkDetailsPage(androidDriver);
        ConfigureClassesPage configureClassesPage=new ConfigureClassesPage(androidDriver);
        WechatNativePage wechatHomePage=new WechatNativePage(androidDriver);

        //pre-condition
        //点击更多作业进入作业管理
        wechatHomePage.searchAndEnterForPublicAccountForTeacher(ENVFLAG);
        homePage.enterHomeWorkManagementPage();
        homeworkManagementPage.verifyDisplayed();
        //点击发布作业按钮到发布作业界面
        homeworkManagementPage.clickPublishButton();

        //step1:选择科目
        String homeworkSubject="语文";
        publishHomeWorkPage.selectDesiredSubject(homeworkSubject);

        //step2:填写作业标题
        long timeStamp=System.currentTimeMillis();
        String homeworkTitle="自动化"+homeworkSubject+timeStamp;
        publishHomeWorkPage.fillInHomeworkTitle(homeworkTitle);


        //step3:输入作业内容
        String homeworkContent="作业内容"+timeStamp;
        publishHomeWorkPage.fillInHomeworkContent(homeworkContent);


        //step4:添加图片
        String[] desiredImageNameArray={"1.jpg","2.jpg"};
        publishHomeWorkPage.uploadImageOrVideo(CommonTestData.PICTURESOURCEFROMPC,desiredImageNameArray,0);

        //收集图片存放的地址
        List<String> listOfUploaedImageAndVideoSrcs= FunctionUtil.getAttributesFromWebElementList("data-src",publishHomeWorkPage.listOfUploadedBlockes);

        //step5:选择发布对象
        String[] desiredClasses={"一年级(1)班","二年级(1)班"};
        Set<String> gradeList=configureClassesPage.collectGradesToSet(desiredClasses);
        List<String> gdList=new ArrayList(gradeList);
        publishHomeWorkPage.selectPublishObjectToClass(desiredClasses);


        //step6:选择无需要在线提交
        publishHomeWorkPage.selectSubmitOnlineOrOffline(false);

        //发布前收集作业信息
        LinkedHashMap<String,Object> homeworkInfo=new LinkedHashMap<String,Object>();

        homeworkInfo.put("subject",homeworkSubject);
        homeworkInfo.put("title",homeworkTitle);
        homeworkInfo.put("content",homeworkContent);homeworkInfo.put("imageAndVideoSize",desiredImageNameArray.length);
        homeworkInfo.put("imageAndVideoSrc",listOfUploaedImageAndVideoSrcs);

        homeworkInfo.put("publishObject", Arrays.asList(desiredClasses));
        homeworkInfo.put("publishToGrade",gdList);





        //step7:布置作业
        publishHomeWorkPage.btnOfArrangeHomework.click();
        homeworkManagementPage.verifyDisplayed();
        homeworkManagementPage.verifyPublishedHomeWorkSection(false);

        //step8:验证作业详情页面
        int i = FunctionUtil.getIndexOfDesiredElementInList(homeworkManagementPage.listOfPublishedHomeworkList,homeworkTitle);
        homeworkManagementPage.listOfPublishedHomeworkList.get(i).click();
        homeWorkDetailsPage.verifyDisplayed();
        homeWorkDetailsPage.verifyHomeWorkDetailsInDetailsPage(homeworkInfo,false);


    }
}
