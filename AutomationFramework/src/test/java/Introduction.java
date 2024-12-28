public class Introduction {
    /**
     *   Automation framework :
     *   1.we can organize automation project files/folders in structured manner. ex utility folder config file Excel files
     *   2.we can create separate package for different purpose.
     *   3.Objective:
     *      A.Re-usability --avoid duplication
     *      B.Maintainability ---handover is easy add new test cases easily at anytime
     *      C.Readability --everyone should be able to understand test cases easily helps new employee
     *                      to understand and write test cases easily.
     *    Types of framework:
     *    1)Built-in
     *     ex: testNG,junit,cucumber,robot(python),pytest(python) ... etc.
     *    2)customized(user defined)
     *      modular framework,data driven,keyword driven,hybrid driven
     */

    /**
     *  Phases/Stages
     *  1) Analysing Application under test : No of pages ,
     *                                        what are all elements /how/type
     *                                        what to automate /what we cannot automate
     *  2)choose test cases for automation : Sanity test cases (highest priority p1)
     *                                       Data driven test cases/re-tests --p2
     *                                       Regression test cases --p3
     *                                       Any other test cases---p4
     *  3)Design & Development of framework:
     *                            Design : creation of folder structure
     *                            Development:Addition of test classes and page classes
     *  4)Execution : locally(IDE),remotely(jenkins,docker,seleniumGrid)
     *  5)Maintenance:Global Repositories--github,bitbucket
     */
    /** Design :
     *  1.Create Maven Project and add dependency to pom.xml
     *      selenium
     *      org.apache.poi
     *      org.apache.poi poi-ooxml
     *      log4j-core
     *      log4j-api
     *      commons-io
     *      commons-lang3
     *      testNG
     *      extent-reports
     * 2.Create folder structure
     *   src/test/java
     *       PageObjects-->pages
     *       testBase -->common methods
     *       testCases
     *       utilities
     *   src/test/resources
     *   logs
     *   reports
     *   screenshots
     *   testData
     *   pom.xml
     *   testng.xml
     *
     */
    /** Development :
     *  1.Test Case :Account Registration
     *      1.1 create "BasePage" under 'pageObjects' which includes only constructor.This will be invoked by every
     *      page Object class constructor(Re-usability)
     *      1.2 create Page Object classes for HomePage,RegistrationPage under 'pageObjects' package.
     *      (these classes extends from BasePage)
     *      1.3 create AccountRegistrationTest under package 'testCases'
     *      1.4 create 'TestBase' under testCases package and copy reusable methods.
     *      1.5 create reusable methods to generate random numbers and strings in BaseClass.
     *
     *  2.Adding Logs to test case(log4j2)
     *      Logging : record all the events in the form of text
     *      log levels -- All < trace < debug < info < warn < error < fatal < off
     *      Appender: decide where to generate logs(console/file)
     *      loggers : decide what type of logs to generate(All < trace < debug < info < warn < error < fatal < off)
     *
     *      2.1 Add log4j2.xml file under src/test/resources
     *      2.2 update TestBase class
     *      2.3 Add log statements to AccountRegistrationTest.
     *
     *  3. Run Tests on Desired Browser/Cross Browser/Parallel
     *      3.1 create testng.xml file to run test cases and parameterize browser name and OS to 'TestBase'
     *          class setup() method
     *      3.2 update TestBase-->setup() method,launch browser bases on condition.
     *      3.3 maintain separate testng.xml file to run test on multiple browsers parallel.
     *
     *  4. Read Common Values from config.properties file
     *      4.1 Add config.properties file under src/test/resources
     *      4.2 update TestBase-->setup() method,add script to load config.properties file.
     *      4.3 replace hard coded values in test case like url,username,password etc.
     *  5. Login Test Case
     *      5.1 create and update page object classes,loginPage,MyAccountPage-new classes
     *          update HomePage by adding login page link element
     *      5.2 Create Login Test
     *      5.3 Add entry testng.xml
     *  6. Data Driven Login Test
     *      6.1 prepare test data in Excel,place the Excel file inside testData folder.
     *      6.2 create ExcelUtility class under utilities package.
     *      6.3 update page object class MyAccountPage,add logout link element
     *      6.4 create DataProvider class in utilities package to maintain data providers for data driven tests
     *      6.5 create LoginDDTTest under testClasses package
     *      6.6 Add an Entry in testNG.xml file
     *
     *  7.Grouping Tests
     *     7.1 Add all test cases into specific group{Sanity,Regression}
     *     7.2 Also add TestBase Methods setup() and tearDown() to all groups
     *     7.3 create testng.xml file to run the groups which we want to execute
     *
     *  8.Add Extent Reports
     *     8.1 create ExtentReportUtility utility class under utility package
     *     8.2 Add captureScreen() method in TestBase
     *     8.3 Add ExtentReportUtility(Listener class) entry in testng.xml
     *     8.4 make sure WebDriver is static in TestBase we refer same driver instance in ExtentReportUtility
     *
     *
     *
     */


}
