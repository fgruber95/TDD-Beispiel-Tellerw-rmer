package mainRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src//test//resources//features"},
        glue = {"glue"},
        plugin = {"pretty", "json:target/tellerwaermer-report.json"},
        strict = true
)

public class Runner {
    public Runner(){

    }
}
