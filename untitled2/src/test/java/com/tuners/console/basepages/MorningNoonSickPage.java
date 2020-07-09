package com.tuners.console.basepages;

import com.tuners.console.utils.FunctionUtil;
import com.tuners.console.utils.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Shaine
 * @Description
 * @Date 2020/4/28 15:09
 **/
public class MorningNoonSickPage extends PageObject {
    public MorningNoonSickPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void verifyDisplayed() throws Exception {

    }

    //晨午检及病假
    @FindBy(xpath ="//span[contains(text(),'晨午检与病假')]//ancestor::div[@class='el-submenu__title']")
    public WebElement linkOfMorningNoonSick;


    //晨午检初诊数据
@FindBy(xpath ="//span[contains(text(),'晨午检初诊数据')]//ancestor::a[1]")
public WebElement sublinkOfMorningInitialData;


    //晨午检追踪数据
    @FindBy(xpath ="//span[contains(text(),'晨午检追踪数据')]//ancestor::a[1]")
    public WebElement sublinkOfMorningTrackData;


    //请假人数汇总列表
    @FindBy(xpath ="//span[contains(text(),'请假人数汇总列表')]//ancestor::a[1]")
    public WebElement sublinkOfLeaveSummary;


    //选择分页的框
    @FindBy(xpath ="//div[@class='el-input el-input--mini el-input--suffix']//span[@class='el-input__suffix']")
    public WebElement frameOfPagination;

    //500条
    @FindBy(xpath ="//span[text()='500条/页']//ancestor::li")
    public WebElement fiveHundredPerPagePagination;


    //查询按钮
    @FindBy(xpath ="//span[contains(text(),'查询')]//ancestor::button[@type='button']")
    public WebElement btnOfSearch;

    //每行病假人数
    @FindBy(xpath ="(//tbody//tr//td[4])[not(contains(@class,'hidden'))]")
    public List<WebElement> listOfSickCountLine;


    @FindBy(xpath ="(//tbody//tr//td[3])[not(contains(@class,'hidden'))]")
    public List<WebElement> listOfEventLeaveCountLine;


    @FindBy(xpath ="(//tbody//tr//td[2])[not(contains(@class,'hidden'))]")
    public List<WebElement> listOfSickLeaveClass;

//教育局  病假数据   因病请假人数
    @FindBy(xpath ="(//tbody//tr//td[3])[not(contains(@class,'hidden'))]")
    public List<WebElement> listOfSickCountOfOrg;



@FindBy(xpath ="//input[contains(@placeholder,'选择年级')]//ancestor::div[1]")
public WebElement selectOfGrade;


@FindBy(xpath ="//div[contains(@style,'position: absolute;')]//li//span")
public List<WebElement> listOfGradeText;


//下拉框  一年级  二年级   三年级...
    @FindBy(xpath ="//div[contains(@style,'position: absolute;')]//li//span")
    public List<WebElement> listOfGrade;


//按年级展示按钮
@FindBy(xpath ="//span[text()='按年级展示']//ancestor::button")
public WebElement btnOfDisplayBaseOnGrade;

//教育局:病假数据
@FindBy(xpath ="//span[text()='病假数据']//ancestor::li[@tabindex]")
public WebElement linkOfSickStastics;

//请选择时间段
@FindBy(xpath ="//input[@placeholder='请选择时间段']//ancestor::div[1]")
public WebElement selectOfPeroid;

    public void login(String username, String password) throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='button']")).click();
        PageObject.waitForPageLoadComplete();
        Thread.sleep(8000);
    }



    public void switchGrade(String grade) throws Exception {
        selectOfGrade.click();
        Thread.sleep(2000);
FunctionUtil.clickByJS(driver.findElement(By.xpath("//div[contains(@style,'position: absolute;')]//li//span[text()='"+grade+"']")));
    }


    public void selectMaxPagination() throws Exception {
        //选择最大分页
        PageObject.waitForElementClickable(frameOfPagination,30);
        frameOfPagination.click();

        //切换最大的分页
       waitForElementVisible(fiveHundredPerPagePagination,30);
       fiveHundredPerPagePagination.click();
        Thread.sleep(6000);
    }



    public List<String>  getGardeList(){
        List<String> gradeStr=new ArrayList<String>();
        if(listOfGradeText.size()==0){
            selectOfGrade.click();
        }

        for(WebElement e:listOfGradeText){
            System.out.println("e.getText().trim():"+e.getText().trim());
            gradeStr.add(e.getText().trim());

        }

        return gradeStr;
    }
}
