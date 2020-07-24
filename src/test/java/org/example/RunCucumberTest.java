package org.example;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "@demoAmazon",
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json"},
        features = {"classpath:features/"},
        glue = {"org.example.steps"}
)
public class RunCucumberTest {
}
