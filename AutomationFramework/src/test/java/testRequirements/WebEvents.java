package testRequirements;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
@Component
public class WebEvents {
    public static WebDriver driver;
    public Logger logger;
    public Properties prop;
    public String action = "";

    @Parameters({"os","browser"})
    @BeforeClass(groups =  {"Sanity","Regression","Data-driven"})
    public void setup(String os,String br) throws IOException {
        //loading config.properties file
        prop=new Properties();
        FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
        prop.load(file);

        logger= LogManager.getLogger(this.getClass());
        //setup for grid
        //to execute tests on grid server should be up
        if (prop.getProperty("execution_env").equalsIgnoreCase("remote"))
        {
            String hubUrl="http://localhost:4444/wd/hub";
            DesiredCapabilities capabilities=new DesiredCapabilities();
            // os
            if (os.equalsIgnoreCase("Windows"))
            {
                capabilities.setPlatform(Platform.WIN10);
            } else if (os.equalsIgnoreCase("Linux")) {
                capabilities.setPlatform(Platform.LINUX);
            } else {
                System.out.println("No Matching OS");
                return;
            }
            //browser
            switch (br.toLowerCase()){
                case "chrome" :capabilities.setBrowserName("chrome");break;
                case "edge"   :capabilities.setBrowserName("MicrosoftEdge"); break;
                default:
                    System.out.println("Invalid browser");return;
            }
            //for selenium grid execution to launch driver we need to user RemoteWebDriver
            driver=new RemoteWebDriver(new URL(hubUrl),capabilities);
        }
        if (prop.getProperty("execution_env").equalsIgnoreCase("local"))
        {
            switch (br.toLowerCase()){
                case "chrome" :driver=new ChromeDriver();break;
                case "edge"   :driver=new EdgeDriver(); break;
                case "firefox":driver=new FirefoxDriver();break;
                default:
                    System.out.println("Invalid browser");return;
            }
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies(); //to delete all cookies
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(prop.getProperty("appUrl")); //reading url from properties file
    }
    @AfterClass(groups = {"Sanity","Regression","Data-driven"})
    public void tearDown()
    {
        driver.quit();
    }

    public String randomString()
    {
        //here RandomStringUtils class came from commons-lang3 library
        String randomString= RandomStringUtils.randomAlphabetic(5);
        return randomString;
    }
    public String randomNumber()
    {
        //here RandomStringUtils class came from commons-lang3 library
        String randomString=RandomStringUtils.randomNumeric(10);
        return randomString;
    }
    public String randomAlphanumeric()
    {
        //here RandomStringUtils class came from commons-lang3 library
        String randomString=RandomStringUtils.randomAlphabetic(3);
        String randomNum=RandomStringUtils.randomAlphanumeric(3);
        return randomString+"@"+randomNum;
    }
    public String captureScreenshot(String name)
    {
        String timestamp=new SimpleDateFormat("yyyyMMddhhss").format(new Date());
        TakesScreenshot sc=(TakesScreenshot)driver;
        File sourceFile = sc.getScreenshotAs(OutputType.FILE);
        String filePath=System.getProperty("user.dir") + "\\screenshots\\" +name;
        File target = new File( filePath);
        sourceFile.renameTo(target);

        return filePath;
    }

    /**
     * Description: Wait for any element to appear on screen and check whether it is clickable.
     * @param driver
     * @param locator
     * @param sClick
     * @param stepInfo
     * @return
     */
    public WebElement waitForVisibilityAndClick(final WebDriver driver, final By locator, final boolean sClick, final String stepInfo) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

            if (sClick) {
                try {
                    return wait.until(ExpectedConditions.elementToBeClickable(locator));
                } catch (Exception e) {
                    Assert.fail(locator + "Not Clickable--------Error Message: " + e.getMessage());
                }
            }
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        } catch (Exception e) {


            Assert.fail("Not able to find " + locator + "--------Error Message: " + e.getMessage());
            return null;

        }

    }
    /**
     * This method will check whether element is visible or not.
     *
     * @param driver
     * @param locator
     * @param stepInfo
     * @return boolean
     * @throws Exception
     */
    public boolean isWebElementVisible(
            final WebDriver driver, final By locator, final String stepInfo) {
        action = "Check Element visible: " + locator.toString();
        System.out.println(action);
        WebElement element = waitForVisibilityAndClick(driver, locator, false, stepInfo);
        try {
            element.isDisplayed();

            return true;
        } catch (Exception e) {
            Assert.fail(locator + " Element not found--------Error Message: " + e.getMessage());
            System.out.println(locator + " Element not found--------Error Message: " + e.getMessage());
            return false;
        }
    }

}
