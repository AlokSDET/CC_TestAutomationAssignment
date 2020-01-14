

This is gradle project. Please switch to the 'FrontEndAutomationTesting' branch.


Note: before running please put the encoded password in password field in property file:

Generate encoded password by using below URL .

https://www.base64encode.org/




We can run individual test cases , test class and using testng.xml as well.

This framework is designed using Page object model and page factory design pattern.

Reporting : Default Testng Report and Extent report.

Unit Test Case: TestNG framework , used all applicable annotations.

Used Singleton design pattern to get only one instance of webdriver.


Enum is used to keep all constants .

Utilities, Wrapper methods , helper methods are created using static, which can be invoked by using static import.

Constants files is created to store all constants variable, which can be used across the framework.

Configuration data( url, browser, username, password) is kept in property files.



