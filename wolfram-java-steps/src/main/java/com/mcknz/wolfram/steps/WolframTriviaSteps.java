package com.mcknz.wolfram.steps;

import com.mcknz.wolfram.pages.WolframPage;
import cucumber.api.java8.En;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class WolframTriviaSteps implements En {

    public WolframTriviaSteps() {

        WolframPage page = new WolframPage();

        Given("^I navigate to Wolfram Alpha$", page::go);

        When("^I ask \"([^\"]*)\"$", page::ask);

        Then("^Wolfram Alpha answers \"([^\"]*)\"$", (String answer) -> {
            assertThat(page.getAnswer(), containsString(answer));
        });
    }
}