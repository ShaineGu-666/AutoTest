package com.tuners.mobile.testcase.teacher.wechat.homework.editHomework;

import com.tuners.mobile.BasePages.Teacher.ConfigureClassesPage;
import com.tuners.mobile.BasePages.Teacher.HomePage;
import com.tuners.mobile.BasePages.Teacher.HomeWorkDetailsPage;
import com.tuners.mobile.BasePages.Teacher.HomeworkManagementPage;
import com.tuners.mobile.BasePages.Teacher.PublishHomeWorkPage;
import com.tuners.mobile.util.Bases.WechatBase;
import org.testng.annotations.Test;

/**
 * @Author Mason
 * @Description
 * @Date 2020/6/29 11:49
 **/
public class TC09_复制发布 extends WechatBase {

@Test
    public void verify_TC09(){
    HomeworkManagementPage homeworkManagementPage=new HomeworkManagementPage(androidDriver);
    HomePage homePage=new HomePage(androidDriver);
    PublishHomeWorkPage publishHomeWorkPage=new PublishHomeWorkPage(androidDriver);
    HomeWorkDetailsPage homeWorkDetailsPage=new HomeWorkDetailsPage(androidDriver);
    ConfigureClassesPage configureClassesPage=new ConfigureClassesPage(androidDriver);

    //登录老师端


    //作业->作业管理



    //点击一个作业，进入作业详情



    //记录该作业的信息



    //点击复制发布


    //发布前验证作业内容是否与选择复制的作业的内容一致


    //更改作业标题，内容，点击发布


    //验证发布后的作业内容为编辑后的作业内容









    }
}
