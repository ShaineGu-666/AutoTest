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
 * @Date 2020/6/24 11:25
 **/
public class TC04_发布作业到一个常用班级在线提交不允许互看 extends WechatBase {

String homeworkTitle;
    LinkedHashMap<String,Object> homeworkInfo;

    @Test
    public void testTC04() throws Exception {
        HomeworkManagementPage homeworkManagementPage=new HomeworkManagementPage(androidDriver);
        HomePage homePage=new HomePage(androidDriver);
        PublishHomeWorkPage publishHomeWorkPage=new PublishHomeWorkPage(androidDriver);
        HomeWorkDetailsPage homeWorkDetailsPage=new HomeWorkDetailsPage(androidDriver);
        WechatNativePage wechatHomePage=new WechatNativePage(androidDriver);


        //点击更多作业进入作业管理
        wechatHomePage.searchAndEnterForPublicAccountForTeacher(ENVFLAG);
        homePage.enterHomeWorkManagementPage();
        homeworkManagementPage.verifyDisplayed();
        //点击发布作业按钮到发布作业界面
        homeworkManagementPage.clickPublishButton();

        //step1:选择科目
        String homeworkSubject="历史";
        publishHomeWorkPage.selectDesiredSubject(homeworkSubject);

        //step2:填写作业标题
        long timeStamp=System.currentTimeMillis();
        String homeworkTitle="自动化"+homeworkSubject+timeStamp;
        publishHomeWorkPage.fillInHomeworkTitle(homeworkTitle);


        //step3:输入作业内容
        String homeworkContent="作业内容"+timeStamp;
        publishHomeWorkPage.fillInHomeworkContent(homeworkContent);


        //step4:图片、视频

        //图片
        String[] desiredImageNameArray={"1.jpg","2.jpg"};
        publishHomeWorkPage.uploadImageOrVideo(CommonTestData.PICTURESOURCEFROMPC,desiredImageNameArray,0);


        //视频
        String[] desiredVideoNameArray={"1.mp4","2.mp4"};

        publishHomeWorkPage.uploadImageOrVideo(CommonTestData.VIDEOSOURCEFROMPC,desiredVideoNameArray,1);


        //收集视频与图片的存放地址

        List<String> listOfUploaedImageAndVideoSrcs= FunctionUtil.getAttributesFromWebElementList("data-src",publishHomeWorkPage.listOfUploadedBlockes);



        //选择发布对象
        String[] desiredClasses={"一年级(1)班"};
        ConfigureClassesPage configureClassesPage=new ConfigureClassesPage(androidDriver);
        Set<String> gradeList=configureClassesPage.collectGradesToSet(desiredClasses);
        List<String> gdList=new ArrayList(gradeList);
        publishHomeWorkPage.selectPublishObjectToClass(desiredClasses);



        //选择是否需要在线提交
        publishHomeWorkPage.selectSubmitOnlineOrOffline(true);



        //选择截止日期
        String dateStr=FunctionUtil.getAfterDayDate("7", "M月d日 HH:mm");

//选择一周后的日期
        String date=dateStr.split(" ")[0];
        String homeworkEndTime=publishHomeWorkPage.selectEndDate(date);


//是否允许学生互看作业
        publishHomeWorkPage.selectAllowViewHomeWork(false);

        //布置作业
        Thread.sleep(2000);
        publishHomeWorkPage.btnOfArrangeHomework.click();
        homeworkManagementPage.verifyDisplayed();



        homeworkInfo=new LinkedHashMap<String,Object>();
        homeworkInfo.put("subject",homeworkSubject);
        homeworkInfo.put("title",homeworkTitle);
        homeworkInfo.put("content",homeworkContent);
        homeworkInfo.put("endTime",homeworkEndTime);
      homeworkInfo.put("imageAndVideoSize",desiredImageNameArray.length+desiredVideoNameArray.length);
     homeworkInfo.put("imageAndVideoSrc",listOfUploaedImageAndVideoSrcs);
        homeworkInfo.put("publishObject", Arrays.asList(desiredClasses));
        homeworkInfo.put("publishToGrade",gdList);



        //布置完作业到作业管理验证基础的个性信息
        int i = FunctionUtil.getIndexOfDesiredElementInList(homeworkManagementPage.listOfPublishedHomeworkList,homeworkTitle);
        System.out.println("i is:"+ i);
        homeworkManagementPage.verifyDetailedPublishedHomeworkContent(homeworkInfo,true);


        //点击作业进入作业详情页面
        homeworkManagementPage.listOfPublishedHomeworkList.get(i).click();
        homeWorkDetailsPage.verifyDisplayed();
        homeWorkDetailsPage.verifyHomeWorkDetailsInDetailsPage(homeworkInfo,true);




    }



    @Test
    public void testTCParent(){





    }

}
