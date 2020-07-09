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
 * @Date 2020/4/29 9:01
 **/
public class TC02_sickStatus extends TestCaseBase {
    @Test
    public void testTc02() throws Exception {
        String time = "13:48";
        MorningNoonSickPage morningNoonSickPage = new MorningNoonSickPage(TestCaseBase.driver);
        driver.findElement(By.xpath("//i[contains(@class,'up')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[text()='教育局']//ancestor::li")).click();
        morningNoonSickPage.login("15700067103", "123456");

        PageObject.waitForElementClickable(morningNoonSickPage.linkOfSickStastics, 30);
        morningNoonSickPage.linkOfSickStastics.click();
        Thread.sleep(5000);

        morningNoonSickPage.selectMaxPagination();

        morningNoonSickPage.selectOfPeroid.click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//span[text()='" + time + "']//ancestor::li")).click();

        morningNoonSickPage.btnOfSearch.click();
        Thread.sleep(6000);
        int count = 0;
        for (WebElement e : morningNoonSickPage.listOfSickCountOfOrg) {
            count = count + Integer.parseInt(e.getText().trim());
        }
        System.out.println("因病请假人数总共为：" + count);

//-------------------------------------------------------
        String[] gradeStr = new String[]{"一年级","二年级","三年级","四年级","五年级","六年级","七年级","八年级","九年级","高一","高二","高三"};
        List<String> classHasSickLeaveList = new ArrayList<>();

        driver.findElement(By.xpath("//span[text()='杭州书僮实验学校']//ancestor::tr//td[not(contains(@class,'hidden'))]//span[text()='查看详情']")).click();


        PageObject.waitForPageLoadComplete();
        Thread.sleep(6000);
        morningNoonSickPage.selectMaxPagination();

        morningNoonSickPage.selectOfPeroid.click();
        Thread.sleep(3000);
//        driver.findElement(By.xpath("//span[text()='" + time + "']//ancestor::li")).click();

        for (String s : gradeStr) {

            //选年级
            morningNoonSickPage.switchGrade(s);
            morningNoonSickPage.btnOfSearch.click();
            Thread.sleep(6000);
            int resultSize = morningNoonSickPage.listOfSickCountOfOrg.size();
            if (resultSize > 0) {
                int value = 0;
                for (int i = 0; i < morningNoonSickPage.listOfSickCountOfOrg.size(); i++) {
                    value = Integer.parseInt(morningNoonSickPage.listOfSickCountOfOrg.get(i).getText().trim());
                    if (value != 0) {
                        classHasSickLeaveList.add(morningNoonSickPage.listOfSickLeaveClass.get(i).getText().trim());
                        System.out.println("书僮实验学校：" + morningNoonSickPage.listOfSickLeaveClass.get(i).getText().trim() + value + "人");
                    }

                }


            }
        }
    }
}