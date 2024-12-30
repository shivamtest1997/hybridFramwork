package testCases;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.MyAccountPage;
import testRequirements.BaseTest;
import testRequirements.ObjectFactory;
import testRequirements.WebEvents;
import utils.PropertyFileReader;
import utils.TestCaseSheetUtil;

import java.io.IOException;

import static utils.TestCaseSheetUtil.readJsonFromFile;

public class LoginTestNew extends WebEvents {
    private String stepInfo = "";
    private JsonNode loginTestData;
    private JsonNode jsonNode;
    private BaseTest test;

    @BeforeClass
    public void startTest()
    {
        TestCaseSheetUtil.init(PropertyFileReader.readPropertyValues("testCaseExcelPath"), "LoginPage");
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ObjectFactory.class);
        test = context.getBean( BaseTest.class);
    }
    @Test
    public void testCaseTest() throws IOException {

        stepInfo = TestCaseSheetUtil.readTestCaseSteps("login01", "1");
        System.out.println(stepInfo);
        stepInfo = TestCaseSheetUtil.readExpectedResults();
        System.out.println(stepInfo);

        String filePath = "src/test/resources/JsonFiles/LoginTestData.json";
        jsonNode = readJsonFromFile(filePath);
        loginTestData = jsonNode.path("login");
        System.out.println( loginTestData.path("email").asText());
        test.login.clickOnLoginButton();
    }

    @Test
    public void verifyLoginPage()
    {
        try {
            stepInfo = TestCaseSheetUtil.readTestCaseSteps("login01", "1");
            System.out.println(stepInfo);
            test.homePage.clickMyAccount();
            stepInfo = TestCaseSheetUtil.readExpectedResults();
            System.out.println(stepInfo);
            stepInfo = TestCaseSheetUtil.readTestCaseSteps("login01", "2");
            test.homePage.clickLogin();
            test.login.setUsername(prop.getProperty("email"));

            test.login.setPassword(prop.getProperty("password"));
            test.login.clickOnLoginButton();

            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean targetPage = myAccountPage.isMyAccountPageExist();

            Assert.assertEquals(targetPage, true, "Login Failed");
        }
        catch (Exception e)
        {
            Assert.fail();
        }
        logger.info("**Test Case Finished ****");


    }
}
