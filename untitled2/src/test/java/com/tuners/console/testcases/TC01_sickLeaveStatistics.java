package com.tuners.console.testcases;

import com.tuners.console.basepages.MorningNoonSickPage;
import com.tuners.console.utils.PageObject;
import com.tuners.console.utils.TestCaseBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Shaine
 * @Description
 * 验证学校管理员
 * 晨午检初诊数据（新发）+跟踪数据（跟踪中）=统计的病假总人数
 *
 *
 *
 * @Date 2020/4/15 14:06
 **/
public class TC01_sickLeaveStatistics extends TestCaseBase {
    @Test
public void testTC01() throws Exception {
        List<Integer> newList=new ArrayList<Integer>();
        List<Integer> followList=new ArrayList<Integer>();
        List<Integer> totalList=new ArrayList<>();
        List<Integer> classWholeList=new ArrayList<>();
        List<Integer> gradeWholeList=new ArrayList<>();
        List<WebElement> sickLine;
        int newRecords;
        int followRecords;
        int wholeRecords;
        MorningNoonSickPage morningNoonSickPage=new MorningNoonSickPage(TestCaseBase.driver);
// login("18757121095","123456");
        morningNoonSickPage.login("16912345678","123456");


//点击晨午检及病假
        morningNoonSickPage.linkOfMorningNoonSick.click();
//点击晨午检初诊数据
        PageObject.waitForElementVisible(morningNoonSickPage.sublinkOfMorningInitialData,30);
morningNoonSickPage.sublinkOfMorningInitialData.click();
Thread.sleep(6000);



List<String> gradeStr=morningNoonSickPage.getGardeList();


for(String s:gradeStr){

    //选年级
    morningNoonSickPage.switchGrade(s);
    //默认当天
    Thread.sleep(1000);
    selectStatus("新发");
    newRecords=returnRecords();
    newList.add(newRecords);

}

     //追踪数据
     morningNoonSickPage.sublinkOfMorningTrackData.click();
     PageObject.waitForPageLoadComplete();


        for(String s:gradeStr){

            //选年级
            morningNoonSickPage.switchGrade(s);
            //默认当天
            //选择跟踪完成
            selectStatus("跟踪中");
            followRecords=returnRecords();
            followList.add(followRecords);

        }

        for(int i=0;i<newList.size();i++){
            totalList.add(newList.get(i)+followList.get(i));
            System.out.println(gradeStr.get(i)+"初诊加跟踪中的总人数totalValue:"+(newList.get(i)+followList.get(i)));
        }

//统计界面

       morningNoonSickPage.sublinkOfLeaveSummary.click();
       PageObject.waitForPageLoadComplete();

       //选择最大分页
       morningNoonSickPage.selectMaxPagination();


        for(String s:gradeStr){
            int count=0;
            //选年级
            morningNoonSickPage.switchGrade(s);
            //默认当天
           morningNoonSickPage.btnOfSearch.click();
           PageObject.waitForPageLoadComplete();
           Thread.sleep(3000);


            for(WebElement e:morningNoonSickPage.listOfSickCountLine){
                count=count+(Integer.parseInt(e.getText().trim()));

            }
            System.out.println(s+"按班级展示统计中的病假总人数:count total:"+count);
            classWholeList.add(count);


        }
//按班级展示    各年级病假总人数与初诊新发加跟踪数据  相等
        Assert.assertEquals(classWholeList,totalList);

        //点击按年级展示
        morningNoonSickPage.btnOfDisplayBaseOnGrade.click();
        PageObject.waitForPageLoadComplete();
        //选择最大分页
        morningNoonSickPage.selectMaxPagination();
        int gradeSickLeaveCount=0;

        for(WebElement e:morningNoonSickPage.listOfSickCountLine){
                gradeSickLeaveCount=Integer.parseInt(e.getText().trim());
                System.out.println("按年级展示统计中的病假总人数:count total:"+gradeSickLeaveCount);
                gradeWholeList.add(gradeSickLeaveCount);


            }
        gradeWholeList.remove(gradeWholeList.size()-1);

//按年级展示    各年级病假总人数与初诊新发加跟踪数据  相等
        Assert.assertEquals(gradeWholeList,totalList);






}



public void selectStatus(String status) throws InterruptedException {
    Thread.sleep(2000);
    driver.findElement(By.xpath("//input[@placeholder='请选择状态']//ancestor::div[@class='el-input el-input--medium el-input--suffix']")).click();
    Thread.sleep(4000);
    //选择状态
    driver.findElement(By.xpath("//span[contains(text(),'"+status+"')]//ancestor::li")).click();
    driver.findElement(By.xpath("//span[contains(text(),'查询')]//ancestor::button[@type='button']")).click();
Thread.sleep(6000);
}



public int returnRecords(){
    WebElement pagination=driver.findElement(By.xpath("//span[@class='el-pagination__total']"));
    int records =0;
    if(pagination.isDisplayed()) {
        String s=driver.findElement(By.xpath("//span[@class='el-pagination__total']")).getText();
        String initStr=s.replaceAll(" ","").trim().substring(1);
         records=Integer.parseInt(initStr.substring(0,initStr.length()-1));

    }


    return records;
}



}
