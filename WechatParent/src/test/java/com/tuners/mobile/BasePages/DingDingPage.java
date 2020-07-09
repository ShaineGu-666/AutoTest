package com.tuners.mobile.BasePages;

import com.tuners.mobile.util.PageObject;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * @Author Mason
 * @Description
 * @Date 2020/6/23 19:03
 **/
public class DingDingPage extends PageObject {
    public DingDingPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    @Override
    public void verifyDisplayed() throws Exception {

    }

    @AndroidFindBy(xpath="//android.widget.TextView[@text='书僮教育']")
    public AndroidElement iconOfShuTongEdu;
}
