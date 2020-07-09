package com.tuners.mobile.BasePages.Teacher;

import com.tuners.mobile.util.Functions.FunctionUtil;
import com.tuners.mobile.util.PageObject;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @Author Shaine
 * @Description
 * @Date 2020/6/12 10:13
 **/
public class EntrancePage extends PageObject {

    public EntrancePage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    FunctionUtil functionUtil;
    @Override
    public void verifyDisplayed() throws Exception {

    }

    //admin pwd
    // 超管密码输入框
    @FindBy(xpath="//input[@type='password']")
    public WebElement inputOfPWD;

    //button of confirm
    //确定按钮
    @FindBy(xpath="//button[@class='base-btn']")
    public WebElement btnOfConfirm;


    //school dropdown icon
    //选择学校的下拉箭头
    @FindBy(xpath="(//img[contains(@class,'iconManger')])[1]")
    public WebElement iconOfSchoolDropdown;

    //role dropdown
    //选择角色的下拉箭头
    @FindBy(xpath="(//img[contains(@class,'iconManger')])[2]")
    public WebElement iconOfRoleDropdown;

    //class dropdown
    //选择班级的下拉箭头
    @FindBy(xpath="(//img[contains(@class,'iconManger')])[3]")
    public WebElement iconOfClassDropdown;

    //teacher dropdown
    //选择班级的下拉箭头
    @FindBy(xpath="(//img[contains(@class,'iconManger')])[3]")
    public WebElement iconOfTeacherDropdown;


    //list of roles
    //选择权限的选项
    @FindBy(xpath="//div[contains(text(),'选择权限')]//ancestor::div[contains(@class,'pop')]//li//div[@class='van-ellipsis']")
    public List<WebElement> listOfRoleOptions;


    //list of schools
    //选择学校的选项
    @FindBy(xpath="//div[contains(text(),'选择学校')]//ancestor::div[contains(@class,'pop')]//li//div[@class='van-ellipsis']")
    public List<WebElement> listOfSchoolOptions;

    //list of classes
    //选择班级的选项
    @FindBy(xpath="//div[contains(text(),'选择班级')]//ancestor::div[contains(@class,'pop')]//li//div[@class='van-ellipsis']")
    public List<WebElement> listOfClassesOptions;


    //list of teacher names
    //选择老师的选项
    @FindBy(xpath="//div[contains(text(),'选择老师')]//ancestor::div[contains(@class,'pop')]//li//div[@class='van-ellipsis']")
    public List<WebElement> listOfNormalTeacherOptions;

//选择学校页面的确定按钮
    @FindBy(xpath="//div[contains(@class,'centerBtn')]")
    public WebElement btnOfConfirmOnSelectSchoolPage;

//the selected item text
    @FindBy(xpath="//div[contains(text(),'选择学校')]//ancestor::div[contains(@class,'pop')]//li[contains(@class,'selected')]")
    public WebElement itemOfSelectedSchool;

    @FindBy(xpath="//div[contains(text(),'选择权限')]//ancestor::div[contains(@class,'pop')]//li[contains(@class,'selected')]")
    public WebElement itemOfSelectedRole;

    @FindBy(xpath="//div[contains(text(),'选择班级')]//ancestor::div[contains(@class,'pop')]//li[contains(@class,'selected')]")
    public WebElement itemOfSelectedClass;

    @FindBy(xpath="//div[contains(text(),'选择老师')]//ancestor::div[contains(@class,'pop')]//li[contains(@class,'selected')]")
    public WebElement itemOfSelectedTeacher;
//school icon
    @FindBy(xpath="//img[@class='iconSchool']")
    public WebElement imgOfSchoolIcon;


    public void login(String pwd) throws Exception {

        //输入超管密码

        FunctionUtil.sendkeysAfterVisible(inputOfPWD,pwd);

        FunctionUtil.clickAfterVisible(btnOfConfirm);

        PageObject.waitForPageLoadComplete();


    }

    public void selectSchoolAndRole(String school,String role) throws Exception {
       functionUtil=new FunctionUtil(androidDriver);
        //点击学校下拉箭头
        FunctionUtil.clickAfterVisible(iconOfSchoolDropdown);
      functionUtil.selectDesiredValue(school,listOfSchoolOptions,itemOfSelectedSchool);
        functionUtil.singleTabByCoordinate(400,400);

        //点击角色下拉箭头
        FunctionUtil.clickAfterVisible(iconOfRoleDropdown);
        functionUtil.selectDesiredValue(role,listOfRoleOptions,itemOfSelectedRole);
        functionUtil.singleTabByCoordinate(400,400);
    }


//select a role as a schoolManager

    public void loginAsASchoolManager(String school) throws Exception {
        selectSchoolAndRole(school,"学校管理员");
        FunctionUtil.clickAfterVisible(btnOfConfirmOnSelectSchoolPage);
        PageObject.waitForPageLoadComplete();

    }


    public void loginAsAClassTeacher(String school,String className) throws Exception {
       functionUtil=new FunctionUtil(androidDriver);
        selectSchoolAndRole(school,"班主任");
        //点击班级下拉箭头
        FunctionUtil.clickAfterVisible(iconOfClassDropdown);
       functionUtil.selectDesiredValue(className,listOfClassesOptions,itemOfSelectedClass);
        functionUtil.singleTabByCoordinate(400,400);
        //点击确定按钮
        FunctionUtil.clickAfterVisible(btnOfConfirmOnSelectSchoolPage);
        PageObject.waitForPageLoadComplete();
    }


    public void loginAsNormalTeacher(String school,String teacherName) throws Exception {
       functionUtil=new FunctionUtil(androidDriver);
        selectSchoolAndRole(school,"普通老师");
        //点击班级下拉箭头
        FunctionUtil.clickAfterVisible(iconOfTeacherDropdown);
        functionUtil.selectDesiredValue(teacherName,listOfNormalTeacherOptions,itemOfSelectedTeacher);
        functionUtil.singleTabByCoordinate(400,400);
        //点击确定按钮
        FunctionUtil.clickAfterVisible(btnOfConfirmOnSelectSchoolPage);
        PageObject.waitForPageLoadComplete();
    }






}
