package basePages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import util.PageManager;
import util.PageObject;

public class HomePage extends PageObject{

	public HomePage(PageManager pm) {
		super(pm);
		// TODO Auto-generated constructor stub
	}

	 //it can be located by xpath
    @FindBy(xpath = "//*[@name='q']")
    public WebElement searchInput;

    @FindBy(xpath = ".//*[@name='go']")
    public WebElement searchSubmit;
	
	
}
