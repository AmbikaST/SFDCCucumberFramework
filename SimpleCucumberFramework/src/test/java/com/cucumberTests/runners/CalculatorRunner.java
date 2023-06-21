package com.cucumberTests.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/features/simpleFeature.feature"},
		glue = {"com.cucumberTests.steps"},
		monochrome = true,
		dryRun = false,
		tags = "@add"
		)

public class CalculatorRunner extends AbstractTestNGCucumberTests{

}
