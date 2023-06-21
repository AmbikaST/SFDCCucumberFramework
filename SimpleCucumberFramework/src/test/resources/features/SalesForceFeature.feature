
Feature: SalesForce LoginFunctionality

Background:
Given application is up and running and in loginpage

Scenario: login with correct credentials
When i enter username and password
And click on the login button
Then i should get the home page

Scenario: login with blank password
When i enter username and blank password
And click on the login button
Then i should get the error message

Scenario: Test the remember username checkbox
When i enter username and password
When select the remember username checkbox
When click on the login button
When i should get the home page
When click on the usermenu drop down and select the logout link
Then user name should be displayed in the username field
 
Scenario: Test forgot password
When click on forgot password
When provide username and click on continue button
Then password reset link should be send to user email id

Scenario: Validate Error Message
When i enter invalidUsername and invalidPassword
When click on the login button
Then error message should be displayed