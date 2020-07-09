package com.tuners.mobile.BasePages.Teacher;

import com.tuners.mobile.util.Functions.FunctionUtil;
import com.tuners.mobile.util.PageObject;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author Mason
 * @Description
 * @Date 2020/6/18 17:43
 **/
public class ConfigureClassesPage extends PageObject {
    @FindBy(xpath="//button[@class='base-btn']//span[contains(text(),'常用班级')]")
    public WebElement btnOfOftenClass;

    @FindBy(xpath="//button[@class='base-btn']//span[contains(text(),'添加分组')]")
    public WebElement btnOfAddGroup;

    @FindBy(xpath="//button[@class='base-btn'][contains(text(),'设置为分组')]")
    public WebElement btnOfSetToGroup;

    @FindBy(xpath="//button[@class='base-btn']//span[contains(text(),'确定选择')]")
    public WebElement btnOfConfirmChoice;


    @FindBy(xpath="//button[text()='设置为常用班级']")
    public WebElement btnOfSetToOftenClass;



    @FindBy(xpath="//i[@slot='content']//img")
    public List<WebElement> listOfTicksOfFatherUnit;


    @FindBy(xpath="//div[@class='van-collapse-item__wrapper'][not(contains(@style,'none'))]//div[@class='rows-wrapper rows']//div[@class='rows-titles']")
    public List<WebElement> listOfShownClassesUnderExpandedGrade;

    //常用班级列表内的班级标题
@FindBy(xpath="//div[contains(@class,'van-collapse-item')]//span[contains(text(),'年级')]")
public List<WebElement> listOfOftenClassesTitles;

@FindBy(xpath="//div[contains(@class,'van-collapse-item__wrapper')][not(contains(@style,'none'))]//div[contains(@class,'content')]//i//img")
public List<WebElement> listOfExpandedStudentTicklist;


@FindBy(xpath="//div[contains(@class,'van-collapse-item__wrapper')][not(contains(@style,'none'))]//div[contains(@class,'content')]//div[@class='rows-titles']")
public List<WebElement> listOfExpandedStudentNamelist;



@FindBy(xpath="//textarea[@placeholder='请输入分组名字']")
public WebElement textareaOfSetGroupName;


@FindBy(xpath="//div[contains(@class,'linetop')]//div[contains(text(),'确定')]")
public WebElement btnOfConfirmOnSetGroupNamePanel;


@FindBy(xpath="//span[contains(text(),'分组设置')]")
public List<WebElement> listOfGroupSetting;


@FindBy(xpath="//span[contains(text(),'分组设置')]//ancestor::div[contains(@class,'hairline')]//i[@slot='content']")
public List<WebElement> listOfGroupTicks;


    public ConfigureClassesPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    @Override
    public void verifyDisplayed() throws Exception {
waitForPageLoadComplete();
    }

        public void chooseClass(String[] classes) throws Exception {

        //若没在常用班级，先设置常用班级
        setOftenClassToListIfNotExist(classes);

        //设置常用班级后
        //1）取消不在这次选择对象中的班级的勾
            cancelTickForNoNeedClasses(classes);
        //2）如果选择对象的班级勾没勾上把勾勾上
            selectTickForDesiredClasses(classes);

            btnOfConfirmChoice.click();
            waitForPageLoadComplete();

        }
        public void setOftenClasses(String[] classes) throws Exception {

            Set<String> gradeList=collectGradesToSet(classes);
            String xpathOfGradeArrow = "";
            String xpathOfExpandedClassesTickIcon = "";
            WebElement desiredClassElement;

            FunctionUtil.clickAfterVisible(btnOfOftenClass);
            waitForPageLoadComplete();


          //点击对应年级的箭头

            for (String gd : gradeList) {
                xpathOfGradeArrow = "//span[contains(text(),'" + gd + "')]//ancestor::div[@role='button']//i";
                System.out.println("正在点击的年级是:" + gd);
                Thread.sleep(3000);
                androidDriver.findElement(By.xpath(xpathOfGradeArrow)).click();
                //遍历需要设置的班级，若属于该年级，也没勾选就勾选上

                for (String cl : classes) {

                    System.out.println("现在正在判断的是:" + cl);
                    if (cl.substring(0, 3).equals(gd)) {
                        xpathOfExpandedClassesTickIcon = "//div[@class='van-collapse-item__wrapper'][not(contains(@style,'none'))]//div[contains(text(),'" + cl + "')]//ancestor::div[contains(@class,'bottomLine')]//i//img";

                        desiredClassElement = androidDriver.findElement(By.xpath(xpathOfExpandedClassesTickIcon));


                        if (desiredClassElement.getAttribute("src").contains("Pamxra7dRXt79o8tXtfxYYGF60IothAAAAAElFTkSuQmCC")) {

                            desiredClassElement.click();
                            System.out.println("该" + cl + "属于该:" + gd + "下进行勾选");
                        }

                    }

                }


            }


            btnOfSetToOftenClass.click();
            waitForPageLoadComplete();


        }
        public void setOftenClassToListIfNotExist(String[] classes) throws Exception {
            List<String> allDesiredOftenClass = new ArrayList(Arrays.asList(classes));
       int index;
     for(int i=0;i<allDesiredOftenClass.size();i++) {

         index = FunctionUtil.getIndexOfDesiredElementInList(listOfOftenClassesTitles, allDesiredOftenClass.get(i));


         //如果该班级已经在常用班级列表，在需要设置常用班级的集合中删除
         if (index != -1) {
             allDesiredOftenClass.remove(i);
         }

     }
           //如果还剩下需要设置常用班级的班级，则去设置
           if(allDesiredOftenClass.size()!=0) {
System.out.println("需要设置常用班级的班级数量为:"+allDesiredOftenClass.size());
                 setOftenClasses((String[])allDesiredOftenClass.toArray(new String[allDesiredOftenClass.size()]));
             }

        }

    public Set<String> collectGradesToSet(String[] classes){
        Set<String> gradeList=new HashSet<String>();
        String grade="";
        //收集哪些需要配置的年级
        for (String s : classes) {
            grade = s.substring(0, 3);
            gradeList.add(grade);
        }
        return gradeList;
    }


    public void cancelOftenClasses(String[] oftenClassesNeedToCancel){
      int index;
        for(String cls:oftenClassesNeedToCancel){

            index=FunctionUtil.getIndexOfDesiredElementInList (listOfOftenClassesTitles,cls);
            if(!verifyOftenClassesTicksCheckedOrNot(cls)) {
                listOfTicksOfFatherUnit.get(index).click();
            }
        }



    }


    public Boolean verifyOftenClassesTicksCheckedOrNot(String cls){
     Boolean Flag;
       int  index=FunctionUtil.getIndexOfDesiredElementInList(listOfOftenClassesTitles,cls);
       String imgSrc=listOfTicksOfFatherUnit.get(index).getAttribute("src");

       if(imgSrc.contains("3zuEOd5CyXwx52IcY1zTR6EIQ/khqdyKx4jFW7djeNvirdvHwqzmodB731eunV3E/5JWrpT8mVBtAAAAAElFTkSuQmCC")){

           Flag=true;

       }else{

           Flag=false;
       }

       return Flag;


    }



    public void cancelTickForNoNeedClasses(String[] classes) throws Exception {

        List<WebElement> list=new ArrayList<WebElement>();

        //获取不需要勾选的常用班级   所有常用班级- 需要设置的常用班级
        waitForListVisible(listOfOftenClassesTitles,30);
        List<String> noNeedTickList=FunctionUtil.receiveDefectList(FunctionUtil.getValuesFromWebElementList(listOfOftenClassesTitles), Arrays.asList(classes));

        int index;
       for(String noneed:noNeedTickList){

           if(verifyOftenClassesTicksCheckedOrNot(noneed)){
             index=FunctionUtil.getIndexOfDesiredElementInList(listOfOftenClassesTitles,noneed);

             listOfTicksOfFatherUnit.get(index).click();

           }
       }




    }


public void selectTickForDesiredClasses(String[] classes){
int index;
    for (String cl : classes) {


        if(!verifyOftenClassesTicksCheckedOrNot(cl)){
            index=FunctionUtil.getIndexOfDesiredElementInList(listOfOftenClassesTitles,cl);
           listOfTicksOfFatherUnit.get(index).click();
        }

    }


}


    public void addNewGroup() throws Exception {

    //点击添加分组
     FunctionUtil.clickAfterVisible(btnOfAddGroup);
     //选择每个常用班级的第一个学生作为一个分组

      List<AndroidElement> listOfDrop=androidDriver.findElements(By.cssSelector("i.van-icon.van-icon-arrow.van-cell__right-icon"));


    for(int i=0;i<listOfOftenClassesTitles.size();i++){
        listOfDrop.get(i).click();
         waitForListVisible(listOfExpandedStudentTicklist,30);
        listOfExpandedStudentTicklist.get(0).click();

    }

        btnOfSetToGroup.click();
        waitForElementVisible(textareaOfSetGroupName,30);
      textareaOfSetGroupName.sendKeys("常1分组"+System.currentTimeMillis());
        btnOfConfirmOnSetGroupNamePanel.click();

    }



    public Set<String> chooseGroup(int n) throws Exception {
       Set<String> selectedStudentInGroup=new HashSet<String>();
        int securecount=0;
        while(listOfGroupSetting.size()<n&&securecount<n) {
         addNewGroup();
         securecount++;
        }
String xpath;
        for(int i=0;i<n;i++){
            listOfGroupTicks.get(i).click();

            //获取选中的分组的学生
            xpath="(//span[contains(text(),'分组设置')]//ancestor::div[contains(@class,'hairline')])["+(i+1)+"]//i[contains(@class,'van-icon-arrow')]";
            androidDriver.findElement(By.xpath(xpath)).click();
selectedStudentInGroup.addAll(FunctionUtil.getValuesFromWebElementList(listOfExpandedStudentNamelist));
        }
         btnOfConfirmChoice.click();
        waitForPageLoadComplete();
        return  selectedStudentInGroup;
    }
}











