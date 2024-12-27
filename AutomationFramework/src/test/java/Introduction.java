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
    /**
     *
     * PageObjects :    ---->  Test cases:
     *      Base Page          Base Class
     *      Home Page          Login Test
     *      Registration Page  Login DDT
     *      Login Page
     *      Search Page ...
     *
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
     *      1.4 create BaseClass under testBase package and copy reusable methods.
     *      1.5 create reusable methods to generate random numbers and strings in BaseClass.
     */


}
