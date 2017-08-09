# Java-Test-Automation-FW
Test Automation and Automation Report System
- Using:
Clone source code
Create new test code in com.mh.ta.test
GUI Test:
  - Extend BaseWebTestNG for test class
  - Extend YourPageClass to BasePage<YourPageElementClass, YourPageValidationClass>
  - Extend YourPageElementClass to BaseElement
  - Extend YourPageValidationClass to BaseValidation<YourPageElementClass>
  - Project is build with some action keywords for using in automation GUI
API Test:
  - Using @OneTimeConfigEnable anotation with set up enableAPI = true
Record Video Test:
  - Using @RecordVideo annotation, set saveOnTestStatus for record mode(ALL,PASSED,FAILED,SKIPED)
TestReport:
  - Output dir in com.mh.ta.test/TestReport, Using ExtentReport with add screenshot when test is failed
run mvn clean install test -DsuitesName=testng.xml(or testNG xml suites file)
