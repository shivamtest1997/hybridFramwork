package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testRequirements.WebEvents;

public class LoginTest extends WebEvents {

    @Test(groups = "Sanity")
    public void verifyLogin()
    {
        try {
            logger.info("***Login Test Started****");
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(driver);
            logger.info("***Entering Email and Password****");
            lp.setUsername(prop.getProperty("email"));
            lp.setPassword(prop.getProperty("password"));
            lp.clickOnLoginButton();

            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean targetPage = myAccountPage.isMyAccountPageExist();

            Assert.assertEquals(targetPage, true, "Login Failed");
        }catch (Exception e)
        {
            Assert.fail();
        }
        logger.info("**Test Case Finished ****");

    }

}
