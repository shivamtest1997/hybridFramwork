package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testRequirements.WebEvents;
import utilities.DataProviders;

public class LoginDDT extends WebEvents {

    /**
     * Data is valid -->login success-->test pass--logout
     * Data is valid -->login failed -->test failed
     *
     * Data is invalid-->login success-->test failed--logout
     * Data is invalid-->login fail-->test pass
     */
    @Test(dataProvider = "loginData",
            dataProviderClass = DataProviders.class,
        groups = "DataDriven") //getting dataProvider from different class
    public void verifyLoginDDT(String username,String password,String res)
    {

            logger.info("***Login Test Started****");
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(driver);
            logger.info("***Entering Email and Password****");

            lp.setUsername(username);
            lp.setPassword(password);
            lp.clickOnLoginButton();

            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean targetPage = myAccountPage.isMyAccountPageExist();

        if (res.equalsIgnoreCase("valid")) {
            if (targetPage == true) {
                myAccountPage.logout();
                Assert.assertTrue(true);

            } else {
                Assert.fail();
            }
        } else if (res.equalsIgnoreCase("invalid")) {

            if (targetPage == true) {
                myAccountPage.logout();
                Assert.assertTrue(false);

            } else {
                Assert.assertTrue(true);
            }
        }
        logger.info("**Test Case Finished ****");

    }
}
