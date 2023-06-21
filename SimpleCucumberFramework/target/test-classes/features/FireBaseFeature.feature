
Feature: FireBase Login Functionality

Scenario: login with correct credentials
Given application is up and running and in loginpage
When i enter username and password
And click on the login button
Then i should get the home page