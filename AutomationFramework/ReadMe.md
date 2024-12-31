
Automation framework :

    1.we can organize automation project files/folders in structured manner. ex utility folder config file Excel files
    2.we can create separate package for different purpose.
    3.Objective:
    A.Re-usability --avoid duplication
    B.Maintainability ---handover is easy add new test cases easily at anytime
    C.Readability --everyone should be able to understand test cases easily helps new employee
    to understand and write test cases easily.
    Types of framework:
    1)Built-in
    ex: testNG,junit,cucumber,robot(python),pytest(python) ... etc.
    2)customized(user defined)
    modular framework,data driven,keyword driven,hybrid driven

Phases/Stages

       1) Analysing Application under test : No of pages ,
                                             what are all elements /how/type
                                             what to automate /what we cannot automate
       2)choose test cases for automation : Sanity test cases (highest priority p1)
                                            Data driven test cases/re-tests --p2
                                            Regression test cases --p3
                                            Any other test cases---p4
       3)Design & Development of framework:
                                 Design : creation of folder structure
                                 Development:Addition of test classes and page classes
       4)Execution : locally(IDE),remotely(jenkins,docker,seleniumGrid)
       5)Maintenance:Global Repositories--github,bitbucket

 Design :

       1.Create Maven Project and add dependency to pom.xml
           selenium
           org.apache.poi
           org.apache.poi poi-ooxml
           log4j-core
           log4j-api
           commons-io
           commons-lang3
           testNG
           extent-reports
      2.Create folder structure
        src/test/java
            PageObjects-->pages
            testBase -->common methods
            testCases
            utilities
        src/test/resources
        logs
        reports
        screenshots
        testData
        pom.xml
        testng.xml

 Development :

       1.Test Case :Account Registration
           1.1 create "BasePage" under 'pageObjects' which includes only constructor.This will be invoked by every
           page Object class constructor(Re-usability)
           1.2 create Page Object classes for HomePage,RegistrationPage under 'pageObjects' package.
           (these classes extends from BasePage)
           1.3 create AccountRegistrationTest under package 'testCases'
           1.4 create 'TestBase' under testCases package and copy reusable methods.
           1.5 create reusable methods to generate random numbers and strings in BaseClass. 

       2.Adding Logs to test case(log4j2)
           Logging : record all the events in the form of text
           log levels -- All < trace < debug < info < warn < error < fatal < off
           Appender: decide where to generate logs(console/file)
           loggers : decide what type of logs to generate(All < trace < debug < info < warn < error < fatal < off)
     
           2.1 Add log4j2.xml file under src/test/resources
           2.2 update TestBase class
           2.3 Add log statements to AccountRegistrationTest.
     
       3. Run Tests on Desired Browser/Cross Browser/Parallel
           3.1 create testng.xml file to run test cases and parameterize browser name and OS to 'TestBase'
               class setup() method
           3.2 update TestBase-->setup() method,launch browser bases on condition.
           3.3 maintain separate testng.xml file to run test on multiple browsers parallel.
     
       4. Read Common Values from config.properties file
           4.1 Add config.properties file under src/test/resources
           4.2 update TestBase-->setup() method,add script to load config.properties file.
           4.3 replace hard coded values in test case like url,username,password etc.
       5. Login Test Case
           5.1 create and update page object classes,loginPage,MyAccountPage-new classes
               update HomePage by adding login page link element
           5.2 Create Login Test
           5.3 Add entry testng.xml
       6. Data Driven Login Test
           6.1 prepare test data in Excel,place the Excel file inside testData folder.
           6.2 create ExcelUtility class under utilities package.
           6.3 update page object class MyAccountPage,add logout link element
           6.4 create DataProvider class in utilities package to maintain data providers for data driven tests
           6.5 create LoginDDTTest under testClasses package
           6.6 Add an Entry in testNG.xml file
     
       7.Grouping Tests
          7.1 Add all test cases into specific group{Sanity,Regression}
          7.2 Also add TestBase Methods setup() and tearDown() to all groups
          7.3 create testng.xml file to run the groups which we want to execute
     
       8.Add Extent Reports
          8.1 create ExtentReportUtility utility class under utility package
          8.2 Add captureScreen() method in TestBase
          8.3 Add ExtentReportUtility(Listener class) entry in testng.xml
          8.4 make sure WebDriver is static in TestBase we refer same driver instance in ExtentReportUtility
     
       9.Run Failed Tests
           test-output> testng-failed.xml

       10.Run Tests on Selenium Grid:

           Selenium Grid: Good for cross browser testing
               1.Hub --client>hub (main system where we start execution)
               2.Node -->remote machine(mac+browsers),(win+browsers),(linux+browsers)

           Grid Setup:
                we need to attach each node to hub where we can control all the notes 
                -Download selenium-server-4.15.0.jar and place in one folder
                -run below command in cmd to start selenium grid server
                     java -jar selenium-server-4.27.0.jar standalone
                -URL to see sessions http://localhost:4444/
                -we need hub URL : http://localhost:4444/wd/hub

           10.1 Add execution_enc=local/remote in config.properties file
           10.2 update setup() method in the TestBase (capture execution_env from config.properties file
                then add required capabilities of OS&browser in conditions
           10.3 Run tests from testng.xml file

       11. Run Test on Docker with selenium Grid Environment 
                pull Docker images: selenium-hub ,selenium/node-firefox,selenium/node-edge,selenum/node-chrome
                docker pull selenium/hub
                docker pull selenium/node-chrome
                docker pull selenium/node-edge

                setup network for selenium grid: connect noded to hub
                docker network create grid --this command will create grid network
                docker run -d -p 4442-4444:4442-4444 --net grid --name selenium-hub selenium/hub
                        --this command will create hub and running
                docker run -d --net grid -e SE_EVENT_BUS_HOST=selenium-hub -e SE_EVENT_BUS_PUBLISH_PORT=4442 -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 selenium/node-chrome

            11.1 create docker-compose file 
            11.2 run docker-compose up
            11.3 check running nodes : http://localhost:4444/grid/console

       12.Run tests through pom.xml
            12.1 Add plugins to compile and run tests in pom.xml
                    Maven Compiler plugin --for compiling project
                    Maven surefire plugin--for running tests 
                    install maven into system and configure path in environment variables
                    execute command 'mvn clean test' 
       13. push the code to github repository 
       14. Executes Test in Jenkins CI 
                    1. Download jenkins.war file 
                    2. open CMD and Run war file run commmand : 'java -jar jenkins.war'
                    3. capture password to unlock jenkins screen from http://localhost:8080

Framework Explanation 

    My Automation framework is hybrid framework designed to handle UI and API testing effeciently.It integrate multiple
    design priciples and tools for better maintainability,scalability and resuability 
    
    1.We have used Java Language and Maven as build automation tool to design our framework 
    2.Framework follows Page Object Moadel as dsign pattern to seperate out page classes and test classes this framework
      consist of multiple packages like src/main/java,src/test/java,src/test/resources
    3.Each Web Page or feature is represented as java page class which consist of 
        WebElements: Defined using selenium locators(@FindBy) annotations and 
        Actions:method encaspulating user interactions to initialize web elements we have used PageFactory class
        this approach pramotes modularity and simplifies maintainance 
    4.this framework suppots data driven test where we kept our test cases description,steps and expected result sheet and test data
        in excel sheet and json files to achieve this we have used combination of apache POI and Fillo and jacson databind dependancy
    5.This perticular test script developed on the basis of OOPs concept for optimization of project
    6.This framework extents support for API automation for this we have used RestAssured library 
    7.This framework consist of utiliy packages to maintain reusable Utility classes like ExtentReport Manager,ExcelUtils ScreenReader etc
    8.we have config.properties file which consist of frequently used changing data like browser,environment,Url excelSheet name execution type
    9.we have exntent report as third party tool to support good report of automation execution for this we have used ExtentReport dependancy
    10.we also have testng.xml file running multiple test cases belongs to different classes and grouping of tests and cross browser testing
    11.we have pom.xml file where we manage all depencies and plugins to comiple and run project
    12.This framework incorporate with Spring for dependancy injection to create Objctes of Page classes and utility classes automatically
        for this we have used @Componant and @Autowired anotation 
    this is brief about my framework

Framework Explanation:2
Our automation framework is a hybrid system that supports both UI (Selenium) and API (RestAssured) testing.
It combines several design principles and tools to ensure better maintainability, scalability, and reusability.
Let’s go through the key components of the framework.

    1.Language and Build Automation:
        We use Java as the programming language and Maven as the build automation tool for managing dependencies and project structure.

    2.Page Object Model (POM):
        We implement the Page Object Model (POM) design pattern, which keeps the page-specific code separated from the test scripts. 
        This improves the clarity, organization, and maintainability of the tests.
        The framework consists of multiple packages like:
            src/main/java
            src/test/java
            src/test/resources and few utility packages

    3.src/page/java consists of Page Classes and WebElements:
        Each web page or feature is represented by a separate Java class.
        We use Selenium’s @FindBy annotations to define the WebElements (locators) for the page.
        The PageFactory class is used to initialize these elements, which helps keep the code modular and easier to maintain.
    4.src/test/java:
        This directory contains the test classes and essential test components, such as WebEvents and CommonTest, required for executing the tests. 
    5.Data-Driven Testing:
        The framework enables data-driven testing by fetching test case descriptions, steps, and expected results from Excel and testd data from JSON files.
        To handle this, we use dependencies like Apache POI (for Excel), Fillo, and Jackson Databind (for JSON).

    6.Object-Oriented Programming (OOP) Concepts:
        The framework is designed using object-oriented programming (OOP) principles,
        such as inheritance and encapsulation, to enhance code reuse and simplify the overall project structure.
    7.API Testing with RestAssured:
        For API testing, we use the RestAssured library to send API requests and validate responses.

    8.Utility Classes:
        We have a set of utility classes stored in a separate package to handle common operations such as:
            ExtentReports (for generating test reports)
            ExcelUtils (for handling Excel file operations)
            ScreenReader (for capturing screenshots)

    9.Configuration File:
        We store frequently changing data (e.g., browser settings, environment URLs, test data file names)
        in a config.properties file, making it easy to update and manage.


    10.ExtentReports for Reporting:
        We use ExtentReports for generating rich, detailed reports about the automation test execution, including test results, logs, and screenshots.

    11.TestNG for Test Execution:
        TestNG is used to manage and execute test cases.
        The testng.xml file helps in:
            Running tests from multiple classes
            Grouping tests (e.g., smoke, regression)
            Cross-browser testing

    12.Maven for Dependency Management:
        The pom.xml file is used for managing all project dependencies and plugins (e.g., for Selenium, TestNG, RestAssured).   

    13.Spring for Dependency Injection:
        Spring is used for dependency injection, which automatically manages the creation and injection of objects for page and utility classes.
        We use annotations like @Component and @Autowired to automate object creation.
    14.Test Eexcution
        The framework supports automated test execution through Jenkins, enabling continuous integration and seamless test runs.
        We also use Docker to create consistent test environments, ensuring that tests can be executed reliably across different systems and configurations.
        
           .       