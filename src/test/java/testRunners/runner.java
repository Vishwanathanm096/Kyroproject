package testRunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="feature/Create_a_new_task_and_validate.feature",glue= {"stepDefinition"},
plugin = {"pretty"},
monochrome = true
)
public class runner {

}
