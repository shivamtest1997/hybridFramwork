package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testRequirements.WebEvents;


public class AccountRegistrationTest extends WebEvents {

    @Test(groups = "Regression")
    public void verifyAccountRegistration()
    {
        try {
            logger.info("**** Test case started *****");
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("click on my account");
            hp.clickRegister();
            logger.info("click on register");

            AccountRegistrationPage ap = new AccountRegistrationPage(driver);
            logger.info("provide customer details");
            ap.setTxtFirstName(randomString().toUpperCase());
            ap.setTxtLastName(randomString().toUpperCase());
            ap.setTxtEmail(randomString() + "@yopmail.com"); //when we run multiple times it will not accept same email
            ap.setTxtTelephone(randomNumber());

            String password = randomAlphanumeric();
            ap.setTxtPassword(password);
            ap.setTxtConfirmPassword(password);

            ap.clickPrivacyPolicy();
            ap.clickContinue();
            logger.info("validate confirmation message");
            String confirmationMsg = ap.getConfirmationMsg();
            if (confirmationMsg.equals("Your Account Has Been Created!"))
            {
                Assert.assertTrue(true);
            }
            else {
                Assert.assertTrue(false);
                logger.error("Test Failed");
            }

        }catch (Exception e)
        {
            Assert.fail();
        }


    }



}
