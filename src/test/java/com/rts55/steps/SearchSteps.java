package com.rts55.steps;

import com.rts55.domain.CurrentUser;
import com.rts55.domain.UserBuilder;
import com.rts55.pages.Pages;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import static com.rts55.domain.ClientType.MAIN;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@Slf4j
public class SearchSteps {

    @Autowired
    private Pages pages;

    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private UserBuilder userBuilder;

    private String subject;

    @Given("^I am a valid user$")
    public void iAmAValidUser() throws Throwable {
        currentUser.set(userBuilder.clientType(MAIN).build());
        log.info("Current user {}", currentUser.get());
    }

    @Given("^I require information about \"([^\"]*)\"$")
    public void iRequireInformationAbout(String subject) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        this.subject = subject;
    }

    @When("^I search for the given subject$")
    public void iSearchForTheGivenSubject() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        pages.wikipediaHomePage().enterText(subject);
        pages.wikipediaHomePage().search();
    }

    @Then("^I will be shown some real interesting stuff$")
    public void iWillBeShownSomeRealInterestingStuff() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertThat(pages.searchResultPage().getHeading(), is(subject));
    }
}
