package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver)
    {
        super(driver);
    }
    @FindBy(id = "input-email")
    WebElement username;
    @FindBy(id = "input-password")
    WebElement password;
    @FindBy(xpath = "//input[@value='Login']")
    WebElement loginBtn;

    public void setUsername(String usrName)
    {
        username.sendKeys(usrName);
    }
    public void setPassword(String pass)
    {
        password.sendKeys(pass);
    }
    public void clickOnLoginButton()
    {
        loginBtn.click();
    }

}
