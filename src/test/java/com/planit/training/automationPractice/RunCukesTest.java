package com.planit.training.automationPractice;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(
		// enable if you want to run automatically
     features={"src/test/resources/featuresAutomationPractice"}
)
public class RunCukesTest extends AbstractTestNGCucumberTests{
	
	// this class will automatically run all the cucumber features above when testNG runs
}
