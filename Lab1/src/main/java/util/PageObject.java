package util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

//this class contains many method could be reuse in tests
//it should always be re-factor to keep this class clean
//for example, the wait method is put in a separate class now, it was a part of this class
public class PageObject {

    protected Log log = LogFactory.getLog(this.getClass());
    protected PageManager pageManager;
    public static final String IE="ie";
    public static final String FIREFOX="firefox";
    public static final String CHROME="chrome";
    private WebDriver driver;

    public PageObject(PageManager pm) {
        pageManager=pm;
        PageFactory.initElements(driver,this);
    }


}






