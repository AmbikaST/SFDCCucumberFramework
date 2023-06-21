package com.cucumberTests.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/features/SalesForceFeature.feature"},
		glue = {"com.cucumberTests.steps"},
		monochrome = true
		)
public class SalesforceRunner extends AbstractTestNGCucumberTests{

}
