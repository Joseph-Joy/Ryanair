package com.test.ryanair;

import org.junit.runner.RunWith;
import courgette.api.CourgetteOptions;
import courgette.api.CourgetteRunLevel;
import courgette.api.CucumberOptions;
import courgette.api.junit.Courgette;

@RunWith(Courgette.class)
@CourgetteOptions(
		 threads = 10,
	        runLevel = CourgetteRunLevel.FEATURE,
	        rerunFailedScenarios = false,
	        showTestOutput = true,
	        reportTargetDir = "target",
	        environmentInfo = "browser=chrome; git_branch=master; project_info=Courgette-JVM is awesome!",
	        plugin = "extentreports",
	        cucumberOptions = @CucumberOptions(
	                features = "src/test/resources/features",
	                glue = {"com.test.stepdefs","com.test.helper"},
	                monochrome=true,
	                publish = false,
	               
	                plugin = {
	                        "pretty",
	                        "json:target/cucumber-report/cucumber.json",
	                        "html:target/cucumber-report/cucumber.html",
	                        "junit:target/cucumber-report/cucumber.xml",
	                        "message:target/cucumber-report/cucumber.ndjson"}
	        ))
public class RunCucumberTest {

}
