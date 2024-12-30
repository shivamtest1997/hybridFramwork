package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

    public AccountRegistrationPage(WebDriver driver)
    {
        super(driver);
    }
    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement txtFirstName;
    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement txtLastName;
    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmail;
    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement txtTelephone;
    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtPassword;
    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement txtConfirmPassword;
    @FindBy(xpath = "//input[@name='agree']")
    WebElement privacyPolicyCheckbox;
    @FindBy(xpath = "//input[@value='Continue']")
    WebElement continueButton;
    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement confirmationMessage;

    public void setTxtFirstName(String fname)
    {
        txtFirstName.sendKeys(fname);
    }
    public void setTxtLastName(String lname)
    {
        txtLastName.sendKeys(lname);
    }
    public void setTxtEmail(String email)
    {
        txtEmail.sendKeys(email);
    }
    public void setTxtTelephone(String tel)
    {
        txtTelephone.sendKeys(tel);
    }
    public void setTxtPassword(String pass)
    {
        txtPassword.sendKeys(pass);
    }
    public void setTxtConfirmPassword(String pass)
    {
        txtConfirmPassword.sendKeys(pass);
    }
    public void clickPrivacyPolicy()
    {
        privacyPolicyCheckbox.click();
    }
    public void clickContinue()
    {
        continueButton.click();
    }
    public String getConfirmationMsg()
    {
        try {
            return (confirmationMessage.getText());
        }catch (Exception e)
        {
            return (e.getMessage());
        }
    }


}
