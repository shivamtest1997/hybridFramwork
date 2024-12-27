package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    // constructor
    public HomePage(WebDriver driver)
    {
        super(driver);
    }
    //locators
    @FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement myAccount;
    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement linkRegister;

    //Action methods
    public void  clickMyAccount()
    {
        myAccount.click();
    }
    public void clickRegister()
    {
        linkRegister.click();
    }

}
