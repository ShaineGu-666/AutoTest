package com.tuners.console.testcases;

import com.tuners.console.basepages.MorningNoonSickPage;
import com.tuners.console.utils.PageObject;
import com.tuners.console.utils.TestCaseBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Mason
 * @Description
 * @Date 2020/4/29 10:27
 **/
public class TC03_统计书僮事假人数 extends TestCaseBase

{


    @Test
    public void testTc03() throws Exception {
        MorningNoonSickPage morningNoonSickPage=new MorningNoonSickPage(TestCaseBase.driver);

        morningNoonSickPage.login("19216900002","123456abc");
//点击晨午检及病假
        morningNoonSickPage.linkOfMorningNoonSick.click();
        Thread.sleep(3000);
        morningNoonSickPage.sublinkOfLeaveSummary.click();
        PageObject.waitForPageLoadComplete();


        morningNoonSickPage.btnOfDisplayBaseOnGrade.click();
        PageObject.waitForPageLoadComplete();
        //选择最大分页
        morningNoonSickPage.selectMaxPagination();
        int gradeEventLeaveCount=0;

        for(WebElement e:morningNoonSickPage.listOfEventLeaveCountLine){
            gradeEventLeaveCount= gradeEventLeaveCount+Integer.parseInt(e.getText().trim());

        }
        System.out.println("按年级展示统计中的事假总人数:count total:"+gradeEventLeaveCount);





    }
}
