package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testCases.TestBase;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    String reportName;

    //Before staring execution execute only once
    public void onStart(ITestContext context) {

        //time Stamp
        String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        reportName="Test Report"+timeStamp+".html";
        //Specify location of report in project
        sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"\\reports\\"+reportName);
        sparkReporter.config().setDocumentTitle("Automation Test Report"); // title of the report
        sparkReporter.config().setReportName("Functional Testing"); // name of the report
        sparkReporter.config().setTheme(Theme.DARK);

        extent=new ExtentReports();
        extent.attachReporter(sparkReporter);

        //set some common info later we can call it dynamically from config.properties file
        extent.setSystemInfo("Computer Name","localhost");
        extent.setSystemInfo("Environment",System.getProperty("environment"));
        extent.setSystemInfo("Executor name",System.getProperty("user.name"));

        // read data from xml file and put it into report
        String os=context.getCurrentXmlTest().getParameter("os");
        String browser=context.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("OS", os);
        extent.setSystemInfo("Browser",browser);

        // we can add groups to report
        List<String> includeGroups=context.getCurrentXmlTest().getIncludedGroups();
        if (!includeGroups.isEmpty())
        {
            extent.setSystemInfo("Groups", includeGroups.toString());
        }

    }
     public void onTestSuccess(ITestResult result) {
        test=extent.createTest(result.getTestClass().getName()); //create a new entry in the report
        test.assignCategory(result.getMethod().getGroups()); //to display groups in reports
        test.log(Status.PASS, "Test case Passed :"+result.getName()); //update status i.e pass/fail/skip
    }

    public void onTestFailure(ITestResult result) {
        test=extent.createTest(result.getTestClass().getName()); //create a new entry in the report
        test.assignCategory(result.getMethod().getGroups()); //to display groups in reports
        test.log(Status.FAIL, "Test case Failed :"+result.getName());
        test.log(Status.FAIL, "Test case Failed due to:"+result.getThrowable());

        //attach screenshot
        try{
            String imgPath = new TestBase().captureScreenshot(result.getName());
            test.addScreenCaptureFromPath(imgPath);

        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void onTestSkipped(ITestResult result) {
        test=extent.createTest(result.getTestClass().getName()); //create a new entry in the report
        test.assignCategory(result.getMethod().getGroups()); //to display groups in reports
        test.log(Status.SKIP, "Test case Skipped :"+result.getName());
    }
    public void onFinish(ITestContext context) {
        extent.flush();  // it will update all above information in report
        String pathOfReport=System.getProperty("user.dir")+"\\reports\\"+reportName;
        File extentReport=new File(pathOfReport);
        try {
            Desktop.getDesktop().browse(extentReport.toURI()); // this will open report automatically
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // to send report on email
        try{
            URL url=new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+reportName);

            //create email message add commons-email dependency
            ImageHtmlEmail email=new ImageHtmlEmail();
            email.setDataSourceResolver(new DataSourceUrlResolver(url));
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("shivamtest1997@gmail.com", "Shivam@123"));
            email.setSSLOnConnect(true);
            email.setFrom("shivamtest1997@gmail.com"); //sender
            email.setSubject("Test Results");
            email.setMsg("Please see attached result");
            email.addTo("shivamtest1997@gmail.com"); //receiver
            email.attach(url,"extent report","please see...");
            email.send();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
