package com.tuners.mobile.testcase.teacher.wechat.homework.editHomework;

import com.tuners.mobile.BasePages.Teacher.ConfigureClassesPage;
import com.tuners.mobile.BasePages.Teacher.HomePage;
import com.tuners.mobile.BasePages.Teacher.HomeWorkDetailsPage;
import com.tuners.mobile.BasePages.Teacher.HomeworkManagementPage;
import com.tuners.mobile.BasePages.Teacher.PublishHomeWorkPage;
import com.tuners.mobile.BasePages.WechatNativePage;
import com.tuners.mobile.util.Bases.WechatBase;
import com.tuners.mobile.util.PageObject;
import org.testng.annotations.Test;

/**
 * @Author Mason
 * @Description
 * @Date 2020/6/29 11:47
 **/
public class TC08_修改已发布的作业 extends WechatBase {

    @Test
    public void verify_TC08_修改已发布的作业() throws Exception {
        HomeworkManagementPage homeworkManagementPage=new HomeworkManagementPage(androidDriver);
        HomePage homePage=new HomePage(androidDriver);
        PublishHomeWorkPage publishHomeWorkPage=new PublishHomeWorkPage(androidDriver);
        HomeWorkDetailsPage homeWorkDetailsPage=new HomeWorkDetailsPage(androidDriver);
        ConfigureClassesPage configureClassesPage=new ConfigureClassesPage(androidDriver);
        WechatNativePage wechatHomePage=new WechatNativePage(androidDriver);

        //进入作业-作业管理
        //pre-condition
        //进入公众号
        wechatHomePage.searchAndEnterForPublicAccountForTeacher(ENVFLAG);

        //点击更多作业进入作业管理
        homePage.enterHomeWorkManagementPage();
        homeworkManagementPage.verifyDisplayed();


        //选择某个作业，比如第0个
        PageObject.waitForListVisible(homeworkManagementPage.listOfPublishedHomeworkList,30);


        //作业详请 点击修改作业



        //修改作业页面
        //1.验证保留了原先的作业内容
        //2.修改截止时间以及是否互看




        //验证发布后的作业（其他信息保持不变， 截止时间以及是否互看发生变化）






    }
}
