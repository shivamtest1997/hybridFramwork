package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
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
    @FindBy(xpath = "//ul[contains(@class,'dropdown-menu')]//a[text()='Login']")
    WebElement loginLink;

    //Action methods
    public void  clickMyAccount()
    {
        myAccount.click();
    }
    public void clickRegister()
    {
        linkRegister.click();
    }
    public void clickLogin()
    {
        loginLink.click();
    }
    public void verifyMyAccountOptionsDisplayed()
    {

    }

}
