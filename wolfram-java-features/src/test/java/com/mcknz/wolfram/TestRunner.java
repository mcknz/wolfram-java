package com.mcknz.wolfram;

import com.mcknz.wolfram.web.Driver;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
  plugin = { "pretty", "junit:target/cucumber-reports/cucumber.xml" },
  features = {"src/test/resources"}
)
public class TestRunner {

    @AfterClass
    public static void teardown() {
        Driver.quit();
    }

}