package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class TestBase {
    public static WebDriver driver;
    public Logger logger;
    public Properties prop;

    @Parameters({"os","browser"})
    @BeforeClass(groups =  {"Sanity","Regression","Data-driven"})
    public void setup(String os,String br) throws IOException {
        //loading config.properties file
        prop=new Properties();
        FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
        prop.load(file);

        logger= LogManager.getLogger(this.getClass());
        switch (br.toLowerCase()){
            case "chrome" :driver=new ChromeDriver();break;
            case "edge"   :driver=new EdgeDriver(); break;
            case "firefox":driver=new FirefoxDriver();break;
            default:
                System.out.println("Invalid browser");return;
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

}
