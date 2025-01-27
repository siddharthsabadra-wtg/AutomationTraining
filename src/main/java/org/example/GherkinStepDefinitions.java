package org.example;

import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class GherkinStepDefinitions {
    private static final String URL = "https://cucumber.io/docs/gherkin/";
    private static final String ENTER = "Enter";
    private static final String REFERENCE = "Reference";
    private static Page page;
    private static Playwright playwright;
    private static Browser browser;


    @Before
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
    }

    @Given("I navigate to Gherkin website")
    public void iNavigateToGherkinWebsite() {
        page.navigate(URL);
    }

    @When("I click on search")
    public void iClickOnSearchButton() {
        page.locator("//span[@class='DocSearch-Button-Placeholder']").click();
    }

    @And("I search for {string}")
    public void iSearchFor(String searchText) {
        page.locator("//input").fill(searchText);
        page.waitForTimeout(2000);
        page.locator("//input").press(ENTER);
    }

    @Then("I verify search results")
    public void iVerifySearchResults() {
        assertThat(page.locator("//h1")).isVisible();
        assertThat(page.locator("//h1")).containsText(REFERENCE);
    }

    @After
    public void tearDown() {
        page.close();
        browser.close();
        playwright.close();
    }
}
