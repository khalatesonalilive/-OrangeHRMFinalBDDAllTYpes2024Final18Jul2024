package OrangeHRMFinalTestRunner;

import io.cucumber.junit.CucumberOptions;

@CucumberOptions(
		
		features= {"AllFeatureFiles"},
		glue= {"OrangeHrmFinalSteps"},
		dryRun=false,
		monochrome=true,
		plugin= {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
		
		
		
		
		)



public class OrangeHRMFinalRunnertEST {

}
